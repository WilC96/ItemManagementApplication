-- INSERT DELIVERIES			
insert into delivery (delivery_id, company_name, description, distance) values (1000, 'Tesco', 'Bakery order', 'LONG');
insert into delivery (delivery_id, company_name, description, distance) values (1001, 'GameStop',  'Games and merch restock', 'OVERSEAS');
insert into delivery (delivery_id, company_name, description, distance) values (1002, 'Japan Centre', 'Stock order', 'OVERSEAS');
insert into delivery (delivery_id, company_name, description, distance) values (1003, 'Recycling Centre', 'Send unwanted trash here', 'LOCAL');

-- INSERT ITEMS	
insert into item (item_id, name, description, delivery_id) values (1, 'PlayStation 5', 'The strongest console', 1001);
insert into item (item_id, name, description, delivery_id) values (2, 'Switch', 'The weakest console', 1001);
insert into item (item_id, name, description, delivery_id) values (3, 'Smash Bros', 'Online or local?', 1001);
insert into item (item_id, name, description, delivery_id) values (4, 'One Punch Man', 'The greatest superhero series', 1002);
insert into item (item_id, name, description, delivery_id) values (5, 'Comic Books', 'Use as fire fuel', 1003);
insert into item (item_id, name, description, delivery_id) values (6, 'Manga', 'Getting popular', 1002);
insert into item (item_id, name, description, delivery_id) values (7, 'Anime blu rays', 'Buy more of these', 1002);
insert into item (item_id, name, description, delivery_id) values (8, 'Bakery goods', 'Butter flavour', 1000);
insert into item (item_id, name, description, delivery_id) values (9, 'Sandwich', 'With crusts', 1000);								