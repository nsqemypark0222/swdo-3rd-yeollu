drop sequence seq_searched_stores;
drop table searched_stores;
drop table insta_locations;
drop table users;
drop table stores;

create table users(
    user_email varchar2(50) primary key
    , user_pw varchar2(200) not null
    , user_name varchar2(30) not null
    , user_type varchar2(10)
    , user_profile varchar2(1000)
);

-- sqlldr userid=hr/hr control='C:\Users\user\Desktop\stores_control.ctl'
-- sqlplus sys as sysdba
-- alter system set processes=500 scope=spfile;
-- shutdown immediate
-- startup
-- show parameter processes
create table stores(
    store_no varchar2(200) primary key
    , store_name varchar2(200)
    , store_name2 varchar2(200)
    , store_cate1 varchar2(200)
    , store_cate2 varchar2(200)
    , store_cate3 varchar2(200)
    , store_dem varchar(200)
    , store_adr1 varchar(300)
    , store_adr2 varchar(300)
    , store_x number
    , store_y number
);

create table insta_locations (
    location_id varchar2(200) primary key
    , store_no varchar2(200)
    , location_indate date default sysdate
    , constraint fk1_insta_locations foreign key (store_no) references stores(store_no)
);

create table searched_stores (
    searched_no number primary key
    , store_name varchar2(200)
    , searched_indate date default sysdate
);


create sequence seq_searched_stores;

commit;













select * from users;
select * from stores;
select * from insta_locations;
select count(*) from insta_locations;
select * from searched_stores;

select
	decode(count(*), 0, 'false', 'true') as result
from
	searched_stores
where
	store_name = '¸ð¶õ½ÄÅ¹'
	and to_char(searched_indate, 'HH24') = to_char(sysdate, 'HH24');

select s.store_no
from insta_locations i, stores s
where i.store_no = s.store_no
group by s.store_no;

select location_id, count(location_id)
from insta_locations
group by location_id
order by count(location_id) desc;

select
	s.store_no, count(s.store_no)
from
	stores s, insta_locations l
where
	s.store_no = l.store_no
group by s.store_no
order by count(s.store_no) desc;
