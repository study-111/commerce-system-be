package study111.commerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import study111.commerce.annotation.WithMockCustomUser;
import study111.commerce.config.SecurityConfiguration;
import study111.commerce.request.UserJoinRequest;
import study111.commerce.request.UserModifyRequest;
import study111.commerce.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({SecurityConfiguration.class})
@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserService userService;

    @Test
    void join_case01() throws Exception {
        // given
        UserJoinRequest command = new UserJoinRequest();
        command.setUsername("tester");
        command.setPassword("pass");
        String content = objectMapper.writeValueAsString(command);

        when(userService.join(any(UserJoinRequest.class))).thenReturn(1L);

        // when
        var result = mockMvc.perform(
            post("/users").with(anonymous())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        // then
        result.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data").exists())
            .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    void join_case02() throws Exception {
        // given
        UserJoinRequest command = new UserJoinRequest();
        command.setUsername("2");
        command.setPassword("");
        String content = objectMapper.writeValueAsString(command);

        // when(userService.join(any(UserJoinCommand.class))).thenReturn(1L);

        // when
        var result = mockMvc.perform(
            post("/users").with(anonymous())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        // then
        result.andDo(print())
            .andExpect(status().isBadRequest())
        // .andExpect(jsonPath("$.data").exists())
        // .andExpect(jsonPath("$.data").value(1L))
        ;
    }

    @Test
    @WithMockCustomUser
    void edit_case01() throws Exception {
        // given
        var request = new UserModifyRequest();
        request.setUsername("2");
        String content = objectMapper.writeValueAsString(request);

        // when
        var result = mockMvc.perform(
            post("/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        // then
        result.andDo(print())
            .andExpect(status().isOk())
        ;
    }

    @Test
    void edit_case02() throws Exception {
        // given
        var request = new UserModifyRequest();
        request.setUsername("2");
        String content = objectMapper.writeValueAsString(request);

        // when
        var result = mockMvc.perform(
            post("/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        // then
        result.andDo(print())
            .andExpect(status().isForbidden())
        ;
    }

    @Test
    @WithMockCustomUser
    void edit_case03() throws Exception {
        // given
        var request = new UserModifyRequest();
        String content = objectMapper.writeValueAsString(request);

        // when
        var result = mockMvc.perform(
            post("/users/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));

        // then
        result.andDo(print())
            .andExpect(status().isBadRequest())
        ;
    }
}
