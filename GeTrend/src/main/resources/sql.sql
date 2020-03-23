drop table insta_users;
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
    , location_x number
    , location_y number
    , indate date default sysdate
    , constraint fk1_insta_locations foreign key (store_no) references stores(store_no)
);

create table insta_users (
    insta_id varchar2(200) primary key
    , store_no varchar2(200)
    , profile_pic_url varchar2(2000)
    , indate date default sysdate
    , constraint fk1_insta_users foreign key (store_no) references stores(store_no)
);

select * from users;
select * from stores;
select * from insta_users;
select * from insta_locations;

commit;