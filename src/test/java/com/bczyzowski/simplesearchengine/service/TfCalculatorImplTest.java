package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;
import com.bczyzowski.simplesearchengine.service.impl.TfCalculatorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TfCalculatorImplTest {

    @InjectMocks
    private TfCalculatorImpl tfCalculator;

    @Mock
    private Tokenizer tokenizer;

    @Test
    public void givenIndexWithDocument_whenCalculatingTf_tfShouldBeCalculated() {
        final String documentContent = "the brown fox jumped over the brown dog";
        final Index index = new Index("brown");
        final Document document = new Document(documentContent);

        when(tokenizer.tokenize(eq(documentContent))).thenReturn(Arrays.asList("the", "brown", "fox", "jumped", "over", "the", "brown", "dog"));

        final double result = tfCalculator.tf(index, document);

        assertEquals(0.25, result);
    }
}