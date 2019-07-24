package com.kakao.payservice;

import com.kakao.payservice.model.Role;
import com.kakao.payservice.model.User;
import com.kakao.payservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class PayServiceApplication implements CommandLineRunner {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }

    @Override
    public void run(String... params) throws Exception {
        User admin = User.builder().username("admin").password("admin").email("admin@email.com").roles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN))).build();
        userService.signUp(admin);

        User client = User.builder().username("client").password("client").email("client@email.com").roles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT))).build();
        userService.signUp(client);
    }
}
