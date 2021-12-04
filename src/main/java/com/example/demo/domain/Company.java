package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//会社管理DB
@Data
@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue
	@Column(name="company_id")
	private int companyId;

	@Column(name="company_name")
	private String companyName;

    @Column(name="industry_id")
    private int industryId;

    @Column(name="capital")
	private String capital;

    @Column(name="empcnt")
	private int empcnt;

    @Column(name="fromY")
	private String fromY;
}
