package com.bczyzowski.simplesearchengine.domain;

import lombok.Value;

@Value
public class Index {

    private String value;

    public Index(String value) {
        this.value = value.toLowerCase();
    }
}
