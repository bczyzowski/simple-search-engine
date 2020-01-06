package com.bczyzowski.simplesearchengine.service.impl;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import com.bczyzowski.simplesearchengine.service.IdfCalculator;
import com.bczyzowski.simplesearchengine.service.TfCalculator;
import com.bczyzowski.simplesearchengine.service.TfIdfCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TfIdfCalculatorImpl implements TfIdfCalculator {

    private final IdfCalculator idfCalculator;

    private final TfCalculator tfCalculator;

    @Override
    public double calculate(Index index, Document document, Collection<Document> documents) {
        return tfCalculator.tf(index, document) * idfCalculator.idf(index, documents);
    }

}
