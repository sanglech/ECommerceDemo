# AnyMind Ecommerce Demo

## Technology used

- Java 17
- Spring + Spring Boot
- gradle
- graphql + graphqli

## Assumptions

- Price modifier defined in pdf are rates which the user must enter between for the given payment method (i.e. AMEX price modifer must be between 0.98 and 1.01). Also, in the pdf if no multiplier is written a value of 1 is assumed (i.e. CASH final price calculation).
- Mastercard point rate is 0.03. Thus sample response given in pdf is incorrect.
- Camel case vs varaiable_name does not matter. We can follow whatever naming conventions we want (as long as it makes sense).

## Kickstart

1. Ensure you have Java 17 installed.
2. Run the command to build the project.

``` bash
./gradlew clean build
```

3. Run the command to run the server

```bash
./gradlew bootrun
```

4. Send sample queries to the server.

    - Method 1: Navigate to <http://localhost:8080/graphiql> and begin sending queries there.

    - Methos 2:  Import postman-collection and send queries using the methods provided.

## Known Issues

- Uses in memory structure to save history. Thus when the server closes the sales data will be lost.
- Possible Integer overflow with points, if user enter a price that is too large.
- Concurrency and race condition issues.
- Values in query need to follow exact format or will throw an InvalidInputException.

## Future Improvements

- Add SQL DB for saving data so data isnt lost on close.
- To avoid race conditions/concurrency:
  - With a SQLDB implemented leverage the jpa and javax libaries and implement the inserts as Transactional to make sure the price history is updated accordingly.
  - Switch codebase over to kotlin and make use of coroutines for better concurrency handling/scaling.
- Handle more various types of input from user instead of throwing error. (Ex. differnt date/time formats).

## Sample Queries

### Get Final Price for payment method

```graphql
  getFinalPrice(
    price: "100", 
    priceModeifer: 1.01, 
    paymentMethod: "AMEX", 
    dateTime: "2022-09-01T00:00:00Z") {
    finalPrice
    points
  }
```

### Get Payment History/Sales Data

```graphql
  getSalesHistory(
    startDate: "2022-09-01T00:00:00Z", 
    endDate: "2022-09-02T00:00:00Z") {
    dateTime
    sales
    points
  }
```
