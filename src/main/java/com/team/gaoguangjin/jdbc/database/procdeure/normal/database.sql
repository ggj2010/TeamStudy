--创建测试表
create table gao.tcprocedure(
 id integer primary key,
 name varchar2(10),
 content varchar2(100),
 createdate date 
)
--存储过程
create or replace procedure gao.pcinsert(i integer) as
iContent varchar2(100);
begin
iContent:='内容';
  insert into gao.tcprocedure values(i,'名称',iContent,sysdate);
end;

--删除数据
delete from gao.tcprocedure;




--查询返回单个记录
create or replace procedure gao.pcqueryone(sId       in integer,
                                           oId      out integer,
                                           oContent out varchar,
                                           oDates   out varchar) as
begin
  select t.id, t.content, to_char(createdate, 'yyyy-mm-dd')
    into oId, oContent, oDates
    from gao.tcprocedure t where t.id=sId;
end;


--创建游标
create or replace function gao.pcquerymore(
  iId integer,
   curLists out gao.pkdual.AnyCursor
  ) return integer is
  timess varchar2(100);
  sSql varchar2(200);
  begin
 select sysdate into timess from dual;
 --格式化存储过程日期 不然存储过程默认返回的日期格式为 2015-03-18！,设置别名createdate
 sSql:='select id,name,content,to_char(createdate,''yyyy-MM-dd HH:mm:ss'') createdate from gao.tcprocedure';
 open curLists for sSql;
 return 0;
 end;
  


