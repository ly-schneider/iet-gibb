SET GLOBAL local_infile=1;

USE LetsWichtel;

LOAD DATA LOCAL INFILE "/Users/levyn/M114/Place.csv" INTO TABLE PLACE
CHARACTER SET latin1
FIELDS TERMINATED BY ";"
LINES TERMINATED BY "\r\n"
IGNORE 1 LINES
(Place, PLZ);

LOAD DATA LOCAL INFILE "/Users/levyn/M114/Account.csv" INTO TABLE ACCOUNT
CHARACTER SET latin1
FIELDS TERMINATED BY ";"
LINES TERMINATED BY "\r\n"
IGNORE 1 LINES
(Firstname, Lastname, Email, Password, Address, Place_ID, Admin);

INSERT INTO WICHTEL (Name, URL, MaxPrice, Private, Password) VALUES
("INF2023d Wichtel", "inf2023d", 30, false, null),
("Post Wichteln", "post", 50, true, "GeheimesPasswort"),
("BBC-Wichteln", "bbc-wichtel", 5, false, null),
("GiBB", "gibb", 99, false, null),
("Kinderheim", "kinderheim", 200, true, "S€cr€t"),
("Altersheim", "altersheim", 20, true, null),
("Kreative Köpfe", "kreative-koepfe", 40, false, "12345"),
("Schneider Wichteln", "wichtel-schneider", 25, true, "FamilieSchneider"),
("Tierliebhaber", "tierliebhaber", 35, false, null),
("Reisefreunde", "reisefreunde", 60, true, "wichtel2024");

INSERT INTO TYPE (Name) VALUES
("Account"),
("Create"),
("Join");

INSERT INTO ROLE (Name) VALUES
("Administrator"),
("Moderator"),
("Nutzer");

INSERT INTO WICHTEL_ACCOUNT_INFO (Account_ID, Wichtel_ID, Partner_ID, Wunschliste) VALUES
(1, 1, null, "Gute Noten"),
(2, 2, null, "Brackgutschein"),
(3, 3, null, "Gamingtastatur"),
(4, 4, null, "Migrosgutschein"),
(5, 5, null, "Kuscheltier"),
(6, 6, null, "Wolle"),
(7, 7, null, "Pinsel"),
(8, 8, null, "Klobürste"),
(9, 9, null, "Katzengemälde"),
(10, 10, null, "Wanderschuhe"),
(1, 3, null, "Gamingstuhl"),
(2, 8, null, "Pfannen"),
(3, 9, null, "Hundespielzeug"),
(4, 7, null, "Leinwand"),
(5, 9, null, "Goldfisch"),
(6, 7, null, "Kreuzworträtsel"),
(7, 4, null, "Mensa-Rabatt"),
(8, 7, null, "Harrypotter Buch"),
(9, 10, null, "Rucksack"),
(10, 6, null, "Rollator"),
(4, 1, null, "Playstaion 5"),
(5, 7, null, "Sudoku"),
(6, 10, null, "Sonnenbrille"),
(7, 2, null, "Apéro"),
(8, 1, null, "Computermaus"),
(2, 9, null, "Aquarium"),
(5, 10, null, "Stirnband"),
(8, 2, null, "GitHubAI"),
(2, 7, null, "Farbe");

INSERT INTO WICHTEL_ACCOUNT_ROLE (Account_ID, Wichtel_ID, Role_ID) VALUES
(11, 1, 1),
(12, 2, 1),
(13, 3, 1),
(14, 4, 1),
(15, 5, 1),
(16, 6, 1),
(17, 7, 1),
(19, 9, 1),
(20, 10, 1),
(13, 5, 2),
(19, 9, 2),
(17, 1, 2),
(12, 3, 2),
(1, 1, 3),
(2, 2, 3),
(3, 3, 3),
(4, 4, 3),
(5, 5, 3),
(6, 6, 3),
(7, 7, 3),
(8, 8, 1),
(9, 9, 3),
(10, 10, 3),
(1, 3, 3),
(2, 8, 3),
(3, 9, 3),
(4, 7, 3),
(5, 1, 3),
(6, 2, 3),
(7, 4, 3),
(8, 5, 3),
(9, 10, 3),
(10, 6, 3),
(4, 1, 3),
(5, 8, 3),
(6, 10, 3),
(7, 2, 3),
(8, 1, 3),
(2, 9, 3),
(5, 3, 3),
(8, 2, 3),
(2, 7, 3);

INSERT INTO BAN (BannedBy_ID, Type_ID, Email, Reason, ExpiresAt) VALUES
(20, 3, "Josefine_Jansen579@acrit.org", "Fehlverhalten", "2024-12-31 11:59:59"),
(20, 1, "Aris_Braunwald6031@zorer.org", "Spam", "2024-04-26 13:08:23"),
(20, 1, "Manuel_Pohl3496@ubusive.com", "Ignorieren von Verwarnungen", "2024-08-26 13:10:44"),
(20, 2, "Emmerich_Salz8323@infotech44.tech", "Anstössiger Wichtel Name", "2024-07-31 11:59:59"),
(20, 1, "Nikolaus_Richter6750@womeona.net", "Komische / unpassende Dinge in der Wunschliste", "2024-04-06 13:14:33"),
(20, 2, "Daria_Simmons3500@mafthy.com", "Beleidigung anderer Mitglieder", "2024-05-15 11:59:59"),
(20, 3, "Daria_Simmons3500@mafthy.com", "Verstoß gegen die Nutzungsbedingungen", "2024-06-30 18:45:12"),
(20, 1, "Manuel_Schiesser99@typill.biz", "Hacking-Versuche", "2024-07-12 09:30:00"),
(20, 2, "Greta_Norton2339@eirey.tech", "Veröffentlichung von persönlichen Informationen", "2024-09-20 11:59:59"),
(20, 3, "Manuel_Schiesser99@typill.biz", "Verbreitung von Falschinformationen", "2024-10-29 14:22:17");

INSERT INTO PASSWORD_RESET (Account_ID, Valid, Token) VALUES 
(3, 1, "75056fcd-eb88-49e7-9b85-6a199494d46e"),
(7, 1, "1a2b3c4d-5e6f-7g8h-9i10-jk1l2m3n4o5p"),
(10, 1, "532c6002-395f-4f7f-aba0-b5ef701246d0"),
(12, 1, "9ca15e14-7d89-4efc-bec8-60181fb376a2"),
(15, 1, "52966a68-cd3a-4dba-9110-8a92f2b3eb4e"),
(18, 1, "4265a9ac-f82d-47ab-8e02-1906582a01ce"),
(2, 1, "d2a7049e-4768-4bdd-8793-c7cdf182df55"),
(5, 1, "fe71f591-5628-41c8-90e9-4ec3420e5601"),
(8, 1, "e8e65db5-6cc5-42f4-bd95-4d8deb0340b1"),
(9, 1, "1da02dab-51c7-4776-ab4a-8d055abeeafb");