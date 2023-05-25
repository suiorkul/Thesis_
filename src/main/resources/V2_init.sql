insert into users (created,
                   updated,
                   active,
                   email,
                   firstname,
                   lastname,
                   password,
                   role,
                   username)
values ('2023-01-20 14:38:42.000000',
        '2023-01-20 14:38:43.000000',
        true,
        'admin@mail.kg',
        'admin',
        'admin',
        '$2a$12$v8gAMZ2gJzN5k6oWJ2AD8.AaKw7HyN9bpdDtQ.nlzNYr6mZlajMny',
        'ADMIN',
        'admin'),
       ('2023-01-20 14:38:42.000000',
        '2023-01-20 14:38:43.000000',
        true,
        'doctor@mail.kg',
        'Nursultan',
        'Kudaikulov',
        '$2a$12$v8gAMZ2gJzN5k6oWJ2AD8.AaKw7HyN9bpdDtQ.nlzNYr6mZlajMny',
        'DOCTOR',
        'doctor');

insert into departments (name, description) values ('Уролог', 'Уролог');
insert into departments (name, description) values ('Кардиолог', 'Кардиолог');
insert into departments (name, description) values ('Нефролог', 'Нефролог');

INSERT INTO public.patients (created, updated, date_of_birth, email, first_name, last_name, patronymic, phone_number, sex, department)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1990-01-01', 'john@example.com', 'John', 'Doe', 'Smith', '123456789', 'MALE', 1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1995-05-15', 'jane@example.com', 'Jane', 'Smith', 'Doe', '987654321', 'FEMALE', 2),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '1988-12-25', 'alex@example.com', 'Alex', 'Johnson', '', '555555555', 'MALE', 1);

INSERT INTO public.users (created, updated, active, email, firstname, lastname, password, role, username)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'doctor1@example.com', 'John', 'Doe', 'password1', 'DOCTOR', 'johndoe'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'doctor2@example.com', 'Jane', 'Smith', 'password2', 'PATIENT', 'janesmith'),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'doctor3@example.com', 'Alex', 'Johnson', 'password3', 'PATIENT', 'alexjohnson');