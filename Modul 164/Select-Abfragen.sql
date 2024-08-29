USE LetsWichtel;

/* 1. Alle Informationen zu einem bestimmten Account abrufen */
SELECT Firstname AS "Vorname", Lastname AS "Nachname", Email, Password AS "Passwort", Admin, Address AS "Adresse", PLZ, Place AS "Ort" FROM ACCOUNT AS A 
JOIN Place AS P ON A.Place_ID=P.ID_Place 
WHERE A.Firstname = 'Karina' AND A.Lastname = 'Adler';

/* 2. Vom Account wird erfolgreich der Vorname, Nachname, Volle Adresse und alle Wichtel Name + dazugehörige Wunschliste ausgegeben */
SELECT A.Firstname AS "Vorname", A.Lastname AS "Nachname", CONCAT(A.Address, ", ", P.PLZ, " ", P.Place) AS "Volle Adresse", WA.Wunschliste, W.Name AS "Wichtel Name" FROM
ACCOUNT AS A
JOIN PLACE AS P ON P.ID_Place=A.Place_ID
JOIN WICHTEL_ACCOUNT_INFO AS WA ON A.ID_Account=WA.Account_ID
JOIN WICHTEL AS W ON W.ID_Wichtel=WA.Wichtel_ID WHERE A.Firstname = "Eva" AND A.Lastname = "Scherer";

/* 3. Alle Wichtel, die einem bestimmten Account zugewiesen sind und die dazugehörige Wunschliste */
SELECT W.Name AS 'Wichtelname', WAI.Wunschliste
FROM WICHTEL_ACCOUNT_INFO AS WAI
JOIN WICHTEL AS W ON WAI.Wichtel_ID = W.ID_Wichtel
WHERE WAI.Account_ID = (SELECT ID_Account FROM ACCOUNT WHERE Firstname = 'Karl' AND Lastname = 'Aschenbrenner');

/* 4. Alle gesperrten Accounts abrufen */
SELECT B.Email, B.Reason AS "Grund", B.ExpiresAt AS "Läuft ab", A.Firstname AS "Bannausteller Vorname", A.Lastname AS "Banausteller Nachname", T.Name AS "Typ" FROM BAN AS B JOIN ACCOUNT AS A ON A.ID_Account=B.BannedBy_ID JOIN TYPE AS T ON T.ID_Type=B.Type_ID;

/* 5. Partner und Partner Wunschliste von einem Account in einem Wichtel abrufen (falls vorhanden) */
SELECT W.Name AS 'Wichtel', CONCAT(A2.Firstname, ' ', A2.Lastname) AS 'Partner', WA2.Wunschliste AS 'Partner Wunschliste'
FROM WICHTEL_ACCOUNT_INFO AS WA1 JOIN WICHTEL AS W ON WA1.Wichtel_ID = W.ID_Wichtel
LEFT JOIN WICHTEL_ACCOUNT_INFO AS WA2 ON WA1.Partner_ID = WA2.Account_ID
LEFT JOIN ACCOUNT AS A2 ON WA2.Account_ID = A2.ID_Account
WHERE WA1.Account_ID = (SELECT ID_Account FROM ACCOUNT WHERE Firstname = 'Alexander' AND Lastname = 'Amsel');

/* 6. Alle Wichtel, die nicht auf private gestellt sind */
SELECT * FROM WICHTEL WHERE Private = false;

/* 7. Alle Password-Reset-Token für einen bestimmten Account anzeigen */
SELECT CONCAT(A.Firstname, ' ', A.Lastname) AS "Account", PR.Valid, PR.ValidUntil AS "Valid bis", PR.Token FROM PASSWORD_RESET AS PR JOIN ACCOUNT AS A ON A.ID_Account=PR.Account_ID WHERE PR.Account_ID = (SELECT ID_Account FROM ACCOUNT WHERE Firstname = 'Winnie' AND Lastname = 'Amstutz');

/* 8. Anzahl Personen in einem spezifischen Ort */
SELECT COUNT(*) AS 'Anzahl Personen'
FROM ACCOUNT AS A
JOIN PLACE AS P ON A.Place_ID = P.ID_Place
WHERE P.Place = 'Basel';

/* 9. Alle Administratoren und Moderator in einem Wichtel abrufen */
SELECT A.Firstname AS 'Vorname', A.Lastname AS 'Nachname', R.Name AS 'Rolle', W.Name AS 'Wichtelname'
FROM WICHTEL_ACCOUNT_ROLE AS WAR
JOIN ACCOUNT AS A ON WAR.Account_ID = A.ID_Account
JOIN ROLE AS R ON WAR.Role_ID = R.ID_Role
JOIN WICHTEL AS W ON WAR.Wichtel_ID = W.ID_Wichtel
WHERE R.Name IN ("Administrator", "Moderator") AND W.Name = "BBC-Wichteln";

/* 10. Anzahl Personen in einem spezifischen Wichtel */
SELECT COUNT(*) AS 'Anzahl Personen' FROM WICHTEL_ACCOUNT_INFO
WHERE Wichtel_ID = (SELECT ID_Wichtel FROM WICHTEL WHERE Name = 'GiBB');
