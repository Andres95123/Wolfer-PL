package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Ensamblador {

    BufferedWriter writer;

    public Ensamblador() {
        try {
            writer = new BufferedWriter(new FileWriter("resources/output.asm"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciarEnsamblaje(List<codigoIntermedio> codigo) {
        try {
            for (codigoIntermedio element : codigo) {

                ensamblar(element);

            }
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("ERROR CRITICO AL ENSAMBLAR");
        }

    }

    private void ensamblar(codigoIntermedio codigo) throws IOException {
        switch (codigo.operation) {
            case ADD:
                write("SET " + codigo.args[0] + " , D0");
                write("ADD " + codigo.args[1] + " , D0");
                write("SET D0 , " + codigo.args[2]);
                break;
            case SUB:
                write("SET " + codigo.args[0] + " , D0");
                write("SUB " + codigo.args[1] + " , D0");
                write("SET D0 , " + codigo.args[2]);
                break;
            case MUL:
                write("SET " + codigo.args[0] + " , D0");
                write("MUL " + codigo.args[1] + " , D0");
                write("SET D0 , " + codigo.args[2]);
                break;
            case DIV:
                write("SET " + codigo.args[0] + " , D0");
                write("DIV " + codigo.args[1] + " , D0");
                write("SET D0 , " + codigo.args[2]);
                break;
            case ASSIGN:
                write("SET " + codigo.args[0] + " , " + codigo.args[2]);
                break;

        }
    }

    private void write(String string) {
        try {
            writer.write(string + "\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
