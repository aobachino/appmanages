package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "application")
public class App {

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
}
