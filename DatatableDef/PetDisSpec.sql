CREATE TABLE  petDisSpec (
  petDisSpecId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`petDisSpecId`) USING BTREE,
  petDisSpecName VARCHAR(30) NOT NULL,
  UNIQUE KEY `UNI_PDSNAME` (`petDisSpecName`),
  type VARCHAR(10) NOT NULL,
  alias VARCHAR(10) NOT NULL,
  severity INT(10) NOT NULL,
  commonImpactonYield INT(10) NOT NULL,
  maxImpactonYield INT(10) NOT NULL,
  preventionorRemedy VARCHAR(10) NOT NULL,
  indvidualorGroup VARCHAR(10) NOT NULL,
  impactedArea VARCHAR(10) NOT NULL,
  color VARCHAR(10) NOT NULL,
  shape VARCHAR(10) NOT NULL,
  description VARCHAR(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;