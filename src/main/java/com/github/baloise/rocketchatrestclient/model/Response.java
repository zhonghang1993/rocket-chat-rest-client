package com.github.baloise.rocketchatrestclient.model;

public class Response {
    private boolean success;
    private Message[] messages;
    private Message message;
    private User[] users;
    private User user;

    public void setSuccess(boolean result) {
        this.success = result;
    }

    public boolean isSuccessful() {
        return this.success;
    }

    public void setMessages(Message[] messages) {
        this.messages = messages;
    }

    public Message[] getMessages() {
        return this.messages;
    }

    public boolean isMessages() {
        return this.messages != null;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return this.message;
    }

    public boolean isMessage() {
        return this.message != null;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return this.users;
    }

    public boolean isUsers() {
        return this.users != null;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public boolean isUser() {
        return this.user != null;
    }
}
