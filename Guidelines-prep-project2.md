This week, there are 3 activities to do during the lab sessions:

1. Get familiar with JSON Web Token and search for information, so that you can later protect your REST APIs with this standard
2. Do a quick tutorial to discover CucumberJVM, which we will use to test REST APIs.
3. Watch the webcasts that present experiments and code related to transactions


### REST APIS & JSON WEB TOKEN (~90')
-----------------------------------
1. In the project, you will implement REST APIs with Spring Boot and Spring MVC. One thing we want to do is to protect the REST endpoints, so that only authentHandlerInterceptorAdaptericated users can query data (and that they only have access to their data). To do that, you will need to use JSON Web Tokens (JWT).

**Task 1: what is JWT? If you are not familiar with the authentication method, do some research to understand the basic idea.**

[Site officiel JWT](https://jwt.io/). JWT is **J**SON **W**eb **T**oken; it's an open standard ([RFC 7519](https://tools.ietf.org/html/rfc7519)) that allows to securely transmit information between parties with JSON objects.
This information is signed with either HMAC or RSA/ECDSA.

**Task 2: how can I generate a JWT token in Java code? Surely, there is a library that I can use for that... (hint: do NOT use Spring Security, unless you are ready to dig into quite complex configuration and prefer a more low-level approach)**

To generate a JWT, one can use the jwt library: `maven: com.auth0 / java-jwt / 3.3.0`.

**Task 3: how can I generate a JWT token online, and how can I see what is inside an existing token? (hint: <https://jwt.io/>)**

One can see what is inside a jwt token and also generate one on the [jwt website debugger](https://jwt.io/).

**Task 4: in one of the Spring Boot tutorials I did last week, I have created a REST endpoint (returning a Greeting payload). How can I extend the code to generate and return a JWT token? How can I make sure that this token contains a user identifier (e.g. e-mail).**

You can add a filter that will generate jwt token for each new session. To make sure that this token contains a user identifier you can implement a login page and put the identifier (encrypted) in the jwt token or use the session id.

**Task 5: in the same code, how do I protect another endpoint and check that 1) there is a JWT token, 2) it has a valid signature, 3) it contains the expected user identifier? (hint: start by add the authorization code directly in the @RestController).**

In the filter first check if there is a jwt token, then check its signature.

**Task 6: assuming that I have several endpoints to protect, how do I factor the authorization code in a reusable component? Similarly to Servlet Filters, how can I intercept calls to Spring MVC Controllers? (hint: look for HandlerInterceptorAdapter).**

You can use a class `Interceptor extends HandlerInterceptorAdapter` that will intercept and handle the requests to the end point. This class will check the validity of the jwt token and if necessery issue new ones.


### BDD WITH CUCUMBER JVM (~20')
------------------------------
**Task 7: In the project, you will learn about a new type of automated tests. You will use CucumberJVM to write "executable specifications", and to apply the notion of "Behaviour Driven Development". To get a first feel, before we talk about the approach in the course, do this [quick tutorial](https://cucumber.io/docs/guides/10-minute-tutorial/)**


### TRANSACTIONS WITH EJBS (30')
-------------------------------
**Task 8: watch the 4 webcasts in the "AMT Transactions with EJB" series (in the AMT 2019 playlist). The companion GitHub repo is available [here] (https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-AMT-Transactions-with-EJB)**

