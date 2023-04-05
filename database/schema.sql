use bgg;

create table user (
	user_id varchar(8) not null,
    username varchar(50) not null,
    name varchar(50),
    constraint user_pk primary key(username)
);

create table task (
	task_id int not null auto_increment,
    description varchar(255) not null,
    priority int,
    due_date varchar(20),
    username varchar(50) not null,
    constraint task_pk primary key (task_id),
    constraint user_task_fk foreign key (username) references user (username)
);