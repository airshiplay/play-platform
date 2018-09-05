--
-- Copyright (C) 2017 CMCC, Inc. and others. All rights reserved.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

use registry;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `alarms_additionalinformation`
-- ----------------------------
DROP TABLE IF EXISTS `alarms_additionalinformation`;
CREATE TABLE `alarms_additionalinformation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `headerId` varchar(100) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `sourceId` varchar(500) DEFAULT NULL,
  `startEpochMicrosec` varchar(500) DEFAULT NULL,
  `lastEpochMicroSec` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `alarms_additionalinformation` ADD INDEX `alarms_additionalinformation_index` (`headerId`, `sourceId`);


-- ----------------------------
-- Table structure for `alarms_commoneventheader`
-- ----------------------------
DROP TABLE IF EXISTS `alarms_commoneventheader`;
CREATE TABLE `alarms_commoneventheader` (
  `id` varchar(100) NOT NULL,
  `version` varchar(500) DEFAULT NULL,
  `eventName` varchar(500) DEFAULT NULL,
  `domain` varchar(500) DEFAULT NULL,
  `eventId` varchar(500) DEFAULT NULL,
  `eventType` varchar(500) DEFAULT NULL,
  `nfcNamingCode` varchar(500) DEFAULT NULL,
  `nfNamingCode` varchar(500) DEFAULT NULL,
  `sourceId` varchar(500) DEFAULT NULL,
  `sourceName` varchar(500) DEFAULT NULL,
  `reportingEntityId` varchar(500) DEFAULT NULL,
  `reportingEntityName` varchar(500) DEFAULT NULL,
  `priority` varchar(50) DEFAULT NULL,
  `startEpochMicrosec` varchar(500) DEFAULT NULL,
  `lastEpochMicroSec` varchar(500) DEFAULT NULL,
  `startEpochMicrosecCleared` varchar(255) DEFAULT NULL,
  `lastEpochMicroSecCleared` varchar(255) DEFAULT NULL,
  `sequence` varchar(500) DEFAULT NULL,
  `faultFieldsVersion` varchar(500) DEFAULT NULL,
  `eventServrity` varchar(500) DEFAULT NULL,
  `eventSourceType` varchar(500) DEFAULT NULL,
  `eventCategory` varchar(500) DEFAULT NULL,
  `alarmCondition` varchar(500) DEFAULT NULL,
  `specificProblem` varchar(500) DEFAULT NULL,
  `vfStatus` varchar(500) DEFAULT NULL,
  `alarmInterfaceA` varchar(500) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `alarms_commoneventheader` ADD INDEX `alarms_commoneventheader_index` (`sourceId`);


-- ----------------------------
-- Table structure for `performance_additionalinformation`
-- ----------------------------
DROP TABLE IF EXISTS `performance_additionalinformation`;
CREATE TABLE `performance_additionalinformation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `headerId` varchar(100) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `value` varchar(500) DEFAULT NULL,
  `sourceId` varchar(500) DEFAULT NULL,
  `startEpochMicrosec` varchar(500) DEFAULT NULL,
  `lastEpochMicroSec` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `performance_additionalinformation` ADD INDEX `performance_additionalinformation_index` (`headerId`, `sourceId`);


-- ----------------------------
-- Table structure for `performance_commoneventheader`
-- ----------------------------
DROP TABLE IF EXISTS `performance_commoneventheader`;
CREATE TABLE `performance_commoneventheader` (
  `id` varchar(100) NOT NULL,
  `version` varchar(500) DEFAULT NULL,
  `eventName` varchar(500) DEFAULT NULL,
  `domain` varchar(500) DEFAULT NULL,
  `eventId` varchar(500) DEFAULT NULL,
  `eventType` varchar(500) DEFAULT NULL,
  `nfcNamingCode` varchar(500) DEFAULT NULL,
  `nfNamingCode` varchar(500) DEFAULT NULL,
  `sourceId` varchar(500) DEFAULT NULL,
  `sourceName` varchar(500) DEFAULT NULL,
  `reportingEntityId` varchar(500) DEFAULT NULL,
  `reportingEntityName` varchar(500) DEFAULT NULL,
  `priority` varchar(50) DEFAULT NULL,
  `startEpochMicrosec` varchar(500) DEFAULT NULL,
  `lastEpochMicroSec` varchar(500) DEFAULT NULL,
  `sequence` varchar(500) DEFAULT NULL,
  `measurementsForVfScalingVersion` varchar(500) DEFAULT NULL,
  `measurementInterval` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `performance_commoneventheader` ADD INDEX `performance_commoneventheader_index` (`sourceId`);

