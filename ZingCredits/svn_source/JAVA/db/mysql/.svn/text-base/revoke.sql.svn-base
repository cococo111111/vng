revoke all on zingcredits.* from zingcredits@'10.74.40.31';
revoke all on zingcredits.* from zingcredits@'10.74.40.35';
revoke all on zingcredits.* from zingcredits@'10.30.22.135';
revoke all on zingcredits.* from zingcredits@'10.30.22.153';
revoke all on zingcredits.* from zingcredits@'10.30.22.155';
revoke all on zingcredits.* from zingcredits@'10.30.22.158';

delete from mysql.db where user='zingcredits';
delete from mysql.user where user='zingcredits';

flush privileges;

select user,host from mysql.user;
select user,host,db from mysql.db;
