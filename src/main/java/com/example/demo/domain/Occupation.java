package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//職種DB
@Data
@Entity
@Table(name = "occupation")
public class Occupation {

	@Id
    @Column(name="occupation_id")
    private int occupationId;

    @Column(name="occupation_name")
    private String occupationName;

}
