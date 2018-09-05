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
RUNHOME=`cd $DIRNAME/; pwd`
MAINHOME=$(cd `dirname $0`/..; pwd)
echo @MAINHOME@ $MAINHOME

echo "Starting mysql"
service mysql start
sleep 10

echo "grep initDB status"
ps -fe | grep initDB.sh
SCRIPT="$MAINHOME/resources/bin/initDB.sh"
chmod 755 $SCRIPT
$SCRIPT root root 3306 127.0.0.1

echo @JAVA_HOME@ $JAVA_HOME
JAVA="$JAVA_HOME/bin/java"
echo @JAVA@ $JAVA
cd $MAINHOME
JAVA_OPTS="-Xms50m -Xmx128m"
#port=9500
#JAVA_OPTS="$JAVA_OPTS -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=$port,server=y,suspend=n"
echo @JAVA_OPTS@ $JAVA_OPTS

class_path="$MAINHOME/:$MAINHOME/registry-center-server.jar"
echo @class_path@ $class_path

"$JAVA" $JAVA_OPTS -classpath "$class_path" -jar "$MAINHOME/registry-center-server.jar"
