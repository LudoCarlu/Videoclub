CREATE TABLE Adherent (
	numeroTel VARCHAR(15) PRIMARY KEY NOT NULL,
	codeSecret VARCHAR(5) NOT NULL,
	nom VARCHAR(50),
	prenom VARCHAR(50),
	adresse TEXT NOT NULL,
	numeroCB VARCHAR(20) NOT NULL
);

CREATE TABLE DescriptionArticle (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	codeArticle VARCHAR NOT NULL,
	description TEXT NOT NULL,
	prixVente REAL NOT NULL,
	prixJournalier REAL
	prixHebdomadaire REAL,
	titre VARCHAR(100),
	genre VARCHAR(50),
	estNouveau BOOLEAN,
	estPerdu BOOLEAN

);

CREATE TABLE Vente (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	montant REAL NOT NULL,
	dateHeure DATETIME NOT NULL
);

CREATE TABLE Location (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	numeroAdherent VARCHAR(15) NOT NULL,
	codeBarre VARCHAR NOT NULL,
	dateHeure DATETIME NOT NULL,
	dateDue DATETIME NOT NULL,
	dateRetour DATETIME NOT NULL,
	montant REAL NOT NULL,
	FOREIGN KEY (numeroAdherent) REFERENCES Adherent (numeroTel)
);

CREATE TABLE Employe (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	nom VARCHAR(100) NOT NULL,
	gerant BOOLEAN
	--Plus simple d'intégrer avec valeur boolean pour définir si l'employé est gérant ou nom que de définir un champs VARCHAR
);


/*CREATE TABLE Film (
	
	codeArticle INT NOT NULL,
	estNouveau BOOLEAN NOT NULL,
	FOREIGN KEY (codeArticle) REFERENCES Article(codeArticle)
);

CREATE TABLE DescriptionFilm(
	id INTEGER PRIMARY KEY NOT NULL,
	titre VARCHAR(100) NOT NULL,
	genre VARCHAR(50) NOT NULL,
	dateParution DATETIME NOT NULL,
);	

INSERT INTO Adherent VALUES ('8193296425','1234','Carlu','Ludovic','71 rue Scott J8Y4G8 Gatineau','1234567812345678','2016-11-06 21:50');
INSERT INTO Article VALUES (1,2);
INSERT INTO DescriptionArticle (codeArticle,description,prix)
VALUES (1,"James Bond",16);
INSERT INTO Location (numeroAdherent,dateHeure,datePrevue,dateRetour,montant)
VALUES 


/* Idée
Il faut des tarifs de locations soit à la semaine soit journalier
	1. On met tarifJournalier et tarifHebdomadaire dans DescriptionArticle
	et qui peut etre null
	2. On crée une classe Tarif avec type de tarif et le prix

*/



