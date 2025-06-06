package com.example.webapp.exception;

public class CnpjJaCadastradoException extends RuntimeException{

    public CnpjJaCadastradoException(){
        super("CNPJ já cadastrado no sistema");
    }
}

