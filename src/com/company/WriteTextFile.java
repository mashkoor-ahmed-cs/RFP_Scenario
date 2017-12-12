package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class WriteTextFile {
    public final String username = "sabadell";
    public final String password = "dg9o8luhuq1rpf8v3aanm81c6f";
    private PrintWriter writer;

    public WriteTextFile(String filename) {
        writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(username);
        writer.println(password);
    }

    public void writeLine(String line) {
        writer.println(line);
    }

    public void closeFile() {
        writer.close();
    }
}
