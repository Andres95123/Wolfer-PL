package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nodo {

    private static int ids = 0;
    private static List<Nodo> nodos = new ArrayList<>();
    private static Nodo error;

    private Nodo[] nexts;
    private int idNodo;

    public Nodo() {

        idNodo = ids++;

        this.nexts = new Nodo[Comands.values().length];

        nodos.add(this);
    }

    public static void makeError() {
        error = new Nodo();
    }

    public Nodo next(Comands index) {
        return nexts[index.ordinal()];
    }

    public Nodo addNext(Comands index) {
        nexts[index.ordinal()] = new Nodo();
        return nexts[index.ordinal()];
    }

    public Nodo addNext(Comands index, Nodo nodo) {
        nexts[index.ordinal()] = nodo;
        return nexts[index.ordinal()];
    }

    public int[] getArray() {
        int[] array = new int[Comands.values().length];
        for (int i = 0; i < Comands.values().length; i++) {
            if (nexts[i] != null) {
                array[i] = nexts[i].idNodo;
            }
        }
        return array;
    }

    public static int[][] getMatrix() {
        int[][] matrix = new int[nodos.size()][Comands.values().length];
        for (int i = 0; i < nodos.size(); i++) {
            matrix[i] = nodos.get(i).getArray();
        }
        return matrix;
    }

}
