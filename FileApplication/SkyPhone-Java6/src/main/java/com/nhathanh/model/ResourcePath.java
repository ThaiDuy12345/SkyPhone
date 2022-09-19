package com.nhathanh.model;

import org.springframework.beans.factory.annotation.Value;



public class ResourcePath {
	@Value(value = "")
	private ResourcePath pathImage;

	public ResourcePath() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourcePath(ResourcePath pathImage) {
		super();
		this.pathImage = pathImage;
	}

	public ResourcePath getPathImage() {
		return pathImage;
	}

	public void setPathImage(ResourcePath pathImage) {
		this.pathImage = pathImage;
	}
	
	
}
