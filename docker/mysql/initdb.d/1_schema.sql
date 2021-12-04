CREATE TABLE company (
    company_id INT NOT NULL AUTO_INCREMENT,
    company_name varchar(30) NOT NULL,
    industry_id INT,
    capital bigint,
    empcnt INT,
    fromY varchar(4),
    FOREIGN KEY (industry_id) REFERENCES industry(industry_id),
    PRIMARY KEY (company_id,company_name)
);

CREATE TABLE application (
    application_id INT NOT NULL AUTO_INCREMENT,
    application_name varchar(40) NOT NULL,
    applocation_explain varchar(1000),
    capacity INT,
    industry_id INT,
    industry_name varchar(20),
    company_id INT,
    company_name varchar(30),
  	occupation_id INT,
	occupation_name varchar(20),
    _from char(8),
    _to char(8),
    FOREIGN KEY (company_id) REFERENCES company(company_id),
    FOREIGN KEY (industry_id) REFERENCES industry(industry_id),
    FOREIGN KEY (occupation_id) REFERENCES occupation(occupation_id),
    PRIMARY KEY (application_id,application_name)
);

CREATE TABLE user (
    user_id varchar(30) NOT NULL,
    user_pass char(10) NOT NULL,
    user_name varchar(50),
    user_tel varchar(15),
    user_email varchar(50),
    PRIMARY KEY (user_id,user_pass)
);

CREATE TABLE offer (
    offer_id INT NOT NULL AUTO_INCREMENT,
    offer_name varchar(30),
    user_id varchar(30),
    application_id INT,
    company_id INT,
    FOREIGN KEY (application_id) REFERENCES application(application_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    applyTime char(8),
    PRIMARY KEY (offer_id)
);

CREATE TABLE industry (
	industry_id INT NOT NULL AUTO_INCREMENT,
	industry_name varchar(20) NOT NULL,
	PRIMARY KEY (industry_id,industry_name)
);

CREATE TABLE occupation (
	occupation_id INT NOT NULL AUTO_INCREMENT,
	occupation_name varchar(20) NOT NULL,
	PRIMARY KEY (occupation_id,occupation_name)
);

