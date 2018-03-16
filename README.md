# Spring Data Application

The project provides a RESTful API to manage the *de.smava.recrt.model.AppUser* and *de.smava.recrt.model.BankAccount*
entities.

For the backend an embedded H2 Database is used. Project data is initialized upon application start-up using the
*[sql/init.sql](recrt-persistence/src/main/resources/sql/init.sql)*
script present at **recrt-peristence** package.
There are 3 users pre-loaded on the initialization *init.sql* script:

username | password | email
---|---|---
user1 | 1111 | user1@smava.de
user2 | 2222 | user2@smava.de
user3 | 3333 | user3@smava.de

**user1** is the only user with **ROLE_ADMIN** so, is the only who can use some of the entry points, for example, *get users*.

## How to run it

This is a multi-layer Maven project built using **Spring framework: 4.1.0.RELEASE**.

To build the project just execute maven `mvn clean package` commands from the project root, with this you'll get a generated war file on target folder.

If you want to run the project in an embedded Tomcat instance execute `mvn tomcat7:run` command also from the project root.

## RESTful API

The API provides the following interfaces

### Login
**POST rest/login**

Request:
```
Headers:
Accept: application/json
Content-Type: application/json
Body: { "username":"user1", "password":"1111" }
```

Login success response:
```
Code: 200
Body: {"username":"user1","loggedIn":true}
```
Login incorrect response:
```
Code: 200
Body: {"loggedIn":false}
```
Login error reponse:
```
Code: 400
Body: empty
```

### Logout
**DELETE rest/login**

Request:
```
Headers: Accept: application/json, Content-Type: application/json
Body: { "username":"user1", "password":"1111" }
```

Logout success response:
```
Code: 200
Body: empty
```
Logout error reponse:
```
Code: 400
Body: empty
```

### Get users
**GET rest/users**

Request:
```
Headers:
Accept: application/json
```
success response:
```
Code: 200
Body: [{"username":"user1","email":"user1@smava.de"},{"username":"user2","email":"user2@smava.de"},{"username":"user3","email":"user3@smava.de"}]
```
unauthorized response:
```
Code: 401
Body: empty
```

### Get accounts
**GET rest/accounts**

request:
```
Headers: Accept: application/json
```
success response:
```
Code: 200
Body: [{"iban":"TESTIBAN0","bic":"TESTBIC0","appUser":{"username":"user2","email":"user2@smava.de"}}]
```
unauthorized response:
```
Code: 401
Body: empty
```
### Create account
**POST rest/accounts**

request:
```
Headers: Accept: application/json
Body: {"iban":"TESTIBAN1","bic":"TESTBIC1","appUser":{"username":"user2","email":"user2@smava.de"}}
```
success response:
```
Code: 200
Body: {"iban":"TESTIBAN1","bic":"TESTBIC1","appUser":{"username":"user2","email":"user2@smava.de"}}
```
unauthorized response:
```
Code: 401
Body: empty
```

## How to test it

To verify if it's correctly working you can use, for example with **curl**, read the manual using `man curl` to get details about how to use it, some examples bellow using our local [running server on **http://localhost:8080**](#How-to-run-it):

##### GET request example
This example will make a **GET** request on path *rest/users*, the cookie content is gotten from a successfully login request.
```
 curl -XGET -H'Accept: application/json' -H'Content-Type: application/json' --cookie "JSESSIONID=F642BF70B000FE382DE92E5F7A024C08" -D- http://localhost:8080/rest/users
```
you'll get in your terminal something similar to this with the *status code* on the first line and the *body of the response* in the last one
```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=F9DEDD5E6162EF2A96B443C72764A3DE; Path=/; HttpOnly
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 23 May 2017 12:06:08 GMT

{"username":"user1","loggedIn":true}
```

##### POST request example
This example will make a **POST** request on path *rest/login* in our local running server on *localhost:8080*
```
curl -XPOST -H'Accept: application/json' -H'Content-Type: application/json' -d'{"username":"user1","password":"1111"}' -D- http://localhost:8080/rest/login
```
you'll get in your terminal something like this with the status code on the first line and the body of the response in the last one
```
HTTP/1.1 200 OK
Server: Apache-Coyote/1.1
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Set-Cookie: JSESSIONID=F9DEDD5E6162EF2A96B443C72764A3DE; Path=/; HttpOnly
Content-Type: application/json
Transfer-Encoding: chunked
Date: Tue, 23 May 2017 12:06:08 GMT

{"username":"user1","loggedIn":true}
```
