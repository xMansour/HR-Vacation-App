# Welcome to HR Vacations App

Table of Contents
1. [About The Project](#about)
2. [Built With](#builtWith)
3. [Cloning](#cloning)
4. [How To Use](#howToUse)


## About The Project<a id='about'></a>
HR Vacations App is a desktop app made with JavaFX to allow employees to effortlessly take vacations and HRs to simply accept or reject it with a push of a button.


## Built With<a id='builtWith'>
1. Java
2. JavaFX
3. SQLite and JDBC  
   I used SQLite as it is embedded database, added as a maven dependency and independent of any software. However, the app can be modified to work with any other DBMS. You just need to modify the `dbUrl` in the `config.properties` file and add the DBMS `username` and `password`.


## Cloning<a id='cloning'>  
1. Clone this project through this link `https://github.com/xMansour/HR-Vacation-App.git`  
2. Download all the maven dependencies by running this command `mvn dependency:copy-dependencies
   `  
## How To Use<a id='howToUse'></a>
First, you have to log in.

[<img src="/docs/screenshots/loginScreen.png"  hspace="10" vspace="10">]()

There are some dummy data in the employees table just to show the app's functionality. However, it can be stopped by removing the call to the `DBManager` class `populateEmployeesTable` method.

[<img src="/docs/screenshots/hrLogedInGetEmployees.png"  hspace="10" vspace="10">]()

You can use any of the above employees username and password to login. Usernames are the same as Passwords ex. use `mahmoudMansour` as username and `mahmoudMansour` as a password to login as Mahmoud Mansour as below.

[<img src="/docs/screenshots/employeeLogedIn.png"  hspace="10" vspace="10">]()

If you want to log in as an HR, use `hr` as username and `hr` as password.

[<img src="/docs/screenshots/hrLogedInGetEmployees.png"  hspace="10" vspace="10">]()

**As an HR you can**

1. Get all employees

[<img src="/docs/screenshots/hrLogedInGetEmployees.png"  hspace="10" vspace="10">]()

2. Create a new employee

[<img src="/docs/screenshots/hrLogedInCreateEmployee.png"  hspace="10" vspace="10">]()

3. Update an employee

[<img src="/docs/screenshots/hrLogedInUpdateEmployees.png"  hspace="10" vspace="10">]()

4. Delete an employee

[<img src="/docs/screenshots/hrLogedInDeleteEmployees.png"  hspace="10" vspace="10">]()

5. View vacation requests made by employees

[<img src="/docs/screenshots/hrLogedInVacationRequestsNone.png"  hspace="10" vspace="10">]()

Currently there are no vacation requests made so let's login as Mahmoud Mansour and submit a vacation request.

Mahmoud asks for a 15 days vacation as shown below. Let's hope the HR will not have any problems with that.

[<img src="/docs/screenshots/employeeAnnualVacationRequest.png"  hspace="10" vspace="10">]()

Login again as the HR. But unfortunately the HR finds out that Mahmoud's department currently has a lot of work to finish in the backlog and refuses the request made by him for 15 days.

[<img src="/docs/screenshots/employeeAnnualVacationRequestReject.png"  hspace="10" vspace="10">]()

Mahmoud finds out and asks for just a 5 days vacation.

[<img src="/docs/screenshots/employeeAnnualVacationRequest2.png"  hspace="10" vspace="10">]()

The HR sees it is okay and accepts it.

[<img src="/docs/screenshots/employeeAnnualVacationRequestAccept.png"  hspace="10" vspace="10">]()

Now the there are no other requests for the HR to see.

[<img src="/docs/screenshots/employeeAnnualVacationRequestAccept2.png"  hspace="10" vspace="10">]()

Mahmoud logs in again and boohoo!. Look whose going to the beach. Total annual vacation days left decremented by 5 days however, Watch out ;).

[<img src="/docs/screenshots/employeeLogedInRequestAccepted.png"  hspace="10" vspace="10">]()  


