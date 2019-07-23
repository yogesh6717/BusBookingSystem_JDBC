package com.dev.beans;

public class Suggestion {
	
	private String suggest;
	private int userid;
	private int suggId;
	public String getSuggest() {
		return suggest;
	}
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}
	public int getSuggId() {
		return suggId;
	}
	public void setSuggId(int suggId) {
		this.suggId = suggId;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Suggestion [suggest=" + suggest + ", userid=" + userid + ", suggId=" + suggId + "]";
	}
	
	
	

}
