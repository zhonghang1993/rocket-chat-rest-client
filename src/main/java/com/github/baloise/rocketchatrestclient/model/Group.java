package com.github.baloise.rocketchatrestclient.model;

public class Group extends Room {
    public Group() {
        super();
        this.setType(RoomType.PRIVATE_GROUP);
    }
    
    public Group(String name) {
        super();
        this.setType(RoomType.PRIVATE_GROUP);
        this.setName(name);
    }
    
    public Group(String id, String name) {
        super();
        this.setType(RoomType.PRIVATE_GROUP);
        this.setId(id);
        this.setName(name);
    }
}
