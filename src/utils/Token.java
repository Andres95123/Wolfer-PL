package utils;

import java.util.Arrays;

public class Token {
    private Comands command;
    private String[] args;

    public Token(Comands command, String[] args) {
        this.command = command;
        this.args = args;
    }

    public Comands getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public String toString() {
        return "Token [command=" + command + ", args=" + Arrays.toString(args) + "]";
    }
}
