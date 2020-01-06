package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;

import java.util.Collection;
import java.util.Set;

public interface IdfCalculator {

    double idf(Index index, Collection<Document> documents);
}
