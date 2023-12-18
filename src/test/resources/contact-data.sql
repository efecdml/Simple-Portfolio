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