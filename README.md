# tpo-ai-2c2015

#Agrego el artefacto a maven si no lo encuentra
cd ./lib && mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar
