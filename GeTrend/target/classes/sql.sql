drop sequence seq_insta_locations_info;
drop sequence seq_insta_users_info;
drop table insta_users_info;
drop table insta_users;
drop table insta_locations_info;
drop table insta_locations;
drop table users;
drop table stores;

create table users(
    user_id varchar2(30) primary key,
    user_pw varchar2(50) not null,
    user_name varchar2(30) not null,
    user_email varchar2(50) not null,
    user_kakaoId varchar2(50),
    user_facebookId varchar2(50),
    user_naverId varchar2(50) 
);

-- sqlldr userid=hr/hr control='C:\Users\user\Desktop\stores_control.ctl'
create table stores(
    store_no varchar2(200) primary key,
    store_name varchar2(200),
    store_name2 varchar2(200),
    store_cate1 varchar2(200),
    store_cate2 varchar2(200),
    store_cate3 varchar2(200),
    store_dem varchar(200),
    store_adr1 varchar(300),
    store_adr2 varchar(300),
    store_x number,
    store_y number
);

create table insta_locations (
    location_id varchar2(200) primary key
    , store_no varchar2(200)
    , indate date default sysdate
    , constraint fk1_insta_locations foreign key (store_no) references stores(store_no)
);

create table insta_locations_info (
    location_info_no number primary key
    , store_no varchar2(200)
    , location_id varchar2(200)
    , location_x number
    , location_y number
    , constraint fk1_insta_locations_info foreign key (store_no) references stores(store_no)
    , constraint fk2_insta_locations_info foreign key (location_id) references insta_locations(location_id)
);

create table insta_users (
    insta_id varchar2(200) primary key
    , store_no varchar2(200)
    , indate date default sysdate
    , constraint fk1_insta_users foreign key (store_no) references stores(store_no)
);

create table insta_users_info (
    user_info_no number primary key
    , store_no varchar2(200)
    , profile_pic_url varchar2(2000)
    , insta_id varchar2(200)
    , constraint fk1_insta_users_info foreign key (store_no) references stores(store_no)
    , constraint fk2_insta_users_info foreign key (insta_id) references insta_users(insta_id)
);

create sequence seq_insta_locations_info;
create sequence seq_insta_users_info;

select * from users;
select * from stores;
select * from insta_users_info;
select * from insta_users;
select * from insta_locations_info;
select * from insta_locations;

commit;