drop sequence seq_insta_replys;
drop sequence seq_searched_stores;
drop table searched_stores;
drop table insta_locations;
drop table mango_stores;
drop table mango_stores;
drop table follows;
drop table likes;
drop table users;
drop table stores;
--drop table stores cascade constraints;
drop table insta_replys;


create table users(
    user_email              varchar2(50)            primary key
    , user_pw               varchar2(200)           not null
    , user_name             varchar2(30)            not null
    , user_type             varchar2(10)
    , user_profile          varchar2(1000)
);
insert into users(user_email,user_pw,user_name)values('wldus9656@gmail.com','moon','moon');



-- sqlldr userid=hr/hr control='C:\Users\user\Desktop\stores3_control.ctl'
-- sqlplus sys as sysdba
-- alter system set processes=500 scope=spfile;
-- shutdown immediate
-- startup
-- show parameter processes
create table stores(
    store_no                varchar2(200)           primary key
    , store_name            varchar2(200)
    , store_name2           varchar2(200)
    , store_cate1           varchar2(200)
    , store_cate2           varchar2(200)
    , store_adr             varchar2(300)
    , store_adr1            varchar2(300)
    , store_adr2            varchar2(300)
    , store_x               number
    , store_y               number
);

create table insta_locations (
    location_id             varchar2(200)           primary key
    , store_no              varchar2(200)
    , location_indate       date                    default sysdate
    , constraint fk1_insta_locations foreign key (store_no) references stores(store_no)
);

create table searched_stores (
    searched_no             number                  primary key
    , store_name            varchar2(200)
    , searched_indate       date                    default sysdate
);

create sequence seq_searched_stores;

-- sqlldr userid=hr/hr control='C:\Users\user\Desktop\mango_stores_control.ctl'
create table mango_stores (
    store_no                varchar2(200)           primary key
    , mango_tel             varchar2(100)
    , mango_kind            varchar2(100)
    , mango_price           varchar2(100)
    , mango_parking         varchar2(100)
    , mango_time            varchar2(100)
    , mango_break_time      varchar2(100)
    , mango_last_order      varchar2(100)
    , mango_holiday         varchar2(100)
    , mango_indate          date                    default sysdate
    , constraint fk1_mango_stores foreign key (store_no) references stores(store_no)
);

create table follows (
    user_email              varchar2(50)            not null
    , follows_following     varchar2(50)            not null
    , follows_indate        date                    default sysdate
    , constraint pk_follows primary key (user_email, follows_following)
    , constraint fk1_follows foreign key (user_email) references users(user_email)
);

create table likes (
    user_email 	            varchar2(50)            not null
    , store_no 		        varchar2(200)           not null
    , likes_indate          date                    default sysdate
    , constraint pk_likes primary key (user_email, store_no)
    , constraint fk1_likes foreign key (user_email) references users(user_email)
    , constraint fk2_likes foreign key (store_no) references stores(store_no)
);

create sequence seq_insta_replys;

create table insta_replys(
reply_no 		number 	primary key 
,store_no 	varchar2(200)
,user_email	 varchar2(50)
,reply_contents 	varchar2(1000)
,reply_star	 number
,reply_indate	 date default sysdate
,constraint fk_insta_replys foreign key (store_no) references stores(store_no)
);


commit;













--------------------------------------------------------------------------------------------------------------------------------
select * from users;
select * from stores order by store_no asc;
select count(*) from stores;
select * from insta_locations;
select count(*) from insta_locations;
select * from searched_stores;
select * from mango_stores order by store_no asc;
select count(*) from mango_stores; 
select * from follows;
--------------------------------------------------------------------------------------------------------------------------------
select
    s.store_no
    , s.store_name
    , m.mango_tel
    , m.mango_kind
    , m.mango_price
    , m.mango_parking
    , m.mango_time
    , m.mango_break_time
    , m.mango_last_order
    , m.mango_holiday
from
    stores s, mango_stores m
where
    s.store_no = m.store_no
order by s.store_no;
--------------------------------------------------------------------------------------------------------------------------------
select
	store_no
	, store_name
	, store_name2
	, store_cate1
	, store_cate2
	, store_adr
	, store_adr1
	, store_adr2
	, store_x
	, store_y
from
	stores
order by
	store_no asc;
--------------------------------------------------------------------------------------------------------------------------------
select
    s.store_no
from
    insta_locations i, stores s
where
    i.store_no = s.store_no
group by
    s.store_no;
--------------------------------------------------------------------------------------------------------------------------------
select
    s.store_name
from
    stores s, mango_stores m
where
    s.store_no = m.store_no
    and mango_kind is not null;
--------------------------------------------------------------------------------------------------------------------------------
select
    s.store_name
from
    stores s, mango_stores m, insta_locations l
where
    s.store_no = m.store_no
    and s.store_no = l.store_no;
--------------------------------------------------------------------------------------------------------------------------------
select
    s.store_name, s.store_no, m.mango_time, m.mango_break_time
from
    stores s, mango_stores m, insta_locations l
where
    s.store_no = m.store_no
    and s.store_no = l.store_no;
    
select
    s.store_name, s.store_no, m.mango_time, m.mango_break_time, m.mango_holiday
from
    stores s, mango_stores m
where
    s.store_no = m.store_no
    and m.mango_time is not null;
    
select sysdate from dual;
select mango_time from mango_stores;
select to_char(sysdate, 'HH24') from dual;
select to_char(sysdate, 'dy') from dual;
