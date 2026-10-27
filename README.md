# LoginFormTask

A simple login form built with Java AWT.

## Features
- Checks that the username and password fields are not empty
- Checks that the user exists
- Checks that the password is correct
- Checks that the selected role matches the user's role
- Shows a simple error message when the data is wrong
- Disables the Login button after 3 wrong password attempts (bonus)
- Opens a welcome page showing the username and role after a successful login

## Test Users
| Username | Password | Role |
|----------|----------|------|
| ahmed | 1234 | Student |
| sara | abcd | TA |
| drmohamed | pass123 | Professor |

## How to Run
1. Open the project in IntelliJ IDEA (JDK 17 or newer)
2. Put `LoginFormTask.java` inside the `src` folder
3. Run the `main` method

## Screenshots

### Login form (initial state)
![Login form](Screenshots/1.png)

### 1. Empty fields
![Empty fields](Screenshots/1-empty-fields.png)

### 2. User not found
![User not found](Screenshots/2-user-not-found.png)

### 3. Wrong password
![Wrong password](Screenshots/3-wrong-password.png)

### 4. Login disabled after 3 wrong attempts (Bonus)
![Three attempts](Screenshots/4-three-attempts.png)

### 5. Wrong role
![Wrong role](Screenshots/5-wrong-role.png)

### 6. Valid data entered (before clicking Login)
![Valid data](Screenshots/6.png)

### 7. Successful login
![Success](Screenshots/6-success.png)
