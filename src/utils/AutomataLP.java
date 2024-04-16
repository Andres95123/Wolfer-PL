package utils;

import java.util.Stack;

public class AutomataLP extends AutomataMaker {

    public void make() {
        Nodo nodoPrincipal = new Nodo();

        Nodo tmp1 = nodoPrincipal.addNext(Comands.ID).addNext(Comands.ASSIGN);
        Nodo tmp2 = tmp1.addNext(Comands.NUM);
        tmp1.addNext(Comands.ID, tmp2);

        tmp2.addNext(Comands.ENDLINE, nodoPrincipal);
        tmp2.addNext(Comands.ADD, tmp1);
        tmp2.addNext(Comands.SUB, tmp1);
        tmp2.addNext(Comands.MUL, tmp1);
        tmp2.addNext(Comands.DIV, tmp1);

        makeAutomata(nodoPrincipal);
    }

    @Override
    protected void execute() {
        Stack<Token> stack = new Stack<>();
        while (!this.stack.isEmpty()) {
            stack.push(this.stack.pop());
        }
        Token token = stack.pop();
        String tmpVar = null;
        String oldVar = null;
        switch (token.getCommand()) {
            case ID:
                while (!stack.isEmpty()) {
                    Token nextToken = stack.pop();
                    if (nextToken.getCommand() == Comands.ASSIGN) {
                        Token value = stack.pop();
                        saveVar(token.getArgs()[0], value.getArgs()[0]);
                        addCodigoInt(Operation.ASSIGN, value.getArgs()[0], "", token.getArgs()[0]);
                    } else {

                        Token operator = nextToken;
                        if (operator.getCommand() == Comands.ENDLINE) {
                            break;
                        }
                        Token value = stack.pop();

                        tmpVar = makeTmpVar();
                        if (oldVar == null) {
                            addCodigoInt(Operation.valueOf(operator.getCommand().name()),
                                    variables.get(token.getArgs()[0]) + "", value.getArgs()[0], tmpVar);
                            oldVar = tmpVar;
                        } else {
                            addCodigoInt(Operation.valueOf(operator.getCommand().name()), oldVar, value.getArgs()[0],
                                    tmpVar);
                            oldVar = tmpVar;
                        }
                    }
                }
                break;

            default:
                break;
        }

    }
}
