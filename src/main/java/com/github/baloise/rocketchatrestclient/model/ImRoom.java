package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by zhonghang  2022/3/15.
 */
public class ImRoom  extends Identified {

    private String rid;
    private RoomType type;

    @JsonGetter("rid")
    public String getRid() {
        return rid;
    }
    @JsonSetter("rid")
    public void setRid(String rid) {
        this.rid = rid;
    }

    @JsonGetter("t")
    public RoomType getType() {
        return type;
    }

    @JsonSetter("t")
    public void setType(RoomType type) {
        this.type = type;
    }
}
