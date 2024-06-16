Feature: Role Managment Test

        Scenario: Admin changes the role of a registered user
            Given Admin visits homepage
             When Admin clicks on login button
             When Admin logins with email and password
             When Admin visits Admin Panel
             When Admin clicks on Registered Accounts tab
             Then Admin changes the first user role to Employee
             
        Scenario: User changes the role of another registered user
            Given The user visits the homepage
             When The user clicks on the login button
             When The user logins with their email address and password
             Then The user should not have an admin panel to access and update other users roles