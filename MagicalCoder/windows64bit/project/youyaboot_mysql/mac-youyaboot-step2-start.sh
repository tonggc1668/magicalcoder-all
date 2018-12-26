echo 'mac  please make sure you have installed jdk1.8'

echo 'mac  please make sure you have installed redis'
#export JAVA_HOME=../../jdk/jdk1.8.0_45
#export PATH=%JAVA_HOME%/bin;%M2_HOME%/bin;
cd youyaboot-admin/target

java -jar youyaboot-admin.jar --spring.profiles.active=publish