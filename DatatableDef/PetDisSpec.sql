CREATE TABLE  petDisSpec (
  petDisSpecId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`petDisSpecId`) USING BTREE,
  petDisSpecName VARCHAR(30) NOT NULL,
  type VARCHAR(50),
  alias VARCHAR(100),
  cropName VARCHAR(10),
  UNIQUE KEY `UNI_PDSNAME_CROPNAME` (`petDisSpecName`,`cropName`),
  severity INT(10),
  commonImpactonYield VARCHAR(100),
  maxImpactonYield VARCHAR(100),
  preventionorRemedy VARCHAR(10),
  indvidualorGroup VARCHAR(10),
  impactedArea VARCHAR(50),
  color VARCHAR(20),
  shape VARCHAR(20),
  description TEXT,
  sympthon TEXT,
  forms TEXT,
  habits TEXT,
  rules TEXT,
  pictureIds VARCHAR(20),
  region VARCHAR(20)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;