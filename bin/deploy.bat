@echo off
rem /**
rem  * 
rem  *
rem  * Author: libonan@libn.net
rem  */
echo.
echo [��Ϣ] ����Eclipse�����ļ���
echo.
pause
echo.

cd %~dp0
cd..

call mvn deploy

cd bin
pause