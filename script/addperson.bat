@ECHO OFF
REM 
REM Run java will well defined class path 
REM 

SET JAVA_EXE=java
SET WORK_PATH=./
SET LIB_PATH=%WORK_PATH%/protobuf-java-2.4.1.jar
SET CLASS_PATH=%WORK_PATH%/bin

echo %CLASS_PATH%
%JAVA_EXE% -version
%JAVA_EXE% -cp %CLASS_PATH% io.AddPerson %1 
