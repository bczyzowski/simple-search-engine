package com.bczyzowski.simplesearchengine.controller;

import com.bczyzowski.simplesearchengine.service.DocumentService;
import com.bczyzowski.simplesearchengine.controller.dto.SaveDocumentsDTO;
import com.bczyzowski.simplesearchengine.domain.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public void saveDocuments(@RequestBody SaveDocumentsDTO saveDocumentsDTO) {
        documentService.saveDocuments(saveDocumentsDTO);
    }

    @GetMapping
    public List<Document> searchDocuments(@RequestParam String term,
                                          @RequestParam(required = false) Boolean tfIdf) {
        return documentService.searchDocuments(term, tfIdf);
    }
}
