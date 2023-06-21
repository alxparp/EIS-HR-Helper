# EIS HR Helper

EIS HR Helper is a web application that is used by HR Department to cover such duties as: everyday lettering and events reminding.
System contains of two parts: main and admin applications. Main application is used to perform system functions. Admin application is used to set up system settings.
There are following features of main application:

1.	EIS HR Helper integrates with corporate Bamboo HR system. Based on data retrieved from Bamboo HR, system finds employees that will have some events in future such as: Birthday, anniversary of work in the company, greetings for newcomers, greetings with promotions, remind to book Annual Leave vacation, remind to sign an invoice before vacation starts.
2.	After system finds appropriate employees, draft of letters and reminders are generated.
3.	Draft of letters and reminders fall into approval process.
4.	System sends approved letters and remainders at day when event becomes effective.
Admin application is used to:
    1.	Create new system user.
    2.	Assign specific roles and privileges to the user.
    3.	Add/Edit/Update template of letters and reminds.

The purpose of the EIS HR Helper application is:
    - to reduce time consuming work of HR Department;
    - to use common approach among EIS Group by all HR Departments;
    - not to have missed events or reminds from HR Department side;
    - employees receive greetings letters and useful reminds on time. 



## Getting Started

Nothing yet

### Prerequisites

```
JDK 1.8 or above
Tomcat 8
Maven 
```
## Deployment

Nothing yet

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Technologies

* Java 8
* Oracle DB
* H2DB
* Liquibase
* Hibernate 5
* Spring Framework 4
    - Spring MVC
    - Spring WebFlow
    - Spring Security
* JSF: Primefaces
* CSS
* SLF4J/LOG4J
* JUnit
* Maven
* Tomcat


