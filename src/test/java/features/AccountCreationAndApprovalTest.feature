Feature: Account Creation and Approval

        Scenario: A new user tries to register for a new account
            Given The user visits homepage
             When The user clicks on Sign Up button
             When The user inputs name, email, password, confirm password, and selects a role
             When The user clicks sign up button
             Then The user receives a sign up success message
             
        Scenario: admin approves a new user
            Given The admin visits the app's homepage
             When The admin clicks on login button
             When The admin inputs their correct email address and password
             When The admin visits Admin Panel to approve a user
             Then The admin clicks on the New Accounts tab and approves the first new user
             
        Scenario: admin tries to sign in
            Given The admin visits the app's homepage
             When The admin clicks on login button
             When The admin inputs their correct email address and password
             Then The admin should be signed and access the website functionalities
             
        Scenario: User tries to sign
            Given The user visits homepage
             When The user clicks on the log in button
             When The user logins with correct email address and password
             Then The user should be signed in and access the website functionalities
             
        Scenario: User tries to sign in with incorrect email address
            Given The user visits homepage
             When The user clicks on the log in button
             When The user logins with incorrect email address and correct password
             Then The user should not be signed in and presented with an error message
             
        Scenario: User tries to sign in with incorrect password
            Given The user visits homepage
             When The user clicks on the log in button
             When The user logins with correct email address and incorrect password
             Then The user should not be signed in and presented with an error message
             
