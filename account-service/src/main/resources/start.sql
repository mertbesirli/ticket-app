CREATE KEYSPACE springcloud
    WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

use springcloud;


CREATE TABLE accounts(
                         acc_id text PRIMARY KEY,
                         uname text,
                         name text,
                         surname text,
                         email text,
                         birth_date date,
                         pwd text,
                         created_At date,
                         is_active boolean
);

select * from accounts