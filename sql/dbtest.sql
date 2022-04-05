show tables;

create table dbtest (
	idx 		int not null auto_increment primary key,
	name 		varchar(20) not null,
	age 		int default 20,
	gender 	varchar(2) default '남',
	joinday datetime default now()
);

insert into dbtest values (default, '홍길동', 25, default, default);
--date_format(joinday, '%Y-%m-%d')
update dbtest set  age=18, joinday = '2022-03-28' where idx=5;

select * from dbtest where date_format(joinday, '%Y-%m-%d') = '2022-03-30';
select * from dbtest;

select * from dbtest where age like '%1%' order by idx desc;
select * from dbtest order by idx desc;
--select * from dbtest where idx = 


