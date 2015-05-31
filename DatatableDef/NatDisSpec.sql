CREATE TABLE  natDisSpec (
  natDisSpecId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`natDisSpecId`) USING BTREE,
  natDisSpecName VARCHAR(30) NOT NULL,
  UNIQUE KEY `UNI_NDSNAME` (`natDisSpecName`),
  solution VARCHAR(200)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;