package com.github.baloise.rocketchatrestclient;

import java.io.IOException;

import com.github.baloise.rocketchatrestclient.model.Group;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.model.ServerInfo;
import com.github.baloise.rocketchatrestclient.model.User;

/**
 * Client for Rocket.Chat which relies on the REST API v1.
 * <p>
 * Please note, this client does <strong>not</strong> cache any of the results.
 *
 * @version 0.1.0
 * @since 0.0.1
 */
public class RocketChatClient {
    private static final String USER_ID_PARAM_KEY = "userId";
    private static final String ROOM_ID_PARAM_KEY = "roomId";

    private RocketChatClientCallBuilder callBuilder;
    private RocketChatRestApiV1Channels channels;
    private RocketChatRestApiV1Chat chat;

    /**
     * Initialize a new instance of the client providing the server's url along
     * with username and password to use.
     *
     * @param serverUrl
     *            of the Rocket.Chat server, with or without it ending in
     *            "/api/"
     * @param user
     *            which to authenticate with
     * @param password
     *            of the user to authenticate with
     */
    public RocketChatClient(String serverUrl, String user, String password) {
        this.callBuilder = new RocketChatClientCallBuilder(serverUrl, user, password);
        this.channels = new RocketChatRestApiV1Channels(this.callBuilder);
        this.chat = new RocketChatRestApiV1Chat(this.callBuilder);
    }

    /**
     * Forces a logout and clears the auth token if no exception happened.
     *
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void logout() throws IOException {
        this.callBuilder.logout();
    }

    /**
     * Gets the {@link ServerInfo} from the server, containing the version.
     *
     * @return the {@link ServerInfo}
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public ServerInfo getServerInformation() throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.Info);

        if (!res.isSuccessful())
            throw new IOException("The call out to get the server information was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasServerInfo())
            throw new IOException("The server information was not retrieved from the server.");

        return res.getServerInfo();
    }

    public RocketChatRestApiV1Channels getChannelsApi() {
        return this.channels;
    }

    public RocketChatRestApiV1Chat getChatApi() {
        return this.chat;
    }

    /**
     * Gets the first number of users from the Rocket.Chat server, the amount
     * depends on what the server has configured to return as the default count
     * with the default being 50.
     *
     * @return an array of {@link User}s
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public User[] getUsers() throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.UsersList);

        if (!res.isSuccessful())
            throw new IOException("The call to get the Users was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasUsers())
            throw new IOException("Get User Information failed to retrieve the users.");

        return res.getUsers();
    }

    /**
     * Retrieves a {@link User} from the Rocket.Chat server.
     *
     * @param userId
     *            of the user to retrieve
     * @return an instance of the {@link User}
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful or there is no user
     */
    public User getUser(String userId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.UsersInfo, new RocketChatQueryParams(USER_ID_PARAM_KEY, userId));

        if (!res.isSuccessful())
            throw new IOException("The call to get the User's Information was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasUser())
            throw new IOException("Get User Information failed to retrieve a user.");

        return res.getUser();
    }

    /**
     * Gets the first number of the private groups the calling user has access
     * to from the Rocket.Chat server, the amount depends on what the server has
     * configured to return as the default count with the default being 50.
     *
     * @return an array of {@link Room}s that are groups
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Group[] getGroups() throws IOException {
        return this.getGroups(new RocketChatQueryParams());
    }

    /**
     * Gets the private groups from the Rocket.Chat server, depending on the
     * query params provided otherwise uses the server's defaults.
     *
     * @param params
     *            an {@link RocketChatQueryParams} instance
     * @return an array of {@link Group}s
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Group[] getGroups(RocketChatQueryParams params) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsList, params);

        if (!res.isSuccessful())
            throw new IOException("The call to get the private Groups was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasGroups())
            throw new IOException("Get Groups failed to retrieve the groups.");

        return res.getGroups();
    }

    /**
     * Retrieves the information about the group.
     *
     * @param groupId
     *            the "_id" of the room to get information
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     * @return The {@link Room} of the channel
     */
    public Group getGroupInfo(String groupId) throws IOException {
        return this.getGroupInfo(new Group(groupId, ""));
    }

