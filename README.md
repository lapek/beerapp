# BeerApp 
Web application for homebrewers. The application was originally a student project, but I decided to develop it further.
* Website is in Polish
* Signup/login, stateless (JWT)
* Logged user can add a new recipe
* List of user's recipes

More to come (English/Multilanguage support, editing recipes, editing user's profile, admin page, contact page etc).


## Installing / Getting started

* Create empty database
* Complete properties (database name, password etc). 
  * Create a new folder in _profile/_ directory. 
  * Copy files from _test_ profile and fill them.
  * Add your profile modifying _pom.xml_ file:

 ```
    <profiles>
        ...
        <profile>
            <id> profile_name </id>
            <properties>
                <config.name> direcotry_name </config.name>
            </properties>
        </profile>
    </profiles>
``` 
* Then set the _your_profile_name_ in maven (at now, I don't know how do this without IDE)
* Run the project with your IDE or write in the terminal
```sh
mvn spring-boot:run
```


## Developing

### Built With
* Spring Boot 1.5.2
* Spring Data JPA 1.11.1 (with Hibernate 5.0.2) 
* PostgreSQL 9.4
* AngularJS 1.6.5 (with Angular Ui-Router 0.4.2 and Angular-Translate 2.15.2)
* Angular Material 1.1.4
* Satellizer 0.15.5

Webjars for frontend dependencies.


### Setting up Dev

To start developing the project:

```shell
git clone https://github.com/lapek/beerapp.git
cd path-to-project/
mvn clean install
```

