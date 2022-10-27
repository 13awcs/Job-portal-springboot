insert into candidate(name,major,dob,phone,email,address,level,category,avatar,link_cv,gender)
values ('nam','cntt','1999-06-06','012312321','add@gmail.com','HN','senior','backend','https://recmiennam.com/wp-content/uploads/2018/03/hinh-anh-su-tu-1.jpg' ||
 '','jhgh','Nam');
insert into candidate(name,major,dob,phone,email,address,level,category,avatar,link_cv,gender)
values ('huy','cntt','2000-06-06','012312321','ahuy@gmail.com','HN','fresher','frontend','adasd','jhgh','Nam');

insert into recruiter(name,dob,address,phone,email,avatar,company_name,username,password)
values ('Tran Vuong Bac','1999-06-02','HN','2342342342','bac@gmail.com','https://recmiennam.com/wp-content/uploads/2018/03/hinh-anh-su-tu-1.jpg','Hybrid','bac','123a');

insert into job(title,category,amount,type,level,deadline,description,company_name,salary,location,status,create_at)
values ('java BE','BE',3,'fulltime','senior','2022-10-10','ada','hybrid',123000,'Hanoi','available','2022-10-05');
insert into job(title,category,amount,type,level,deadline,description,company_name,salary,location,status,create_at)
values ('Android dev','Android',3,'fulltime','senior','2022-10-10','ada','hybrid',123000,'Hanoi','available','2022-10-01');

insert into apply(candidate_id,job_id,status,apply_date)
values (1,1,'accepted','2022-10-05');
insert into apply(candidate_id,job_id,status,apply_date)
values (1,2,'rejected','2022-10-01');
insert into apply(candidate_id,job_id,status,apply_date)
values (2,1,'accepted','2022-09-19');


