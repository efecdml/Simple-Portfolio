insert into locales (id, name)
values (1, 'en');
insert into locales (id, name)
values (2, 'tr');
select setval('locales_seq', 2);

INSERT INTO about_pages (id, image_name, title, text, locale_id)
VALUES (1, 'image1.jpg', 'title', 'text', 1);
INSERT INTO about_pages (id, image_name, title, text, locale_id)
VALUES (2, 'image1.jpg', 'baslik', 'metin', 2);
select setval('about_pages_seq', 2);

INSERT INTO about_simple_card_components (id, image_name, title, text, display_order, about_id, locale_id)
VALUES (1, 'image1.jpg', 'title', 'text', 1, 1, 1);
INSERT INTO about_simple_card_components (id, image_name, title, text, display_order, about_id, locale_id)
VALUES (2, 'image2.jpg', 'title two', 'text two', 2, 1, 1);
INSERT INTO about_simple_card_components (id, image_name, title, text, display_order, about_id, locale_id)
VALUES (3, 'image3.jpg', 'title three', 'text three', 3, 1, 1);
INSERT INTO about_simple_card_components (id, image_name, title, text, display_order, about_id, locale_id)
VALUES (4, 'image1.jpg', 'baslik', 'metin', 1, 2, 2);
INSERT INTO about_simple_card_components (id, image_name, title, text, display_order, about_id, locale_id)
VALUES (5, 'image2.jpg', 'baslik iki', 'metin iki', 2, 2, 2);
INSERT INTO about_simple_card_components (id, image_name, title, text, display_order, about_id, locale_id)
VALUES (6, 'image3.jpg', 'baslik üc', 'metin üc', 3, 2, 2);
select setval('about_simple_card_components_seq', 6);