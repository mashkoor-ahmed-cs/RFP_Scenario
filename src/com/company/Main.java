package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    private ArrayList<String> questions = new ArrayList<String>();
    public String problemFileName = "problems.rfp";

    private void readFile(){
        BufferedReader reader = new BufferedReader(new FileReader(problemFileName));
        String line;
        try{
            while ((line = reader.readLine()) != null)
            {
                questions.add(line);
            }
            reader.close();
        }
        catch (Exception e){
            System.err.format("Exception occurred trying to read '%s'.", problemFileName);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
	// write your code here
        System.out.println("There are 30 problems to solve");
        readFile();


    }
}
