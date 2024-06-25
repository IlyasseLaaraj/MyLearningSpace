package com.advancia.models;

import com.advancia.daos.AddKebabRequest;

public class UpdateKebabRequest extends AddKebabRequest {

	private int kebabId;

	public int getKebabId() {
		return kebabId;
	}

	public UpdateKebabRequest setKebabId(int kebabId) {
		this.kebabId = kebabId;
		return this;
	}
}
