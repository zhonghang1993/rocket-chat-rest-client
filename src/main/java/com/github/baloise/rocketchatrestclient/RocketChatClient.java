package com.github.baloise.rocketchatrestclient;

import java.io.IOException;
import java.util.Set;

import org.json.JSONObject;

import com.github.baloise.rocketchatrestclient.model.Room;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RocketChatClient {

	private final String serverUrl;
	private final String user;
	private final String password;

	public RocketChatClient(String serverUrl, String user, String password) {
		this.serverUrl = serverUrl;
		this.user = user;
		this.password = password;
	}

	public Set<Room> getPublicRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getApiVersion() throws IOException {
		return getVersions().getString("api");
	}

	public String getRocketChatVersion() throws IOException {
		return getVersions().getString("rocketchat");
	}
	
	JSONObject lazyVersions;

	private JSONObject getVersions() throws IOException {
		if (lazyVersions == null) {
			try {
				lazyVersions = Unirest.get(serverUrl + "version").asJson().getBody().getObject()
						.getJSONObject("versions");
			} catch (UnirestException e) {
				throw new IOException(e);
			}
		}
		return lazyVersions;
	}

}
