package com.bczyzowski.simplesearchengine.service;

import com.bczyzowski.simplesearchengine.domain.Document;
import com.bczyzowski.simplesearchengine.domain.Index;

public interface TfCalculator {

    double tf(Index index, Document document);
}
