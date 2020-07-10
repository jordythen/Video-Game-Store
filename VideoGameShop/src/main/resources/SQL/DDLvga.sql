drop table DEVELOPER cascade constraints;
drop table DEVELOPER_GAME cascade constraints;
drop table GAME cascade constraints;
drop table GAME_GAMECATEGORY cascade constraints;
drop table GAME_GAMEDETAILS cascade constraints;
drop table GAME_GAMESYSTEM cascade constraints;
drop table GAMECATEGORY cascade constraints;
drop table GAMEDETAILS cascade constraints;
drop table GAMEORDER cascade constraints;
drop table GAMESYSTEM cascade constraints;
drop table ORDERDETAILS cascade constraints;
drop table PERSON cascade constraints;
drop table PERSON_GAME cascade constraints;
drop table PERSON_GAMEDETAILS2SELL cascade constraints;
drop table PERSONROLE cascade constraints;
drop table PUBLISHER_GAME cascade constraints;
drop table PUBLISHER cascade constraints;

create table GAME (
    
    id number(10) primary key,
    name varchar2(255),
    dateReleased timestamp,
    esrbRating varchar2(10),
    playerLimit varchar2(100)
    
);

create table GAMESYSTEM(
    
    id number(10) primary key,
    name varchar2(255)

);

--one to many deals with uniqueness
--many to many 
create table GAME_GAMESYSTEM(

    gameID number(10),
    systemID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (systemID) references GAMESYSTEM(id)

);


create table DEVELOPER(

    id number(10) primary key,
    name varchar2(255)
    
);

create table DEVELOPER_GAME (
    
    gameID number(10),
    developerID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (developerID) references DEVELOPER(id)
    
);

create table PUBLISHER(

    id number(10) primary key,
    name varchar2(255)

);

create table PUBLISHER_GAME (
    
    gameID number(10),
    publisherID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (publisherID) references PUBLISHER(id)
    
);

create table GAMECATEGORY(
    
    id number(10) primary key,
    name varchar2(255),
    description varchar2(255)
    
);

--many to many
create table GAME_GAMECATEGORY(

    gameID number(10),
    categoryID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (categoryID) references GAMECATEGORY(id)
    
);

create table GAMEDETAILS(

    id number(10) primary key,
    gameID number(10),
    status varchar2(255),
    quantity number(10),
    price number(20,2),
    
    foreign key (gameID) references GAME(id)
);

create table GAME_GAMEDETAILS(

    gameID number(10),
    detailID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (detailID) references GAMEDETAILS(id)
    
);

create table PERSONROLE(

    id number(10) primary key,
    personRole varchar(255)

);

create table PERSON(
    
    id number(10) primary key,
    roleID number(10),
    username varchar2(255),
    passwd varchar2(255),
    firstName varchar2(255),
    lastName varchar2(255),
    money number(20,2),

    foreign key (roleID) references PERSONROLE(id)
);

--one to many/many to one
--showing ownership of games
create table PERSON_GAME(

    personID number(10),
    gameID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (personID) references PERSON(id)
);

create table PERSON_GAMEDETAILS2SELL(

    personID number(10),
    gameDetailsID number(10),
    
    foreign key (gameDetailsID) references GAMEDETAILS(id),
    foreign key (personID) references PERSON(id)
);

create table GAMEORDER(

    id number(10) primary key,
    personID number(10),
    tax number(20,2),
    orderTotal number(20,2),
    orderDate timestamp,
    
    foreign key (personID) references PERSON(id)

);


create table ORDERDETAILS(

    id number(10) primary key,
    gameID number(10),
    orderID number(10),
    quantity number(10),
    taxAmount number(20,2),
    totalLineAmount number(20,2),
    
    foreign key (gameID) references GAME(id),
    foreign key (orderID) references GAMEORDER(id)
);

drop sequence game_seq;
drop sequence gamesystem_seq;
drop sequence developer_seq;
drop sequence publisher_seq;
drop sequence category_seq;
drop sequence gamedetails_seq;
drop sequence person_seq;
drop sequence order_seq;
drop sequence orderdetails_seq;
drop sequence role_seq;

drop trigger developer_trig;
drop trigger game_trig;
drop trigger gamedetails_trig;
drop trigger gamesystem_trig;
drop trigger order_trig;
drop trigger orderdetails_trig;
drop trigger person_trig;
drop trigger publisher_trig;
drop trigger role_trig;

create sequence role_seq;
create sequence game_seq;
create sequence gamesystem_seq;
create sequence developer_seq;
create sequence publisher_seq;
create sequence category_seq;
create sequence gamedetails_seq;
create sequence person_seq;
create sequence order_seq;
create sequence orderdetails_seq;


create or replace trigger GAME_TRIG
before insert or update on GAME
for each row
begin
    if INSERTING then
        select GAME_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger GAMESYSTEM_TRIG
before insert or update on GAMESYSTEM
for each row
begin
    if INSERTING then
        select GAMESYSTEM_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger DEVELOPER_TRIG
before insert or update on DEVELOPER
for each row
begin
    if INSERTING then
        select DEVELOPER_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger PUBLISHER_TRIG
before insert or update on PUBLISHER
for each row
begin
    if INSERTING then
        select PUBLISHER_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger GAMEDETAILS_TRIG
before insert or update on GAMEDETAILS
for each row
begin
    if INSERTING then
        select GAMEDETAILS_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger PERSON_TRIG
before insert or update on PERSON
for each row
begin
    if INSERTING then
        select PERSON_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger ORDER_TRIG
before insert or update on GAMEORDER
for each row
begin
    if INSERTING then
        select ORDER_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger ORDERDETAILS_TRIG
before insert or update on ORDERDETAILS
for each row
begin
    if INSERTING then
        select ORDERDETAILS_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/
create or replace trigger ROLE_TRIG
before insert or update on PERSONROLE
for each row
begin
    if INSERTING then
        select ROLE_SEQ.nextval into :new.id from dual;
    elsif UPDATING then
        select :old.id into :new.id from dual;
    end if;
end;
/










