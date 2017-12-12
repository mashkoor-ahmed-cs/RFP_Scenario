package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("There are 30 problems to solve");
        String problemFileName = "problems.rfp";
        String outputFileName = "output.txt";

        ReadTextFile readTextFile = new ReadTextFile(problemFileName);
        ArrayList<String> questions = readTextFile.getQuestions();

        ArrayList<Problem> problems = new ArrayList<Problem>();
        for (String question : questions) {
            problems.add(Parser.parseString(question));
        }
        System.out.println();

        JFrame frame = new JFrame("Draw problem #1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ShapeDrawer(problems.get(0)));
        frame.pack();
        frame.setSize(new Dimension(420, 240));
        frame.setVisible(true);

        WriteTextFile writer = new WriteTextFile(outputFileName);
        for(Problem p : problems) {
            String solution = p.solveProblem();
            System.out.println(solution);
            writer.writeLine(solution);
        }
        writer.closeFile();
    }
}
