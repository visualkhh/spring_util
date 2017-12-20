package com.khh;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Default {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
