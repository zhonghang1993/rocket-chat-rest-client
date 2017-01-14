# rocket-chat-rest-client
Lightweight Java client for [Rocket.Chat](https://rocket.chat/)'s [REST API](https://rocket.chat/docs/developer-guides/rest-api) using [Unirest](http://unirest.io/java.html) and [Jackson](https://github.com/FasterXML/jackson-databind).

[![Build Status](https://ci.craftyn.com/job/rocket-chat-rest-client/badge/icon)](https://ci.craftyn.com/job/rocket-chat-rest-client/) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/a9564ad6e28743bea43b056ef667f328)](https://www.codacy.com/app/graywolf336/rocket-chat-rest-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=baloise/rocket-chat-rest-client&amp;utm_campaign=Badge_Grade)

## Notes
* Rocket.Chat v0.48 rewrote the REST API, see the [pull request #5140](https://github.com/RocketChat/Rocket.Chat/pull/5140) for details
* This api is still a work in progress, feel free to submit pull requests to add functionality
* Server url doesn't require `api/` anymore, but it can still be provided
* None of the results are cached, every time a method is called it goes out and gets it
* The method calls are sync and blocking
* Maven is configured to startup Rocket.Chat v.0.48-develop via docker therefore docker must be installed.

### Java
```java
RocketChatClient rc = new RocketChatClient("https://demo.rocket.chat/api/", "<user>", "<password>");

// get meta info
System.out.println("Rocket.Chat Server Version is: " + info.getServerInformation().getVersion());
		
// use typed API to retrieve rooms		
Room[] channels = rc.getChannels();
for (Room c : channels) {
	System.out.println(String.format("name: %s, id: %s", c.getName(), c.getId()));
}

//NOTE: Sending a message isn't supported yet, due to inconsistencies that `v1/chat.postMessage` has versus other `v1/` APIs. 
// send a message to a room. Room ID is resolved automatically
rc.send("test", "Hello from REST client" + new Date());

// Call this if you are done or want to refresh the auth token
rc.logout();
```

### Maven
```xml
<repositories>
    <repository>
        <id>repo-snapshots</id>
        <url>https://repo.craftyn.com/repository/snapshots/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.baloise</groupId>
        <artifactId>rocket-chat-rest-client</artifactId>
        <version>0.1.2-SNAPSHOT</version>
    </dependency>
</dependencies>
```

### Compiling

The maven build needs [Docker](https://www.docker.com) for integration testing. Please install it for your platform before running and also make sure your user has permission to use the docker command (Linux, add your user to the docker group).

```
mvn clean install
```

To keep the docker containers running:

```
mvn -Ddocker.keepRunning clean install
```

