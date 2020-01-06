package com.bczyzowski.simplesearchengine.repository;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;

import java.util.List;
import java.util.Set;

public interface DocumentRepository {

    void addDocument(Index index, Document document);

    List<Document> searchDocuments(Index index);

    Set<Document> findAll();
}
