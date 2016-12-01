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

CREATE TABLE Article (
	codeBarre VARCHAR PRIMARY KEY,
	codeDescription VARCHAR NOT NULL,
	estLoue BOOLEAN NOT NULL,
	estPerdu BOOLEAN NOT NULL,
	FOREIGN KEY (codeDescription) REFERENCES DescriptionArticle(codeArticle)
);

INSERT INTO Article VALUES ("12A","12","false","false");
INSERT INTO Article VALUES ("13A","13","false","false");
INSERT INTO Article VALUES ("133A","133","false","false");
INSERT INTO Article VALUES ("19A","19","false","false");
INSERT INTO Article VALUES ("14A","14","false","false");
INSERT INTO Article VALUES ("15A","15","false","false");
INSERT INTO Article VALUES ("16A","16","false","false");






