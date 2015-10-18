**Sample:  Hibernate/Spring/SpringMVC/REST**

##Run sample

**Use Maven:**
  To run the application use Maven ```  mvn clean spring-boot:run ```

**Use Jar file:**
  Or you can build the JAR file with ```mvn clean package ``` and run the JAR by typing: ``` java -jar target/gs-serving-web-content-0.1.0.jar ```
The procedure above will create a runnable JAR. You can also opt to build a classic WAR file instead.

**Logging:**
Logging output is displayed. The service should be up and running within a few seconds.

**Test the service:**
Now that the web site is running, visit <http://localhost:8080/echo>, where you see:
``` {  "counter": 0,  "content": "Hello, World!"}```
Provide a name query string parameter with <http://localhost:8080/echo?name=Test>. Notice how the message changes from
```{  "counter": 6,  "content": "Hello, Test!"}```

## API document
To see API document open <http://localhost:8080/swagger-ui.html>

Application is connected to in memory database with mock data (for more details see [AppConfig.java](https://github.com/ivanmakhnyk/restaurantvote/blob/master/src/main/java/org/imakhnyk/interview/menuvoting/AppConfig.java) file):
- Accouts to login (login/password): user/userp, admin/adminp, user1/user1p, user2/user2p, admin1/admin1p
- Three restaurants mocked with few menu items for current date.

To change database [application.properties](https://github.com/ivanmakhnyk/restaurantvote/blob/master/src/main/resources/application.properties) config file should be updated (add SQL driver maven dependency)

