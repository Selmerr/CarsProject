Week 2 questions:

- What are the benefits of using a RESTful API

There are probably lots of benefits but the main ones i remember are 
  flexability in handling different types in requests such as JSON or XML
statelessness (if that is a word) so all the data is transferred with the request to our server. It ensures data doesn't get lost as easily if different servers handle the request and the corrolating response. Which also makes it easier to upscale our project

- What is JSON, and why does JSON fit so well with REST?
  
JSON is a format where data is written as one long string, where you can "store" objects in {}, lists in [] and set key and value pairs.
  So we can basically express anything in JSON as a string, and that makes it easy with REST to transfer all the data through each request, so each request to our controller continues to be stateless

- How you have designed simple CRUD endpoints using spring boot and DTOs to separate api from data  -> Focus on your use of DTO's
  
In my api package I have my CarController and MemberController with the CRUD endpoints. For instance in MemberController i have the restcontroller annotation in line 11 so spring knows its a rest controller. Then mappings for CRUD. I use dto's to seperate the repository and database from the layer that the client would interact with, so they don't have to take into account my database setup when they use my project. Dto's also help with managing what gets sent back to the client, and makes sure our entity classes don't get a lot fields that arent necessary. The service layer then works as the business logic and then calls the dto's and repository when it is needed.   

-  What is the advantage of using using DTOs to separate api from data structure when designing rest endpoints

The advantage of using dto's is you can easily manage the data you get through the rest endpoints. So we can have many different dtos for many different endpoints, without it affecting the actual structure of our database, since we can just create specific objects through the dtos.

- Explain shortly the concept mocking in relation to software testing
  
Mocking is used to make a temperay database that is only used in testing. We have learned to do it through our H2 in memory databse, but there are also other ways to do it. With mockito i understand it as not actually creating an in memory database, but more like we are recreating the parts of the database that are related to each method we are testing

- How did you mock database access in your tests, using an in-memory database and/or mockito → Refer to your code
  
I used an in-memory database, specifically H2. With the @DataJpaTest annotation in line 19 in MemberServiceH2Test Spring Boot automatically sets up H2 as our in-memory databse (I assume because it's added as a dependency in our pom), but then also disables autoconfiguration so we only things that are specific to the tests, and it makes each test transactional so it rolls back after each test. Though I did have a problem with the auto-incremented id in my CarServiceH2Test. Each test worked individually but when I tried to test the entire class the majority would fail. Think it's because H2 would stil auto-increment the id through each test.
  ChatGPT helped me by telling me about the @DirtiesContext annotation, which resets the spring application context, and therefore also the H2 database.

- Explain the concept Build Server and the role Github Actions play here
  
A build server is a server that "listens" to our repository and pulls the latest update and tries to build it, every time we push to main. Github actions acts as our build server and with the YAML file it added, builds and deploys my project every time I push to main.

- Explain maven, relevant parts in maven, and how maven is used in our CI setup. Explain where maven is used by your GitHub Actions Script(s)
  
We use maven because with its lifecycles it can automatically do a bunch of stuff with our project, like building, testing, running, creating our jar file and more. Maven also handles our dependencies through the pom file so we don't have to go get the dependencies ourselves from external sites. Maven is used with Github actions in the building part of our project. We can see in my YAML file in line 25 that it runs a clean install every time Github actions does the build job.

-  Understand and chose cloud service models (IaaS, PaaS, SaaS, DBaaS)for your projects -> Just explain what you have used for this handing

We have used DBaaS for our MySQL database on Azure, and I think SaaS for our Web App service.
