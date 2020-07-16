insert into PERSONROLE(personrole) values ('customer');
insert into PERSONROLE(personrole) values ('employee');
insert into PERSONROLE(personrole) values ('manager');

insert into person(roleid, username, passwd, firstname, lastname, money) values (1, 'jordythen', 'pass', 'Jordy', 'Then', 2420.69);
insert into person(roleid, username, passwd, firstname, lastname, money) values (2, 'rgonz166', 'pass', 'Raul', 'Gonzalez', 860);

select * from PERSON where username = 'jordythen' AND passwd = 'pass';
select * from person order by id;
delete from person where id > 20;
select * from personrole;
select * from developer;
delete from developer;
select * from developer_person;
delete from developer_person;