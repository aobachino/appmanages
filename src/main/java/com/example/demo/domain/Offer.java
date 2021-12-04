package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//応募管理DB
@Data
@Entity
@Table(name = "offer")
public class Offer {

	public Offer(String offName,String userId, int appId, int companyId, String appTime) {
		setOfferName(offName);
		setUserId(userId);
		setAppId(appId);
		setCompanyId(companyId);
		setAppTime(appTime);

	}

	@Id
	@GeneratedValue
	@Column(name="offer_id")
	private int offerId;

	@Column(name="offer_name")
	private String offerName;

	@Column(name="user_id")
	private String userId;

	@Column(name="application_id")
	private int appId;

	@Column(name="company_id")
	private int companyId;

	@Column(name="apply_time")
	private String appTime;
}
