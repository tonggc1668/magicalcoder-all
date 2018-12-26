echo 'mac please make sure you have installed jdk1.8'
#export JAVA_HOME=../../jdk/jdk1.8.0_45

export M2_HOME=./../apache-maven-3.3.9
chmod -R 755 $M2_HOME/bin
#export PATH=$PATH:%M2_HOME%/bin

sh $M2_HOME/bin/mvn clean package
