package com.yeollu.getrend.dataPreprocess.model;

public class Morpheme {
	private String text;
	private String type;
	private Integer count;
	
	public Morpheme() {
		
	}

	public Morpheme(String text, String type, Integer count) {
		this.text = text;
		this.type = type;
		this.count = count;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
}
