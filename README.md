# Project to read files and Analists

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

Or if you prefer to use your IDE the file to run is Run on the package org.todeschni

you can modify the field delimiter
if you do not put will be assigned any of them will be assigned their standards as "; , -" respectively

frist delimiter for field
second delimiter for itens of sale
third delimiter for attributes of a sale

java -jar ilegra-1.0-SNAPSHOT.jar ç , -

this program needs three directories in the user's default directory
%user.home%/data/in
%user.home%/data/out
%user.home%/data/process

Note, if you do not have these directories the program will create them but you must have permission to create these directories.


