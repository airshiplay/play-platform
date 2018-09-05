@REM
@REM Copyright 2016-2017 ZTE Corporation.
@REM
@REM Licensed under the Apache License, Version 2.0 (the "License");
@REM you may not use this file except in compliance with the License.
@REM You may obtain a copy of the License at
@REM
@REM     http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM

@echo off
title stopping registry-center-server

set HOME=%~dp0
set Main_Class="registry-center-server"

echo ================== esr-service info =============================================
echo HOME=$HOME
echo Main_Class=%Main_Class%
echo ===============================================================================

echo ### Stopping registry-center-server
cd /d %HOME%

for /f "delims=" %%i in ('"%JAVA_HOME%\bin\jcmd"') do (
  call find_kill_process "%%i" %Main_Class%
)
exit
