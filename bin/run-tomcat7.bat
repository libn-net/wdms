@echo off
rem /**
rem  * 
rem  *
rem  * Author: libonan@libn.net
rem  */
title %cd%
echo.
echo [信息] 使用Tomcat7插件运行工程。
echo.
rem pause
rem echo.

cd %~dp0
cd..

set MAVEN_OPTS=%MAVEN_OPTS% -Xms256m -Xmx512m -XX:PermSize=128m -XX:MaxPermSize=256m
call mvn tomcat7:run

cd bin
pause