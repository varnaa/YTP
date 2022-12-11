package com.varnaa.ytp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class YoutubeTranscriptParser {
    private final String outputFileName;
    private final String outputFilePath;

    public YoutubeTranscriptParser(){
        this.outputFileName = "OutputTranscript.txt";
        this.outputFilePath = "resources/"+outputFileName;
    }

    public YoutubeTranscriptParser(String outputFileName, String outputFilePath){
        this.outputFileName = outputFileName;
        this.outputFilePath = outputFilePath;
    }

    public boolean parseTranscript(String inputFilePath){
        boolean result = true;
        try{
            Path path = Paths.get(inputFilePath);
            assertFalse(Files.exists(path));
            File file = new File(inputFilePath);
            InputStream inputStream = new FileInputStream(file);
            try (BufferedReader br
                         = new BufferedReader(new InputStreamReader(inputStream))) {
                File outputFile = new File(outputFilePath);
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile.getAbsolutePath()));
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.isBlank() && !line.startsWith("**")){
                        System.out.println("Writing new line.");
                        writer.write(line);
                        writer.newLine();
                    }
                }
                writer.close();
            }
        }catch (Exception e){
            result = false;
            System.out.println("Exception occurred while parsing transcript" + Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    private void assertFalse(boolean exists) {
        if(!exists) throw new RuntimeException("File not found! Check the path");
    }
}
