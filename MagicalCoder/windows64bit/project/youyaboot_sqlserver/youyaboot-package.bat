
set JAVA_HOME=%CD%\..\..\jdk\jdk1.8.0_45

set M2_HOME=%CD%\..\apache-maven-3.3.9

set PATH=%JAVA_HOME%\bin;%M2_HOME%\bin;


mvn clean package

pause;
