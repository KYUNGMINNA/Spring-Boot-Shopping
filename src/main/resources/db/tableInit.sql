SET foreign_key_checks = 0;    # 외래키 체크 설정 해제
drop table if exists Product cascade;
drop table if exists ProductOrder cascade;
SET foreign_key_checks = 1;    # 외래키 체크 설정




create table Product (
                         id integer not null auto_increment,
                         productContent varchar(255) not null,
                         productCount varchar(255) not null,
                         productImage varchar(255) not null,
                         productPrice integer not null,
                         productTitle varchar(255) not null,
                         primary key (id)
);

create table if not exists ProductOrder
(
    id         int auto_increment
    primary key,
    orderCount int not null,
    productId  int null,
    constraint FK3svc5ffdaomqsfl8usmdvf1nq
    foreign key (productId) references shopping.Product (id)
);
