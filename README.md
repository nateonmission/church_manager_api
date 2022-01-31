* README

``
https://church-mgr-proj.herokuapp.com/api/v1/
``

| Method | Endpoints                           | Description                                          | Request Body |
|--------|-------------------------------------|------------------------------------------------------|--------------|
| GET    | /                                   | Verifies app is live. Returns ``<h1>I'm Alive</h1>`` | None         |
| POST   | /people                             | Creates a new person in the DB                       ||
| GET    | /people                             | Gets a list of ALL people in DB                      ||
| GET    | /people/{id}                        | Gets an individual by ID                             ||
| GET    | /people/email/'{emailAddress}'      | Gets an individual by email address                  ||
| GET    | /people/phoneNumber/'{phoneNumber}' | Gets an individual by phone number                   ||
| PUT    | /people/{id}                        | Updates a person's main info                         ||
| DEL    | /people/{id}                        | Deletes a person from the DB                         ||
| PUT    | /people/{id}/profile                | Updates a person's personal profile                  ||
| PUT    | /people/{id}/memberRecord           | Updates a person's member record                     ||
| PUT    | /people/{id}/addSpouse/{id}         | Adds a spouse to a persons main info                 ||
| PUT    | /people/{id}/removeSpouse           | Removes a spouse from a persons main info            ||
| PUT    | /people/{id}/addChild/{id}          | Adds a child to a persons main info                  ||
| PUT    | /people/{id}/removeChild/{id}       | Removes a child from a persons main info             ||
| PUT    | /people/{id}/addParent/{id}         | Adds a parent to a persons main info                 ||
| PUT    | /people/{id}/removeParent/{id}      | Removes a parent from a persons main info            ||
|        |                                     |                                                      ||
| POST   | /groups                             | Creates a group                                      ||
| GET    | /groups                             | Gets list of all groups                              ||
| GET    | /groups/{id}                        | Gets a group by Id                                   ||
| PUT    | /groups/{id}                        | Updates a group                                      ||
| PUT    | /groups/{id}/addStaffSup{id}        | Adds or updates a Staff Supervisor to group          ||
| PUT    | /groups/{id}/removeStaffSup         | Removes a Staff Supervisor                           ||
| PUT    | /groups/{id}/addLeader/{id}         | Adds an individual Leader to the group               ||
| PUT    | /groups/{id}/removeLeader/{id}      | Removes an individual Leader from the group          ||
| PUT    | /groups/{id}/addMember/{id}         | Adds an individual Member to the group               ||
| PUT    | /groups/{id}/removeMember/{id}      | Removes an individual Member from the group          ||

