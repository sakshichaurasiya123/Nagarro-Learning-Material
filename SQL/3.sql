--Show the most recent five orders that were purchased from account numbers that have spent more than $70,000 with AdventureWorks.

select AccountNumber,OrderDate,TotalDue,SalesOrderID  from Sales.SalesOrderHeader where SalesOrderID IN (select top 5
Sales.SalesOrderHeader.SalesOrderID from Sales.SalesOrderHeader where  AccountNumber In(
select AccountNumber from Sales.SalesOrderHeader group by AccountNumber having sum(TotalDue)>70000))
