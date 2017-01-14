package com.github.baloise.rocketchatrestclient;

import java.io.IOException;

import com.github.baloise.rocketchatrestclient.model.Message;
import com.github.baloise.rocketchatrestclient.model.Room;

public class RocketChatRestApiV1Chat {
    private RocketChatClientCallBuilder callBuilder;
    
    protected RocketChatRestApiV1Chat(RocketChatClientCallBuilder callBuilder) {
        this.callBuilder = callBuilder;
    }
    
    public void postMessage(Room room, Message message) throws IOException {
        message.setRoomId(room.getId());
        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ChatPostMessage, null, message);
    }
}
