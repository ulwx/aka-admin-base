package com.github.ulwx.aka.admin.utils.secure;

public enum SecureAlgorithm {

	SHA1WithRSA("SHA1WithRSA"),

	MD5WithRSA("MD5WithRSA");

	private String signAlgorithm;

	private SecureAlgorithm(String signAlgorithm) {
		this.signAlgorithm = signAlgorithm;
	}

	public String getSignAlgorithm() {
		return signAlgorithm;
	}
}
