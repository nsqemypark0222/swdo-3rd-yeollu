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
    store_no number primary key,
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

commit;

select * from users;
select * from stores;