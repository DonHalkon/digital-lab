FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/digital-lab-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Xmx512m -Xms128m"
ENTRYPOINT exec java -Dspring.profiles.active=docker $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar