package com.bczyzowski.simplesearchengine.repository;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class InMemoryDocumentRepository implements DocumentRepository {

    private final Map<Index, List<Document>> indexListMap = new HashMap<>();

    @Override
    public void addDocument(Index index, Document document) {
        if (!indexListMap.containsKey(index)) {
            indexListMap.put(index, new ArrayList<>());
        }
        final List<Document> documents = indexListMap.get(index);
        if (!documents.contains(document)) {
            documents.add(document);
        }
    }

    @Override
    public List<Document> searchDocuments(Index index) {
        return indexListMap.getOrDefault(index, Collections.emptyList());
    }

    @Override
    public Set<Document> findAll() {
        return indexListMap.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

}
