package com.github.baloise.rocketchatrestclient;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

public class RocketChatClientTestIT {

	@Test
	public void testRocketCatExists() throws Exception {
		String serverUrl = "http://localhost/api/";
		String user = "admin";
		String password = "supersecret";
		RocketChatClient rc = new RocketChatClient(serverUrl, user, password);

		ServerInfo info = rc.getServerInformation();
		assertTrue("The Rocket.Chat Version is empty, when it shouldn't be.", !info.getVersion().isEmpty());

		User rocketCat = rc.getUser("rocket.cat");
		assertTrue("The Rocket.Cat user's id doesn't match what it should be.", "rocket.cat".equals(rocketCat.getId()));

	}
}
