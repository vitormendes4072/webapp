package com.example.webapp.exception;

public class EmailJaCadastradoException extends RuntimeException{

    public EmailJaCadastradoException(){
        super("Email já cadastrado no sistema");
    }

}

