# order_management
Microservices for Order Management
There are 3 projects - Eureka Server, OrderItemService, OrderService.
Steps to run
1) Start Eureka server project
2) Start OrderItemService project
3) Start OrderService project

OrderItem Service
Included one extra field Cost in order item. 
based on product and quantity when orderservice calls item service, total bill will be sent in response
Due to lack of time, Junit test cases arent included. 

Given below are the urls and Json input.

Retrive all items
Protocol - GET
http://localhost:8080/items/

Retrive a specific item: 
Protocol -GET
http://localhost:8080/items/HP01

Create new order item:
Protocol - POST
 input json:
             {"productCode": "LT01",
            "productName": "Logitech Mouse",
            "quantity":10,
            "cost":900}
            

Retrieve cost of product and update quantity based on customer order, to be consumed by order service.
Protocol -  PUT
Success Case - When product and stock is available - http://localhost:8080/items/HP01,DL01
 input json:
 [{"productCode": "HP01",
            "quantity":10}, {"productCode": "DL01",
            "quantity":10}]

Failure Case- When product not available : http://localhost:8080/items/test,HP01 
 input json:
       [{"productCode": "HP01",
            "quantity":10},
            {"productCode": "test",
            "quantity":10}
            ]        

Failure Case- When product not available, but not stock not available : http://localhost:8080/items/HP01,DL01
 input json:
                [      {"productCode": "HP01",
            "quantity":1000}
            ]

json order service
Create Order
Protocol - POST
Success case
{"customerName": "test",
            "addressLine1": "85 ave rd",
            "orderDateStr": "2020-08-20",
            "city": "phoenix",
            "state": "az",
             "zip": "89765",
            "orderItems":[{"productCode": "HP01",
            "quantity":10},
            {"productCode": "DL01",
            "quantity":10}]}

Failure case
{"customerName": "test",
            "addressLine1": "85 ave rd",
            "orderDateStr": "2020-08-20",
            "city": "phoenix",
            "state": "az",
             "zip": "89765",
            "orderItems":[{"productCode": "test",
            "quantity":10},
            {"productCode": "temp",
            "quantity":10}]}

Test Request Params while creating order (Hibernate Validators)
{"customerName": null,
            "addressLine1": null,
            "orderDateStr": "202008-20",
            "city": "phoenix",
            "state": null,
             "zip": "8909878",
            "orderItems":[{"productCode": "HP01",
            "quantity":10},
            {"productCode": "DL01",
            "quantity":900000}]}

View created Customer orders
Protocol GET
Returning response in same entity object, can be customised to have a separate response object

All orders
http://localhost:8090/orders/

View specific Customer order

http://localhost:8090/orders/1
