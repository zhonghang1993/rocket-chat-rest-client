package com.github.baloise.rocketchatrestclient;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.github.baloise.rocketchatrestclient.model.Attachment;
import com.github.baloise.rocketchatrestclient.model.Message;
import com.github.baloise.rocketchatrestclient.model.Room;
import com.github.baloise.rocketchatrestclient.util.TestConnectionInfo;
import com.vdurmont.emoji.EmojiManager;

public class RocketChatClientChatTestIT {
    private RocketChatClient rc;

    @Before
    public void setUp() throws Exception {
        this.rc = new RocketChatClient(TestConnectionInfo.ServerUrl, TestConnectionInfo.User, TestConnectionInfo.Password);

        assertNotNull("An error occured setting up Rocket.Chat Client, it is null.", this.rc);
    }


    @Test
    public void testPrivateMessage(){

    }

    @Test
    public void testPostMessage() throws IOException {
        Room room = new Room("onlyread", false);
//        room.setTopic("研发");
//        Message msg = this.rc.getChatApi().postMessage(room, new Message("普通消息."));

//        assertNotNull("The returned message is null?", msg);
//        assertEquals("The message text is not the same.", "Testing this out.", msg.getText());
        
//        Message attachmentMsg = new Message("我是主消息.");
//        attachmentMsg.addAttachment(new Attachment("我是附加消息"));
//        attachmentMsg.setEmojiAvatar(EmojiManager.getForAlias("smirk"));
//        Message returnedAttachmentMsg = this.rc.getChatApi().postMessage(room, attachmentMsg);


        Message attachmentMsg = new Message("可收缩的消息.");
//        Attachment attachment = new Attachment("子消息title", "我是附加消息");
//        attachment.setMessageLink("https://www.baidu.com");
//        attachment.setColor("yellow");//线条颜色
//
//        attachmentMsg.addAttachment(attachment);


        Attachment img = new Attachment();
        img.setImageUrl("https://img2.baidu.com/it/u=1814268193,3619863984&fm=253&fmt=auto&app=138&f=JPEG?w=632&h=500");

        attachmentMsg.addAttachment(img);
        attachmentMsg.setEmojiAvatar(EmojiManager.getForAlias("smirk"));
         this.rc.getChatApi().postMessage(room, attachmentMsg);







//        assertNotNull("The returned attachment message is null?", returnedAttachmentMsg);
//        assertEquals("The attachment message text is not the same.", "Testing with attachment.", returnedAttachmentMsg.getText());
//        assertEquals("The attachment message doesn't have attachments?", 1, returnedAttachmentMsg.getAttachments().length);
    }
}
