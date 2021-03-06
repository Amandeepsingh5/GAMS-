package GAMS.GAMS.integration;

import GAMS.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class , webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Test
    public void submitStudentProfileWithValidData() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("role", "STUDENT")
                .param("username", "testUserName")
                .param("password", "testpassword")
                .param("confPassword", "testpassword")
        ).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:login"));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "testUserName")
                .param("password", "testpassword")
        ).andDo(print())
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/studentProfile")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("email", "testemail")
                .param("cv", "testcv")
                .param("diploma", "testdiploma")
                .param("gradeAudit", "testgradeAudit")
        ).andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
