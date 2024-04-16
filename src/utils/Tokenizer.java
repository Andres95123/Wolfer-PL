package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {
    List<String> tokensRaw = new ArrayList<>();
    List<Token> tokens = new ArrayList<>();

    public Tokenizer(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tokensRaw.addAll(Arrays.asList(line.split("\\s+")));
                tokensRaw.add("\n");
            }
        }

        // Transform raw tokens to Token objects

        for (int i = 0; i < tokensRaw.size(); i++) {
            String token = tokensRaw.get(i);

            Comands comando = parseComand(token);

            if (comando == Comands.ID) {
                tokens.add(new Token(Comands.ID, new String[] { token }));
            } else if (comando == Comands.NUM) {
                tokens.add(new Token(Comands.NUM, new String[] { token }));
            } else {
                tokens.add(new Token(parseComand(token), new String[] {}));
            }

        }

    }

    private Comands parseComand(String token) {
        if (token.equals("=")) {
            return Comands.ASSIGN;
        } else if (token.equals("+")) {
            return Comands.ADD;
        } else if (token.equals("-")) {
            return Comands.SUB;
        } else if (token.equals("*")) {
            return Comands.MUL;
        } else if (token.equals("/")) {
            return Comands.DIV;
        } else if (token.equals("if")) {
            return Comands.IF;
        } else if (token.equals("else")) {
            return Comands.ELSE;
        } else if (token.equals("while")) {
            return Comands.WHILE;
        } else if (token.equals("\n")) {
            return Comands.ENDLINE;
        } else if (token.matches("-?[0-9]+")) {
            return Comands.NUM;
        } else if (token.matches("[a-zA-Z]+")) {
            return Comands.ID;
        } else {
            throw new IllegalArgumentException("Unknown command: " + token);
        }
    }

    public boolean hasNext() {
        return !tokens.isEmpty();
    }

    public Token next() {
        return tokens.remove(0);
    }
}
