package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class AutomataMaker {

    private static int[][] matrix;
    private int currentState = 1;
    Stack<Token> stack = new Stack<>();
    public HashMap<String, String> variables = new HashMap<>();
    public List<codigoIntermedio> codigoIntermedio = new ArrayList<>();
    private int tmpVar = 0;

    public AutomataMaker() {
        Nodo.makeError();
    }

    public void makeAutomata(Nodo origen) {
        matrix = origen.getMatrix();
        System.out.println(Arrays.deepToString(matrix));

    }

    public void runAutomata(Token token) {
        stack.add(token);
        System.out.println(token + " | " + currentState);
        currentState = matrix[currentState][token.getCommand().ordinal()];

        if (currentState == 1) {
            execute();
        }
    }

    protected void execute() {

    }

    protected void saveVar(String v, String value) {
        variables.put(v, value);
    }

    protected void addCodigoInt(Operation operation, String arg1, String arg2, String save) {
        codigoIntermedio.add(new codigoIntermedio(operation, arg1, arg2, save));
    }

    protected String makeTmpVar() {
        String tmpVar = "tmp" + this.tmpVar++;
        saveVar(tmpVar, null);
        return tmpVar;
    }
}
