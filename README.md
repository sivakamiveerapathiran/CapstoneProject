# CapstoneProject

This Project is about creating  e-Commerce Website for the Nursery called
GreenWay nursery.
Here users can browse Nursery Plants, select them into Shopping Cart
and order them online. 

The system uses card payment fields to process payments.
The system handles the shipping using the shipping address provided by the customers.

Admin module is used to handle the Inventory Maintenance in the Application.

It is built using SpringBoot. Following Technologies are used.

Front-End: Html, CSS, JS,ThymeLeaf 
Server-side: Spring Boot
Back-end: MYSQL, Hibernate.
Server: Tomcat
Logging Framework:SLF4J/Logback

Security of this application is handled by Spring Security. 

The following functionalities are available

Module: User 
      Users can register their profile in the system. Email ID is used as the user ID. Email ID can be used to 
login to the system. Users can browse the products without logging in. However, for placing the items in the 
shopping cart and ordering them, user needs to login with their user IDs. This can be accessed by anyone

Module: Product
       This module can be accessed by everyone. This application uses View All Products as the main landing area and main connecting area for the whole application.
Users will be taken to this page once they login, added a specific item to shopping cart and after placing the orders.

Module: Shopping Cart
        Shopping Cart can be accessed only with registered used with USER access. Shopping cart is a module where users can add the products they want to purchase. Once they are done adding the
product they want to purchase, they can either edit or delete the item in their shopping cart. Once everythin
is finalized, they can order their products.

Module: Order
        Order can be accessed only with registered used with USER access. Order module is used to finalize the shopping cart and place the order. Users can enter their Billing, Shipping 
addresses and also their Payment details.

Modules: Admin
        Admin modules is accessible only to the Admin users. This module can be used to add new products and manage inventories.
     
