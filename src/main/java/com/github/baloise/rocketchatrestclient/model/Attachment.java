package com.github.baloise.rocketchatrestclient.model;

import java.util.ArrayList;

public class Attachment {
    private String color, text, ts, thumbUrl, messageLink;
    private boolean collapsed;
    private String authorName, authorLink, authorIcon;
    private String title, titleLink, titleLinkDownload;
    private String imageUrl, audioUrl, videoUrl;
    private ArrayList<AttachmentField> fields;

    public Attachment() {
        this.fields = new ArrayList<AttachmentField>();
    }

    public Attachment(String text) {
        this.fields = new ArrayList<AttachmentField>();
        this.text = text;
    }

    public Attachment(String title, String text) {
        this.fields = new ArrayList<AttachmentField>();
        this.title = title;
        this.text = text;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
