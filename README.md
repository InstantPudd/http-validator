# Spring Web Validator
Utility methods for concise server-side http validation for client errors using Spring Web

## Adding to your project
You will need to have the spring-web dependency in your project https://mvnrepository.com/artifact/org.springframework/spring-web. Anything below version 3.2 should not work.

Create a bean for ClientErrorExceptionHandler. This will allow it to work with Spring to map the exceptions this library throws to appropriate responses.
```java
@Bean
public ClientErrorExceptionHandler clientErrorExceptionHandler() {
    return new ClientErrorExceptionHandler();
}
```
## Validating
```java
@RequestMapping(method=RequestMethod.POST)
public void someEndpoint(@RequestBody SomeObject someObject) {
    //Will result in a 400 status with the response body containing the message
    //"someProperty cannot be null" if someProperty is null.
    Validator
        .returnStatus(ClientErrorStatusCode.BAD_REQUEST)  //Synonym to ClientErrorStatusCode.STATUS_400. Every 4xx http status represented.
        .ifNull(someObject.getSomeProperty())             //Other options: always(), ifTrue(expression), ifFalse(expression), ifNotNull()
        .withErrorMessage("someProperty cannot be null")  //withNoErrorMessage() also possible
        .execute();                                       //Does the validation
   
   //...rest of the method
}
```
