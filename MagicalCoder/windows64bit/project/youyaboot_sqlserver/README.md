当前版本 - v1.0.0

youyaboot_sqlserver是youyaboot系列框架，默认数据库引擎采用sqlserver,如需要其他数据库，如需要其他数据库，请至www.magicalcoder.com下载对应引擎
配置模板时 请设置 模板版本=youyaboot_sqlserver

1 如何启动
    管理后台：youyaboot-admin
        
        安装环境
            框架需要redis服务，请自行下载或者双击project/redis/redis-启动.bat
        配置环境
           youyaboot\youyaboot-admin\src\main\resources\application-publish.yml
           请根据实际情况配置 sqlserver redis
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
3 常见问题：
    1 验证码无法使用 null报错 解决方法：请更换最新jdk1.8版本 建议多尝试几个版本的 即可解决
