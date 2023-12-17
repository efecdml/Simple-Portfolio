insert into locales (id, name)
values (1, 'en');
insert into locales (id, name)
values (2, 'tr');
select setval('locales_seq', 2);

INSERT INTO works_pages (id, image_name, title, text, locale_id)
VALUES (1, 'image2.png', 'title', 'text', 1);
INSERT INTO works_pages (id, image_name, title, text, locale_id)
VALUES (2, 'image2.png', 'baslik', 'metin', 2);
select setval('works_pages_seq', 2);

INSERT INTO works_detailed_card_components (id, image_name, title, text, display_order, works_id, locale_id)
VALUES (1, 'image1.jpg', 'title', 'text', 1, 1, 1);
INSERT INTO works_detailed_card_components (id, image_name, title, text, display_order, works_id, locale_id)
VALUES (2, 'image2.png', 'title two', 'text two', 2, 1, 1);
INSERT INTO works_detailed_card_components (id, image_name, title, text, display_order, works_id, locale_id)
VALUES (3, 'image3.jpg', 'title three', 'text three', 3, 1, 1);
INSERT INTO works_detailed_card_components (id, image_name, title, text, display_order, works_id, locale_id)
VALUES (4, 'image1.jpg', 'baslik', 'metin', 1, 2, 2);
INSERT INTO works_detailed_card_components (id, image_name, title, text, display_order, works_id, locale_id)
VALUES (5, 'image2.png', 'baslik iki', 'metin iki', 2, 2, 2);
INSERT INTO works_detailed_card_components (id, image_name, title, text, display_order, works_id, locale_id)
VALUES (6, 'image3.jpg', 'baslik üc', 'metin üc', 3, 2, 2);
select setval('works_detailed_card_components_seq', 6);