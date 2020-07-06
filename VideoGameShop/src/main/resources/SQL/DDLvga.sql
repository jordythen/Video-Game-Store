drop table CUSTOMER cascade constraints;
drop table CUSTOMER_GAME cascade constraints;
drop table CUSTOMER_GAMEORDER cascade constraints;
drop table DEVELOPER cascade constraints;
drop table DEVELOPER_GAME cascade constraints;
drop table EMPLOYEE cascade constraints;
drop table GAME cascade constraints;
drop table GAME_GAMECATEGORY cascade constraints;
drop table GAME_GAMEDETAILS cascade constraints;
drop table GAME_GAMESYSTEM cascade constraints;
drop table ORDERDETAILS cascade constraints;
drop table GAMECATEGORY cascade constraints;
drop table GAMEDETAILS cascade constraints;
drop table GAMESYSTEM cascade constraints;
drop table GAMEORDER cascade constraints;
drop table PUBLISHER cascade constraints;
drop table PUBLISHER_GAME cascade constraints;


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
    status varchar2(255),
    quantity number(10),
    price number(20,2)
    
);

create table GAME_GAMEDETAILS(

    gameID number(10),
    detailID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (detailID) references GAMEDETAILS(id)
    
);

create table EMPLOYEE(
    
    id number(10) primary key,
    username varchar2(255),
    passwd varchar2(255),
    firstName varchar2(255),
    lastName varchar2(255),
    employeeRole varchar2(255)

);

create table CUSTOMER(

    id number(10) primary key,
    
    username varchar2(255),
    passwd varchar2(255),
    firstName varchar2(255),
    lastName varchar2(255),
    money number(20,2)

);

--one to many/many to one
--showing ownership of games
create table CUSTOMER_GAME(

    customerID number(10),
    gameID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (customerID) references CUSTOMER(id)
);

create table GAMEORDER(

    id number(10) primary key,
    tax number(20,2),
    orderTotal number(20,2),
    orderDate timestamp
    
);

--one to many
create table CUSTOMER_GAMEORDER(

    customerID number(10),
    orderID number(10),
    
    
    foreign key (customerID) references CUSTOMER(id),
    foreign key (orderID) references GAMEORDER(id)

);

create table ORDERDETAILS(

    id number(10) primary key,
    gameID number(10),
    orderID number(10),
    quantity number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (orderID) references GAMEORDER(id)
);

drop sequence game_seq;
drop sequence gamesystem_seq;
drop sequence developer_seq;
drop sequence publisher_seq;
drop sequence category_seq;
drop sequence gamedetails_seq;
drop sequence employee_seq;
drop sequence customer_seq;
drop sequence order_seq;
drop sequence orderdetails_seq;


create sequence game_seq;
create sequence gamesystem_seq;
create sequence developer_seq;
create sequence publisher_seq;
create sequence category_seq;
create sequence gamedetails_seq;
create sequence employee_seq;
create sequence customer_seq;
create sequence order_seq;
create sequence orderdetails_seq;











