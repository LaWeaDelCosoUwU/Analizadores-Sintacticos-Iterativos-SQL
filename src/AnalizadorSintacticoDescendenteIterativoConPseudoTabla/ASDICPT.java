package src.AnalizadorSintacticoDescendenteIterativoConPseudoTabla;

import src.AnalizadorLexicoSQL.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ASDICPT {

    private final List<Token> tokens;
    private Map<String, Map<String, String>> tabla = new HashMap<>();
    public ASDICPT(List<Token> tokens){

        this.tokens = tokens;

        tabla.put("Q", crearFila("SELECT D FROM T", ""));
        tabla.put("D", crearFila("DISTINCT P",      "P"));
        tabla.put("P", crearFila("*",               "A"));
        tabla.put("A", crearFila("A2 A1",           ""));
        tabla.put("A1", crearFila(", A",            "ERROR"));
        tabla.put("A2", crearFila("IDENTIFIER A3",  "ERROR"));
        tabla.put("A3", crearFila(". IDENTIFIER",    ""));
        tabla.put("T", crearFila("T2 T1",           "ERROR"));
        tabla.put("T1", crearFila(", T",          ""));
        tabla.put("T2", crearFila("ERROR",          "ERROR"));
        tabla.put("T3", crearFila("ERROR",          "ERROR"));

    }

    private Map<String, String> crearFila(String... valores) {

        Map<String, String> fila = new HashMap<>();

        fila.put("Produccion0", valores[0]);
        fila.put("produccion1", valores[1]);

        return fila;
    }

    public void analizar(){

    }

}