## Project name
### TIME TRACKER

## Description
Time tracking system. The administrator assigns activity to the user. A user can have one or several activities.
 The user notes the amount of time spent on each activity. The user can send a request to add / delete activity.

## Table of Contents
* [Project name](#project-name)
* [Description](#description)
* [Technologies](#technologies)
* [Functional](#functional)
* [Prerequisites](#prerequisites)
* [Installation and running](#installation-and-running)
* [General project requirements](#general-project-requirements)
* [EERD scheme](#EERD-scheme)
* [Architecture](#architecture)
* [Authors](#author)

### Technologies
* Maven
* Tomcat
* MySql Data Base
* JDBC
* JSP + JSTL
* Servlets
* Log4J
* JUnit
* Mockito
* JavaScript
* HTML
* CSS
### Functional
* Custom tag, UseBean
* Localisation
* Connection Pool
* Transaction 
* Sessions, Filters
* Pagination
* Authorisation, Authentication system

### Prerequisites
To run the project you need installed (according to the documentation): 
  * Java 8 (jre/jdk) or higher version 
  * Apache Tomcat 8.5.32 or higher version
  * Apache Maven 3.0.1 or higher version
  * MySQL 8.0.13 or higher version
  
### Installation and running
To install and run the project on localhost:
 * Clone/fork or download the project [time tracker project](https://github.com/Ray-ParkerDEV/Servlet_login_origin) from the GitHub 
 * Create database **timetracker** on MySQL server from folder resources timetracker.sql. By default sql queries creates admin user. You can log in into app as admin using pass: admin, login: admin. You can change parameters of admin by editing corresponding data in file timetracker.sql and to assigne administrator your own credentials.
Install JDK, JRE, set parameters for environment variables. Install Apache Tomcat, install Apache Maven. 
GO ON!!! Nothing gonna stop you from tracking the Activities:)))

### General project requirements
You need to build a web application that supports the following functionality:
Based on the entities create classes that describe them.
Classes and methods must have a name reflecting their functionality and must be properly structured by package.
Information about the subject area is stored in the database, for access, use the JDBC API using a connection pool, 
standard or developed independently. MySQL is recommended as a DBMS.
The application should support working with Cyrillic (be multilingual), including storing information in the database.
The code must be documented.
The application must be covered by unit tests.
During developing business logic, use sessions and filters and process events in the system by Log4j.
In the application you need to implement Pagination, Transaction, depending on your project.
Using servlets and JSP, you have to implement the functionality proposed in the formulation of a specific task.
In JSP pages, use the JSTL library.
The application should react correctly when errors and exceptions occurs of various kinds (the User should never see 
the stack-trace on the front-end side).
The application must have an Authorization and Authentication system.

### EERD scheme

![alt text](https://github.com/Ray-ParkerDEV/Servlet_login_origin/blob/master/src/main/webapp/images/EERD.jpg)

### Architecture

![alt text](https://github.com/Ray-ParkerDEV/Servlet_login_origin/blob/master/src/main/webapp/images/architecture.jpg)

![alt text](https://github.com/Ray-ParkerDEV/TIME-TRACKER/blob/master/src/main/webapp/images/ex.jpg)
## Author
Bodyak Iaroslav (e-mail: [4456602@gmail.com](mailto:4456602@gmail.com))




