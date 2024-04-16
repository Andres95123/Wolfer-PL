package main;

import java.io.File;

import utils.Parser;
import utils.Tokenizer;

public class App {
    public static void main(String[] args) throws Exception {

        // Gramática del compilador
        Tokenizer tokenizer = new Tokenizer(new File("resources/input.txt"));
        // Semántica y Sintáctica del compilador
        Parser parser = new Parser(tokenizer);
        parser.parse();

    }
}
