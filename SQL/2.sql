--Write separate queries using a join, a subquery, a CTE, and then an EXISTS to list all AdventureWorks customers who have not placed an order.

/* Join*/
select c.CustomerID from Sales.Customer c left Join Sales.SalesOrderHeader s on c.CustomerID=s.CustomerID
where s.SalesOrderID IS NULL

/* SubQuery */
select c.CustomerID from Sales.Customer c where c.CustomerID NOT IN (select CustomerID from Sales.SalesOrderHeader);

/* CTE */
with CustomerNotPlaceOrder
as(
	select CustomerID,SalesOrderID from Sales.SalesOrderHeader
)
select c.CustomerID from Sales.customer c
Left Join CustomerNotPlaceOrder on c.CustomerID = CustomerNotPlaceOrder.CustomerID
where CustomerNotPlaceOrder.SalesOrderID IS NULL;

/* EXISTS */
select c.customerID from Sales.Customer c where NOT EXISTS(select CustomerID from Sales.SalesOrderHeader s
where s.CustomerID=c.CustomerID);