package tiasmardiansyah.springrest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tiasmardiansyah.springrest.configurations.security.UserCredential;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void loginNotValidTest() throws JsonProcessingException, Exception {

        mockMvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .param("username", "NonExistingUsername")
                .param("password","NonExistingPassword")
        ).andExpectAll(
            status().isBadRequest(),
            content().string(Matchers.containsString("Username or Password is Incorrect"))
        );
    }

    @Test
    public void loginSuccessTest() throws JsonProcessingException, Exception {

        mockMvc.perform(
            post("/auth/login")
                .param("username", "test")
                .param("password","123")
        ).andExpectAll(
            status().isOk(),
            content().string(Matchers.containsString("Accepted"))
        );
    }

    @Test
    public void registerFailTest() throws JsonProcessingException, Exception {
        UserCredential user = new UserCredential();
        user.setPassword("");
        //password not set

        mockMvc.perform(
            post("/auth/register")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
            status().isBadRequest()
        );

    }

    @Test
    public void registerSuccessTest() {
        
    }


}
