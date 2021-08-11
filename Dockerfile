## Part 1 : clean and package using maven
FROM maven:3.6.3-openjdk-14
## download dependencies
ADD pom.xml /
RUN mvn verify clean
## build after dependencies are down so it wont redownload unless the POM changes
ADD . /
RUN mvn package

# Part 2: use the JAR file used in the first part and copy it across ready to RUN
FROM openjdk:14-jdk
EXPOSE 8080:8080
WORKDIR /root/
## COPY packaged JAR file and rename as app.jar
## → this relies on your MAVEN package command building a jar
## that matches *-jar-with-dependencies.jar with a single match
COPY --from=0 /target/*-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]