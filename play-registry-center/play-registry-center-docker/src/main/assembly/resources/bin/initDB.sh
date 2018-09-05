#!/bin/bash
#
# Copyright (C) 2017 CMCC, Inc. and others. All rights reserved.
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
user=$1
password=$2
port=$3
host=$4
echo "start create registry-center db"
sql_path=$HOME/../
mysql -u$user -p$password -P$port -h$host <$sql_path/dbscripts/mysql/registry-center-createdb.sql
sql_result=$?
if [ $sql_result != 0 ] ; then
    echo "failed to create registry-center database"
    exit 1
fi
fileFlag=*createobj.sql
location=$sql_path/dbscripts/mysql
fileName=""
for i in `ls $location`
do
    if [[ $i == ${fileFlag} ]];then
        fileName=${i};
        echo "start create table:${fileName}"
        mysql -u$user -p$password -P$port -h$host <$sql_path/dbscripts/mysql/$fileName
        sql_result=$?
        if [ $sql_result != 0 ] ; then
          echo "failed to init registry-center table:${fileName}"
          exit 1
        fi
    fi
done
echo "init registry-center database success!"
exit 0

