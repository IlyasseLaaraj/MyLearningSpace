package com.advancia.models;

public class SlotNumber {
	    private int content;

	    public SlotNumber(int content) {
	        this.content = content;
	    }

	    public int getContent() {
	        return content;
	    }

	    public void setContent(int content) {
	        this.content = content;
	    }
	    
	    @Override
	    public String toString() {
	      return  String.valueOf(content);
	    }
	}