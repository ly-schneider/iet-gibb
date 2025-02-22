DROP DATABASE IF EXISTS LetsWichtel;

CREATE DATABASE LetsWichtel;

USE LetsWichtel;

CREATE TABLE PLACE (
ID_Place INT PRIMARY KEY AUTO_INCREMENT,
PLZ SMALLINT NOT NULL,
Place VARCHAR(20) NOT NULL,
CONSTRAINT CHK_PLZ CHECK (PLZ >= 1000 AND PLZ <= 9999)
);

CREATE TABLE TYPE (
ID_Type INT PRIMARY KEY AUTO_INCREMENT,
Name VARCHAR(20) NOT NULL
);

CREATE TABLE ROLE (
ID_Role INT PRIMARY KEY AUTO_INCREMENT,
Name VARCHAR(20) NOT NULL
);

CREATE TABLE WICHTEL (
ID_Wichtel INT PRIMARY KEY AUTO_INCREMENT,
CreatedAt DATETIME DEFAULT now(),
Name VARCHAR(30) NOT NULL,
URL VARCHAR(35) NOT NULL,
MaxPrice SMALLINT NOT NULL,
Private BOOLEAN NOT NULL,
Password VARCHAR(65),
Started BOOLEAN DEFAULT false
);

CREATE TABLE ACCOUNT (
ID_Account INT PRIMARY KEY AUTO_INCREMENT,
CreatedAt DATETIME DEFAULT now(),
Firstname VARCHAR(20) NOT NULL,
Lastname VARCHAR(20) NOT NULL,
Email VARCHAR(50) NOT NULL,
Password VARCHAR(65) NOT NULL,
Address VARCHAR(40) NOT NULL,
Place_ID INT NOT NULL,
Admin BOOLEAN NOT NULL,
FOREIGN KEY (Place_ID) REFERENCES PLACE(ID_Place) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE PASSWORD_RESET (
ID_Reset INT PRIMARY KEY AUTO_INCREMENT,
Account_ID INT NOT NULL,
Valid BOOLEAN NOT NULL,
ValidUntil DATETIME NOT NULL,
Token VARCHAR(40) UNIQUE NOT NULL,
FOREIGN KEY (Account_ID) REFERENCES ACCOUNT(ID_Account) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BAN (
ID_Ban INT PRIMARY KEY AUTO_INCREMENT,
CreatedAt DATETIME DEFAULT now(),
BannedBy_ID INT,
Type_ID INT NOT NULL,
Email VARCHAR(50) NOT NULL,
Reason VARCHAR(255) NOT NULL,
ExpiresAt DATETIME,
FOREIGN KEY (BannedBy_ID) REFERENCES ACCOUNT(ID_Account) ON DELETE SET NULL ON UPDATE CASCADE,
FOREIGN KEY (Type_ID) REFERENCES TYPE(ID_Type) ON DELETE NO ACTION ON UPDATE CASCADE
);

CREATE TABLE WICHTEL_ACCOUNT_ROLE (
ID_Wichtel_Account_Role INT PRIMARY KEY AUTO_INCREMENT,
Account_ID INT NOT NULL,
Wichtel_ID INT NOT NULL,
Role_ID INT NOT NULL,
FOREIGN KEY (Account_ID) REFERENCES ACCOUNT(ID_Account) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Wichtel_ID) REFERENCES WICHTEL(ID_Wichtel) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Role_ID) REFERENCES ROLE(ID_Role) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE WICHTEL_ACCOUNT_INFO (
ID_Wichtel_Account_Info INT PRIMARY KEY AUTO_INCREMENT,
Account_ID INT NOT NULL,
Wichtel_ID INT NOT NULL,
Partner_ID INT DEFAULT NULL,
Wunschliste VARCHAR(250),
FOREIGN KEY (Account_ID) REFERENCES ACCOUNT(ID_Account) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Wichtel_ID) REFERENCES WICHTEL(ID_Wichtel) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (Partner_ID) REFERENCES ACCOUNT(ID_Account) ON DELETE SET NULL ON UPDATE CASCADE
);

DELIMITER //
CREATE TRIGGER set_valid_until
BEFORE INSERT ON PASSWORD_RESET
FOR EACH ROW
BEGIN
    SET NEW.ValidUntil = DATE_ADD(NOW(), INTERVAL 1 HOUR);
END;
//
DELIMITER ;