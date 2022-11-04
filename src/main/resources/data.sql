insert into candidate(name,major,dob,phone,email,address,level,category,avatar,link_cv,gender)
values ('nam','cntt','1999-06-06','012312321','add@gmail.com','HN','senior','backend','https://recmiennam.com/wp-content/uploads/2018/03/hinh-anh-su-tu-1.jpg' ||
 '','jhgh','Nam');
insert into candidate(name,major,dob,phone,email,address,level,category,avatar,link_cv,gender)
values ('huy','cntt','2000-06-06','012312321','ahuy@gmail.com','HN','fresher','frontend','adasd','jhgh','Nam');
insert into candidate(name,major,dob,phone,email,address,level,category,avatar,link_cv,gender)
values ('bao','cntt','1999-06-06','012312321','bao@gmail.com','HN','fresher','frontend','https://recmiennam.com/wp-content/uploads/2018/03/hinh-anh-su-tu-1.jpg','jhgh','nam');

insert into recruiter(name,dob,address,phone,email,avatar,company_name,username,password)
values ('Vu Xuan Binh','1999-10-25','HN','2342342342','binh@gmail.com','https://scontent.fhan2-5.fna.fbcdn.net/v/t1.6435-9/163990797_1162047084237289_5680994929546241358_n.jpg','Hybrid','binh','123a');

insert into job(title,category,amount,type,level,deadline,description,company_name,salary,location,status,create_at,recruiter_id)
values ('java BE','BE',3,'fulltime','senior','2022-10-10','ada','hybrid',123000,'Hanoi','available','2022-10-05',1);
insert into job(title,category,amount,type,level,deadline,description,company_name,salary,location,status,create_at,recruiter_id)
values ('Android dev','Android',3,'fulltime','senior','2022-10-10','ada','hybrid',123000,'Hanoi','available','2022-10-01',1);

insert into apply(candidate_id,job_id,status,apply_date)
values (1,1,'accepted','2022-10-05');
insert into apply(candidate_id,job_id,status,apply_date)
values (1,2,'rejected','2022-10-01');
insert into apply(candidate_id,job_id,status,apply_date)
values (2,1,'accepted','2022-09-19');
insert into apply(candidate_id,job_id,status,apply_date)
values (3,2,'unread','2022-09-19');


