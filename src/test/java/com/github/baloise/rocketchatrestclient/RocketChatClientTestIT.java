package com.github.baloise.rocketchatrestclient;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Channel;
import com.github.baloise.rocketchatrestclient.model.Group;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

/**
 * This IT tests basic functionality of the client.
 *
 * @author Ryne Fagin (rynefagin)
 * @since 0.1.1
 * @version 0.0.1
 */
public class RocketChatClientTestIT {
    private static final String TEST_CASE_8 = "test0008";
    private static final String TEST_CASE_7 = "test0007";
    private static final String TEST_CASE_6 = "test0006";
    private static final String TEST_CASE_5 = "test0005";
    private static final String TEST_CASE_4 = "test0004";
    private static final String TEST_CASE_3 = "test0003";
    private static final String TEST_CASE_2 = "test0002";
    private static final String TEST_CASE_1 = "test0001";
    private static final String TEST_CASE_1234 = "test1234";
    private static final String TEST_CASE_0 = "test0000";

    String serverUrl = "http://localhost/api/";
    String user = "admin";
    String password = "supersecret";
    RocketChatClient rc;

    @Before
    public void beforeTests() {
        this.rc = new RocketChatClient(this.serverUrl, this.user, this.password);

        assertNotNull("An error occured setting up Rocket.Chat Client, it is null.", this.rc);
    }

    @After
    public void afterTests() {
        if (this.rc == null)
            return;

        // TODO: Clean up/delete the channels/groups/rooms/etc
    }

    @Test
    public void testRocketCatExists() throws Exception {

        ServerInfo info = this.rc.getServerInformation();
        assertTrue("The Rocket.Chat Version is empty, when it shouldn't be.", !info.getVersion().isEmpty());

        User rocketCat = this.rc.getUser("rocket.cat");
        assertTrue("The Rocket.Cat user's id doesn't match what it should be.", "rocket.cat".equals(rocketCat.getId()));
    }

    @Test
    public void testCreateCloseAndOpenChannel() throws Exception {
        String roomNameTest = TEST_CASE_0;
        Room room = this.rc.createChannel(roomNameTest);
        assertTrue("Room Id shouldn't be null if the room was created",
                room.getId() != null && !room.getId().isEmpty());

        this.rc.closeChannel(room.getId());
        this.rc.openChannel(room.getId());
    }

    @Test
    public void testCreateChannel() throws Exception {
        Room room = this.rc.createChannel(TEST_CASE_1234);
        assertTrue("Room Id shouldn't be null if the room was created",
                room.getId() != null && !room.getId().isEmpty());
        assertTrue("Room name wasn't created with the provided name.", room.getName().equals(TEST_CASE_1234));
    }

    @Test
    public void testCreateArchiveAndUnarchiveChannel() throws Exception {
        String roomNameTest = TEST_CASE_1;
        Channel room = this.rc.createChannel(roomNameTest);
        assertTrue("Room Id shouldn't be null if the room was created", (room.getId() != null && !room.getId().isEmpty()));

        this.rc.archiveChannel(room);
        room = this.rc.getChannelInfo(room);
        assertTrue("The channel should be archived, but it wasn't.", room.isArchived());

        this.rc.unarchiveChannel(room);
        room = this.rc.getChannelInfo(room);
        assertFalse("The channel shouldn't be archived, but it is.", room.isArchived());
    }

    @Test
    public void testCreateCloseAndOpenGroup() throws Exception {
        String roomNameTest = TEST_CASE_2;
        Room room = this.rc.createGroup(roomNameTest);
        assertTrue("Room Id shouldn't be null if the room was created",
                (room.getId() != null && !room.getId().isEmpty()));

        this.rc.closeGroup(room.getId());
    }

    @Test
    public void testCreateArchiveAndUnarchiveGroup() throws Exception {
        String roomNameTest = TEST_CASE_3;
        Group room = this.rc.createGroup(roomNameTest);
        assertTrue("Room Id shouldn't be null if the room was created", (room.getId() != null && !room.getId().isEmpty()));

        this.rc.archiveGroup(room);
        room = this.rc.getGroupInfo(room);
        assertTrue("The group should be archived, but it wasn't.", room.isArchived());

        this.rc.unarchiveGroup(room);
        room = this.rc.getGroupInfo(room);
        assertFalse("The group shouldn't be archived, but it is.", room.isArchived());
    }

    @Test
    public void testCreateAndGetGroup() throws Exception {
        String roomNameTest = TEST_CASE_4;
        Group room = this.rc.createGroup(roomNameTest);
        assertTrue("Room Id shouldn't be null if the room was created", room.getId() != null && !room.getId().isEmpty());

        Group room1 = this.rc.getGroupInfo(room.getId());

        assertTrue("Error, room was null", room1 != null);
        assertEquals("Error, group names were not equal", room1.getName(), roomNameTest);
    }

    @Test
    public void testRenameChannel() throws Exception {
        String roomNameTest = TEST_CASE_5;
        Room room = this.rc.createChannel(roomNameTest);
        assertTrue("Room Id shouldn't be null if the room was created", room.getId() != null && !room.getId().isEmpty());
        assertEquals(TEST_CASE_5, room.getName());

        this.rc.renameChannel(room.getId(), TEST_CASE_6);
        room = this.rc.getChannelInfo(room.getId());
        assertEquals(TEST_CASE_6, room.getName());
    }

    @Test
    public void testRenameGroup() throws Exception {
        String roomNameTest = TEST_CASE_7;
        Room room = this.rc.createGroup(roomNameTest);
        assertTrue("Room Id shouldn't be null if the room was created", room.getId() != null && !room.getId().isEmpty());
        assertEquals(TEST_CASE_7, room.getName());

        this.rc.renameGroup(room.getId(), TEST_CASE_8);
        room = this.rc.getGroupInfo(room.getId());
        assertEquals(TEST_CASE_8, room.getName());
    }
}
