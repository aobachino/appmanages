package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//求人管理DB
@Data
@Entity
@Table(name = "application")
public class App {


	public App(){
		super();
	}

	public App(int appId,String appName,String appExplain,int industryId,String industryName,int companyId,String companyName,int occupationId, String occupationName,String from,String to,String delflg,int capacity) {
		setAppId(appId);
		setAppName(appName);
		setAppExplain(appExplain);
		setIndustryId(industryId);
		setIndustryName(industryName);
		setCompanyId(companyId);
		setCompanyName(companyName);
		setOccupationId(occupationId);
		setOccupationName(occupationName);
		setFrom(from);
		setTo(to);
		setDelflg(delflg);
		setCapacity(capacity);
	}
    @Id
    @Column(name="application_id")
    private int appId;

    @Column(name="application_name")
    private String appName;

    @Column(name="applocation_explain")
    private String appExplain;

    @Column(name="industry_id")
    private int industryId;

    @Column(name="industry_name")
    private String industryName;

    @Column(name="company_id")
    private int companyId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="occupation_id")
    private int occupationId;

    @Column(name="occupation_name")
    private String occupationName;

    @Column(name="_from")
    private String from;

    @Column(name="_to")
    private String to;

    @Column(name="delflg")
    private String delflg;

    @Column(name="capacity")
    private int capacity;

}
