# rocket-chat-rest-client
Leight weight java client for [Rocket.Chat](https://rocket.chat/)'s [REST API](https://github.com/RocketChat/Rocket.Chat/wiki/REST-APIs) using [Unirest](http://unirest.io/java.html) and [Jackson](https://github.com/FasterXML/jackson-databind).

### Java
```java
RocketChatClient rc = new RocketChatClient("https://demo.rocket.chat/", "<user>", "<password>");

// get meta info
System.out.println("Api version is "+rc.getApiVersion());
System.out.println("Rocket.Chat version is "+rc.getRocketChatVersion());
		
//use typed API to retrieve rooms		
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
		<version>master-SNAPSHOT</version>
	</dependency>
</dependencies>
```
