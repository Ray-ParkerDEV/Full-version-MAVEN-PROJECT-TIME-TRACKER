## Project name
### TIME TRACKER

## Description
Time tracking system. The administrator assigns activity to the user. A user can have one or several activities. The user notes the amount of time spent on each activity. The user can send a request to add / delete activity.

## Table of Contents
* [Project name](#project-name)
* [Description](#description)
* [Prerequisites](#prerequisites)
* [Installation and running](#installation-and-running)
* [Authors](#author)

### Prerequisites
To run the project you need installed (according to the documentation): 
  * Java 8 (jre/jdk) or higher version 
  * Apache Tomcat 8.5.32 or higher version
  * Apache Maven 3.0.1 or higher version
  * MySQL 8.0.13 or higher version
  
### Installation and running
To install and run the project on localhost:
 * Clone/fork or download the project [time tracker project](https://github.com/Ray-ParkerDEV/Servlet_login_origin) from the GitHub 
 * Create database **timetracker** on MySQL server from folder resources timetracker.sql. Password for access and connect mySQL server root, user - root. By default sql queries creates admin user. You can log in into app as admin using pass: admin, login: admin. You can change parameters of admin by editing corresponding data in file timetracker.sql and to assigne administrator your own credentials.
 * Run maven command clean install, than copy project_file.war into the /TOMCAT/webapps folder. 
 Appe will be available by web-address: http:\\localhost:8080

## Author
Bodyak Iaroslav (e-mail: [4456602@gmail.com](mailto:4456602@gmail.com))




