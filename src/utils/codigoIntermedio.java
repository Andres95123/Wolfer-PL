package utils;

import java.util.Arrays;

public class codigoIntermedio {

    Operation operation;
    String[] args;

    public codigoIntermedio(Operation operation, String val1, String val2, String save) {
        this.operation = operation;
        this.args = new String[] { val1, val2, save };
    }

    public String toString() {
        return "codigoIntermedio [operation=" + operation + ", args=" + Arrays.toString(args) + "]";
    }

}
