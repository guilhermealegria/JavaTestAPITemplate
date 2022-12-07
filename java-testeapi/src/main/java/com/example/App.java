package com.example;

import org.json.simple.JSONObject;

/**
 * imports
 */

public class App {

    private String url;
    private String cpfSemRestricao;
    private String cpfcomRestricao;
    private String cpfSimulacao;
    private JSONObject usuario;
    private JSONObject usuarioSemDados;
    private String id;

    public String getUrl(){
        return url = "http://localhost:8080/api/v1/";
    }

    
    public JSONObject getUsuario(){
        usuario = new JSONObject();
        usuario.put("nome", "Guilherme");
        usuario.put("cpf", 14145393716l);
        usuario.put("email", "guilherme@gmail.com");
        usuario.put("valor", 3200);
        usuario.put("parcelas", 10);
        usuario.put("seguro", true);
        return usuario;
    }

    public JSONObject getEdicaoUsuario(){
        this.usuario.put("nome", "Guilherme");
        this.usuario.put("cpf", 14145393716l);
        this.usuario.put("email", "guilherme@gmail.com");
        this.usuario.put("valor", 4300);
        this.usuario.put("parcelas", 5);
        this.usuario.put("seguro", false);
        return this.usuario;
    }

    public void setID (String id){
        this.id = id;
    }

    public String getID(){
        return this.id;
    }

    public String getcpfSemRestricao(){
        return cpfSemRestricao = "03243021753";
    }

    public String getcpfComRestricao(){
        return cpfcomRestricao = "97093236014";
    }

    public String getcpfSimulacao(){
        return cpfSimulacao = "17822386034";
    }


    public JSONObject getUsuarioSemDados() {
        usuarioSemDados = new JSONObject();
        usuarioSemDados.put("nome", "Guilherme");
        usuarioSemDados.put("email", "guilherme@gmail.com");
        usuarioSemDados.put("valor", 3200);
        usuarioSemDados.put("parcelas", 10);
        usuarioSemDados.put("seguro", true);
        return usuarioSemDados;
    }


    

}
