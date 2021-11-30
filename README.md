# Shopping cart - Sales Tax Report

This is a spring boot application that is used to solve the tax sales problem occured on shopping cart.

## Features

- The sales tax will be calculated with the input request .
- Currenlty two sales tax is implemented , basic and import tax.
## Tech

Please find the Technology used.

- [Java] - 1.8
- [Junit] - 4.7
- [lombock] 

## Build
Install the dependencies and devDependencies and start the server.

Clone the project from below location.
```sh
https://github.com/shuaibabdulla/shopping.git
```

```sh
mvn clean install
```
## Application Start

Application can be started once the build is done ,default port is 8080.

Once the application is started it will ask three option to choose as below.
Enter fileName : Options is 1 . input1.txt 2. input2.txt 3. input3.txt 
once you enter the filename corresponding file data will be processsed. 

## Docker
I have pushed the same as docker also as i have little exposure fpr docker with my local test purpose.

```sh
docker pull shuaibabdulla40/shoppingcart:version1
```
Once done, run the Docker image and map the port to whatever you wish on
your host. In this example, we simply map port 8000 of the host to
port 8080 of the Docker (or whatever port was exposed in the Dockerfile):




