#!/bin/bash
#
# Copyright 2016-2017 ZTE Corporation.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

DIRNAME=`dirname $0`
HOME=`cd $DIRNAME/; pwd`
Main_Class="registry-center-server"

echo ================== registry-center-server info =============================================
echo HOME=$HOME
echo Main_Class=$Main_Class
echo ===============================================================================
cd $HOME; pwd

echo @WORK_DIR@ $HOME

function save_service_pid(){
    service_pid=`ps -ef | grep $Main_Class | grep -v grep | awk '{print $2}'`
    echo @service_pid@ $service_pid
}

function kill_service_process(){
    ps -p $service_pid
    if [ $? == 0 ]; then
        kill -9 $service_pid
    fi
}

save_service_pid;
echo @C_CMD@ kill -9 $service_pid
kill_service_process;

echo "Stoping mysql"
service mysql stop
sleep 1
