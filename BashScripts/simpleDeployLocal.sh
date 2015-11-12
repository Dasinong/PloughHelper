#!/bin/sh

# Setup color constants
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

# Check if script is run from directory root
if [[ ! -f "./pom.xml" ]]; then
	echo "${RED}You must run this script from the root of code base${NC}"
	exit
fi

# Parse version number first
VERSION=`xml2 < pom.xml | grep "/project/version" | sed -e 's#.*=\(\)#\1#'`

# Confirm version number
while true; do
	read -p "Do you want to deploy version ${VERSION}? [y/n]" yn
	case $yn in
		[Yy]* ) break;;
		[Nn]* ) exit;;
		* ) echo "Please answer yes or no.";;
	esac
done

# Verify database configuration
printf "Validate database conf\t"
DB_CONFIG="src/main/webapp/WEB-INF/spring/properties/database.properties"
DB_URL=`head -n 2 $DB_CONFIG | cut -d '=' -f 2`
if [[ ! $DB_URL =~ "120.26.208.198:3306" ]]; then
	echo "${RED}[Fail]${NC}"
	echo "database url should be 120.26.208.198:3306"
	exit
fi
echo "${GREEN}[Success]${NC}"

# Verify if IKAnalyzer2012FF_u1.jar exits
printf "Validate IKAnalyzer2012EF_u1.jar\t"
IKANALYZER="target/ploughHelper-$VERSION/WEB-INF/lib/IKAnalyzer2012FF_u1.jar"
if [[ ! -f $IKANALYZER ]]; then
	if [ -f src/main/resources/IKAnalyzer2012FF_u1.jar ]; then 
		cp src/main/resources/IKAnalyzer2012FF_u1.jar $IKANALYZER 
	else
		echo "${RED}[Fail]${NC}"
		echo "IKAnalyzer2012FF_u1.jar is not presenet"
		exit
	fi
fi
echo "${GREEN}[Success]${NC}"

# Maven install
printf "mvn package\t"
mvn package | grep "BUILD SUCCESS" &> /dev/null
if [ $? -ne 0 ]; then
	echo "${RED}[FAIL]${NC}"
	exit
fi
echo "${GREEN}[SUCCESS]${NC}"

# Move war to server
WARFILE="ploughHelper-${VERSION}.war"
printf "scp ${WARFILE}\t"
scp target/$WARFILE root@120.26.208.198:~ &> /dev/null
if [ $? -ne 0 ]; then
	echo "${RED}[FAIL]${NC}"
	exit
fi
echo "${GREEN}[SUCCESS]${NC}"

# copy the uploaded war file to tomcat app base
UPLOADED_WAR_FILE="~/ploughHelper-${VERSION}.war"
EXISTING_WAR_FILE="/usr/local/tomcat7/webapps/ploughHelper.war"
TIMESTAMP=`date +'%Y-%m-%d-%H-%M-%S'`
BACKUP_FILE="~/ploughHelperBackup/ploughHelper-${TIMESTAMP}.war" 
ssh root@120.26.208.198 -t "sudo cp $EXISTING_WAR_FILE $BACKUP_FILE; sudo cp $UPLOADED_WAR_FILE $EXISTING_WAR_FILE"
echo "Remote update ${GREEN}[SUCCESS]${NC}"
