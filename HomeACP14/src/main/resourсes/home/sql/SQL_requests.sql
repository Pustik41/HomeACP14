--task1
select s.firstname, s.lastname, s.mail, s.phone, g.name_group from students s inner join groups g on s.group_id = g.group_id;
select name_group from groups;
select t.firstname, t.lastname, t.mail, t.phone, t.experience, s.name  from teachers t inner join subjects s on t.subject_id = s.subject_id;
select name, description from subjects;
--task2
insert into students(firstname, lastname, mail, phone, group_id) values ('Alex', 'Butko', '14@mail.ru' , '541', 1);
-- также автоматом нужно присвоить ему предметы и оценки
insert into teachers(firstname, lastname, mail, phone, subject_id) values ('Fedor', 'Grabnik', '18@mail.com' , '942', 1);
-- присвоить автоматом ему предмет
insert into groups(name_group) values ('IAP102');
--присвоить предметы два конструктора
insert into students(firstname, lastname, mail, phone, group_id) values ('Alex', 'Butko', '14@mail.com' , '541', 1);

private static final String STUDENTS_REQUEST = "SELECT s.firstname, s.lastname, s.mail, g.name_group " +
            "FROM students s INNER JOIN groups g ON s.group_id = g.group_id ORDER BY s.lastname;";
    private static final String GROUPS_REQUEST = "SELECT group_id, name_group FROM groups ORDER BY name_group;";
    private static final String TEACHERS_REQUEST = "SELECT t.firstname, t.lastname, t.mail, t.experience, s.name  " +
            "FROM teachers t INNER JOIN subjects s ON t.subject_id = s.subject_id ORDER BY t.lastname;";
    private static final String SUBJECTS_REQUEST = "SELECT name, description FROM subjects ORDER BY name;";