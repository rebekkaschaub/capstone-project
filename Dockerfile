FROM openjdk:16

MAINTAINER Rebekka Schaub <re.schaub94@gmail.com>

ADD backend/target/sympathise.jar app.jar

CMD [ "sh", "-c", "java  -jar /app.jar" ]
