drop sequence seq_insta_replys;
drop sequence seq_insta_images;
drop table insta_locations;
drop table mango_stores;
drop table mango_days;
drop table mango_times;
drop table follows;
drop table likes;
drop table insta_replys;
drop table insta_images;
drop table recommend;
drop table users;
drop table stores;
--drop table stores cascade constraints;
--drop table users cascade constraints;

create table users(
    user_email              varchar2(50)            primary key
    , user_pw               varchar2(200)           not null
    , user_name             varchar2(30)            not null
    , user_type             varchar2(10)
    , user_profile          varchar2(1000)
    , user_profileId        varchar2(50)
    , constraint unique_users unique(user_name)
);

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
    , constraint fk1_follows foreign key (user_email) references users(user_email) on delete cascade
);

create table likes (
    user_email 	            varchar2(50)            not null
    , store_no 		        varchar2(200)           not null
    , likes_indate          date                    default sysdate
    , constraint pk_likes primary key (user_email, store_no)
    , constraint fk1_likes foreign key (user_email) references users(user_email) on delete cascade
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
,constraint fk1_insta_replys foreign key (store_no) references stores(store_no)
,constraint fk2_insta_replys foreign key (user_email) references users(user_email) on delete cascade
);

create table mango_days (
    store_no        varchar2(200)       primary key
    , mango_sun     char(1)
    , mango_mon     char(1)
    , mango_tue     char(1)
    , mango_wed     char(1)
    , mango_thu     char(1)
    , mango_fri     char(1)
    , mango_sat     char(1)
    , mango_indate  date                default sysdate
    , constraint fk1_mango_days foreign key (store_no) references stores(store_no)
);

create table mango_times (
     store_no        varchar2(200)       primary key
    , mango_start    varchar2(20)
    , mango_end      varchar2(20)
    , mango_indate   date                default sysdate
    , constraint fk1_mango_times foreign key (store_no) references stores(store_no)
);

create table insta_images (
    image_no        number              primary key
    , store_no      varchar2(200)       
    , image_type    varchar2(50)        not null
    , image_url     varchar2(1000)
    , image_like    number
    , image_indate  date                default sysdate
    , constraint fk1_insta_images foreign key (store_no) references stores(store_no)
);

create sequence seq_insta_images;

create table recommend (
    store_adr               varchar2(300)   not null
    , store_cate1           varchar2(200)   not null
    , recommend_indate      date            default sysdate
    , constraint pk_recommend primary key (store_adr, store_cate1)
);

commit;

select count(*) from stores;
select count(*) from mango_stores;
select count(*) from mango_days;
select count(*) from mango_times;
