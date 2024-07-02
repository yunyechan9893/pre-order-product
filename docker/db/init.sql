USE msa;

CREATE TABLE `catalogue` (
    `id`	        bigint	NOT NULL auto_increment PRIMARY KEY,
    `name`	        varchar(255)	NULL,
    `price`	        int	NULL,
    `category`	    varchar(255)	NULL,
    `delivery_fee`   int NULL,
    `created_at`	datetime	NOT NULL DEFAULT NOW(),
    `modified_at`	datetime	NOT NULL DEFAULT NOW()
);

CREATE TABLE `image` (
    `id`	        bigint	NOT NULL auto_increment PRIMARY KEY,
    `type_id`	    bigint	NULL,
    `type`	        varchar(255)	NULL,
    `url`	        varchar(4048)	NULL,
    `created_at`	datetime	NOT NULL DEFAULT NOW(),
    `modified_at`	datetime	NOT NULL DEFAULT NOW()
);

CREATE TABLE `product` (
    `id`	        bigint	NOT NULL auto_increment PRIMARY KEY,
    `catalogue_id`	    bigint	NOT NULL,
    `name`	        varchar(255)	NULL,
    `price`	        int	NULL,
    `stock`	        int	NULL,
    `created_at`	datetime	NOT NULL DEFAULT NOW(),
    `modified_at`	datetime	NOT NULL DEFAULT NOW(),
    FOREIGN KEY (`catalogue_id`) REFERENCES `catalogue`(`id`)
);
