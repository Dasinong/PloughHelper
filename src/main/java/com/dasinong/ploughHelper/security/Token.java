package com.dasinong.ploughHelper.security;

public class Token {
	private Long tokenId;

	public Token(Long tokenId) {
		this.tokenId = tokenId;
	}

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

}
