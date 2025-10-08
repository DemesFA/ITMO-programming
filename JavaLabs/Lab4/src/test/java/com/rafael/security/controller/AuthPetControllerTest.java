package com.rafael.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafael.security.model.Owner;
import com.rafael.security.model.User;
import com.rafael.security.repository.OwnerRepository;
import com.rafael.security.repository.UserInfoRepository;
import com.rafael.security.service.JwtService;
import com.rafael.security.service.UserInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthPetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String adminToken;
    private String userToken;
    private String uniqueAdminUsername;
    private String uniqueUserUsername;

    @BeforeEach
    void setUp() throws Exception {
        // Clear the database
        userInfoRepository.deleteAll();
        ownerRepository.deleteAll();

        // Generate unique usernames
        uniqueAdminUsername = "admin_" + UUID.randomUUID().toString().substring(0, 8);
        uniqueUserUsername = "user_" + UUID.randomUUID().toString().substring(0, 8);

        // Create admin user with owner
        Map<String, String> adminData = new HashMap<>();
        adminData.put("username", uniqueAdminUsername);
        adminData.put("password", "admin123");
        adminData.put("role", "ADMIN");
        adminData.put("name", "Admin User");
        adminData.put("birthday", "1990-01-01");

        // Create regular user with owner
        Map<String, String> userData = new HashMap<>();
        userData.put("username", uniqueUserUsername);
        userData.put("password", "user123");
        userData.put("role", "USER");
        userData.put("name", "Normal User");
        userData.put("birthday", "1995-01-01");

        // Register users
        mockMvc.perform(post("/auth/addNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adminData)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/auth/addNewUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userData)))
                .andExpect(status().isOk());

        // Get tokens
        Map<String, String> adminCreds = new HashMap<>();
        adminCreds.put("username", uniqueAdminUsername);
        adminCreds.put("password", "admin123");

        Map<String, String> userCreds = new HashMap<>();
        userCreds.put("username", uniqueUserUsername);
        userCreds.put("password", "user123");
        adminToken = mockMvc.perform(post("/auth/generateToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(adminCreds)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        userToken = mockMvc.perform(post("/auth/generateToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreds)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }






    @Test
    void generateTokenShouldWork() throws Exception {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", uniqueUserUsername);
        credentials.put("password", "user123");

        mockMvc.perform(post("/auth/generateToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isOk());
    }

    @Test
    void generateTokenShouldFailWithWrongCredentials() throws Exception {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "wrong");
        credentials.put("password", "wrong");

        mockMvc.perform(post("/auth/generateToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credentials)))
                .andExpect(status().isUnauthorized());
    }
}
