package utils;

public class Parser {
    Tokenizer tokenizer;

    public Parser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public void parse() {
        // Generación del automata
        AutomataLP automataMaker = new AutomataLP();
        automataMaker.make();
        // Recorrer el automat y ejecutarlo
        while (tokenizer.hasNext()) {
            automataMaker.runAutomata(tokenizer.next());
        }

        // Ensamblar el codigo intermedio a codigo máquina
        System.out.println(automataMaker.variables);
        new Ensamblador().iniciarEnsamblaje(automataMaker.codigoIntermedio);
    }

}
