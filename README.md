
The idea with, and reasons for why to use, a ORM-mapper

    It's faster, easier and allows us to still work with an OOP mindset and then map our objects to the database
   
The meaning of the terms JPA, Hibernate and Spring Data JPA and how they are connected

    As far as I understand, JPA is a set of rules or standards for ORM, Hibernate is then a framework that uses these JPA specifications, and Spring Data JPA is an additional framework laid on top of Hibernate, giving more methods to use in Spring Boot projects
   
How to create simple Java entities and map them to a database via the Spring Data API

    You create en entity and then use the @Entity annotation

How to control the mapping between individual fields in an Entity class and their matching columns in the database

    With the @Column annotation
  
How to auto generate IDs, and how to ensure we are using  a specific database's preferred way of doing it (Auto Increment in our case for  MySQL)

    With the two annotations @Id
                             @GeneratedValue(strategy = GenerationType.IDENTITY)

How to use and define repositories and relevant query methods using Spring Data JPAs repository pattern

    We make a repository interface that extends JpaRepository. Then inject it into our controller

   
How to write simple "integration" tests, using H2 as a mock-database instead of MySQL

    We can write the methods we want to test in our repository and then inject it into our tests repository. H2 can use our tests if we dont have access to our database since it is in memory

How to add (dev) connection details for you local MySQL database

    We use enviroment variables
   
