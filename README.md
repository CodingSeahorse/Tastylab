Version 1.0

# 🔥 Welcome to Tastylab 🔥
🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔
## 🍓 Concept 🍓

### What is Tastylab?🤔

+ 🔥**Tastylab**🔥 is a platform where you can *easily* find new recipe inspirations.
  You can also 🔗share your 🍝**favorite recipe**🍝 with the ***community*** and 🏆***win vouchers*** *🏆 for
  popular events☕.

### New day and don't know what you like to eat?🙄

+ Use ***"Lizzy"*** the food **program**🤖 to search for a recipe **based on your fridge**.
  So what are you waiting for?!👀

##  🍓 API Design 🍓

### 🍪 HomeController 🍪
> **Parent-Path** : "/api/v1/home"
> ___
> + *GET* ⟹ "/?page=0&size=3"
    >
    >   *HttpStatus* ⟹ 200 OK
    >
    >   *Authorized* ⟹ everyone
    >
    >   *Description* ⟹ this endpoint returns **two separately** pages *"Explore"* & *"Highlight"* for the homepage.
### 🍪 MemberController 🍪
> **Parent-Path** : "/api/v1/member"
> ___
> + *POST* ⟹ ~ Request Payload
    >
    >   *HttpStatus* ⟹ 201 Created
    >
    >   *Authorized* ⟹ only Blogger
    >
    >   *Description* ⟹ this endpoint post the user input data to the recipe service to *create a new recipe*.
> ___
>
> + *GET* ⟹ "/recipes?page=0&size=10"
    >
    >   *HttpStatus* ⟹ 200 Ok
    >
    >   *Authorized* ⟹ only Blogger
    >
    >   *Description* ⟹ this endpoint *returns* the logged-in user recipes.
### 🍪 MemberManagementController 🍪
> **Parent-Path** : api/v1/member-management
> ___
> + *POST* ⟹ ~ Request Payload
    >
    >   *HttpStatus* ⟹ 200 Ok
    >
    >   *Authorized* ⟹ only Blogger
    >
    >   *Description* ⟹ this endpoint sends a request to the support to delete the Member.
> ___
> + *PUT* ⟹ ~ Request Payload
    >
    >   *HttpStatus* ⟹ 200 Ok
    >
    >   *Authorized* ⟹ only Blogger
    >
    >   *Description* ⟹ this endpoint sends a request with *new* Member-Data to the service.
> ___
>
> + *DELETE* ⟹ "?username="" "
    >
    >   *HttpStatus* ⟹ 404 Not Found
    >
    >   *Authorized* ⟹ only Admin's
    >
    >   *Description* ⟹ this endpoint *deletes* a member from database.
### 🍪 LoginController 🍪
> **Parent-Path** : api/v1/welcome/login
> ___
> + *POST* ⟹ ~ Request Payload
    >
    >   *HttpStatus* ⟹ 200 Ok
    >
    >   *Authorized* ⟹ everyone
    >
    >   *Description* ⟹ this endpoint *sends* the username & password to the first filter.
### 🍪 RegistrationController 🍪
> **Parent-Path** : api/v1/welcome/signup
> ___
> + *POST* ⟹ ~ Request Payload
    >
    >   *HttpStatus* ⟹ 201 Created
    >
    >   *Authorized* ⟹ everyone
    >
    >   *Description* ⟹ this endpoint *sends* the user input data to the service.