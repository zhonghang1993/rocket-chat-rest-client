package com.github.baloise.rocketchatrestclient;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import com.github.baloise.rocketchatrestclient.model.User;

public class RocketChatClientTest {

	@Test
	@Ignore
	public void testRocketCatExists() throws Exception {
	    String serverUrl = "https://demo.rocket.chat/api/";
		String user = "";
		String password = "";
		RocketChatClient rc = new RocketChatClient(serverUrl, user, password);
		
		User rocketCat = rc.getUser("rocket.cat");
		assertTrue("The Rocket.Cat user's id doesn't match what it should be.", "rocket.cat".equals(rocketCat.getId()));
	}
}
