group_subjects subject_id, group_id
groups group_id, name_group
students student_id, firstname, lastname, group_id
students_progress student_id, subject_id, progress
subjects subject_id, name, description
teachers teacher_id, firstname, lastname, subject_id, experience

CREATE TABLE groups(
    group_id int PRIMARY KEY AUTO_INCREMENT,
    name_group VARCHAR(45) UNIQUE NOT NULL
 );

CREATE TABLE students(
    student_id int PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    group_id int,
    FOREIGN KEY(group_id) REFERENCES groups(group_id)
 );

 CREATE TABLE subjects(
     subject_id int PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(45),
     description VARCHAR(45)
  );

  CREATE TABLE teachers(
      teacher_id int PRIMARY KEY AUTO_INCREMENT,
      firstname VARCHAR(20),
      lastname VARCHAR(20),
      subject_id int,
      experience int,
      FOREIGN KEY(subject_id) REFERENCES subjects(subject_id)
   );

CREATE TABLE group_subjects(
     subject_id int,
     group_id int,
     FOREIGN KEY(subject_id) REFERENCES subjects(subject_id),
     FOREIGN KEY(group_id) REFERENCES groups(group_id)
  );

CREATE TABLE students_progress(
     student_id int,
     subject_id int,
     progress int NOT NULL,
     FOREIGN KEY(subject_id) REFERENCES subjects(subject_id),
     FOREIGN KEY(student_id) REFERENCES students(student_id)
  );

--default values for tests

INSERT INTO groups(name_group) VALUES('IAP101');
INSERT INTO groups(name_group) VALUES('IKIT101');

INSERT INTO students(firstname, lastname, group_id) VALUES('Alex', 'Browko', 1);
INSERT INTO students(firstname, lastname, group_id) VALUES('Dima', 'Vasiliv', 2);
INSERT INTO students(firstname, lastname, group_id) VALUES('Oleg', 'Vorona', 2);
INSERT INTO students(firstname, lastname, group_id) VALUES('Katja', 'Kirko', 1);

INSERT INTO subjects(name, description) VALUES('Math', 'Technical');
INSERT INTO subjects(name, description) VALUES('Eanglish', 'Humanities');
INSERT INTO subjects(name, description) VALUES('History', 'Humanities');
INSERT INTO subjects(name, description) VALUES('Programming', 'Technical');
INSERT INTO subjects(name, description) VALUES('Construction', 'Technical');

INSERT INTO teachers(firstname, lastname, subject_id, experience) VALUES('Irina', 'Holovko', 1, 12);
INSERT INTO teachers(firstname, lastname, subject_id, experience) VALUES('Igor', 'Voevoda', 3, 21);
INSERT INTO teachers(firstname, lastname, subject_id, experience) VALUES('Lidia', 'Ivanova', 2, 18);
INSERT INTO teachers(firstname, lastname, subject_id, experience) VALUES('Kolja', 'Barkar', 4, 25);
INSERT INTO teachers(firstname, lastname, subject_id, experience) VALUES('Pasha', 'Stepanenko', 5, 34);

INSERT INTO group_subjects(subject_id, group_id) VALUES(1, 1);
INSERT INTO group_subjects(subject_id, group_id) VALUES(1, 2);
INSERT INTO group_subjects(subject_id, group_id) VALUES(2, 1);
INSERT INTO group_subjects(subject_id, group_id) VALUES(2, 2);
INSERT INTO group_subjects(subject_id, group_id) VALUES(3, 1);
INSERT INTO group_subjects(subject_id, group_id) VALUES(3, 2);
INSERT INTO group_subjects(subject_id, group_id) VALUES(4, 2);
INSERT INTO group_subjects(subject_id, group_id) VALUES(5, 1);

INSERT INTO students_progress(student_id, subject_id, progress) VALUES(1, 1, 87);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(1, 2, 88);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(1, 3, 76);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(1, 5, 90);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(2, 1, 87);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(2, 2, 88);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(2, 3, 91);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(2, 4, 92);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(3, 1, 78);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(3, 2, 74);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(3, 3, 65);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(3, 4, 89);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(4, 1, 90);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(4, 2, 76);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(4, 3, 82);
INSERT INTO students_progress(student_id, subject_id, progress) VALUES(4, 5, 91);



