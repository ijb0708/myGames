create user gameuser identified by gameuser;

grant connect, resource, dba to gameuser;

create table gamescore(
name varchar2(5) not null,
enemies number,
stage number,
score number 
);

commit;