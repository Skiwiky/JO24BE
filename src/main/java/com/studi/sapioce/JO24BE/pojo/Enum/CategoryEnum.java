package com.studi.sapioce.JO24BE.pojo.Enum;

public enum CategoryEnum {

	SOLO("Solo"),
	
	DUO("Duo"),
	
	FAMILLE("Famille");
	
	private final String category;

	public String getCategory() {
		return category;
	}

	private CategoryEnum(String category) {
		this.category = category;
	}
	
	
}
