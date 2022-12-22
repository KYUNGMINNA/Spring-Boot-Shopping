drop table if exists Product;
create table Product (
                         id integer not null auto_increment,
                         productContent varchar(255) not null,
                         productCount varchar(255) not null,
                         productImage varchar(255) not null,
                         productPrice integer not null,
                         productTitle varchar(255) not null,
                         primary key (id)
)