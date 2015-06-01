CREATE TABLE field (
  fieldId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (fieldId) USING BTREE,
  fieldName VARCHAR(30) NOT NULL,
  isActive TINYINT(1) DEFAULT 1,
  UNIQUE KEY UNI_FIELDNAME (fieldName),
  varietyId int(10) unsigned not null,
  KEY FK_TRANSACTION_VARIETY_ID (varietyId),
  CONSTRAINT FK_TRANSACTION_VARIETY_ID FOREIGN KEY (varietyId)
  REFERENCES variety (varietyId) ON DELETE CASCADE ON UPDATE CASCADE,
  userId int(10) unsigned not null,
  KEY FK_FD_USER_ID (userId),
  CONSTRAINT FK_FD_USER_ID FOREIGN KEY (userId)
  REFERENCES user (userId) ON DELETE CASCADE ON UPDATE CASCADE,
  locationId int(10) unsigned not null,
  KEY FK_TRANSACTION_LOCATION_ID (locationId),
  CONSTRAINT FK_TRANSACTION_LOCATION_ID FOREIGN KEY (locationId)
  REFERENCES location (locationId) ON DELETE CASCADE ON UPDATE CASCADE,
  other VARCHAR(100) 
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;