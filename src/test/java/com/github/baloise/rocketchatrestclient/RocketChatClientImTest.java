package com.github.baloise.rocketchatrestclient;

import com.github.baloise.rocketchatrestclient.model.*;
import com.github.baloise.rocketchatrestclient.util.TestConnectionInfo;
import com.vdurmont.emoji.EmojiManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by zhonghang  2022/3/15.
 */
public class RocketChatClientImTest {
    private RocketChatClient rc;

    @Before
    public void setUp() throws Exception {
        this.rc = new RocketChatClient(TestConnectionInfo.ServerUrl, TestConnectionInfo.User, TestConnectionInfo.Password);

        assertNotNull("An error occured setting up Rocket.Chat Client, it is null.", this.rc);
    }

    @Test
    public void createIm() throws IOException {
        ImRoom imRoom = rc.getIm().create("yyy");
        System.out.println("roomId : "+imRoom.getRid());


        Room room = new Room(imRoom.getRid(), false);
//        room.setTopic("研发");
//        Message msg = this.rc.getChatApi().postMessage(room, new Message("普通消息."));

//        assertNotNull("The returned message is null?", msg);
//        assertEquals("The message text is not the same.", "Testing this out.", msg.getText());

        Message attachmentMsg = new Message("我是主消息.");
        attachmentMsg.addAttachment(new Attachment("我是附加消息"));
        attachmentMsg.setEmojiAvatar(EmojiManager.getForAlias("smirk"));
        Message returnedAttachmentMsg = this.rc.getChatApi().postMessage(room, attachmentMsg);

        attachmentMsg.setEmojiAvatar(EmojiManager.getForAlias("smirk"));
        this.rc.getChatApi().postMessage(room, attachmentMsg);

    }


    @Test
    public void userList() throws IOException {
        final User[] list = rc.getUsersApi().list();
        for (User user : list) {
            System.out.println(user.getName()+"\t" + user.getUsername());
        }

    }

}
