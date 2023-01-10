JPA stands for Java Persistence API
We always create a `jpa` package inside a `feature` package
and here we describe the database entities that make up
this feature.
In this case, for the user feature, we have the `User` entity
which will map to a `user` table in the database.
Also, here we should specify the repository as well.
The `UserRepository` is the clas responsible 
for making SQL queries in the `user` table, and returns
`User` class results.