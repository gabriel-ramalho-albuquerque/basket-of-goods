# basket-of-goods

A Spring Boot console application that accept a list of items in the basket and output the subtotal, the special offer discounts and the final price.

Java version 17.

The project structure was designed with the Model–View–Controller (MVC) software architectural pattern.

Directory source structure:

```
 .
    ├── main
    |	├── java
    |	|	├── com.basketofgoods.basket
    |	|	|	├── controller # Controller classes. Used to manipulate the model classes and apply business logic.
    |	|	|	├── model      # Model classes. Application's data structure.
    |	|	|	├── util       # Util classes. Has constants class, view messages class and custom exceptions.
    |	|	|	├── view       # View classes. Used to show the view messages to the user and get the inputs using the controller classes.
    |	|	├── BasketApplication.java   # Application runnable class.
    ├── test
    |	├── java
    |	|	├── com.basketofgoods.basket
    |	|	|	├── controller  # Controller Junit test classes using Mockito framework.
```

The project uses Maven and has the Spring Boot DevTools, Lombok and JUnit dependencies.
All the tests has more than 80% code coverage.

## View Messages Examples:

```
Available products: 
Bread - price: 0.8 per loaf
Milk - price: 1.3 per bottle
Apples - price: 1.0 per bag
Soup - price: 0.65 per tin

Available special offers: 
Apples have 10% off their normal price this week
Buy 2 tins of soup and get a loaf of bread for half price

Enter the products in the format 'PriceBasket item1 item2 item3 ...': 
Invalid input. Input can't be null or empty or blank.

Enter the products in the format 'PriceBasket item1 item2 item3 ...': Apples
Invalid input. Input must start with the string 'PriceBasket'.

Enter the products in the format 'PriceBasket item1 item2 item3 ...': PriceBasket Apples Milk Bread
Subtotal: 3.1
Apples 10% off: -0.1
Total: 3.0

Enter the products in the format 'PriceBasket item1 item2 item3 ...': PriceBasket Soup Soup Bread
Subtotal: 2.10
Buy 2 tins of soup and get a loaf of bread for half price: -0.4
Total: 1.70
