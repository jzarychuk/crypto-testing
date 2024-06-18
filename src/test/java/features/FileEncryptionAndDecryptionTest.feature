Feature: Role Managment Test

        Scenario: User wants to encrypt an uploaded file
            Given The user visits the app's homepage
             When The user clicks on login button
             When The user logs in using their email address and password
             When The user cliks on the Try Encryption button on homepage
             When The user cliks on Choose File button and selects a files to upload for Encryption
             When The user selects encryption type, and auto generate encryptio key
             Then The user clicks the Encrypt button to encrypt the file
             
        Scenario: User wants to decrypt an uploaded file
            Given The user visits the app's homepage
             When The user clicks on login button
             When The user logs in using their email address and password
             When The user cliks on the Try Decryption button on homepage
             When The user cliks on Choose File button and selects a files to upload for Decryption
             When The user selects encryption type, and inputs the key used for encryption
             Then The user clicks the Decrypt button to decrypt the file
