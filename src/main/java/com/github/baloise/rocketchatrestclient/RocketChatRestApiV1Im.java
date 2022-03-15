package com.github.baloise.rocketchatrestclient;

import com.github.baloise.rocketchatrestclient.model.ImRoom;

import java.io.IOException;

/**
 * Created by zhonghang  2022/3/15.
 */
public class RocketChatRestApiV1Im {
    private RocketChatClientCallBuilder callBuilder;
    protected RocketChatRestApiV1Im(RocketChatClientCallBuilder callBuilder) {
        this.callBuilder = callBuilder;
    }

    public ImRoom create(String username) throws IOException {

        RocketChatClientResponse res = this.callBuilder.buildCall(RocketChatRestApiV1.ImCreate,null, new RocketChatQueryParams("username" , username).get());

        if (!res.isSuccessful())
            throw new IOException("The postMessage was unsuccessful: \"" + res.getError() + "\"");


        return res.getImRoom();
    }


}
