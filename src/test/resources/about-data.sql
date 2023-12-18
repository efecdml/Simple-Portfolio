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