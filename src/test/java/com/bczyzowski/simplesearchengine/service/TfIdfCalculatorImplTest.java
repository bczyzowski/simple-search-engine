package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import com.bczyzowski.simplesearchengine.service.impl.TfIdfCalculatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TfIdfCalculatorImplTest {

    @InjectMocks
    private TfIdfCalculatorImpl tfIdfCalculator;

    @Mock
    private TfCalculator tfCalculator;

    @Mock
    private IdfCalculator idfCalculator;

    @Test
    public void givenIndexWithDocuments_whenCalculatingTfIdf_tfIdfShouldBeCalculated() {
        final Index index = new Index("dog");

        final String firstDocumentContent = "a";
        final Document firstDocument = new Document(firstDocumentContent);

        final String secondDocumentContent = "b";
        final Document secondDocument = new Document(secondDocumentContent);

        when(tfCalculator.tf(eq(index), eq(firstDocument))).thenReturn(0.5);
        when(idfCalculator.idf(eq(index), eq(Arrays.asList(firstDocument, secondDocument)))).thenReturn(0.5);

        final double result = tfIdfCalculator
                .calculate(index, firstDocument, Arrays.asList(firstDocument, secondDocument));
        assertEquals(0.25, result);
    }
}