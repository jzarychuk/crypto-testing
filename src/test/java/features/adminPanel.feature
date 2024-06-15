Feature: Admin Panel

        Scenario: Check that Edit Role of a user is working successfully
            Given I am logged in as an admin
            When I open the admin panel
            Then I should see the admin panel title as "ADMIN PANEL"
            When I edit the role of the first user to "User"
            Then I should see a success message "Role is edited successfully"