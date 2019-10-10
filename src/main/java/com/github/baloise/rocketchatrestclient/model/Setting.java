package com.github.baloise.rocketchatrestclient.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Setting {
	private String id;
	private String value;

	@JsonGetter("_id")
	public String getId() {
		return id;
	}

	@JsonSetter("_id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonGetter("value")
	public String getValue() {
		return value;
	}

	@JsonSetter("value")
	public void setValue(String value) {
		this.value = value;
	}

}
