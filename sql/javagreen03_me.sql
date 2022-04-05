create table INTEREST_PART (
PART_CODE INT(98) not null primary key auto_increment,
PART_NAME varchar(10) not null
);
desc INTEREST_PART;
insert into INTEREST_PART values (default, '관광');
insert into INTEREST_PART values (default, '자격증');
insert into INTEREST_PART values (default, '다이소');
/*delete from INTEREST_PART where part_code = '3';*/
select * from INTEREST_PART;

create table RECENT_POPULAR_PROMOTION (
CREATE_DATE datetime not null primary key default now(),
PART_CODE INT(98) not null, 
SUBJECT varchar(100) not null,
ORIGIN varchar(300) not null,
CONTENT text not null, /*text는 oracle CLOB과 같다고 함*/
PICTURE mediumblob not null, 
PICTURE_ORG longblob, /*BFILE일까*/
constraint FK_PART_CODE foreign key (PART_CODE) references INTEREST_PART(PART_CODE)
);
alter table RECENT_POPULAR_PROMOTION modify column CONTENT varchar(4000) not null;/*최대길이 4000*/
desc RECENT_POPULAR_PROMOTION;
insert into RECENT_POPULAR_PROMOTION values (default, 1, '테스트', '출처N', '내용N','test1234567890%%%', null);
update RECENT_POPULAR_PROMOTION set subject = '제목_1', origin = '출처N_1' where create_date = '2022-04-04 13:12:15';
delete from RECENT_POPULAR_PROMOTION where create_date = '2022-04-04 16:57:13';
select * from RECENT_POPULAR_PROMOTION where (create_date >= '2022-01-01' AND create_date <= '2022-04-05');
