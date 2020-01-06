package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;

import java.util.Collection;

public interface TfIdfCalculator {

    double calculate(Index index, Document document, Collection<Document> documents);
}
