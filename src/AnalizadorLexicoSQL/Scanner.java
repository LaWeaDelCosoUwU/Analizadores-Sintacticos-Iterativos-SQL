package src.AnalizadorLexicoSQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    // Mapa que asocia palabras reservadas con sus tipos de tokens correspondientes
    private static final Map<String, TipoToken> palabrasReservadas;

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("SELECT", TipoToken.SELECT);
        palabrasReservadas.put("DISTINCT", TipoToken.DISTINCT);
        palabrasReservadas.put("FROM", TipoToken.FROM);
    }

    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    public Scanner(String source) {
        this.source = source + " ";
    }

    public List<Token> scan() throws Exception {

        int estado = 0, linea = 0;
        String lexema = "";
        char c;

        for (int i = 0; i < source.length(); i++) {
            
            c = source.charAt(i);
            
            //Tiene que estár por fuera del switch porque sino llevaría mal la cuenta de las lineas
            if (c == '\n')
                linea++;

            switch (estado) {
                
                case 0:



                    break;

            }
        }

        return tokens;
    }
}