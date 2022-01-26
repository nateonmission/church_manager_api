package com.projects.church_manager_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InfoAlreadyExists extends RuntimeException{
    public InfoAlreadyExists(String message){
        super(message);
    }

}

