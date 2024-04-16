package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class AutomataLambda<Alfabeto extends Enum<Alfabeto>> {

    Alfabeto[] alfabeto;
    List<int[]> estados;
    int[][] transiciones;
    int[][] lambdaTransiciones;
    int estadoInicial;
    int[] estadosFinales;

    public AutomataLambda(Alfabeto[] alfabeto, int[][] transiciones, int[][] lambdaTransiciones, int estadoInicial,
            int[] estadosFinales) {
        this.alfabeto = alfabeto;
        this.estados = new ArrayList<>();
        this.estados.add(new int[] { estadoInicial });

        this.transiciones = transiciones;
        this.lambdaTransiciones = lambdaTransiciones;

        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;

        transformToFiniteAutomat();
    }

    private void transformToFiniteAutomat() {

        List<int[]> explorados = new ArrayList<>();
        List<int[]> noExplorados = new ArrayList<>();
        HashMap<String, Integer> hash = new HashMap<>();
        int numEstados = 0;
        List<int[]> newTransiciones = new ArrayList<>();

        noExplorados.add(lambdaContains(new int[] { estadoInicial }));
        hash.put(Arrays.toString(lambdaContains(new int[] { estadoInicial })), numEstados++);

        while (!noExplorados.isEmpty()) {

            int[] estado = noExplorados.remove(0);
            int[] valor = new int[alfabeto.length];

            // Añadir
            explorados.add(estado);

            for (int j = 0; j < alfabeto.length; j++) {

                int[] newState = new int[alfabeto.length];
                for (int i = 0; i < newState.length; i++) {

                    int index = estado[i % estado.length];
                    newState[i] = transiciones[index][alfabeto[j].ordinal()];

                }

                newState = lambdaContains(newState);
                if (!contains(explorados, newState) && !contains(noExplorados, newState)) {
                    noExplorados.add(newState);
                    hash.put(Arrays.toString(newState), numEstados++);
                }

                valor[j] = hash.get(Arrays.toString(newState));

            }

            newTransiciones.add(valor);

        }

        // estados = newStates;

        System.out.println(Arrays.deepToString(hash.entrySet().toArray()));
        System.out.println(Arrays.deepToString(newTransiciones.toArray()));
        transiciones = newTransiciones.toArray(new int[newTransiciones.size()][]);

    }

    public int[] lambdaContains(int[] estados) {
        List<Integer> nuevosEstados = new ArrayList<>();

        for (int integer : estados) {
            nuevosEstados.add(integer);
        }

        for (int i = 0; i < estados.length; i++) {
            for (int j = 0; j < lambdaTransiciones[estados[i]].length; j++) {
                nuevosEstados.add(lambdaTransiciones[nuevosEstados.get(i)][j]);
            }
        }

        return nuevosEstados.stream().distinct().mapToInt(Integer::intValue).toArray();

    }

    public boolean contains(List<int[]> list, int[] target) {
        for (int[] arr : list) {
            if (Arrays.equals(arr, target)) {
                return true;
            }
        }
        return false;
    }

    public int[] fuseArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i];
        }
        for (int i = 0; i < arr2.length; i++) {
            result[arr1.length + i] = arr2[i];
        }
        return result;
    }

    public void transicion(Alfabeto simbolo) {
        for (int i = 0; i < estados.size(); i++) {
            int[] estadoExplorar = estados.get(i);
            for (int j = 0; j < estadoExplorar.length; j++) {
                estadoExplorar[j] = transiciones[estadoExplorar[j]][simbolo.ordinal()];
                // lamdaTransicion(i);
            }
        }
        System.out.println(Arrays.deepToString(estados.toArray()));
    }

    // private void lamdaTransicion(int estado) {
    // if (lambdaTransiciones != null && lambdaTransiciones[estado].length > 0) {
    // int[] nuevosEstados = new int[estados.get(estado).length +
    // lambdaTransiciones[estado].length];
    // // NuevoEstados es el resultado de fusionar estados.get(estado) y
    // // lambdaTransiciones[estado]
    // for (int i = 0; i < estados.get(estado).length; i++) {
    // nuevosEstados[i] = estados.get(estado)[i];
    // }
    // for (int i = 0; i < lambdaTransiciones[estado].length; i++) {
    // nuevosEstados[estados.get(estado).length + i] =
    // lambdaTransiciones[estado][i];
    // }
    // // Sustituye el antiguo estado por el nuevo
    // estados.set(estado, nuevosEstados);
    // }
    // }

    public boolean isFinal() {
        for (int i = 0; i < estadosFinales.length; i++) {
            for (int j = 0; j < estados.get(i).length; j++) {
                if (estados.get(i)[j] == estadosFinales[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Define the alphabet
        enum Alphabet {
            B0, B1;
        }

        // Define the transition table
        int[][] transitions = {

                { 0, 1 },
                { 1, 2 },
                { 2, 2 },

        };

        // Define the lambda transition table
        int[][] lambdaTransitions = {
                { 0 },
                { 0 },
                { 0 },
        };

        // Define the initial state
        int initialState = 0;

        // Define the final states
        int[] finalStates = { 2 };

        // Create an instance of AutomataLambda
        AutomataLambda<Alphabet> automata = new AutomataLambda<>(Alphabet.values(), transitions, lambdaTransitions,
                initialState,
                finalStates);

        // Perform transitions
        automata.transicion(Alphabet.B1);
        automata.transicion(Alphabet.B1);
        automata.transicion(Alphabet.B1);

        // Check if the current state is a final state
        boolean isFinal = automata.isFinal();
        System.out.println("Is final state: " + isFinal);
    }
}