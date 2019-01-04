DROP TABLE IF EXISTS teacher CASCADE;
create table teacher
(
  id        varchar2(255) primary key,
  name      varchar2(255),
  school_id varchar2(255)
);

DROP TABLE IF EXISTS student CASCADE;
create table student
(
  id        varchar2(255) primary key,
  name      varchar2(255),
  class_id  varchar2(255),
  school_id varchar2(255)
);

DROP TABLE IF EXISTS class CASCADE;
create table class
(
  id        varchar2(255) primary key,
  name      varchar2(255),
  school_id varchar2(255)
);

DROP TABLE IF EXISTS school CASCADE;
create table school
(
  id   varchar2(255) primary key,
  name varchar2(255)
);

DROP TABLE IF EXISTS class_teacher CASCADE;
create table class_teacher
(
  teacher_id varchar2(255) not null,
  class_id   varchar2(255) not null
);

