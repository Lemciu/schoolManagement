INSERT INTO Student
    (first_name, last_name, age, email, field_of_study)
VALUES
    ('Anna', 'Krawczyk', 24, 'AniaWu@gmail.com', 'LAW'),
    ('Katarzyna', 'Sobota', 31, 'KaEs33@gmail.com', 'LAW'),
    ('Michał', 'Jędryka', 29, 'Michu123@gmail.com', 'LAW'),
    ('Katarzyna', 'Niedziela', 25, 'KrejziKasia@gmail.com', 'LOGISTICS'),
    ('Mateusz', 'Marchewka', 24, 'karociarz@gmail.com', 'PSYCHOLOGY'),
    ('Mateusz', 'Grzyb', 22, 'grzybu@gmail.com', 'PSYCHOLOGY'),
    ('Krzysztof', 'Grzyb', 21, 'grzybek@gmail.com', 'LOGISTICS'),
    ('Kacper', 'Całek', 31, 'kacpi222@gmail.com', 'LOGISTICS'),
    ('Wojciech', 'Polak', 23, 'polski.wojtek@gmail.com', 'MEDICINE'),
    ('Kinga', 'Szecówka', 23, 'kinga.sz@gmail.com', 'MEDICINE'),
    ('Marcin', 'Nowak', 26, 'marcin222@gmail.com', 'SOCIOLOGY');

INSERT INTO Tutor
    (first_name, last_name, age, email, subject)
VALUES
    ('Waldemar', 'Bochra', 54, 'waldemar.be@gmail.com', 'HISTORY'),
    ('Małgorzata', 'Uramowska', 47, 'gosia.gosia@gmail.com', 'ENGLISH'),
    ('Paweł', 'Majewski', 61, 'pe.majewski@gmail.com', 'FRENCH'),
    ('Agnieszka', 'Duda', 59, 'aga.de@gmail.com', 'POLISH'),
    ('Stanisław', 'Gąbka', 44, 'stanislaw.gabka@gmail.com', 'MATHEMATICS'),
    ('Wojciech', 'Pustuł', 42, 'pustul.wojciech@gmail.com', 'CHEMISTRY'),
    ('Paweł', 'Kwiek', 47, 'pawelek.kwiek74@gmail.com', 'PHYSICS');

INSERT INTO tutor_students
(tutor_id, student_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 10),
    (1, 11),
    (2, 1),
    (2, 2),
    (3, 1),
    (3, 5),
    (4, 1);