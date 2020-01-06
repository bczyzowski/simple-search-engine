package com.bczyzowski.simplesearchengine.service.impl;

import com.bczyzowski.simplesearchengine.service.Tokenizer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WhiteSpaceTokenizer implements Tokenizer {

    @Override
    public List<String> tokenize(String content) {
        return Arrays.asList(content.split("\\s+"));
    }
}
