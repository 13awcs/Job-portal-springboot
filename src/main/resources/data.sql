insert into candidate(name,major,dob,phone,email,address,c_level,avatar,link_cv,gender)
values ('nam','cntt','1999-06-06','012312321','add@gmail.com','HN','sdfsd','adasd','jhgh','Nam');

insert into recruiter(name,dob,address,phone,email,avatar,company_name,username,password)
values ('bac','1999-06-02','HN','2342342342','bac@gmail.com','asdasd','Hybrid','bac','123a');

insert into job(title,category,amount,type,level,deadline,description,company_name,salary,location,status)
values ('java BE','BE',3,'fulltime','senior','2022-10-10','ada','hybrid',123000,'Hanoi','available');

insert into apply(candidate_id,job_id,status)
values (1,1,'accepted');
