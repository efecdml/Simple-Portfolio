insert into locales (id, name)
values (1, 'en');
insert into locales (id, name)
values (2, 'tr');
select setval('locales_seq', 2);

insert into home_pages (id, title, text, second_title, second_text, locale_id)
values (1, 'title', 'text', 'second title', 'second text', 1);
insert into home_pages (id, title, text, second_title, second_text, locale_id)
values (2, 'baslik', 'metin', 'ikinci baslik', 'ikinci metin', 2);
select setval('home_pages_seq', 2);

insert into home_carousel_section_components (id, image_name, text, display_order, home_id, locale_id)
values (1, 'image1.jpg', 'text', 1, 1, 1);
insert into home_carousel_section_components (id, image_name, text, display_order, home_id, locale_id)
values (2, 'image2.png', 'text two', 2, 1, 1);
insert into home_carousel_section_components (id, image_name, text, display_order, home_id, locale_id)
values (3, 'image3.jpg', 'text three', 3, 1, 1);
insert into home_carousel_section_components (id, image_name, text, display_order, home_id, locale_id)
values (4, 'image1.jpg', 'metin', 1, 2, 2);
insert into home_carousel_section_components (id, image_name, text, display_order, home_id, locale_id)
values (5, 'image1.jpg', 'metin iki', 2, 2, 2);
insert into home_carousel_section_components (id, image_name, text, display_order, home_id, locale_id)
values (6, 'image1.jpg', 'metin üc', 3, 2, 2);
select setval('home_carousel_section_components_seq', 6);