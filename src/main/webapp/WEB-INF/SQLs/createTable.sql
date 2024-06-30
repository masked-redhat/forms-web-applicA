use testdb;

drop table if exists requests;
drop table if exists lanPortRequests;
drop table if exists projectEmployeeRequests;
drop table if exists vMReallocationRequests;

create table lanPortRequests (
          request_id int auto_increment primary key,
          name varchar(255) not null,
          phone varchar(10) not null,
          unit varchar(255) not null,
          pf_number varchar(22) not null,
          email varchar(255) not null,
          description TEXT not null,
          form_filled_date DATE not null
);

create table projectEmployeeRequests (
          request_id int auto_increment primary key,
          first_name varchar(255) not null,
          middle_name varchar(255),
          last_name varchar(255) not null,
          pf_number varchar(22) not null,
          project_number varchar(255) not null,
          project_investigator_full_name varchar(255) not null,
          project_investigator_address varchar(255) not null,
          account_type ENUM('Web', 'Email', 'Shell') NOT NULL,
          applying_for ENUM('New account', 'Renewal of old account') NOT NULL,
          old_login_id varchar(255),
          old_login_pf_number varchar(255),
          contact_personal varchar(10) not null,
          contact_office varchar(10) not null,
          amount INT not null,
          form_filled_date DATE not null
);

create table vMReallocationRequests (
          request_id int auto_increment primary key,
          name varchar(255) not null,
          pf_number varchar(22) not null,
          department varchar(255) not null,
          email varchar(255) not null,
          phone varchar(14) not null,
          ip_address varchar(15) not null,
          full_domain_name varchar(255) not null,
          services json not null,
          purpose_vm TEXT not null,
          form_filled_date DATE not null
)