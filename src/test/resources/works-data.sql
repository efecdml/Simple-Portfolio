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