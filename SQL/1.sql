--1. Display the number of records in the [SalesPerson] table. (Schema(s) involved: Sales)
select count(*) as 'No of Records' from Sales.SalesPerson;

--2. Select both the FirstName and LastName of records from the Person table where the FirstName begins with the letter ‘B’. (Schema(s) involved: Person)
select FirstName, LastName  from Person.Person where FirstName like 'B%' ;

--3. Select a list of FirstName and LastName for employees where Title is one of Design Engineer, Tool Designer or Marketing Assistant. (Schema(s) involved: HumanResources, Person)
select pp.FirstName, pp.LastName from Person.Person as pp inner join HumanResources.Employee  as he on pp.BusinessEntityID=he.BusinessEntityID  where he.JobTitle in ('Design Engineer','Tool Designer','Marketing Assistant');

--4. Display the Name and Color of the Product with the maximum weight. (Schema(s) involved: Production)
	SELECT top 1 name,color
	FROM Production.Product where weight=(select max(weight) from Production.Product); 
	
--5. Display Description and MaxQty fields from the SpecialOffer table. Some of the MaxQty values are NULL, in this case display the value 0.00 instead. (Schema(s) involved: Sales)
select Description,ISNULL(MaxQty,0.00) as "MaxQty" 
from Sales.SpecialOffer
--6. Display the overall Average of the [CurrencyRate].[AverageRate] values for the exchange rate ‘USD’ to ‘GBP’ for the year 2005 i.e. FromCurrencyCode = ‘USD’ and ToCurrencyCode = ‘GBP’. Note: The field [CurrencyRate].[AverageRate] is defined as 'Average exc--hange rate for the day.' (Schema(s) involved: Sales)
select AVG(AverageRate)as 'Overall Average' from Sales.CurrencyRate where FromCurrencyCode='USD' and ToCurrencyCode='GBP'and year(ModifiedDate)=2005;

--7. Display the FirstName and LastName of records from the Person table where FirstName contains the letters ‘ss’. Display an additional column with sequential numbers for each row returned beginning at integer 1. (Schema(s) involved: Person).
select ROW_NUMBER() Over(order by (select 0)) as 'Sequence Number',  FirstName,LastName from Person.Person where FirstName like '%ss%';

/*8. Sales people receive various commission rates that belong to 1 of 4 bands. (Schema(s) involved: Sales)
CommissionPct	Commission Band
0.00	Band 0
Up To 1%	Band 1
Up To 1.5%	Band 2
Greater 1.5%	Band 3

Display the [SalesPersonID] with an additional column entitled ‘Commission Band’ indicating the appropriate band as above.
*/

select BusinessEntityID as 'SalesPersonID' ,CommissionPct,
CASE
	when CommissionPct=0.00 then 'Band 0'
	when(CommissionPct>0.00 and CommissionPct<=1) then 'Band 1'
	when (CommissionPct>1 and CommissionPct<=1.5) then 'Band 2'
	ELSE
		'Band 3'
	End as 'Commission Band'
	from Sales.SalesPerson;
	

--9. Display the managerial hierarchy from Ruth Ellerbrock (person type – EM) up to CEO Ken Sanchez. Hint: use [uspGetEmployeeManagers] (Schema(s) involved: [Person], [HumanResources]) 
Declare @ID int;
select @ID=Person.Person.BusinessEntityID from HumanResources.Employee  
INNER JOIN Person.Person 
on HumanResources.Employee.BusinessEntityID=Person.Person.BusinessEntityID
where person.Person.FirstName='Ruth' AND Person.Person.LastName='Ellerbrock';

EXEC [dbo].[uspGetEmployeeManagers]
@BusinessEntityID=@ID



--10.	Display the ProductId of the product with the largest stock level. Hint: Use the Scalar-valued function [dbo]. [UfnGetStock]. (Schema(s) involved: Production)
select MAX(dbo.ufnGetStock (ProductID)) as 'Largest Level Stock' from Production.Product;
  