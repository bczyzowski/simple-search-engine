package com.bczyzowski.simplesearchengine.service.impl;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import com.bczyzowski.simplesearchengine.service.TfCalculator;
import com.bczyzowski.simplesearchengine.service.Tokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TfCalculatorImpl implements TfCalculator {

    private final Tokenizer tokenizer;

    @Override
    public double tf(Index index, Document document) {
        double result = 0;
        final List<String> tokens = tokenizer.tokenize(document.getContent());
        for (String token : tokens) {
            if (index.getValue().equalsIgnoreCase(token)) {
                result++;
            }
        }
        return result / tokens.size();
    }
}
