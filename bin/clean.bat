@echo off
rem /**
rem  * 
rem  *
rem  * Author: libonan@libn.net
rem  */
echo.
echo [��Ϣ] ��������·����
echo.
pause
echo.

cd %~dp0
cd..

call mvn clean

cd bin
pause