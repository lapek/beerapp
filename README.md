# BeerApp #

Web application for homebrewers. The application was originally a student project, but I decided to develop it further.
Spring Boot + Hibernate + PostgreSQL + AngularJS + Angular Material

### Content ###
* Website is in Polish (in plans: English/Multilanguage support)
* Working signup/login
* Logged user can add a new recipe
* List of user's recipes
More to come (editing recipes, editing user's profile, admin page, contact page etc).

### How to run? ###
* Create empty database
* Complete the configuration data in _configs/test/_ directory (name of database, login, password, port, etc)
* Set profile _test_ in maven 
* Run the project
```sh
mvn spring-boot:run
```


