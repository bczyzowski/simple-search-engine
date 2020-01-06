package com.bczyzowski.simplesearchengine.service.impl;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import com.bczyzowski.simplesearchengine.service.IdfCalculator;
import com.bczyzowski.simplesearchengine.service.Tokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IdfCalculatorImpl implements IdfCalculator {

    private final Tokenizer tokenizer;

    @Override
    public double idf(Index index, Collection<Document> documents) {
        double number = 0;
        for (Document document : documents) {
            final List<String> tokens = tokenizer.tokenize(document.getContent());
            for (String token : tokens) {
                if (index.getValue().equalsIgnoreCase(token)) {
                    number++;
                    break;
                }
            }
        }
        if (number == 0) {
            return 0;
        }
        return Math.log(documents.size() / number);
    }
}
