package com.github.baloise.rocketchatrestclient.model;

public class RoomUserRequest extends RoomRequest{

	private String userId;

	public RoomUserRequest() {
		super();
	}
	
	public RoomUserRequest(String roomId, String userId) {
		super(roomId);
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
