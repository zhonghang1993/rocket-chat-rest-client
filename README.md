# rocket-chat-rest-client
Lightweight Java client for [Rocket.Chat](https://rocket.chat/)'s [REST API](https://rocket.chat/docs/developer-guides/rest-api) using [Unirest](http://unirest.io/java.html) and [Jackson](https://github.com/FasterXML/jackson-databind).

## Notes
* Rocket.Chat v0.48 rewrote the REST API, see the [pull request #5140](https://github.com/RocketChat/Rocket.Chat/pull/5140) for details
* This api is still a work in progress, feel free to submit pull requests to add functionality
* Server url doesn't require `api/` anymore, but it can still be provided

## Example Usage
**TODO**: Fix the Java example when this gets flushed out

### Java
```java
RocketChatClient rc = new RocketChatClient("https://demo.rocket.chat/api/", "<user>", "<password>");

// get meta info
System.out.println("Api version is "+rc.getApiVersion());
System.out.println("Rocket.Chat version is "+rc.getRocketChatVersion());
		
// use typed API to retrieve rooms		
Set<Room> rooms = rc.getPublicRooms();
for (Room room : rooms) {
	System.out.println(String.format("name: %s, id: %s", room.name, room._id));
}

// send a message to a room. Room ID is resolved automatically		
rc.send("test", "Hello from REST client" + new Date());

// no comment ;-)
rc.logout();
```

### Maven
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		 <url>https://jitpack.io</url>
	</repository>
</repositories>
	
<dependencies>
	<dependency>
		<groupId>com.github.baloise</groupId>
		<artifactId>rocket-chat-rest-client</artifactId>
		<version>0.1.0-SNAPSHOT</version>
	</dependency>
</dependencies>
```
