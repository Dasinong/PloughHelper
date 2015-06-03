CREATE TABLE  variety (
  varietyId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`varietyId`) USING BTREE,
  varietyName VARCHAR(30) NOT NULL,
  UNIQUE KEY `UNI_VARIETYNAME` (`varietyName`),
  cropId int(10) unsigned not null,
  KEY FK_TRANSACTION_CROP_ID (cropId),
  CONSTRAINT FK_TRANSACTION_CROP_ID FOREIGN KEY (cropId)
  REFERENCES crop (cropId) ON DELETE CASCADE ON UPDATE CASCADE,
  
  type VARCHAR(10),
  genoType VARCHAR(10),
  maturityType VARCHAR(20),
  suitableArea VARCHAR(40),
  totalAccumulatedTempNeeded INT(10),
  fullCycleDuration INT(10),
  typicalYield INT(10),
  owner VARCHAR(20),
  nationalStandard VARCHAR(20),
  yearofReg INT(6)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;