## Part 1 : clean and package using maven
FROM maven:3.8.4-openjdk-17
## download dependencies
ADD pom.xml /
RUN mvn verify clean
## build after dependencies are down so it wont redownload unless the POM changes
ADD . /
RUN mvn package

# Part 2: use the JAR file used in the first part and copy it across ready to RUN
FROM openjdk:17-jdk
EXPOSE 8080:8080
EXPOSE 3308:3308
EXPOSE 8443:8443
WORKDIR /root/
RUN keytool -keystore keystore.jks -alias polkauctionStore -keypass foobar -storepass foobar -genkeypair -keyalg RSA -keysize 4096 -validity 36500 -dname 'CN=localhost, OU=ktor, O=ktor, L=Unspecified, ST=Unspecified, C=US'
## COPY packaged JAR file and rename as app.jar
## → this relies on your MAVEN package command building a jar
## that matches *-jar-with-dependencies.jar with a single match
COPY --from=0 /target/*-jar-with-dependencies.jar app.jar
COPY --from=0 /resources/* resources/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]