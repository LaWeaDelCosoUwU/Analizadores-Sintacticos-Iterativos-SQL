package src.AnalizadorSintacticoDescendenteRecursivo;

import src.AnalizadorLexicoSQL.TipoToken;
import src.AnalizadorLexicoSQL.Token;

import java.util.List;

public class ASDR {

    private List<Token> tokens;
    private boolean error = false;
    private int i = 0;
    public ASDR(List<Token> tokens) {

        this.tokens = tokens;

    }

    public void analizar(){

        Q();

        if(!error)
            System.out.println("Si Jala");
        else
            System.out.println("No Jala");

    }

    private void Q() {

        if(tokens.get(i).getTipo()== TipoToken.SELECT) {
            i++;

        }else{
            error = true;
        }

    }

}
