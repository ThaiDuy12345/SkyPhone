package com.nhathanh.until;

import javax.validation.constraints.NotBlank;

public class RefreshFolder {
	@NotBlank
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
