package com.demo.enums;

public enum TYPE {
	
	BILLABLE("billable"),NONBILLABLE("non-billable");
	private String val;
	private TYPE(String val) {
		this.val=val;
	}
	
	public String getVal() {
		return val;
	}

}
