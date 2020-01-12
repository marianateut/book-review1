package org.fasttrackit.bookreview1.steps;

import org.fasttrackit.bookreview1.domain.User;
import org.fasttrackit.bookreview1.service.UserService;
import org.fasttrackit.bookreview1.transfer.SaveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

@Component
public class UserSteps {
    @Autowired
    private UserService userService;

    public User createUser(){
        SaveUserRequest request = new SaveUserRequest();
        request.setFirstName("joe");
        request.setLastName("Unborn");
        request.setEmail("joeUnborn@gmail.com");
        request.setUsername("UsernameJoe");
        request.setPassword("123456789");
        request.setBookTokens(10.0);

        User user = userService.createUser(request);

        assertThat(user, notNullValue());
        assertThat(user.getId(), notNullValue());
        assertThat(user.getId(), greaterThan(0L));
        assertThat(user.getFirstName(), is(request.getFirstName()));
        assertThat(user.getLastName(), is(request.getLastName()));
        assertThat(user.getEmail(), is(request.getEmail()));
        assertThat(user.getUsername(), is(request.getUsername()));
        assertThat(user.getPassword(), is(request.getPassword()));
        assertThat(user.getBookTokens(), is(request.getBookTokens()));


        return user; }

}
