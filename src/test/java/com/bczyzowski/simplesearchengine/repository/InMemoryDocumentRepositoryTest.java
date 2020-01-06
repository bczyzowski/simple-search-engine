package com.bczyzowski.simplesearchengine.repository;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDocumentRepositoryTest {

    private InMemoryDocumentRepository inMemoryDocumentRepository;

    @BeforeEach
    public void setUp() {
        inMemoryDocumentRepository = new InMemoryDocumentRepository();
    }

    @Test
    public void givenDocument_whenAdding_shouldBeAdded() {
        final Index index = new Index("abc");
        final Document document = new Document("abc");

        inMemoryDocumentRepository.addDocument(index, document);

        final List<Document> documents = inMemoryDocumentRepository.searchDocuments(index);
        assertEquals(1, documents.size());
        assertEquals(document, documents.get(0));
    }

    @Test
    public void givenNonExistentIndex_whenSearching_emptyListShouldBeReturned() {
        final List<Document> documents = inMemoryDocumentRepository.searchDocuments(new Index(""));
        assertEquals(0, documents.size());
    }

    @Test
    public void givenAddedDocuments_whenFetchingAll_allDocumentsShouldBeReturned() {
        final Index abcIndex = new Index("abc");
        final Document abcDocument = new Document("abc");

        final Index fooIndex = new Index("foo");
        final Document fooDocument = new Document("foo");

        inMemoryDocumentRepository.addDocument(abcIndex, abcDocument);
        inMemoryDocumentRepository.addDocument(fooIndex, fooDocument);

        final Set<Document> documents = inMemoryDocumentRepository.findAll();
        assertEquals(2, documents.size());
        assertTrue(documents.contains(abcDocument));
        assertTrue(documents.contains(fooDocument));
    }
}