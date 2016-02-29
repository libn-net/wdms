@echo off
rem /**
rem  * 
rem  *
rem  * Author: libonan@libn.net
rem  */
echo.
echo [信息] 打包工程，生成war包文件。
echo.
pause
echo.

cd %~dp0
cd..

call mvn clean package -Dmaven.test.skip=true

cd bin
pause