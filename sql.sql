-- 해당 스키마(데이터베이스)를 사용하겠다.
-- 데이터 베이스
-- : 데이터베이스 객체를 사용할 수 있는 공간
-- : 객체의 종류: 테이블, 뷰, 인덱스, 저장 프로시저
/*
데이터베이스 엔진: 스키마
데이터베이스(스키마) : myapp
Create SCHEMA myapp;
Create DATABASE myapp;
MySQL에서는 스키마와 데이터베이스가 동일한 개념이다.
*/
use myapp;
-- 테이블: 데이터를 저장할 수 있는 기본적인 공간
/* create table 테이블(
   컬럼명 데이터타입 제약조건,
   ....,
   추가적인 제약조건(constraint)
)
*/
/*
 DDL(data defination language)
 create ...
 SQL(Structured Query Language): 시퀄
 SEQUEL(... english)
*/
-- create table contact (
-- -- varchar: variable charactor
-- -- 가변문자열, 255byte까지 넣을 수 있음.
-- email varchar(255) not null, image varchar(255), name varchar(255) not null, phone varchar(255) not null, primary key (email)) engine=InnoDB;
-- select length('홍'); -- 3byte
-- select length('a'); -- 1byte
-- -- insert into 테이블 value(값 목록...);
-- -- 값 목록은 순서를 잘 맞춰야 함.
-- insert into contact
-- value("hong@gmail.com", null, "홍길동", "010-1234-5678");
-- -- 1 row(s) affected(1 행이 영향을 받았다.)
-- -- 데이터 1건을 row(행) 또는 record(레코드)
-- -- 데이터에 대한 속성을 column(열)또는 field(필드)
-- -- insert into 테이블(칼럼명목록...) values (값목록...)
-- -- insert into contact(name, phone, email, image)
-- -- value("John doe", "010-0987-6543", "john@naver.com", null);
-- insert into contact(name, phone, email, image)
-- value("John doe", "010-0987-6543", "john@naver.com", null);
/* 기존에 있는 email로 insert
Error Code: 1062. Duplicate entry 'john@naver.com' for key 'contact.PRIMARY'	0.000 sec
*/
/*
Primary Key: 제약조건:
1. 테이블 내의 데이터 중에서 같은 값이 중복이 있으면 안됨.
2. null 값이 될 수 없음.
*/
-- 목록 조회
select * from contact;
-- 특정 칼럼으로 정렬하여 조회
-- asc(기본값): 순정렬, desc: 역정렬
select * from contact order by name;
-- 데이터베이스의 PK(index, clustered)
-- clustered index에 맞게 데이터가 정렬 되어 있음.
-- index(binaray tree)구조이고, 데이터(linked list) 구조이다.
-- index(목차) 트리를 탐색하여 데이터까지 찾아감.
-- PK 값으로 1건만 찾아옴.CREATE TABLE `contact` (


-- where 조건식
-- where 컬럼명 = '컬럼값'
-- select * from contact where email = 'hong@gmail.com';
-- select * from contact;
-- -- 조건에 맞는 레코드 삭제
-- -- where 절의 조건식에는 PK컬럼 기준으로 나오는게 좋음
-- -- 실수로 불필요한 레코드가 지워지는 것을 방지할 수 있다. (아래아래 줄)
-- delete from contact where email='john@naver.com';
-- -- delete from contact where name='John doe';
-- -- 조건에 맞는 대상 사람들을 지움 ↓
-- delete from contact where email in(
-- select email from contact where name > '강');
-- -- DML(Data Mainpulation(조작) Language): insert, delete
-- -- 테이블의 데이터 전체 삭제
-- -- 테이블 구조를 재생성(DDL): truncate
-- -- truncate는 transaction 로그를 쌓지 않음(복구 불가)
-- truncate table contact;


create table post (
no bigint not null auto_increment,
 content varchar(255),
 creator_name varchar(255),
 creator_time bigint not null,
 title varchar(255),
 primary key (no)
 ) engine=InnoDB;
 
insert into post (creator_time, title) value(1, "제목1");

insert into post (creator_time, title) value(2, "제목2");
delete from post where no = 2;
insert into post (creator_time, title) value(2, "제목2");
insert into post (creator_time, title) value(3, "제목3");
SELECT * FROM post;

truncate table contact;
insert into post (creator_time, title) value(1, "제목1");
insert into post (creator_time, title) value(2, "제목2");

insert into post(creator_name, title, content, creator_time) value("길동","title","내용입니다.",13);
select * from contact;
select * from post where creator_name = "길동";


select * from contact order by name asc;

select * from contact order by name asc;
select * from post;CREATE TABLE `contact` (
  `email` varchar(255) NOT NULL,
  `image` longtext,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
truncate table contact;

-- batch insert
INSERT INTO your_table_name (email, name, phone)
VALUES
  ('email1@example.com', 'Name1', '010-1111-1111'),
  ('email2@example.com', 'Name2', '010-2222-2222'),
  ('email3@example.com', 'Name3', '010-3333-3333'),
  ('email4@example.com', 'Name4', '010-4444-4444'),
  ('email5@example.com', 'Name5', '010-5555-5555'),
  ('email6@example.com', 'Name6', '010-6666-6666'),
  ('email7@example.com', 'Name7', '010-7777-7777'),
  ('email8@example.com', 'Name8', '010-8888-8888'),
  ('email9@example.com', 'Name9', '010-9999-9999'),
  ('email10@example.com', 'Name10', '010-1010-1010'),
  ('email11@example.com', 'Name11', '010-1111-1111'),
  ('email12@example.com', 'Name12', '010-1212-1212'),
  ('email13@example.com', 'Name13', '010-1313-1313'),
  ('email14@example.com', 'Name14', '010-1414-1414'),
  ('email15@example.com', 'Name15', '010-1515-1515'),
  ('email16@example.com', 'Name16', '010-1616-1616'),
  ('email17@example.com', 'Name17', '010-1717-1717'),
  ('email18@example.com', 'Name18', '010-1818-1818'),
  ('email19@example.com', 'Name19', '010-1919-1919'),
  ('email20@example.com', 'Name20', '010-2020-2020'),
  ('email21@example.com', 'Name21', '010-2121-2121'),
  ('email22@example.com', 'Name22', '010-2222-2222'),
  ('email23@example.com', 'Name23', '010-2323-2323'),
  ('email24@example.com', 'Name24', '010-2424-2424'),
  ('email25@example.com', 'Name25', '010-2525-2525'),
  ('email26@example.com', 'Name26', '010-2626-2626'),
  ('email27@example.com', 'Name27', '010-2727-2727'),
  ('email28@example.com', 'Name28', '010-2828-2828'),
  ('email29@example.com', 'Name29', '010-2929-2929'),
  ('email30@example.com', 'Name30', '010-3030-3030');
  select * from contact;
  
  -- 인덱스 기준 역정렬
  select * FROM contact ORDER BY email DESC;
  
  -- 데이터 조회건수 제한
  select * FROM contact 
  limit 10;
  
  
  -- 데이터 조회건수 제한
  select * FROM contact 
  limit 10 offset 10;  
  

