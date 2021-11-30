use AdventureWorks2008R2;
--select LastName,EmailPromotion from Person.Person where EmailPromotion between 0 and 1;

--select EmailPromotion from Person.Person where EmailPromotion>=1 and EmailPromotion<=2;

--select FirstName,MiddleName from Person.Person where middleName is Null;

--select pp.BusinessEntityID, pp1.FirstName, pp.rowguid from Person.Password pp inner join Person.Person pp1 on pp1.BusinessEntityID=pp.BusinessEntityID;
select EmailPromotion, count (*) as Total_count_of_Email_Promotion  from Person.Person group by EmailPromotion;