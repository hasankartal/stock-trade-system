# Foobar
Payday bank is implementing brand new Trading solution.
The customer’s now should be able to purchase stocks from partner brokers through their bank account
## Installation
Use the Java IDE platform.

## Usage
It can be found documentation in this address.
http://localhost:8082/v2/api-docs \
http://localhost:8082/swagger-ui/index.html

You should follow these orders.
Before you load money or place an order,you have to login. Services need customer id.
* It can be registered in this address.After you register, you will get e-mail for confirmation your account.
http://localhost:8082/register.html

* It can be signed in this address.
http://localhost:8082/login.html

* Stocks can be controlled in this address.
http://localhost:8082/stocks.html

* It can be loaded money
http://localhost:8082/depositCash.html

* It can be placed an order. When your order is filled, you will get e-mail.
http://localhost:8082/placeOrder.html


##Need To Know
Real stock exchange api : https://api.twelvedata.com/stocks
When market is closed, this api doesn't send a price. 
Also some stocks are found on the stock list but it can't be bought or sold.  
Because of this situation, for some stocks price are generated random.