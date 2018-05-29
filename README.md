# Project to read files and

Salesman data
001çCPFçNameçSalary
Read File type frist token '001'

Salesman has
cpf name salary

Customer data
002çCNPJçNameçBusinessArea
Read File type frist token '002'

Customer has
cnpj name business

Sales data
003çSaleIDç[ItemID-ItemQuantity-ItemPrice]çSalesmanname
Read File type frist token '003'

Sales has id salesmane and list of itens second token

[ItemID-ItemQuantity-ItemPrice] second toke of sales
item has id, quantity, price


This is a Maven project.

Use Maven to create file jar
mvn package

to execute use the comamnd line
java -jar ilegra-1.0-SNAPSHOT.jar

you can modify the field delimiter
if you do not put will be assigned any of them will be assigned their standards as "; , -" respectively

frist delimiter for field
second delimiter for itens of sale
third delimiter for attributes of a sale

java -jar ilegra-1.0-SNAPSHOT.jar ç , -


