drop table members;
create table members(
    id varchar(20) primary key ,
    pwd varchar(20),
    role varchar(20)
);

insert into members values ('hello','1234','MEMBER');
insert into members values ('AAA','1234','MEMBER');
insert into members values ('admin','1234','ADMIN');