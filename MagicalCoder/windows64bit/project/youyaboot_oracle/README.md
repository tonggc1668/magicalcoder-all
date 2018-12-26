当前版本 - v1.0.0

youyaboot_oracle是youyaboot系列框架，默认数据库引擎采用oracle,如需要其他数据库，请至www.magicalcoder.com下载对应引擎
配置模板时 请设置 模板版本=youyaboot_oracle

1 如何启动
    管理后台：youyaboot-admin
        
        安装环境
            框架需要redis服务，请自行下载或者双击project/redis/redis-启动.bat
        配置环境
           youyaboot\youyaboot-admin\src\main\resources\application-publish.yml
           请根据实际情况配置 oracle redis
        导入数据库
            youyaboot\youyaboot.sql 导入上面配置的数据库
        运行
            youyaboot\youyaboot-admin\src\main\java\com\magicalcoder\youyaboot\admin\YouyaBootAdminApplication.java
            main函数即可启动
        
        也可以打成jar启动也行
        方式1 java -jar youyaboot-admin.jar
        方式2 nohup>nohupGps java -jar youyaboot-admin.jar --spring.profiles.active=deploy 2>&1 &
        
        启动成功后 浏览器访问
        http://localhost:18080/youyaboot-admin
            默认 admin/admin登陆
        
2 声明
    youyaboot 为开源项目，会持续不断的更新，在使用过程中遇到问题 欢迎联系作者 QQ 799374340

如果你熟练掌握oracle，你一定理解自增主键
oracle如何设置自增主键：以GOODS表为例
第一步：创建序列 每个表要单独创建一个
    create sequence SEQ_GOODS
    minvalue 1
    maxvalue 999999999
    start with 1
    increment by 1
    nocache;
说明：SEQ_GOODS是自定义的自增序列名称

第二步: 创建获取序列的函数 这个全局只要创建一次即可
    create or replace function GET_SEQ_V_A_L_ (seq_name in varchar2) return number
    is
      l_res number ;
      begin
        execute immediate 'select '|| seq_name|| '.nextval from dual' into l_res ;
        return l_res ;
      end ;
说明：GET_SEQ_V_A_L_ 获取序列的值 之所以取这么奇怪的名字 主要是最大化避免与您当前库里的函数冲突

  
-- 其他方法：不推荐使用创建触发器 性能稍有损耗 这里列举处理供参考
create or replace trigger TRI_GOODS_ID
  before insert on GOODS
  for each row
declare
  nextid number;
begin
  IF :new.ID IS NULL or :new.ID=0 THEN 
    select SEQ_GOODS.nextval
    into nextid
    from sys.dual;
    :new.ID:=nextid;
  end if;
end TRI_GOODS_ID;
说明：  TRI_GOODS_ID 触发器名称
        ID是GOODS列名
        SEQ_GOODS 是第一步的自增序列


3 常见问题：
    1 验证码无法使用 null报错 解决方法：请更换最新jdk1.8版本 建议多尝试几个版本的 即可解决
