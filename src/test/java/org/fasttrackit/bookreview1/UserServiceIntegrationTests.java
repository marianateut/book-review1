package org.fasttrackit.bookreview1;

import org.fasttrackit.bookreview1.steps.UserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserSteps userSteps;

    @Test
    public void testCreateUser_whenValidRequest_thenReturnCreatedUser() {
        userSteps.createUser();


    }



}

