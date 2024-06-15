Feature: Account Creation and Approval

        Scenario: A new user tries to register for a new account
            Given User visits home page
             When User clicks on Sign Up button
             When User inputs name, email, password, confirm password and selects a role
             When User clicks submit sign up button
             Then User receives a sign up success message
             
        Scenario: Admin approves a new user
            Given Admin visits home page to approve a user
             When Admin clicks on login button to approve a user
             When Admin logins with email and password to approve a user
             When Admin visits Admin Panel to approve a user
             When Admin clicks on New Accounts tab
             Then Admin approves the first new user
             
        Scenario: Admin tries to sign in
            Given The admin visits the homepage
             When The admin clicks on the login in button
             When The admin inputs their correct email address and password
             Then The admin should be signed and access the website functionalities
             
        Scenario: User tries to sign
            Given The user visits the homepage
             When The user clicks on the sign in button
             When The user logins with correct email address and password
             Then The user should be signed in and access the website functionalities
             
        Scenario: User tries to sign in with incorrect email address
            Given The user visits the homepage
             When The user clicks on the sign in button
             When The user logins with incorrect email address and correct password
             Then The user should not be in signed and presented with an error message
             
        Scenario: User tries to sign in with incorrect password
            Given The user visits the homepage
             When The user clicks on the sign in button
             When The user logins with correct email address and incorrect password
             Then The user should not be signed in and presented with an error message
             
