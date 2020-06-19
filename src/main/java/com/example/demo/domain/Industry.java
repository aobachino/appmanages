package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//業種DB
@Data
@Entity
@Table(name = "industry")
public class Industry {

	@Id
    @Column(name="industry_id")
    private int industryId;

    @Column(name="industry_name")
    private String industryName;

}
