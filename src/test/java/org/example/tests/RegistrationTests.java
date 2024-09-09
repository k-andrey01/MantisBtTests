package org.example.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase{

    @Test
    public void testRegistration() {
        app.getRegistrationHelper().start("user1", "user1@localhost.localdomain");

    }
}
