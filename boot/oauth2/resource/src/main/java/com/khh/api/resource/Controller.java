package com.khh.api.resource;

abstract class Controller {
	public static final String ROOT_PACKAGE 	= "com.khh.api.resource";
	public static final String AOP_EXECUTION 	= "* com.khh.project.api..*Controller.*(..)";
	protected Controller() {
	}
}
