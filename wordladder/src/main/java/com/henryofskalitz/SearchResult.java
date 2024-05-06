package com.henryofskalitz;
import java.util.*;

public class SearchResult {
    private List<String> foundPath;
    private int visitedWordsLength;

    public SearchResult(List<String> foundPath, int visitedWordsLength) {
        this.foundPath = foundPath;
        this.visitedWordsLength = visitedWordsLength;
    }

    public List<String> getFoundPath() {
        return foundPath;
    }

    public int getVisitedWordsLength() {
        return visitedWordsLength;
    }
}