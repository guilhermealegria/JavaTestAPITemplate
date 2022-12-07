package com.example;

/**
 * Import dependencies.
 */
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;


public class AppTest {

    private App app = new App();
    private JSONObject usuario  = app.getUsuario();
    private JSONObject usuarioEdicao = app.getEdicaoUsuario();
    private JSONObject usuarioSemDados = app.getUsuarioSemDados() ;
    private Response response;
    private RequestSpecification httpRequest = RestAssured.given();

    /**
     * Requisitions Test
     */
    @Test
    @Order(1)
    public void consultarCpfsemRestricao(){   
        given()
        .when()
            .get(app.getUrl() + "restricoes/" + app.getcpfSemRestricao())
        .then()
            .statusCode(204);
    }

    @Test
    @Order(2)
    public void consultarCpfcomRestricao(){
        String mensagemRestricao = "O CPF " + app.getcpfComRestricao() + " tem problema";
        given()
        .when()
            .get(app.getUrl() + "restricoes/" + app.getcpfComRestricao())
        .then()
            .statusCode(200)
            .body(containsString(mensagemRestricao));
    }

    @Test
    @Order(3)
    public void consultarSimulacoes(){
        given()
        .when()
            .get(app.getUrl() + "simulacoes")
        .then()
            .statusCode(200)
            .body(containsString("id"));
    }

    @Test
    @Order(4)
    public void consultarSimulacaoPorCPF(){
        given()
        .when()
            .get(app.getUrl() + "simulacoes/" + app.getcpfSimulacao())
        .then()
            .statusCode(200)
            .body(containsString(app.getcpfSimulacao()));
    }

    @Test
    @Order(5)
    public void enviarSimulacao(){
        given()
            .body(usuario.toJSONString())
            .contentType(ContentType.JSON)
        .when()
            .post(app.getUrl() + "simulacoes")
        .then()
            .statusCode(201)
            .body(containsString(usuario.get("cpf").toString()));
    }

    @Test
    @Order(6)
    public void enviarSimulacaoDuplicada(){
        given()
            .body(usuario.toJSONString())
            .contentType(ContentType.JSON)
        .when()
            .post(app.getUrl() + "simulacoes")
        .then()
            .statusCode(400)
            .body(containsString("CPF duplicado"));
    }

    @Test
    @Order(7)
    public void enviarSimulacaoComDadosemFalta(){
        String mensagemdeErro = "CPF não pode ser vazio";
        given()
            .body(usuarioSemDados.toJSONString())
            .contentType(ContentType.JSON)
        .when()
            .post(app.getUrl() + "simulacoes")
        .then()
            .statusCode(400)
            .body(containsString("erros"))
            .and()
            .body(containsString(mensagemdeErro));
    }

    @Test
    @Order(8)
    public void enviarEdicaodedaSimulacao(){
        given()
            .body(usuarioEdicao.toJSONString())
            .contentType(ContentType.JSON)
        .when()
            .put(app.getUrl() + "simulacoes/" + usuario.get("cpf").toString())
        .then()
            .statusCode(200)
            .body(containsString(usuario.get("cpf").toString()));
    }

    @Test
    @Order(9)
    public void apagarPublicacao(){
        response = httpRequest.get(app.getUrl() + "simulacoes/" + usuario.get("cpf").toString());
        app.setID(response.getBody().jsonPath().get("id").toString());
        given()
        .when()
            .delete(app.getUrl() + "simulacoes/" + app.getID())
        .then()
            .statusCode(200)
            .body(containsString("OK"));
    }


}
