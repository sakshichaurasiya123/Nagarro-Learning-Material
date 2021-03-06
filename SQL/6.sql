/*Write a trigger for the Product table to ensure the list price can never be
raised more than 15 Percent in a single change. Modify the above trigger to 
execute its check code only if the ListPrice column is   updated (Use AdventureWorks Database).
*/

USE [AdventureWorks2008R2]
GO
/****** Object:  Trigger [Production].[CheckListPrice]    Script Date: 4/28/2021 10:13:22 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 ALTER TRIGGER [Production].[CheckListPrice]
 ON [Production].[Product] 

 INSTEAD OF UPDATE 

 AS BEGIN 

 IF EXISTS 

(SELECT * 

 FROM Production.Product as p 

 INNER JOIN inserted AS I 

 ON p.ProductID = I.ProductID 

 WHERE I.ListPrice > (p.ListPrice + (0.15*p.ListPrice)) 

 ) 

  BEGIN  

PRINT 'CANNOT INCREASE LIST PRICE' 

  END 

  ELSE 

BEGIN  

UPDATE Production.Product 

SET ListPrice = inserted.ListPrice 

FROM inserted 

WHERE Product.ProductID = inserted.ProductID 
END 
END 

