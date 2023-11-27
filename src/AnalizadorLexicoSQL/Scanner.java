package src.AnalizadorLexicoSQL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {

    // Mapa que asocia palabras reservadas con sus tipos de tokens correspondientes
    private static final Map<String, TipoToken> palabrasReservadas;
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    public Scanner(String source) {
        this.source = source + " ";
    }

    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("SELECT", TipoToken.SELECT);
        palabrasReservadas.put("DISTINCT", TipoToken.DISTINCT);
        palabrasReservadas.put("FROM", TipoToken.FROM);
    }

    public List<Token> scan() throws Exception {

        int estado = 0, linea = 0, i = 0;
        String lexema = "";
        char c;

        for (i = 0; i < source.length(); i++) {
            
            c = source.charAt(i);
            
            //Tiene que estár por fuera del switch porque sino llevaría mal la cuenta de las lineas
            if (c == '\n')
                linea++;

            switch (estado) {
                
                case 0:

                    if(c == '*'){
                        tokens.add(new Token(TipoToken.STAR, "*", i + 1));
                    }
                    else if(c == ','){
                        tokens.add(new Token(TipoToken.COMMA, ",", i + 1));
                    }
                    else if(c == '.'){
                        tokens.add(new Token(TipoToken.DOT, ".", i + 1));
                    }
                    else if(Character.isAlphabetic(c) || Character.isDigit(c)){

                        estado = 1;
                        lexema += c;

                    }

                    break;

                case 1:

                    if(Character.isLetterOrDigit(c) || isSpecialCharacter(c)){

                        lexema += c;

                    }else{

                        TipoToken tt = palabrasReservadas.get(lexema);

                        if(tt == null){

                            Token t = new Token(TipoToken.IDENTIFIER, lexema);
                            tokens.add(t);

                        }else{

                            Token t = new Token(tt, lexema);
                            tokens.add(t);

                        }

                        estado = 0;
                        lexema = "";
                        i--;

                    }

                    break;
            }
        }

        return tokens;
    }

    private boolean isSpecialCharacter(char c) {

        return c == '"' || c == '-' || c == '_';

    }
}