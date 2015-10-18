**Sample:  Hibernate/Spring/SpringMVC/REST**

##Run sample

**Maven:**

  To run the application use Maven command line 
  ```
  mvn clean spring-boot:run 
  ```
**Use IDE:**

  Run main method in [org.imakhnyk.interview.menuvoting.Application.java](https://github.com/ivanmakhnyk/restaurantvote/blob/master/src/main/java/org/imakhnyk/interview/menuvoting/Application.java)

**Run as Jar file:**

  Or you can build the JAR file with 
  ```
  mvn clean package 
  ``` 
  and run the JAR by typing: 
  ``` 
  java -jar target/menuvoting-0.1.0.jar
  ```

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

## Curl samples

Test echo ```curl -X GET "http://localhost:8080/echo?name=Test"``` result ```{"counter":2,"content":"Hello, Test!"}```

**Voting scenario**

User 'user' votes for restaurant with ID '7'
```
curl --user user:userp -X POST --header "Content-Type: application/json" http://localhost:8080/vote/7"
```
Users 'user1' and 'user2' vote for restaurant with ID '8'
```
curl --user user1:user1p -X POST --header "Content-Type: application/json" http://localhost:8080/vote/8"
curl --user user2:user2p -X POST --header "Content-Type: application/json" http://localhost:8080/vote/8"
```
Admin request voting results
```
curl --user admin:adminp -X GET  "http://localhost:8080/vote"
```

Console example (curl request/response)
```
curl --user user:userp -X POST --header "Content-Type: application/json" http://localhost:8080/vote/7"
{"status":"ok","message":"vote counted"}

curl --user user1:user1p -X POST --header "Content-Type: application/json" http://localhost:8080/vote/8"
{"status":"ok","message":"vote counted"}

curl --user user2:user2p -X POST --header "Content-Type: application/json" http://localhost:8080/vote/8"
{"status":"ok","message":"vote counted"}

curl --user admin:adminp -X GET  "http://localhost:8080/vote"
[
	{"count":0,"restaurantId":6,"restaurantName":"Restaurant Perkins"},
	{"count":0,"restaurantId":7,"restaurantName":"Restaurant Cracker Barrel"},
	{"count":0,"restaurantId":8,"restaurantName":"Restaurant le Jules Verne - Tour Eiffel Paris"}
]
```

