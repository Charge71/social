# Java Command Line Social Network

A Java/JPA based command line utility to post messages and follow other users.

The persistence is obtained using an in memory H2 database, but can be easily moved to any database supported by Hibernate.
Reference: https://github.com/xpeppers/social_networking_kata

### Requirements
* Java https://www.java.com
* Maven https://maven.apache.org

### Build

From the project root run:
```
mvn package
```

### Run

Go to target folder and run from java:
```
cd target
java -jar social.jar
```

To exit the program type `=`