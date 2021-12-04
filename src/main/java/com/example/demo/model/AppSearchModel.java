package com.example.demo.model;

//検索フォームモデル
public class AppSearchModel {

	private String search_word;
	private String industry;
	private String occupation;
	private String capital;
	private String employeeCnt;
	private String founding;

	public String getSearch_word() {
		return search_word;
	}
	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getEmployeeCnt() {
		return employeeCnt;
	}
	public void setEmployeeCnt(String employeeCnt) {
		this.employeeCnt = employeeCnt;
	}
	public String getFounding() {
		return founding;
	}
	public void setFounding(String founding) {
		this.founding = founding;
	}
}
