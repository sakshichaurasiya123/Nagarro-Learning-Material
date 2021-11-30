/* Write a Procedure supplying name information from the Person.Person table andaccepting 
a filter for the first name. Alter the above Store Procedure to supply Default Values if 
user does not enter any value.( Use AdventureWorks)
*/
-- For Create
CREATE PROCEDURE Name_Information @NAME NVARCHAR(50)
as
SELECT * FROM Person.Person as P where FirstName=@NAME;

-- For Alter
ALTER PROCEDURE Name_Information @NAME VARCHAR(50)=NULL
as 
IF @NAME IS NOT NULL 
select * from Person.Person
where FirstName=@NAME
else
select * from Person.Person
where FirstName='Sakshi';
Go
--Fo Executing
EXEC Name_Information @NAME='Ken'