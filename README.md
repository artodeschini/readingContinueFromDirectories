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

Notes

1. if you do not have these directories above, the program will create them, but you must have permission to create these directories.

2 I was left with some doubts regarding the text, so I will leave as an observation
2.1 - You must build a data analysis system 100% coded in any of the above languages.
I understood that it was not to use any ready tool an ESB etc ...
So I developed a 100% system using the Java language I chose.

2.2 As long as your code is written in one of the above languages, you are free to build whatever kind of application you feel is suitable for the job.
So I understood that no form of data storage that was SQL or NoSQL should not be used.
If my understanding was wrong at this point I believe that the use of MongoDB or Redis should be the most suitable solution to perform the processing. As I described the Modular project and in a way that it can be extensible, in this context the addition of a Repository would not be difficult. It was not used by understanding 2.1 and 2.2.

3. The form of execution
3.1 Note for some reason the character 'ç' was not recognized by my system as a token I used the ';' with default in their replacement in the test files, but if they wish to change the token can be passed as the first parameter when running the application. As explained above.



