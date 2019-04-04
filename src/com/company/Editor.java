package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Editor {

    private Scanner sc;
    private ArrayList <String> textLines;
    private String textInput;
    private String encodingName;

    public Editor(){
        sc = new Scanner(System.in);
        textLines = new ArrayList<>();
        textInput = "";
        encodingName = "UTF8";
    }

    public void run(){
        this.input();
        this.chooseEncoding();
        this.saveFile();
    }
    private void input(){
        while(true){
            textLines.add(textInput);
            textInput= sc.nextLine();
            if(textInput.equals(":wq")) break;
        }
    }

    private void chooseEncoding(){
        System.out.println("Wybierz kodowanie:\n1. UTF-8\n2. ISO-88592\n3. CP-1250");

        int encodingChoice;
        while(true) {
            encodingChoice = sc.nextInt();
            sc.nextLine();
            if (encodingChoice == 1) break;
            else if (encodingChoice == 2) {
                encodingName = "ISO-8859-2";
                break;
            } else if (encodingChoice == 3) {
                encodingName = "Windows-1250";
                break;
            }
        }
    }

    private void saveFile(){
        System.out.println("Podaj nazwę pliku: ");
        String Filename = sc.nextLine();

        try {
            File fileDir = new File(Filename);

            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir), encodingName));

            for (String line:textLines) {
                out.append(line).append("\r\n");
            }

            out.flush();
            out.close();

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
