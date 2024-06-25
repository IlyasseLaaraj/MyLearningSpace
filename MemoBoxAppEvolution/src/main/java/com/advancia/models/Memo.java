package com.advancia.models;

public class Memo {
    private String content;

    public Memo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
      return content;
    }
}