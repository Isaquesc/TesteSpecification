package br.com.pessoal.isaque.teste.specification.controller;

import br.com.pessoal.isaque.teste.specification.model.Funcionario;
import br.com.pessoal.isaque.teste.specification.repository.FuncionarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;

//@WebMvcTest(FuncionarioController.class)
@AutoConfigureMockMvc
@AutoConfigureWebMvc
@Transactional
@DataJpaTest
@ActiveProfiles("test")
class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FuncionarioRepository repositoryMock;

    @InjectMocks
    private FuncionarioController controller;

    public List<Funcionario> listFuncionarios(){

        Funcionario funcionario1 = new Funcionario(1L,"Isaque","Silva",18);
        Funcionario funcionario2 = new Funcionario(2L,"Felipe","Sage",19);
        Funcionario funcionario3 = new Funcionario(3L,"Junior","Ferreira",18);

        return Arrays.asList(funcionario1,funcionario2,funcionario3);
    }

    @Test
    void dadoRequisicaoGetQuandoSolicitadoListaFuncionariosRetorneUmaListaFuncionarios() throws Exception {

        List<Funcionario> funcionarios = listFuncionarios();
        String listFuncionariosJson = objectMapper.writeValueAsString(funcionarios);

        BDDMockito.given(repositoryMock.findAll()).willReturn(listFuncionarios());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/funcionarios"))
                .andReturn();

        String jsonReturn = mvcResult.getResponse().getContentAsString();

        BDDAssertions.assertThat(jsonReturn).isEqualToIgnoringWhitespace(listFuncionariosJson);

    }

}