package tmp;

import java.util.Date;
import java.util.Set;

import org.json.JSONObject;

import com.github.baloise.rocketchatrestclient.RocketChatClient;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class RestClientMain {
	
	public static void main(String[] args) throws Exception {
		final String url = "http://localhost:3000/api/";
		RocketChatClient rc = new RocketChatClient(url, "rocky", "chocolat");
		System.out.println(rc.getApiVersion());
		System.out.println(rc.getRocketChatVersion());
		
		
		Set<Room> rooms = rc.getPublicRooms();
		
		if(true) return;
		System.out.println(Unirest.get(url+"version")
				.asJson().getBody());
		
		
		
		HttpResponse<JsonNode> asJson = Unirest.post(url +"login")
		.field("password", "chocolat")
		.field("user", "rocky")
		  .asJson();
		System.out.println(asJson.getBody());
		JSONObject data = asJson.getBody().getObject().getJSONObject("data");
		String token = data.getString("authToken");
		String user = data.getString("userId");
		System.out.println(token);
		System.out.println(user);
		
		
		asJson = Unirest.get(url+"publicRooms")
				.header("X-Auth-Token", token)
				.header("X-User-Id", user)
				.asJson();
		System.out.println(asJson.getBody());
		
		
//		curl -H "X-Auth-Token: S5u0ZNNbc5W6Qqug90JdWRT2sxEWgz9mR5mu2dWOQ5v" 
//	     -H "Content-Type: application/json" 
//	     -X POST 
//	     -H "X-User-Id: aobEdbYhXfu5hkeqG" 
//	        http://localhost:3000/api/rooms/x4pRahjs5oYcTYu7i/send 
//	     -d "{ \"msg\" : \"OK\" }"
//
//	{
//	  "status": "success"
//	}
	
		String room = "GENERAL";
		String message = "Hello Rest" + new Date();
//		HttpResponse<String> asString = Unirest.post(url+"rooms/"+room+"/join" )
//				.header("X-Auth-Token", token)
//				.header("X-User-Id", user)
//				.header("Content-Type", "application/json")
//				.body("{}")
//				.asString();
//		System.out.println(asString.getBody());
		
		HttpResponse<String> asString = Unirest.post(url+"rooms/"+room+"/send" )
				.header("X-Auth-Token", token)
				.header("X-User-Id", user)
				.header("Content-Type", "application/json")
				.body(String.format("{ \"msg\" : \"%s\" }", message ))
				.asString();
		System.out.println(asString.getBody());
		
		
		asJson = Unirest.post(url+"logout")
				.header("X-Auth-Token", token)
				.header("X-User-Id", user)
				.asJson();
		System.out.println(asJson.getBody());
		
		
	}

}
