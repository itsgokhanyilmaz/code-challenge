
# coding challenge

The challenge is a Java8 + Spring Boot project which uses HSQLDB as the database.


# Question 1: Product Management

Most of merchants sell products online. For these merchants the necessary REST services are listed below. We kindly ask you to implement them.

## Requirements

* Product adding, removing or updating services. 
* Product listing service which returns product name, description, remaining stock count and price.
* Payment service for the end user to buy their selected product.
* A product should not be sold for more than its stock.
* Customers paying for the same product at the same time should not buy the product if the stock is depleted. (i.e. if there are 2 stocks left and 3 customers pay at the same time, first 2 successful should buy the product and the 3rd one should fail with an appropriate message.) 
* No front end is necessary.
* Test coverage for the implemented service should be above 80%. We expect both Integration and unit tests.


# Question 2 : Latency Management

The bank responses are persisted to database.In class we have simulated 100 customers calling the payment service.

```java
    public void pay(BigDecimal price) {
        //pay with bank
        BankPaymentRequest request = new BankPaymentRequest();
        request.setPrice(price);
        BankPaymentResponse response = bankService.pay(request);

        //insert records
        Payment payment = new Payment();
        payment.setBankResponse(response.getResultCode());
        payment.setPrice(price);
        paymentRepository.save(payment);
        logger.info("Payment saved successfully!");
    }
```

In the simulation for some reason the bank response times take ~5 seconds. Due to this latency, a database connection problem is encountered after some time.
class displays "Connection is not available, request timed out after 30005ms." error after some time.)

Find a way to persist bank responses to the database in this situation.

## Requirements

* DB connection pool should stay the same.
* BankService.java and PaymentServiceClients.java classes should not be changed.
* In case of an error, there should not be any dirty data in the database.






