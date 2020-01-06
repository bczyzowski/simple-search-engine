package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.controller.dto.SaveDocumentsDTO;
import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import com.bczyzowski.simplesearchengine.repository.DocumentRepository;
import com.bczyzowski.simplesearchengine.service.impl.WhiteSpaceTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final WhiteSpaceTokenizer whiteSpaceTokenizer;

    private final DocumentRepository documentRepository;

    private final TfIdfCalculator tfIdfCalculator;

    public void saveDocuments(SaveDocumentsDTO saveDocumentsDTO) {
        for (String document : saveDocumentsDTO.getDocuments()) {

            final List<Index> documentIndices = whiteSpaceTokenizer.tokenize(document)
                    .stream()
                    .map(Index::new)
                    .collect(Collectors.toList());

            for (Index index : documentIndices) {
                documentRepository.addDocument(index, new Document(document));
            }

        }
    }

    public List<Document> searchDocuments(String term, Boolean tfIdf) {
        final Index index = new Index(term);
        final List<Document> documents = documentRepository.searchDocuments(index);
        if (tfIdf == null || !tfIdf) {
            return documents;
        }
        final Set<Document> allDocuments = documentRepository.findAll();
        return documents
                .stream()
                .map(document -> Pair.of(tfIdfCalculator.calculate(index, document, allDocuments), document))
                .sorted((p1, p2) -> Double.compare(p2.getFirst(), p1.getFirst()))
                .map(Pair::getSecond)
                .collect(Collectors.toList());
    }
}
