FROM adoptopenjdk/openjdk11:alpine-jre

# Refer to Maven build -> finalName
ARG JAR_FILE=target/mint-project-0.0.1-SNAPSHOT.war

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/mint-app.jar
COPY ${JAR_FILE} mint-app.war

# java -war /opt/app/mint-app.war
ENTRYPOINT ["java","-war","mint-app.war"]