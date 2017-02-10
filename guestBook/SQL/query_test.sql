--query test

--insert
insert into guestbook  values (seq_guestbook.nextval, '미니언즈', '1234', '벨로~', sysdate);

commit;

--select
select no, name, content, to_char(reg_date, 'yyyy-mm-dd') from guestbook order by reg_date desc;

--delete

delete guestbook where no = 6 and password = '12345';

update guestbook set name = '고고', content = 'qqq'  where no = 6 and password = '1234';