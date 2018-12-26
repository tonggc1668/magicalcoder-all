#echo 当前目录 %CD%  mac启动方式-cd到soft根目录然后sh mac_update.sh 否则会报找不到主类
#配置工程环境变量 自行更换linux jdk版本路径
#export JAVA_HOME=./../jdk/jdk1.8.0_45
#export PATH=$JAVA_HOME/bin
export CLASSPATH=.:youyajfxupdate-1.0-SNAPSHOT.jar:
java com.magicalcoder.youyajfxupdate.UpdateApp
