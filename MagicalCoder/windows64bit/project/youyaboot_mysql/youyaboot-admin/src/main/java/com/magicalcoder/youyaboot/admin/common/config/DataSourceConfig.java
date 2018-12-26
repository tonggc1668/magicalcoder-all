package com.magicalcoder.youyaboot.admin.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.magicalcoder.youyaboot.core.utils.StringUtil;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * 数据源配置
 * @author www.magicalcoder.com
 * @date 2018-7-9
 */
@Configuration
@MapperScan(basePackages =
        {"com.magicalcoder.youyaboot.admin.**.mapper"
        ,"com.magicalcoder.youyaboot.admin.rmp.**.mapper" // 如果您觉得日志有warn mapper已经存在 比较碍眼 可以注释本行
        ,"com.magicalcoder.youyaboot.service.**.mapper"
        }
            , sqlSessionFactoryRef = "commonSqlSessionFactory" )
public class DataSourceConfig {

    private static final String MAPPER_LOCATION = "classpath*:sqlmap/**/*Mapper.xml";
    private static final String TYPE_ALIASES_PACKAGE =
            "com.magicalcoder.youyaboot.admin.model," +
            "com.magicalcoder.youyaboot.admin.rmp.model," +
            "com.magicalcoder.youyaboot.model";//逗号分隔多个

    @Value("${common.datasource.url}")
    private String url;

    @Value("${common.datasource.username}")
    private String user;

    @Value("${common.datasource.password}")
    private String password;

    @Value("${common.datasource.driverClassName}")
    private String driverClass;

    @Resource
    private MonitorConfig monitorConfig;

    @Bean(name = "commonDataSource")
    @Primary
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setConnectionInitSqls(Arrays.asList("set names utf8mb4"));
        if(StringUtil.isNotBlank(monitorConfig.getDruidFilters())){
            try {
                dataSource.setFilters(monitorConfig.getDruidFilters());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

    @Bean(name = "commonTransactionManager")
    @Primary
    public DataSourceTransactionManager commonTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "commonSqlSessionFactory")
    @Primary
    public SqlSessionFactory commonSqlSessionFactory(@Qualifier("commonDataSource") DataSource commonDataSource)throws Exception {
        //DefaultVFS在获取jar上存在问题，使用springboot只能修改  ,否则setTypeAliasesPackage 在生产环境报错
        VFS.addImplClass(SpringBootVFS.class);
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(commonDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        return sessionFactory.getObject();
    }
}
