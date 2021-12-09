create database jdbcdemo;
use jdbcdemo;

-- Currency Table
create table undamped_coin(
    wallet_address int not null,
    wallet_balance float default 10000.0,
    PRIMARY KEY (wallet_address)
);

-- Song Table
create table song(
    song_id int not null,
    song_name varchar(50) not null,
    bpm float default 120.0,
    price float default 100.0,
    PRIMARY KEY (song_id)
);

-- Customer Table
create table customer (
    customer_id int not null,
    customer_name varchar(50) not null,
    customer_wallet_address int not null,
    PRIMARY KEY (customer_id),
    FOREIGN KEY (customer_wallet_address) REFERENCES undamped_coin(wallet_address)
);

-- Transactions Table
create table transactions (
    transaction_id int not null,
    transaction_customer_id int not null,
    transaction_song_id int not null,
    PRIMARY KEY (transaction_id),
    FOREIGN KEY (transaction_customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (transaction_song_id) REFERENCES song(song_id)
);