package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner();
        System.out.println("There are 30 problems to solve.");
        System.out.println("Which question do you want to see? Enter number from 0-30: ");
        int problemToShow = scanner.nextInt();

        String problemFileName = "problems.rfp";
        String outputFileName = "output.txt";

        ReadTextFile readTextFile = new ReadTextFile(problemFileName);
        ArrayList<String> questions = readTextFile.getQuestions();

        ArrayList<Problem> problems = new ArrayList<Problem>();
        for (String question : questions) {
            problems.add(Parser.parseString(question));
        }
        System.out.println();



        WriteTextFile writer = new WriteTextFile(outputFileName);
        for(int i = problemToShow - 1; i < problemToShow; i ++) {
            Problem p = problems.get(i);
            String solution = p.solveProblem();
            System.out.println(p.problemNo + ": Area Covered: " + (int) p.getRoom().areaCovered() + "%, Total Real Cost: " + p.getTotalRealCost());
            writer.writeLine(solution);
        }

//        problems.get(2).solveProblem();

        writer.closeFile();

        JFrame frame = new JFrame("Visualiser");


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ShapeDrawer(problems.get(problemToShow - 1)));
        frame.pack();
        frame.setSize(new Dimension(1200, 600));
        frame.setVisible(true);
    }
}
