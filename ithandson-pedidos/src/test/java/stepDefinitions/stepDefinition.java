package stepDefinitions;

import com.example.walkthroughjava.ITHandsonPedidoApplication;
import com.example.walkthroughjava.config.ConfigTest;
import com.example.walkthroughjava.domain.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ITHandsonPedidoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
@Import(ConfigTest.class)
public class stepDefinition {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Given("Um novo usuário")
    public void um_novo_usuário(io.cucumber.datatable.DataTable dataTable) throws Throwable {

        Map<String, String> map = dataTable.asMaps(String.class, String.class).get(0);

        LocalDate dtNascimento = LocalDate.parse(map.get("dataNascimento"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Pessoa wolverine = Pessoa.builder()
                .id(2L)
                .nome(map.get("nome"))
                .sobrenome(map.get("sobrenome"))
                .dataNascimento(dtNascimento)
                .cpf(map.get("cpf"))
                .build();
        mockMvc.perform(post("/api/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wolverine)))
                .andExpect(status().isOk());
    }

    @Then("A quantidade de usuários é {int}")
    public void a_quantidade_de_usuarios_é(Integer quantidade) throws Throwable {
//        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8888/api/pessoa", String.class);
//        System.out.println(forEntity.getBody());
        mockMvc.perform(get("/api/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].nome", equalTo("Charles")))
                .andExpect(jsonPath("$.[1].nome", equalTo("Wolverine")))
                .andExpect(jsonPath("$", hasSize(quantidade)));
    }
}
