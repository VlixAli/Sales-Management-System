package com.pluralsight.project.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final String name;

    public ResourceNotFoundException(String name) {
        this.name = name;
    }
}
