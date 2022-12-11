package com.varnaa.ytp;

public class Main {
    public static void main(String[] args) {
        YoutubeTranscriptParser ytp = new YoutubeTranscriptParser();
        boolean result = ytp.parseTranscript("resources/input.txt");
        if (result){
            System.out.println("Successfully parsed transcript!");
        }
    }
}
