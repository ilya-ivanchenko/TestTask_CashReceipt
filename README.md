## Test task creating cash receipt

#### 1) First version: https://github.com/ivanchenko92/TestTask_CashReceipt/tree/ba2c8cf44cfa7d91e36ad5e01eba8728ac897b75
According to technical specification the task starts with command in console, like this:  **java Receipt 3-1 2-5 5-1 card-1234** . Where the first number is product id and sthe second is quantity of 
current product: 'product id' - 'quantity' ...etc. The parameter 'card-number' is the number of discount card(optionally, not necessary), which gives 30% discount (I decided that would be really good for customers) for all the products.
If the quantity of products in the receipt more, than 5, customer gives the additioanl discount 10%. And you can get receipt in the console or to the file:

![image](https://user-images.githubusercontent.com/90793566/208970220-ca47dc96-b007-4b90-ae66-2451bbd8957f.png)

#### 2) Second version: https://github.com/ivanchenko92/TestTask_CashReceipt/tree/f54811998dca758755682418b2d0ba49140a6837
Where uses PostgreSQL database, JUnit, Spring Boot, Spring Data JPA, REST principles. To get the reeipt you need to write GET-request like this:

**http://localhost:8080/item?id=6&qty=3&id=4&qty=5&id=9&qty=6&id=7&qty=3&id=10&qty=1&card=111** . The parameter 'id' is the product id and 'qty' is the quantity 
of products and  there could be card number (the discount would be applied only if given card number exist into the DB). You also can get the receipt in the file or console and in addition to this you gets products and their quantity in JSON:

![image](https://user-images.githubusercontent.com/90793566/208974630-ac102205-c370-400d-9370-cf7209b28acc.png)




