package com.kakao.payservice;

import com.kakao.payservice.exception.CustomException;
import com.kakao.payservice.model.Role;
import com.kakao.payservice.model.User;
import com.kakao.payservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void signInTest() {
        String token = userService.signIn("admin", "admin");
        log.info("token = {}", token);

        assertNotNull(token);
    }

    @Test
    public void signUpTest() {
        User user = User.builder().username("admin2").password("admin2").email("admin2@email.com").roles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN))).build();

        String token =  userService.signUp(user);
        log.info("token = {}", token);

        assertNotNull(token);
    }

    @Test
    public void deleteTest() {
        userService.delete("admin2");

        expectedException.expect(CustomException.class);

        userService.signIn("admin2", "admin2");
    }

    @Test
    public void searchTest() {
        User user = userService.search("admin");

        assertEquals(user.getUsername(), "admin");
    }

    @Test
    public void refreshTest() {
        String token = userService.refresh("admin");

        assertNotNull(token);
    }
}
