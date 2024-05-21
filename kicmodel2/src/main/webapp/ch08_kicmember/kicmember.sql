
drop table kicmember;
create table kicmember(
	id varchar2(20) primary key,
	pw varchar2(20),
	name varchar2(20),
	gender varchar2(20),
	tel varchar2(20),
	email varchar2(20),
	picture varchar2(20)
);

create sequence kicseq;

