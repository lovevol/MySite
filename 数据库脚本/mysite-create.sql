CREATE DATABASE mysitedb CHARACTER SET utf8;
USE mysitedb;
CREATE TABLE category (
  idcategory  INT          NOT NULL AUTO_INCREMENT,
  name        VARCHAR(100) NOT NULL,
  type        INT          NOT NULL,
  discription VARCHAR(200),
  CONSTRAINT pk_category PRIMARY KEY (idcategory)
)DEFAULT CHARSET = utf8;
CREATE TABLE label (
  idlabel     INT          NOT NULL AUTO_INCREMENT,
  name        VARCHAR(100) NOT NULL,
  idcategory  INT          NOT NULL,
  description VARCHAR(200) NOT NULL,
  articlenum  INT,
  CONSTRAINT pk_label PRIMARY KEY (idlabel),
  CONSTRAINT fk_category_label FOREIGN KEY (idcategory) REFERENCES category (idcategory)
)DEFAULT CHARSET = utf8;
CREATE TABLE ebook
(
    idebook INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    bookname VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    path VARCHAR(200)
)DEFAULT CHARSET = utf8;
CREATE TABLE user
(
  iduser int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  loginname VARCHAR(50),
  username VARCHAR(50),
  password VARCHAR(50),
  gender int,
  roletype TINYINT,
  email VARCHAR(50),
  status INT,
  validatecode VARCHAR(100),
  validatedate DATETIME
)DEFAULT CHARSET = utf8;
CREATE TABLE web
(
    idweb INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    weburl VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL,
    remark VARCHAR(200),
    label VARCHAR(100)

)DEFAULT CHARSET = utf8;
CREATE TABLE content
(
  idcontent INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  content TEXT
)DEFAULT CHARSET = utf8;
CREATE TABLE article
(
    idarticle INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    date DATE,
    idlabel INT NOT NULL,
    idcategory INT NOT NULL,
    sketch VARCHAR(200),
    imagepath VARCHAR(200),
    idcontent INT,
    CONSTRAINT article_category_idcategory_fk FOREIGN KEY (idcategory) REFERENCES category (idcategory),
    CONSTRAINT article_label_idlabel_fk FOREIGN KEY (idlabel) REFERENCES label (idlabel),
    CONSTRAINT article_content_idcontent_fk FOREIGN KEY (idcontent) REFERENCES content (idcontent)
)DEFAULT CHARSET = utf8;
