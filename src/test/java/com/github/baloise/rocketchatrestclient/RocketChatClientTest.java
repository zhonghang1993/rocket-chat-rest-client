package com.github.baloise.rocketchatrestclient;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;

import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

@Ignore
public class RocketChatClientTest {
    private RocketChatClient rc;
    
    @Before
    public void setUp() {
        String serverUrl = "http://192.168.10.153:3000/api/";
        String user = "admin";
        String password = "bigbigsun";
        rc = new RocketChatClient(serverUrl, user, password);
    }

	@Test
	public void testRocketCatExists() throws Exception {
		ServerInfo info = rc.getServerInformation();
		assertFalse("The Rocket.Chat Version is empty, when it shouldn't be.", info.getVersion().isEmpty());
		
		User rocketCat = rc.getUsersApi().getInfo("66qBpKdiTRC5mE59r");
		assertTrue("The Rocket.Cat user's id doesn't match what it should be.", "66qBpKdiTRC5mE59r".equals(rocketCat.getId()));
	}
	
	@Test
	public void testChannelGetting() throws Exception {
	    assertEquals("The default count changed?", 50, rc.getChannelsApi().list().length);
	    assertEquals("The channel count wasn't one?", 1, rc.getChannelsApi().list(new RocketChatQueryParams().setCount(1)).length);
	}
}
