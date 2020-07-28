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

-------------------------------- game stuff 

insert into game(name, datereleased, esrbrating, playerlimit) values ('CrimeWave', '03-MAR-25 06.11.30.951000000 PM', 'T', '1');
select * from game;

insert into developer(name, description) values ('Jordy Now', 'Solo indie developer from Pennsylvania. Interested in action pack pixel art 2D side scrollers. Looking for Unity2D Developers.');
insert into developer_person(devid, personid) values (41, 1);
insert into developer_game(gameid, developerid) values (1, 41);

--1
insert into gamesystem(name) values ('PC'); 
--2
insert into gamesystem(name) values ('XBOX One');
--3
insert into gamesystem(name) values ('PS4');
--4
insert into gamesystem(name) values ('GameCube');
--5
insert into gamesystem(name) values ('XBOX 360');
--6
insert into gamesystem(name) values ('PS3');
select * from gamesystem;

insert into game_gamesystem(gameid, systemid) values (1, 1);
insert into game_gamesystem(gameid, systemid) values (1, 3);

insert into publisher(name) values ('Devolver Digital');
select * from publisher;
insert into publisher_game(gameid, publisherid) values (1, 1);

insert into gamedetails(gameid, status, quantity, price) values (1, 'Early Alpha', 9999, 30.00);
select * from gamedetails;

insert into gamecategory(name, description) values ('Platform Game', 'Large part of the gameplay involves jumping and climbing between suspended platforms while avoiding obstacles.');
select * from gamecategory;
insert into game_gamecategory(gameid, categoryid) values (1, 1);

