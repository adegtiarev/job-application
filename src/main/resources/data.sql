insert into jba_companies(id, name) values (1, 'First customer');
insert into jba_companies(id, name) values (2, 'Second customer');

insert into jba_company_fields(id, company_id, name, type, required) values(1, 1, 'Phone', 'TEXT', true);
insert into jba_company_fields(id, company_id, name, type, required) values(2, 1, 'Experience', 'NUMBER', true);
insert into jba_company_fields(id, company_id, name, type, required) values(3, 1, 'Additional info', 'TEXT', false);

insert into jba_company_fields(id, company_id, name, type, required) values(4, 2, 'Salary requirement', 'NUMBER', true);
insert into jba_company_fields(id, company_id, name, type, required) values(5, 2, 'LinkedIn', 'TEXT', true);
insert into jba_company_fields(id, company_id, name, type, required) values(6, 2, 'Cover letter', 'FILE', true);

insert into jba_position(id, description) values (1, 'Java developer');
insert into jba_position(id, description) values (2, 'Android developer');
insert into jba_position(id, description) values (3, 'UI/UX designer');
insert into jba_position(id, description) values (4, 'HR specialist');
