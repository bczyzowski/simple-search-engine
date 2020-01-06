package com.bczyzowski.simplesearchengine.service;

import java.util.List;

public interface Tokenizer {

    List<String> tokenize(String content);
}
