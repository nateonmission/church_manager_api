# Church Management Application

More information on the front-end can be found here:
[Front-end GitHub Repo link](https://github.com/nateonmission/church_manager_front-end)

## Introduction 
Medium-sized churches frequently have difficult keeping track of their members and small-groups. This application seeks to help in that area by making it easier for church staff to track membership (including family relationships) and small-groups composition.
<br><br>[More on planning this project]("https://github.com/nateonmission/church_manager_api/blob/master/planning/planning.md")
## Technology
The back-end API is a Java application built using the Spring Boot Framework to simplify the data connection. The Database itself is Postgres. The front-end is built with Angular. The IDEs that I used were JetBrains IntelliJ for the Java API and Microsoft's Visual Studio Code for the Angular components. Other tools used during development include Postman (for testing the API endpoints) and PGAdmin (for managing the Postgres DB). Both sides of the app will be deployed on Heroku.

## Installation
In order to run this API locally, you will need to have downloaded and installed a Java Development Kit (JDK) and the IntelliJ IDE. Once everything is installed, IntelliJ should help you get the Maven dependencies installed. Then, you will need to provide access to a Postgres DB. Now, you can run it locally. If you choose to, You can use Heroku's git option and push your fork up. Heroku will compile and attach a DB automatically.

## Use of the Application
When you load the splash page and login, you will be greeted with a HOME page. Click PEOPLE to begin adding Members, Attenders, Children/Youth, and Staff. Click on GROUPS to create Sunday School Classes, Mid-week groups, or other affinity groups. Then on the PEOPLE tab, you can link children, parents, and spouses. On the GROUPS page, you can link group members, volunteer leaders, and staff supervisor. There is a DELETE page if you need to delete a person or group.

## API URL and Endpoints
``
https://church-mgr-proj.herokuapp.com/api/v1/
``

| Method | Endpoints                           | Description                                             | Request Body |
|--------|-------------------------------------|---------------------------------------------------------|--------------|
| GET    | /                                   | Verifies app is live. Returns<br>``<h1>I'm Alive</h1>`` | None         |
| POST   | /people                             | Creates a new person in the DB                          ||
| GET    | /people                             | Gets a list of ALL people in DB                         ||
| GET    | /people/{id}                        | Gets an individual by ID                                ||
| GET    | /people/email/'{emailAddress}'      | Gets an individual by email address                     ||
| GET    | /people/phoneNumber/'{phoneNumber}' | Gets an individual by phone number                      ||
| PUT    | /people/{id}                        | Updates a person's main info                            ||
| DEL    | /people/{id}                        | Deletes a person from the DB                            ||
| PUT    | /people/{id}/profile                | Updates a person's personal profile                     ||
| PUT    | /people/{id}/memberRecord           | Updates a person's member record                        ||
| PUT    | /people/{id}/addSpouse/{id}         | Adds a spouse to a persons main info                    ||
| PUT    | /people/{id}/removeSpouse           | Removes a spouse from a persons main info               ||
| PUT    | /people/{id}/addChild/{id}          | Adds a child to a persons main info                     ||
| PUT    | /people/{id}/removeChild/{id}       | Removes a child from a persons main info                ||
| PUT    | /people/{id}/addParent/{id}         | Adds a parent to a persons main info                    ||
| PUT    | /people/{id}/removeParent/{id}      | Removes a parent from a persons main info               ||
|        |                                     |                                                         ||
| POST   | /groups                             | Creates a group                                         ||
| GET    | /groups                             | Gets list of all groups                                 ||
| GET    | /groups/{id}                        | Gets a group by Id                                      ||
| PUT    | /groups/{id}                        | Updates a group                                         ||
| PUT    | /groups/{id}/addStaffSup{id}        | Adds or updates a Staff Supervisor to group             ||
| PUT    | /groups/{id}/removeStaffSup         | Removes a Staff Supervisor                              ||
| PUT    | /groups/{id}/addLeader/{id}         | Adds an individual Leader to the group                  ||
| PUT    | /groups/{id}/removeLeader/{id}      | Removes an individual Leader from the group             ||
| PUT    | /groups/{id}/addMember/{id}         | Adds an individual Member to the group                  ||
| PUT    | /groups/{id}/removeMember/{id}      | Removes an individual Member from the group             ||

## Problems and Overcoming Them
### Back-End
* One-to-one relationships
* One-to-many relationships
* CORS permissions
* Learning what JPA can and can't do
* Java deployment on Heroku

### Front-End
* Getting a grasp on Observables
* Dealing with variably formatted JSON received from API
* Form behavior in Angular
* More CORS issues
* Angular deployment on Heroku

## Future Opportunities
* Finish the front-end deployment
* Make it easier to search for people by name
* Better form validation
* Authentication and Authorization
* More interesting styling
