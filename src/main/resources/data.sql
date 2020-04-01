insert into 'cursus' ('id', 'nom', 'date_debut', 'date_fin', 'prerequis', 'objectifs', 'contenu', 'salle') values
(1, 'BTS 1', '01-01-2020' , '30-11-2020', 'BAC', 'BAC +1', 'Beaucoup', 1),
(2, 'BTS 2', '01-01-2020' , '30-11-2020', 'BAC', 'BAC +2', 'Beaucoup trop', 1),
(3, 'Licence 3', '01-01-2020' , '30-11-2020', 'BAC', 'BAC +3', 'Facile', 1),
(4, 'M2I', '01-01-2020' , '30-11-2020', 'BAC', 'BAC +5', 'Trop Cool', 1);

insert into 'formateur' ('id', 'nom', 'prenom', 'coordonnees', 'numero_rue', 'rue', 'code_postal', 'ville', 'matieres_debutant', 'matieres_intermediaire', 'matieres_avance', 'matieres_confirme', 'matieres', 'module' ) values
(1, 'Fort', 'Jean', '0102030405', 123, 'rue Bidon', '12345', 'Bidonville', 1, 2, 3, 4, ,1),
(2, 'Vincent', 'Didier', '0102030405', 23, 'Avenue du général', '12345', 'Bidonville', 1, 2, 3, 4, ,1),
(3, 'Juda', 'Martin', '0102030405', 31, 'rue de la paix', '12345', 'Bidonville', 1, 2, 3, 4, ,1),
(4, 'Bichon', 'Nadine', '0102030405', 124, 'rue Bidon', '12345', 'Bidonville', 1, 2, 3, 4, ,1);

insert into 'gestionnaire' ('id', 'nom', 'prenom', 'coordonnees', 'numero_rue', 'rue', 'code_postal', 'ville', 'cursus') values
(1, 'Fort', 'Jean', '0102030405', 123, 'rue Bidon', '12345', 'Bidonville', 1),
(2, 'Vincent', 'Didier', '0102030405', 23, 'Avenue du général', '12345', 'Bidonville', 2),
(3, 'Juda', 'Martin', '0102030405', 31, 'rue de la paix', '12345', 'Bidonville', 3),
(4, 'Bichon', 'Nadine', '0102030405', 124, 'rue Bidon', '12345', 'Bidonville', 4);

insert into 'matiere' ('id', 'nom', 'duree', 'formateur') values
(1, 'matiere1', '5jours', 1),
(2, 'matiere2', '5jours', 4),
(3, 'matiere3', '5jours', 3),
(4, 'matiere4', '5jours', 2);

insert into 'module' ('id', 'nom', 'date_debut', 'date_fin', 'matiere', 'formateur', 'cursue') values
(1, 'module1', '01-01-2020', '31-01-2020', 1, 1, 1),
(2, 'module2', '01-02-2020', '28-02-2020', 4, 3, 2),
(3, 'module3', '01-03-2020', '31-03-2020', 2, 2, 3),
(4, 'module4', '01-04-2020', '30-04-2020', 3, 4, 4);

insert into 'ordinateur' ('id', 'nom', 'cout', 'processeur', 'ram', 'dd', 'date_achat', 'stock') values
(1, 'EC1', 800.50, 'i5', '8Go', '312Go', '01-12-2019', 5),
(2, 'EC2', 700.50, 'i5', '6Go', '256Go', '01-12-2019', 5),
(3, 'EC3', 600.50, 'i5', '4Go', '256Go', '01-12-2019', 5),
(4, 'EC4', 900.50, 'i7', '6Go', '312Go', '01-12-2019', 5)
(5, 'EC5', 750.50, 'i5', '8Go', '312Go', '01-12-2019', 5),
(6, 'EC6', 500.50, 'i3', '6Go', '256Go', '01-12-2019', 5),
(7, 'EC7', 400.50, 'i3', '4Go', '256Go', '01-12-2019', 5),
(8, 'EC8', 1000.50, 'i7', '8Go', '512Go', '01-12-2019', 5);

insert into 'projecteur' ('id', 'nom', 'cout', 'stock') values
(1, 'PRO1', 400, 5),
(2, 'PRO2', 500, 5),
(3, 'PRO3', 200, 5),
(4, 'PRO4', 300, 5);

insert into 'salle' ('id', 'code', 'cout', 'capacite_max', 'projecteur', 'cursus') values
(1, 'S1P50', 78, 50, 3, 2),
(2, 'S1P200', 150, 50, 4, 4)),
(3, 'S1P150', 100, 50, 1, 3)),
(4, 'S4P500', 300, 50, 2, 1));

insert into 'stagiaire' ('id', 'nom', 'prenom', 'coordonnees', 'numero_rue', 'rue', 'code_postal', 'ville', 'ordinateur', 'cursus') values
(1, 'Clement', 'Valentin', '0102030405', 150, 'Avenue Bidon', '12345', 'Bidonville', 1, 1),
(2, 'Martin', 'Martin', '0102030405', 151, 'Avenue Bidon', '12345', 'Bidonville', 2, 1),
(3, 'Dupont', 'Marc', '0102030405', 152, 'Avenue Bidon', '12345', 'Bidonville', 3, 2),
(4, 'Bichon', 'Suzie', '0102030405', 153, 'Avenue Bidon', '12345', 'Bidonville', 4, 3),
(5, 'Mallou', 'Eddy', '0102030405', 154, 'Avenue Bidon', '12345', 'Bidonville', 5, 4,
(6, 'Fillion', 'François', '0102030405', 155, 'Avenue Bidon', '12345', 'Bidonville', 6, 3),
(7, 'Fleuri', 'Editte', '0102030405', 156, 'Avenue Bidon', '12345', 'Bidonville', 7, 3),
(8, 'Legrand', 'Julie', '0102030405', 157, 'Avenue Bidon', '12345', 'Bidonville', 8, 4);

insert into 'technicien' ('id', 'nom', 'prenom', 'coordonnees', 'numero_rue', 'rue', 'code_postal', 'ville') values
(1, 'Bon', 'Akim',  '0102030405', 150, 'Boulevard Bidon', '12345', 'Bidonville'),
(2, 'Mignon', 'Suzanne',  '0102030405', 10, 'Avenue des Bidons', '12345', 'Bidonville');
