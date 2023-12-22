insert into locales (id, name)
values (1, 'en');
insert into locales (id, name)
values (2, 'tr');
select setval('locales_seq', 2);

INSERT INTO contact_pages (id, title, text, email, location, working_days, working_hours, google_maps_coordination,
                           locale_id)
VALUES (1, 'title', 'text', 'email@email.com', 'location', 'monday-friday', '08:00-17:00', 'gmapcoordination', 1);
INSERT INTO contact_pages (id, title, text, email, location, working_days, working_hours, google_maps_coordination,
                           locale_id)
VALUES (2, 'baslik', 'metin', 'email@email.com', 'lokasyon', 'monday-friday', '08:00-17:00', 'gmapcoordination', 2);
select setval('contact_pages_seq', 2);

INSERT INTO contact_simple_card_components (id, image_name, title, text, display_order, contact_id, locale_id)
VALUES (1, 'image1.jpg', 'title', 'text', 1, 1, 1);
INSERT INTO contact_simple_card_components (id, image_name, title, text, display_order, contact_id, locale_id)
VALUES (2, 'image2.jpg', 'title two', 'text two', 2, 1, 1);
INSERT INTO contact_simple_card_components (id, image_name, title, text, display_order, contact_id, locale_id)
VALUES (3, 'image3.jpg', 'title three', 'text three', 3, 1, 1);
INSERT INTO contact_simple_card_components (id, image_name, title, text, display_order, contact_id, locale_id)
VALUES (4, 'image1.jpg', 'baslik', 'metin', 1, 2, 2);
INSERT INTO contact_simple_card_components (id, image_name, title, text, display_order, contact_id, locale_id)
VALUES (5, 'image2.jpg', 'baslik iki', 'metin iki', 2, 2, 2);
INSERT INTO contact_simple_card_components (id, image_name, title, text, display_order, contact_id, locale_id)
VALUES (6, 'image3.jpg', 'baslik üc', 'metin üc', 3, 2, 2);
select setval('contact_simple_card_components_seq', 6);

insert into contact_phone_components (id, tag, number, display_order, contact_id, locale_id)
values (1, 'tag', '123', 1, 1, 1);
insert into contact_phone_components (id, tag, number, display_order, contact_id, locale_id)
values (2, 'etiket', '123', 1, 2, 1);
select setval('contact_phone_components_seq', 2);