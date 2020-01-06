package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.service.impl.WhiteSpaceTokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhiteSpaceTokenizerTest {

    private final WhiteSpaceTokenizer whiteSpaceTokenizer = new WhiteSpaceTokenizer();

    @Test
    public void givenContentToTokenize_whenProcessing_tokensShouldBeReturned() {
        final String content = "the red fox bit the lazy dog";

        final List<String> tokens = whiteSpaceTokenizer.tokenize(content);

        assertEquals(7,tokens.size());
        assertEquals("the",tokens.get(0));
        assertEquals("red",tokens.get(1));
        assertEquals("fox",tokens.get(2));
        assertEquals("bit",tokens.get(3));
        assertEquals("the",tokens.get(4));
        assertEquals("lazy",tokens.get(5));
        assertEquals("dog",tokens.get(6));
    }
}