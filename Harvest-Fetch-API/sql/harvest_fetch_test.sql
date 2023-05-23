drop database if exists harvest_fetch_test;
create database harvest_fetch_test;
use harvest_fetch_test;

create table app_user_info (
	user_id int primary key auto_increment,
	first_name varchar(25) not null,
    last_name varchar(25) not null,
    street_address varchar(255) not null,
    zip_code varchar(10) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    email varchar(255) not null unique,
    phone varchar(15) not null,
    photo_url varchar(1026) default "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdbAPdbKWCW8JZRhrUGNhu0iPGABj_qkZEsxazxMk&s"
);

create table app_user (
	user_id int primary key,
    user_name varchar(255) not null unique,
    password_hash varchar(1024) not null,
    constraint fk_app_user_info
        foreign key (user_id)
        references app_user_info(user_id)
);

create table farmer (
	farmer_id int primary key auto_increment,
    farm_name varchar(50) not null,
    farm_photo_url varchar(450) DEFAULT "https://images.pexels.com/photos/265216/pexels-photo-265216.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
    details varchar(1024) not null,
    user_id int not null unique,
    constraint fk_farmer_id
		foreign key (user_id)
        references app_user(user_id)
);

create table product (
	product_id int primary key auto_increment,
    product_name varchar(50) not null,
    picture_url varchar(500) default "https://images.pexels.com/photos/616401/pexels-photo-616401.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
);

create table farmer_product (
	farmer_id int not null,
    product_id int not null,
    price decimal(5,2) not null,
    is_active boolean not null default true,
    organic boolean DEFAULT false,
    constraint pk_farmer_product
        primary key (farmer_id, product_id),
    constraint fk_farmer_product_farmer_id
        foreign key (farmer_id)
        references farmer(farmer_id),
    constraint fk_farmer_product_product_id
        foreign key (product_id)
        references product(product_id)
);

create table orders (
	order_id int primary key auto_increment,
    order_date date not null,
    order_total decimal(7,2) not null,
    user_id int not null,
    constraint fk_order_id
		foreign key (user_id)
        references app_user(user_id)
);

create table order_item (
	order_item_id int primary key auto_increment,
    order_id int not null,
    quantity int not null,
    price_code varchar(100),
    farmer_id int not null,
    product_id int not null,
    constraint fk_order_item_id
		foreign key (order_id)
        references orders(order_id),
		foreign key (farmer_id)
        references farmer(farmer_id),
        foreign key (product_id)
        references product(product_id)
);

create table app_authority (
	app_authority_id int primary key auto_increment,
    `name` varchar(25) not null
);

create table app_user_authority (
	app_user_authority_id int primary key auto_increment,
    app_authority_id int not null,
    user_id int not null,
    constraint fk_app_user_authority_id
		foreign key (app_authority_id)
        references app_authority(app_authority_id),
		foreign key (user_id)
        references app_user(user_id)
);

