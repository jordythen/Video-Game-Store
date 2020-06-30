create table GAME (
    
    id number(10) primary key,
    name varchar2(255),
    dateReleased timestamp,
    playerLimit varchar2(100)
    
);

create table GAMESYSTEM(
    
    id number(10) primary key,
    name varchar2(255)

);

--one to many deals with uniqueness
--many to many 
create table GAME_GAMESYSTEM(

    id number(12),
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
    
    id number(10) primary key,
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
    
    id number(10) primary key,
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

    id number(10) primary key,
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

    id number(10) primary key,
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

    id number(12) primary key,
    customerID number(10),
    gameID number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (customerID) references CUSTOMER(id)
);

create table GORDER(

    id number(10) primary key,
    tax number(20,2),
    orderTotal number(20,2),
    orderDate timestamp
    
);

--one to many
create table CUSTOMER_GORDER(

    id number(10) primary key,
    customerID number(10),
    orderID number(10),
    
    
    foreign key (customerID) references CUSTOMER(id),
    foreign key (orderID) references GORDER(id)

);

create table GAME_GORDER(

    id number(10) primary key,
    gameID number(10),
    orderID number(10),
    quantity number(10),
    
    foreign key (gameID) references GAME(id),
    foreign key (orderID) references GORDER(id)
);




