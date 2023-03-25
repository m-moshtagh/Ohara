# Authorization & Authentication

When a user wants to use our application we first want them to authenticate using credentials, Then based on their
authorities and roles they can access to various functionalities. Authorities are defined as plain text and, they are
granular like removeUser, editProfile, accessPublicApi. However, roles are richer objects like admin, manager, student.

## HTTP basic Authentication

This authentication is done via HTTP protocol.
In this case when we try to access a resource server responds with a 401 status and a `www-autheticate` header Then
browser will show a popup and we try to authenticate using credentials then we send our request back to server with
a `authorization` header which contains `BASIC [encoded username & password].

> username & password in this case will be encoded in BASE64.

This is the simplest form of authentication which is totally handled by browser not the application. There is no usage
of cookies, sessions and, we have no logout operation. With SSL it can provide a base minimum security level but, not
recommended.

## Form based Authentication

We transmit the credentials using HTML data. Then we need to handle security inside our application. We send back
cookies containing user sessionID which will expire in a certain time. We also, have logout in this method. This is much
better than basic auth however, SSL is recommended to use to prevent phishing. For public APIs it's not recommended
because of stateful characteristics. stateless(token based) is recommended for Restful apps.

## JWT authentication

Json web token is a compact secure way to exchange data between client and server for authentication and authorization.
This is simply done by sending a request to an authentication server which returns a bearer token which we hold in
headers.

JWT contains three parts:

1. Header
2. Payload
3. Signature

This is recommended when we have public APIs or provide our application to 3rd parties.

## SSL & HTTPS

In order to prevent man in the middle attack we need SSL certificates which can be bought. The flow is that we send
HTTPS request, server sends certificate with public key, client verifies SSL and sends back session key which all
communications will be encrypted with this key.
