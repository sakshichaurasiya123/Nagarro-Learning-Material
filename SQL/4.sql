/*
Create a function that takes as inputs a SalesOrderID, a Currency Code,
and a date, and returns a table of all the SalesOrderDetail rows for that
Sales Order including Quantity, ProductID, UnitPrice, and the unit price
converted to the target currency based on the end of day rate for the date provided.
Exchange rates can be found in the Sales.CurrencyRate table. ( Use AdventureWorks)
*/
create or Alter Function dbo.SalesOrder(@SalesOrderID INT,@CurrencyCode NCHAR(6), @CurrencyRateDate DATETIME)
returns table as return (
select OrderQty, ProductID,UnitPrice,UnitPrice*EndOfDayRate as 'Converted Price' from Sales.SalesOrderDetail o, 
Sales.CurrencyRate c where SalesOrderID=@SalesOrderID And c.ToCurrencyCode=@CurrencyCode 
And c.CurrencyRateDate=@CurrencyRateDate
)
