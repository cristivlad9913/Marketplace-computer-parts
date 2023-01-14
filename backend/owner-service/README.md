# Product Owner Service

Product owner se invarte in jurul entitatii de `Product`. User-ul care navigheaza
aplicatia asta este un `ProductOwner`.

## Pagina de Login

Form cu `username` si `password`

API: `POST /login`

```agsl
{
"username": "test1",
"password: "pass"
}
```

- username: string
- password: string

Response: `200 OK` daca e user corect, `401` altfel

## Pagina de Register

Form care sa inregistreze un user now in baza de date.


```
API `POST /register`

Body: {
    "username":"test2",
    "password":"pass2",
    "email":"some@mail.com",
    "firstName":"firstName",
    "lastName":"lastName",
    "phone":"1230909301-20"
}
Response: 
{
    "id": long,
    "username": str,
    "email": str,
    "firstName": str,
    "lastName": str,
    "phone": str
}
```

Momentan autentificare e cu Http Basic, adica trimiti credentialele in header

# Main Page
Practic dashboard-ul userului dupa ce se logeaza.

- Buton de `logout` din care sa scoata userul din app.
- Lista de `Post`-uri pe care le-a facut user-ul
- Buton  de `New Post` sa poti sa creezi un `Post`
- Click pe orice item din lista de `Post`-uri ca sa poti sa vezi pagina de detail

```
GET /my-posts
Response: Lista de Post-uri JSON TBA
```

# Create Post Page
Un `Post` contine mai multe `Item`-uri fiecare cu pretul ei.
```
POST /posts/
Body: Nu e definitiv, TBA
{
"title": str,
"descripition": str,
"items":[
    {
        "name": str,
        "price": float,
        "description":str
    }
]
Response: same body as /posts/{id}

```

# Post Detail Page
Pagina in care se pot vedea toate detaliile unui `Post`, inclusiv `Items`.
Un `Post` poate avea mai multe `Offers` facute de catre un user din
`buyer-service`. User-ul care detine acest `Post`, poate sa dea `ACCEPT/DECLINE`
la toate offers care sunt `PENDING` pentru acest `Post`.

```agsl
GET /posts/{id}
Response: {
    "id": long,
    "title": str,
    "descripition": str,
    "total": float,
    "status": ACTIVE | INACTIVE
"items":[
    {
        "name": str,
        "price": float,
        "description":str
    },
    ...
]
}
```
```agsl
PATCH /posts/
Body: Same as above
Response: Same as above
```
```agsl
DELETE /posts/{id}
Body: Empty
Response: 200 OK if post exists, 404 if it cannot befout
```

## Subsection in Post Details Table
Lista de `Offer` care sunt facute pentru `Post`-ul asta.
```agsl
GET /posts/{id}/offers
Response: [
    {
        "id": long,
        "buyerId": long,
        "buyerUsername": str,
        "offeredPrice": float,
    },
    ...
]
```
Ar trebui ca la fiecare intrare in tabelul de mai sus, sa avem un buton sau doua? 
`ACCEPT` `REJECT` care sa modifice statusul:
```agsl
PATCH /posts/{post-id}/offers/{offer-id}

Body: 
{
    "status": [ACCEPT|REJECT]
}
Response: 200 OK if the offer is updated, 404 if it does not exist
```


