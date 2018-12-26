@echo 当前目录 %CD%
#配置工程环境变量
set JAVA_HOME=%CD%\..\jdk\jdk1.8.0_45
set PATH=%JAVA_HOME%\bin
set CLASSPATH=youyajfxupdate-1.0-SNAPSHOT.jar;
java com.magicalcoder.youyajfxupdate.UpdateApp
pause