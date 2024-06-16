Feature: File Visibility (Admin)

        Scenario: Admin uploads a files to the app for saving
            Given The admin visits the homepage
             When The admin presses the login button
             When The admin logins with email and password
             When The admin clicks on the Admin Panel tab
             When The admin clicks on the Resources category
             When The admin clicks on the Choose button to select a file for upload
             When The admin clicks the Upload button
             Then The file is uploaded to the app

        Scenario: Admin checks if the uploaded file is visible to users
            Given The admin visits the homepage
             When The admin presses the login button
             When The admin logins as user with email and password
             When The admin clicks on the username option and accesses My Profile page
             Then The system shouldn't display the Resources category
          
