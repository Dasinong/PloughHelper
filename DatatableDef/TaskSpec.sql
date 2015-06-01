CREATE TABLE taskSpec (
  taskSpecId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (taskSpecId) USING BTREE,
  taskSpecName VARCHAR(30) NOT NULL,
  UNIQUE KEY UNI_SUBSTAGENAME (taskSpecName),
  subStageId int(10) unsigned not null,
  KEY FK_TS_SUBSTAGE_ID (subStageId),
  CONSTRAINT FK_TS_SUBSTAGE_ID FOREIGN KEY (subStageId)
  REFERENCES subStage (subStageId) ON DELETE CASCADE ON UPDATE CASCADE,
  type VARCHAR(10)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;