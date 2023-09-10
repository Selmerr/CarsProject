- Where and why you have used a @OneToMany annotation
In my car and member entity where i need a one to one relation between them and reservation
  
- Where an why you have used a @ManyToOne annotation
I my reservation entity so i can create a many to many relation between car and member with reservation as a join table
  
- The purpose of the CascadeType, FetchType and mappedBy attributes you can use with one-to-many
CascadeType handles how changes in a parent table cascade to child tables, FetchType.EAGER or FetchType.Lazy determines if the data is retrieved immediately or if it is accessed in some way.
And i think mappedBy handles the foreign key. So i Member i have a list of reservations with a OneToMany annotation and mappedBy "member" and I have a Member member field in my reservation entity which it is mapped to

- How/where you have (if done) added user defined queries to you repositories
  Not sure I understand the question. In my CarRepository I have 2 custom queries, 1 given to me by ChatGPT and 1 I made from scratch after seeing ChatGPT's previous query.
  
- a few words, explaining what you had to do on your Azure App Service in order to make your Spring Boot App connect to your Azure MySqlDatabase
Had to export my enviroment variables in the configuration part of my app service.
  
- a few words about where you have used inheritance in your project, and how it's reflected in your database
- What are the pros & cons of using the Single Table Strategy for inheritance?
- how are passwords stored in the database with the changes suggested in part-6 of the exercise
  I have been sick since wednesday so I haven't looked into it yet, but I'll know it by monday
