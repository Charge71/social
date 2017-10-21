# Java Command Line Social Network

A Java/JPA based command line utility to post messages and follow other users.

The persistence is obtained using an in memory H2 database, but can be easily moved to any database supported by Hibernate.

### Requirements
* Java https://www.java.com
* Maven https://maven.apache.org

### Build

From the project root run:\s\s
`mvn package`

### Run

Go to target folder and run from java:\s\s
`cd target`\s\s
`java -jar social.jar`

To exit the program type `=`