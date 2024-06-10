Feature: Sign Up

        Scenario: Check that Sign Up Scenario works successfully
            Given User visits home page to sign up
             When User clicks on Sign Up Button
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
