create table users(
    -> user_id INT AUTO_INCREMENT,
    -> user_name VARCHAR(100) NOT NULL,
    -> user_password VARCHAR(40) NOT NULL,
    -> user_admin_flag INT,
    -> primary key(user_id)
    -> );
create table patient(
    -> patient_id INT AUTO_INCREMENT,
    -> patient_name VARCHAR(100),
    -> patient_BT_address VARCHAR(100) NOT NULL,
    -> patient_sound_name VARCHAR(100) NOT NULL,
    -> primary key(patient_id)
    -> );
create table speakers(
    -> speaker_id INT AUTO_INCREMENT,
    -> speaker_address VARCHAR(100) NOT NULL, 
    -> primary key(speaker_id)
    -> );
create table familiar_patient(
    -> user_id INT,
    -> user_name VARCHAR(100),
    -> patient_id INT,
    -> patient_name varchar(100),
    -> sound varchar(100)
    -> );
