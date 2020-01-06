package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import com.bczyzowski.simplesearchengine.service.impl.IdfCalculatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IdfCalculatorImplTest {

    @InjectMocks
    private IdfCalculatorImpl idfCalculator;

    @Mock
    private Tokenizer tokenizer;

    @Test
    public void givenIndexWithDocuments_whenCalculatingIdf_idfShouldBeCalculated() {
        final Index index = new Index("dog");

        final String firstDocumentContent = "the brown fox jumped over the brown dog";
        final Document firstDocument = new Document(firstDocumentContent);

        final String secondDocumentContent = "the red fox bit the lazy fox";
        final Document secondDocument = new Document(secondDocumentContent);

        when(tokenizer.tokenize(eq(firstDocumentContent))).thenReturn(Arrays.asList("the", "brown", "fox", "jumped", "over", "the", "brown", "dog"));
        when(tokenizer.tokenize(eq(secondDocumentContent))).thenReturn(Arrays.asList("the", "red", "fox", "bit", "the", "lazy", "fox"));


        final double result = idfCalculator.idf(index, Arrays.asList(firstDocument, secondDocument));
        assertEquals(Math.log(2), result);
    }

    @Test
    public void givenIndexWhichNotExistsInDocuments_whenCalculatingIdf_idfShouldBeCalculated() {
        final Index index = new Index("dog");

        final String firstDocumentContent = "a";
        final Document firstDocument = new Document(firstDocumentContent);

        final String secondDocumentContent = "b";
        final Document secondDocument = new Document(secondDocumentContent);

        when(tokenizer.tokenize(eq(firstDocumentContent))).thenReturn(Collections.singletonList("a"));
        when(tokenizer.tokenize(eq(secondDocumentContent))).thenReturn(Collections.singletonList("b"));

        final double result = idfCalculator.idf(index, Arrays.asList(firstDocument, secondDocument));
        assertEquals(0, result);
    }
}