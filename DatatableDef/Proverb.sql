CREATE TABLE proverb (
  proverbId INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (proverbId) USING BTREE,
  necessaryCondition VARCHAR(100),
  content varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;