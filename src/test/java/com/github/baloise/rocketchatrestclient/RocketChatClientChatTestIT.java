package com.github.baloise.rocketchatrestclient;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Message;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.util.TestConnectionInfo;

public class RocketChatClientChatTestIT {
    private RocketChatClient rc;

    @Before
    public void setUp() throws Exception {
        this.rc = new RocketChatClient(TestConnectionInfo.ServerUrl, TestConnectionInfo.User, TestConnectionInfo.Password);

        assertNotNull("An error occured setting up Rocket.Chat Client, it is null.", this.rc);
    }

    @Test
    public void testPostMessage() throws IOException {
        Room room = new Room("GENERAL", false);
        Message msg = this.rc.getChatApi().postMessage(room, new Message("Testing this out."));

        assertNotNull("The returned message is null?", msg);
        assertEquals("The message text is not the same.", "Testing this out.", msg.getText());
    }
}
