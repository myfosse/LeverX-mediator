# Proxy application for [user-pets](https://github.com/myfosse/user-pets) application

There is a simple proxy application that allows us to get all availbale lists of entites and save 3 entities by one request

## SAP Cloud Platform

By follow this [link](https://mediator.cfapps.eu10.hana.ondemand.com/api/v1/all) you can check all available functional by using posman [collection](https://github.com/myfosse/mediator/blob/developer/Mediator.postman_collection.json)

## Used Technologies

- Java 11
- Maven 3.6.3
- Spring Boot 2.4.2

## How to start application

Go to the root of the project and run next commands:

<blockquote>
<pre>
<code>mvn clean package
cf login
cf push</code></pre>
</blockquote>