delimiter //
create procedure set_known_good_state()
begin
	set foreign_key_checks = 0;
	truncate table farmer_product;
	truncate table farmer;
	truncate table app_user;
    truncate table orders;
    truncate table product;
    truncate table order_item;
    truncate table app_user_authority;
    truncate table app_authority;
    truncate table app_user;
    truncate table app_user_info;
    set foreign_key_checks = 1;
    
    insert into app_user_info(user_id, first_name, last_name, street_address, zip_code, city, state, email, phone, photo_url) values
		(1, "Jon", "Doe", "1000 South Cooper", "38104", "Memphis", "TN", "test1@testemail.com", "9015551234", "https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=1600"),
        (2, "Joan", "Dangle", "3216 Pershing Ave", "38112", "Memphis", "TN", "test2@testemail.com", "9015552345", "https://images.pexels.com/photos/1482101/pexels-photo-1482101.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
        (3, "Mike", "Hall", "3050 Woodhills Dr", "38128", "Memphis", "TN", "test3@testemail.com", "9015553456", "https://images.pexels.com/photos/14634926/pexels-photo-14634926.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
		(4, "Jon", "Doe", "1000 South Cooper", "38104", "Memphis", "TN", "test4@testemail.com", "9015551234", Null),
        (5, "Joan", "Dangle", "3216 Pershing Ave", "38112", "Memphis", "TN", "test5@testemail.com", "9015552345", "https://images.pexels.com/photos/415829/pexels-photo-415829.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
        (6, "Mike", "Hall", "3050 Woodhills Dr", "38128", "Memphis", "TN", "test6@testemail.com", "9015553456", "https://images.pexels.com/photos/697509/pexels-photo-697509.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
        (7, "Jack", "Parrish", "8605 East Kerrville-Roasemark Road", "38053", "Millington", "TN", "test7@testemail.com", "9015554567", Null),
        (8, "Rick", "Frost", "7422 Ward Road", "38053", "Millington", "TN", "test8@testemail.com", "9015555678", Null),
        (9, "John", "Frost", "7422 Ward Road", "38053", "Millington", "TN", "test9@newemail.com", "9015537678", "https://images.pexels.com/photos/2589653/pexels-photo-2589653.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),
        (10, "Lenore", "Kelly", "100 Sparrows Highway", "08035", "Haddon Heights", "NJ", "test10@newemail.com", "9015537678", Null),
        (11, "Stephanie", "Drake", "aunt's house", "29847", "Atlanta", "GA", "test11@newemail.com", "9015537678", Null),
        (12, "Derrick", "Christian", "somewhere memphis", "38085", "Memphis", "TN", "test12@newemail.com", "9015537678", Null);
    
        -- data
insert into app_authority (`name`) values
	('USER'),
    ('FARMER'),
    ('ADMIN');

insert into app_user (user_id, user_name, password_hash) values
	(1, 'user', '$2a$10$ig47xUq8wWqnVvEsjKa64uRG3y0UmKh3eoCMw3lw3RLudJaqTAvqe'), -- password user
    (2, 'farmer', '$2a$10$Z2j78FcmkWd/5.u97uIeye6ztLLdy1u4nU8VND7Cp.fFxU/vmX15m'), -- password farmer
    (3, 'admin', '$2a$10$dM8YvaD00wfU4g36Fm6p7eRc.RSQFdaRJROiTw0F01VlBYVNWsjFe'), -- password admin
    (4, "testone", "testone"),
	(5, "testtwo", "testone"),
	(6, "testthree", "testone"),
	(7, "testfour", "testone"),
	(8, "testfive", "testone"),
	(9, "testSix", "testone"),
    (10, "testSeven", "testone"),
	(11, "testEight", "testone"),
	(12, "testNine", "testone");
		
	insert into app_user_authority(app_user_authority_id, app_authority_id, user_id) values
		(1,1,1),
        (2,2,2),
        (3,3,3),
        (4,2,4),
        (5,2,5),
        (6,2,6),
        (7,2,7),
        (8,2,8),
        (9,1,9),
        (10,2,10),
        (11,2,11),
        (12,1,12);
        
	insert into farmer(farmer_id, farm_name, farm_photo_url, details, user_id) values
		(1, "MidSouth Farm", "https://images.pexels.com/photos/5848486/pexels-photo-5848486.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Farm in the Mid-South", 4),
        (2, "Bluff City Farm", "https://images.pexels.com/photos/235725/pexels-photo-235725.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Farm in the Mid-South", 5),
        (3, "Memphis Farm", "https://images.pexels.com/photos/1112080/pexels-photo-1112080.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Farm in the Mid-South", 6),
        (4, "Blues Farm", "https://images.pexels.com/photos/195226/pexels-photo-195226.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Farm in the Mid-South", 7),
        (5, "Cotton King Farm","https://images.pexels.com/photos/1486976/pexels-photo-1486976.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Farm in the Mid-South", 8),
        (6, "Corn Field Farm", "https://images.pexels.com/photos/974314/pexels-photo-974314.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1", "Corn Field Farms is a small family farm that specializes in growing corn and other crops using sustainable and organic farming practices. Located in a rural area, the farm is run by a close-knit family who is dedicated to providing high-quality, locally sourced produce to their community. In addition to their corn, the farm also offers a variety of other seasonal fruits and vegetables, as well as free-range eggs from their own chickens. Visitors to Corn Field Farms can take a tour of the fields, participate in hands-on activities, and learn more about the importance of sustainable agriculture.", 2);
        
        
	insert into product(product_id, product_name, picture_url) values
		(1, "Broccoli", "https://images.pexels.com/photos/47347/broccoli-vegetable-food-healthy-47347.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"), -- PriceCode  price_1MsxiQB3x4K2H8lxLE5iWCG7		
        (2, "Asparagus", "https://images.pexels.com/photos/351679/pexels-photo-351679.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),                 -- PriceCode  price_1MtGkYB3x4K2H8lxCJecuxUw		
        (3, "Kale", "https://images.pexels.com/photos/51372/kale-vegetables-brassica-oleracea-var-sabellica-l-51372.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"), -- PriceCode  price_1MtGluB3x4K2H8lxfSTJl3D5
        (4, "Collard Greens", "https://images.pexels.com/photos/750952/pexels-photo-750952.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),  -- PriceCode  price_1MtHFCB3x4K2H8lxi6VApojp		
        (5, "Mustard Greens", "https://images.pexels.com/photos/46164/field-of-rapeseeds-oilseed-rape-blutenmeer-yellow-46164.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"), -- PriceCode  price_1MtHFqB3x4K2H8lxPPgG2g0x		
        (6, "Turnips", "https://images.pexels.com/photos/11663131/pexels-photo-11663131.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),  -- PriceCode  price_1MtHH7B3x4K2H8lxKwc10Gfy		
        (7, "Pumpkin", "https://images.pexels.com/photos/14059490/pexels-photo-14059490.jpeg?auto=compress&cs=tinysrgb&w=600"), -- PriceCode  price_1MtHHYB3x4K2H8lxw6OEx2fI		
        (8, "Milk", "https://images.pexels.com/photos/2198626/pexels-photo-2198626.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"), -- PriceCode  price_1MtHI0B3x4K2H8lxw08uxmn9		
        (9, "Goat Cheese", "https://images.pexels.com/photos/5953698/pexels-photo-5953698.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),  -- PriceCode price_1MtHIZB3x4K2H8lxnnl1GrqW		
        (10, "Flank Steak", "https://images.pexels.com/photos/8477074/pexels-photo-8477074.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"), -- PriceCode price_1MtHIyB3x4K2H8lxEw2z4p1H		 
        (11, "Strawberries","https://cdn.pixabay.com/photo/2016/04/15/08/04/strawberry-1330459__340.jpg"),  -- PriceCode price_1Msy1kB3x4K2H8lxVcahzUc8		
		(12, "Blueberries","https://images.pexels.com/photos/70862/pexels-photo-70862.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),   -- PriceCode 	price_1Msy2AB3x4K2H8lxBTPvK8pv		
        (13, "Oranges","https://images.pexels.com/photos/51958/oranges-fruit-vitamins-healthy-eating-51958.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),       -- PriceCode 	price_1MsxzMB3x4K2H8lxIks0KDK7	
        (14, "Peaches", "https://images.pexels.com/photos/2255965/pexels-photo-2255965.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),      -- PriceCode  price_1Msy3kB3x4K2H8lxcY1RI3ef		
        (15, "Watermelon","https://images.pexels.com/photos/2894205/pexels-photo-2894205.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),    -- PriceCode  price_1Msy6PB3x4K2H8lxeXTQ3ytz		 
        (16, "Granny Smith Apples", "https://images.pexels.com/photos/2042562/pexels-photo-2042562.jpeg?auto=compress&cs=tinysrgb&w=1600"),  -- PriceCode   price_1MsxxyB3x4K2H8lxK9IxgolR		
        (17, "Heirloom Tomatoes", "https://images.pexels.com/photos/533280/pexels-photo-533280.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),    -- PriceCode   price_1MsxuuB3x4K2H8lxKOIehrX5		
        (18, "Yellow Onion","https://images.pexels.com/photos/4197447/pexels-photo-4197447.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),          -- PriceCode   price_1MsxtCB3x4K2H8lxJgMpMhLq		
        (19, "Spinach", "https://images.pexels.com/photos/2325843/pexels-photo-2325843.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),              -- PriceCode   price_1MsxnSB3x4K2H8lxg2hZwpBN		
        (20, "Plums", "https://images.pexels.com/photos/248440/pexels-photo-248440.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"),                -- PriceCode   price_1Msy4EB3x4K2H8lx6csrJ9GJ	
        (21, "Corn", "https://cdn.pixabay.com/photo/2015/05/26/17/42/pop-corn-785074__340.jpg");
        
	insert into farmer_product(farmer_id, product_id, price, is_active, organic) values
		(1, 1, 1.99, true, true),
        (1, 3, 3.99, true, true),
        (1, 4, 1.75, true, true),
        (2, 1, 1.99, true, true),
        (2, 5, 1.75, true, true),
        (3, 2, 2.99, true, true),
        (3, 8, 3.87, true, true),
        (3, 9, 9.85, true, true),
        (4, 2, 2.99, true, true),
        (4, 6, 2.18, true, true),
        (4, 7, 2.35, true, true),
        (5, 4, 1.75, true, true),
        (5, 10, 11.88, true, true),
        (6, 17, 2.99, true, true),
        (6, 18, 1.25, true, true),
        (6, 12, 3.85, true, true),
        (6, 19, 1.65, true, true),
        (1, 11, 3.99, true, true),
        (2, 13, 1.25, true, true),
        (3, 14, 1.45, true, true),
        (4, 15, 3.99, true, true),
        (5, 16, 0.75, true, true),
        (6, 20, 1.65, true, true),
        (6, 21, 1.25, true, true);

	
    insert into orders (order_id, order_date, order_total, user_id) values
		(1, '2023-03-29', 4.16, 9),
        (2, '2023-03-15', 57.29, 10),
        (3, '2023-03-14', 2.18, 11),
        (4, '2023-03-14', 15.89, 12),
        (5, '2023-01-18', 5.91, 9);
	
    insert into order_item (order_item_id, order_id, quantity, price_code, farmer_id, product_id) values
		(1, 1, 2, "price_1MtGluB3x4K2H8lxfSTJl3D5", 1, 3),
        (2, 1, 1, "price_1MtGkYB3x4K2H8lxCJecuxUw", 4, 2),
        (3, 2, 3, "price_1MtHFCB3x4K2H8lxi6VApojp", 5, 4),
        (4, 2, 2, "price_1MtHH7B3x4K2H8lxKwc10Gfy", 4, 6),
        (5, 2, 4, "price_1MtHIyB3x4K2H8lxEw2z4p1H", 5, 10),
        (6, 3, 1, "price_1MtHFqB3x4K2H8lxPPgG2g0x", 4, 5),
        (7, 4, 3, "price_1MtHI0B3x4K2H8lxw08uxmn9", 3, 8),
        (8, 4, 2, "price_1MtHHYB3x4K2H8lxw6OEx2fI", 2, 7),
        (9, 5, 3, "price_1MsxiQB3x4K2H8lxLE5iWCG7", 1, 1);
        
        
        
end //
delimiter ;

set sql_safe_updates = 0;
call set_known_good_state();
set sql_safe_updates = 1;

select * from app_user;

select * from app_authority;

select * from farmer;

select * from product;

select * from order_item;

select
	farmer.farmer_id,
    farmer.farm_name,
    product.product_id,
    product.product_name,
    farmer_product.price,
    farmer_product.organic
from farmer_product
left outer join farmer on farmer_product.farmer_id = farmer.farmer_id
right outer join product on farmer_product.product_id = product.product_id
order by farmer.farmer_id;

        
        
        
        