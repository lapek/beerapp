# BeerApp 
> This repository is archived. New version is [here](https://github.com/lapek/beerapp2/).

Web application for homebrewers. The application was originally a student project, but I decided to develop it further.
* Website is in Polish
* Signup/login, stateless (JWT)
* Logged user can add a new recipe
* List of user's recipes

<s>More to come (English/Multilanguage support, editing recipes, editing user's profile, admin page, contact page etc).</s>


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
* Run the project with your IDE (remeber to set right profile) or write in the terminal
```sh
mvn spring-boot:run -Pyour_profile_name
```


## Developing

### Built With

Backend:
* Spring Boot 1.5.2
* Spring Data JPA 1.11.1 (w/ Hibernate 5.0.2)
* PostgreSQL 9.4

Frontend:
* AngularJS 1.6.6 
* Angular Ui-Router 1.0.6 
* Angular-Translate 2.15.2
* Satellizer 0.15.5
* Angular Material 1.1.5

Webjars for frontend dependencies.


### Setting up Dev

To start developing the project:

```shell
git clone https://github.com/lapek/beerapp.git
cd path-to-project/
mvn clean install
```

