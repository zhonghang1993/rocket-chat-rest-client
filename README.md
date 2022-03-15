# 对rocket-chat-rest-client 进行二次开发

原地址：https://github.com/baloise/rocket-chat-rest-client

### 一、背景

- 由于rocket-chat-rest-client已经很久没有维护过了，但是rocket.chat已经更新了很多版本，发布了很多新的接口。
- 目前的旧版本，支持群聊的功能，但是对个人私聊功能没有。
- 所以对IM Endpoints接口中的create进行了补充开发，得到rid（也就是roomId）。

### 二、官方接口文档地址

https://developer.rocket.chat/reference/api/rest-api/endpoints/team-collaboration-endpoints/im-endpoints/create

### 三、示例
测试类：RocketChatClientImTest->create()，创建和某人私聊的房间(direct message ，硬着翻译，就是直聊)，并且发送一条测试消息。


