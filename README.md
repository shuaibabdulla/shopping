# Shopping cart - Sales Tax Report

The spring boot application used here is used to solve the tax sales problem on the shopping cart.

# Business Context
Basic sales tax is applicable at a rate of 10% on all goods, except books, food and medical products which are exempted. Import duty is an additional
sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
When I purchase items, I receive a receipt that lists the name of all the items, respective quantity and their price (including tax), finishing with the total cost
of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (n*p/100
rounded up to the nearest 0.05) amount of sales tax.

## Features

The sales tax will be calculated based on the input request.
- Two sales taxes are currently implemented, a basic and import tax.
## Tech

Please find the Technology used.

- [Java] - 1.8
- [Junit] - 4.7
- [lombock] 

## Build

Clone the project from the below location.
```sh
https://github.com/shuaibabdulla/shopping.git
```

```sh
mvn clean install
```
## Application Start

There are two options for starting the application.

1. Run as spring boot application .

      * Run the application from the command line as follows. The application will start on port 8080 by default.

```sh
java -jar target/shoppingcart-0.0.1-SNAPSHOT.jar
```

2. Docker images are also available for the application.

    Use the following command.
    
```sh
docker run -it shuaibabdulla40/shoppingcart:1.0.1.
```
    
When the application is started, it will provide three options to choose from.
Enter fileName : Options is 1 . input1.txt 2. input2.txt 3. input3.txt 

for example
input1.txt

The request will be executed based on the input in the file input1.txt.

Console log sample

```sh
2021-12-01 09:30:53.431  INFO 9139 --- [           main] c.z.s.ShoppingCartApplication            : No active profile set, falling back to default profiles: default
Enter fileName : Options is 1 . input1.txt 2. input2.txt 3. input3.txt 
file name  is: input1.txt
Input
1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85
OUTPUT
1 book at 12.49
1 music CD at 16.49
1 chocolate bar at 0.85
Sales Tax : 1.50
Total : 29.83


## Deisgn and Assumptions

https://github.com/shuaibabdulla/shopping/wiki/Sales-Tax---Design-and-Assumptions
