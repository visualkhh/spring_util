package com.khh.code;

import com.google.gson.annotations.Expose;

public class FieldInfo {
	@Expose
	String name;
	public FieldInfo() {
	}

	public FieldInfo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public FieldInfo setName(String name) {
		this.name = name;
		return this;
	}

}
