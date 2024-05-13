package br.com.fiapmsclientess.bdd.steps.cadastrarcliente;

import br.com.fiapmsclientess.application.dto.ClienteRequestDTO;
import br.com.fiapmsclientess.application.dto.ClienteResponseDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class CadastrarClienteStepDefinition {

    private ClienteRequestDTO clienteRequestDTO;
    private Response response;
    private static final String HOST = "http://localhost:8080/";
    private Map<String, String> dataTable;

    @Dado("que eu tenha os dados do cliente")
    public void que_eu_tenha_os_dados_do_cliente(DataTable dataTable) {
        final var dadosInformados = dataTable.asMaps().get(0);
        this.dataTable = dadosInformados;

        clienteRequestDTO = ClienteRequestDTO.builder()
                .nome(dadosInformados.get("nome"))
                .endereco(dadosInformados.get("endereco"))
                .telefone(dadosInformados.get("telefone"))
                .email(dadosInformados.get("email"))
                .build();
    }

    @Quando("eu enviar uma requisicao POST para {string} com os dados do cliente")
    public void eu_enviar_uma_requisicao_post_para_com_os_dados_do_cliente(String rota) {
        final var requestSpec = getRequestSpec(rota, clienteRequestDTO);
        response = requestSpec.post();
    }

    @Entao("a resposta deve ter o codigo de status {int}")
    public void a_resposta_deve_ter_o_codigo_de_status(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Entao("o cliente cadastrado deve ter os mesmos dados informados: nome, endereço, telefone e email")
    public void o_cliente_cadastrado_deve_ter_os_mesmos_dados_informados_nome_endereço_telefone_e_email() {
        final var clienteResponse = response.then().extract().as(ClienteResponseDTO.class);

        assert clienteResponse.getNome().equals(this.dataTable.get("nome"));
        assert clienteResponse.getEndereco().equals(this.dataTable.get("endereco"));
        assert clienteResponse.getTelefone().equals(this.dataTable.get("telefone"));
        assert clienteResponse.getEmail().equals(this.dataTable.get("email"));
    }

    @E("a resposta deve ter a mensagem de erro: {string}")
    public void aRespostaDeveTerAMensagemDeErro(String mensagemErro) {
        final var clienteResponse = response.then().extract().asString();

        Assertions.assertThat(clienteResponse).isEqualTo(mensagemErro);
    }

    private RequestSpecification getRequestSpec(String rota, Object request) {

        String correlationId = UUID.randomUUID().toString();
        String flowId = UUID.randomUUID().toString();

        if (rota.startsWith("/")) {
            rota = rota.substring(1);
        }

        final var baseUri = HOST + rota;

        return given()
                .header("correlation-id", correlationId)
                .header("flow-id", flowId)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .contentType(ContentType.JSON)
                .body(request)
                .baseUri(baseUri);
    }

}
