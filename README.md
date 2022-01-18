Version 1.0

# 🔥 Welcome to Tastylab 🔥
🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔🍔�
## 🍓 Concept 🍓

### What is Tastylab?🤔

+ 🔥**Tastylab**🔥 is a platform where you can *easily* find new recipe inspirations.
  You can also 🔗share your 🍝**favorite recipe**🍝 with the ***community*** and 🏆***win vouchers*** *🏆 for
  popular events☕.

### New day and don't know what you like to eat?🙄

+ Use ***"Lizzy"*** the food **program**🤖 to search for a recipe **based on your fridge**.
  So what are you waiting for?!👀

##  🍓 API Design 🍓
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/14005996-7b14cfb8-2158-43ca-8f24-545be03abce1?action=collection%2Ffork&collection-url=entityId%3D14005996-7b14cfb8-2158-43ca-8f24-545be03abce1%26entityType%3Dcollection%26workspaceId%3Dd407fb37-3eb7-4d42-bf97-96e77786073e)

#### swagger: http://localhost:8080/swagger-ui/index.html
###### (note: replace port, if needed)
### 🍪 MemberController 🍪
> ***Parent-Path*** : "/api/member"
> ___
>>   **PUT**  ~ Request Payload(MemberRequest)
>>
>>   *Path* ⟹ ""
>>   
>>   *HttpStatus* ⟹ 200 Ok
>>
>>   *Authorized* ⟹ only Blogger
>>
>>   *Description* ⟹ this endpoint *update* user data and *return* MemberDTO
> ___
>>   **DELETE**
>> 
>>   *Path* ⟹ "/{memberId}"
>>
>>   *HttpStatus* ⟹ 404 Not Found
>>
>>   *Authorized* ⟹ only Blogger
>>
>>   *Description* ⟹ this endpoint *delete* member.

### 🍪 WelcomeController 🍪
> ***Parent-Path*** : "/api/welcome"
> ___
>>  **POST** ~ Request Payload(RegistrationRequest)
>>
>>   *Path* ⟹ "/signup"
>>
>>   *HttpStatus* ⟹ 200 Ok
>>
>>   *Authorized* ⟹ everyone
>>
>>   *Description* ⟹ this endpoint *sends* a form to create new member.
> ___
>>  **POST**
>>
>>   *Path* ⟹ "/login?username=""&password"" "
>>
>>   *HttpStatus* ⟹ 200 Ok
>>
>>   *Authorized* ⟹ only Blogger
>>
>>   *Description* ⟹ this endpoint *sends* login data and *return* a valid token.
> ___
>>   **GET**
>>
>>   *Path* ⟹ "/refresh-token"
>>
>>   *HttpStatus* ⟹ 200 Ok
>>
>>   *Authorized* ⟹ only Blogger
>>
>>   *Description* ⟹ this endpoint *sends* the username & password to authenticate.

### 🍪 RecipeController 🍪
> ***Parent-Path*** : "/api/recipe"
> ___
>>   **POST** ~ Request Payload(RecipeRequest)
>>
>>   *Path* ⟹ ""
>>
>>   *HttpStatus* ⟹ 201 Created
>>
>>   *Authorized* ⟹ BLOGGER
>>
>>   *Description* ⟹ this endpoint *creates* a recipe.
> ___
>>   **GET**
>>
>>   *Path* ⟹ "/home?page=0,0&size=3,3" 
>>   
>>   *HttpStatus* ⟹ 200 Ok
>>
>>   *Authorized* ⟹ BLOGGER
>>
>>   *Description* ⟹ this endpoint *sends* two pages for the home area.
> ___
>>   **GET**
>>
>>   *Path* ⟹ "/{username}?page=0&size=3"
>>   
>>   *HttpStatus* ⟹ 200 Ok
>>
>>   *Authorized* ⟹ BLOGGER
>>
>>   *Description* ⟹ this endpoint *sends* the member recipe.
> ___
>>   **POST**
>>
>>   *Path* ⟹ "/lizzy?foods=Apple,Butter&page=0&size=3"
>>
>>   *HttpStatus* ⟹ 200 Ok
>>
>>   *Authorized* ⟹ everyone
>>
>>   *Description* ⟹ this endpoint *search* a recipe based on the given foods.