    /**
     * Retrieves the information about the group.
     *
     * @param group
     *            the group to get information about
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     * @return The {@link Room} of the channel
     */
    public Group getGroupInfo(Group group) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsInfo, new RocketChatQueryParams(ROOM_ID_PARAM_KEY, group.getId()));

        if (!res.isSuccessful())
            throw new IOException("The call to get the Group's information was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasGroup())
            throw new IOException("The call to get the Group's information was unsuccessful, failed to return channel data.");

        return res.getGroup();
    }

    /**
     * Creates a new private group with only the creator added
     *
     * @param groupName
     *            the of the group to create
     * @return the {@link Room} which is the newly created channel
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Group createGroup(String groupName) throws IOException {
        return this.createGroup(new Group(groupName));
    }

    /**
     * Creates a new private group with any of the members added in the class.
     *
     * @param group
     *            the of the group to create
     * @return the {@link Room} which is the newly created channel
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public Group createGroup(Group group) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsCreate, null, group);

        if (!res.isSuccessful())
            throw new IOException("The call to create a Group was unsuccessful: \"" + res.getError() + "\"");

        if (!res.hasGroup())
            throw new IOException("The response does not contain any group information.");

        return res.getGroup();
    }

    /**
     * Archives a group
     *
     * @param groupId
     *            the "_id" of the room to archive
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void archiveGroup(String groupId) throws IOException {
        this.archiveGroup(new Group(groupId, ""));
    }

    /**
     * Archives a group
     *
     * @param group
     *            the group to archive
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void archiveGroup(Group group) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsArchive, null, group);

        if (!res.isSuccessful())
            throw new IOException("The call to archive the Group was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Unarchives a group
     *
     * @param groupId
     *            the "_id" of the room to unarchive
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void unarchiveGroup(String groupId) throws IOException {
        this.unarchiveGroup(new Group(groupId, ""));
    }

    /**
     * Unarchives a group
     *
     * @param group
     *            the group to unarchive
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void unarchiveGroup(Group group) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsUnarchive, null, group);

        if (!res.isSuccessful())
            throw new IOException("The call to unarchive the Group was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Closes a group
     *
     * @param groupId
     *            the "_id" of the room to close
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void closeGroup(String groupId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsClose, null, new Room(groupId, false));

        if (!res.isSuccessful())
            throw new IOException("The call to close the Group was unsuccessful: \"" + res.getError() + "\"");

    }

    /**
     * Opens a group
     *
     * @param groupId
     *            the "_id" of the room to open
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void openGroup(String groupId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsOpen, null, new Room(groupId, false));

        if (!res.isSuccessful())
            throw new IOException("The call to open the Group was unsuccessful: \"" + res.getError() + "\"");

    }

    /**
     * Invites a user to a private group
     *
     * @param groupId
     *            the "_id" of the room to invite user to
     * @param userId
     *            the "_id" of the user to invite
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void inviteUserToGroup(String groupId, String userId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsInvite, null);
        // TODO: new RoomUserRequest(groupId, userId));

        if (!res.isSuccessful())
            throw new IOException("The call to invite a User to a Group was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Removes a user from a private group
     *
     * @param groupId
     *            the "_id" of the room to remove user from
     * @param userId
     *            the "_id" of the user to remove
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void removeUserFromGroup(String groupId, String userId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsKick, null);
        // TODO: new RoomUserRequest(groupId, userId));

        if (!res.isSuccessful())
            throw new IOException("The call to remove a User from a Group was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Removes the callee from a group
     *
     * @param groupId
     *            the "_id" of the room to leave
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void leaveGroup(String groupId) throws IOException {
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsLeave, null, new Room(groupId, false));

        if (!res.isSuccessful())
            throw new IOException("The call to leave a Group was unsuccessful: \"" + res.getError() + "\"");
    }

    /**
     * Renames the group
     *
     * @param groupId
     *            the "_id" of the room to rename
     * @param name
     *            the new name of the room
     * @throws IOException
     *             is thrown if there was a problem connecting, including if the
     *             result wasn't successful
     */
    public void renameGroup(String groupId, String name) throws IOException {
        Room room = new Room(groupId, false);
        room.setName(name);

        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.GroupsRename, null, room);

        if (!res.isSuccessful())
            throw new IOException("The call to rename a Group was unsuccessful: \"" + res.getError() + "\"");
    }
}