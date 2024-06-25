package com.advancia.models;

public class DeleteKebabRequest {

	private int kebabId;
	private int userId;
	
	public DeleteKebabRequest(int kebabId, int userId) {
		this.kebabId = kebabId;
		this.userId = userId;
	}
	
	public DeleteKebabRequest() {}
	
	public DeleteKebabRequest setKebabId(int kebabId) {
		this.kebabId = kebabId;
		return this;
	}
	
	public int getKebabId() {
		return kebabId;
	}
	
	public DeleteKebabRequest setUserId(int userId) {
		this.userId = userId;
		return this;
	}
	
	public int getUserId() {
		return userId;
	}
}
