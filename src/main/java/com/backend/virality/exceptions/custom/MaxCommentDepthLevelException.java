package com.backend.virality.exceptions.custom;

public class MaxCommentDepthLevelException extends RuntimeException{
    public MaxCommentDepthLevelException(String s){
        super(s);
    }
}
