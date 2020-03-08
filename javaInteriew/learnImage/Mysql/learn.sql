
drop table t1;

create TABLE t1(
a int PRIMARY key,
b int,
c int,
d int,
e varchar(20)
) engine = Innodb;


insert into t1 values(3,5,1,2,'d');
insert into t1 values(6,5,8,2,'d');
insert into t1 values(2,5,5,3,'d');
insert into t1 values(1,6,1,9,'d');
insert into t1 values(5,8,1,7,'d');
insert into t1 values(7,5,6,6,'d');
insert into t1 values(4,5,1,4,'d');
insert into t1 values(9,7,1,6,'d');


-- 优化sql案例之  limit 取第5万条数据后面的5千行
EXPLAIN select * from t1 limit 50000,5000;
-- 优化后 首先找到第50000条数据的前一条 49999，然后再进行limit


EXPLAIN select a from t1 where id >49999 limit 1,5000;
EXPLAIN select a from t1;  -- const ref index  :使用索引执行非常快
EXPLAIN select * from t1 where a > 3;  -- 先找到a=3的 range ref index   :
EXPLAIN select * from t1 where b = 5 and c = 5;  -- 使用索引查询 符合索引查询的最左前缀匹配规则
EXPLAIN select b from t1;  -- const ref index  :使用索引执行非常快

-- 建立索引
create index idx_t1_bcd on t1(b,c,d);
-- 删除索引
alter table `t1` drop index `idx_t1_bcd`;

show index from t1;










