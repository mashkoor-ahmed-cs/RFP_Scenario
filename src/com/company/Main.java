package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("There are 30 problems to solve");
        String problemFileName = "problems.rfp";

        ReadTextFile readTextFile = new ReadTextFile(problemFileName);
        ArrayList<String> questions = readTextFile.getQuestions();

        ArrayList<Problem> problems = new ArrayList<Problem>();
        for(String question : questions) {
            problems.add(Parser.parseString(question));
        }
        System.out.println();
    }
}
