package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadTextFile {

    private ArrayList<String> questions = new ArrayList<String>();

    public ReadTextFile(String filename) throws FileNotFoundException {
        /* Sets up a new file reader
        goes through each line of text file and then adds that to our list of 30 questions */
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        try{
            while ((line = reader.readLine()) != null)
            {
                questions.add(line);
            }
            reader.close();
        }
        catch (Exception e){
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
        }
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

}
