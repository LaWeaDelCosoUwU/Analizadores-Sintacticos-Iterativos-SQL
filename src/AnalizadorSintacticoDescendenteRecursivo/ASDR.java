package src.AnalizadorSintacticoDescendenteRecursivo;

import src.AnalizadorLexicoSQL.TipoToken;
import src.AnalizadorLexicoSQL.Token;

import java.util.List;

public class ASDR {

    private final List<Token> tokens;
    private boolean error = false, finale = false;
    private int i = 0;
    public ASDR(List<Token> tokens) {

        this.tokens = tokens;

    }

    public void analizar(){

        System.out.println(""+tokens.size()+""+i);

        Q();

        if(!error)
            System.out.println("Si Jala ASDR");
        else
            System.out.println("No Jala");

    }

    //Q ->  SELECT D FROM T
    private void Q() {

        System.out.println(""+i);

        match(TipoToken.SELECT);
        D();
        match(TipoToken.FROM);
        T();

    }

    //D -> DISTINCT P | P
    private void D() {

        if(error)
            return;

        if(tokens.get(i).getTipo()==TipoToken.DISTINCT){
            match(TipoToken.DISTINCT);
            P();
        }else if(tokens.get(i).getTipo()==TipoToken.STAR || tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
            P();
        }else
            error = true;

    }

    //P -> * | A
    private void P(){

        if(error)
            return;

        if(tokens.get(i).getTipo()==TipoToken.STAR){
            match(TipoToken.STAR);
        }else if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
            A();
        }else
            error = true;

    }

    //A -> A2A1
    private void A(){

        if(error)
            return;

        A2();
        A1();

    }

    //A2 -> id A3
    private void A2() {

        if(error)
            return;

        if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER);
            A3();
        }else
            error = true;

    }

    //A3 -> .id | EMPTY
    private void A3(){

        if(error)
            return;

        if(tokens.get(i).getTipo()==TipoToken.DOT){
            match(TipoToken.DOT);
            match(TipoToken.IDENTIFIER);
        }

    }

    //A1 -> ,A | EMPTY
    private void A1(){

        if(error)
            return;

        if(tokens.get(i).getTipo()==TipoToken.COMMA){
            match(TipoToken.COMMA);
            A();
        }

    }

    //T -> T2T1
    private void T() {

        if(error)
            return;

        T2();
        T1();

    }

    //T2 -> id T3
    private void T2() {

        if(error)
            return;

        if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER);
            T3();
        }else
            error = true;

    }

    //T3 -> id | EMPTY
    private void T3() {

        if(error || (i==tokens.size()))
            return;

        if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
            match(TipoToken.IDENTIFIER);
        }

    }

    //T1 -> , T | EMPTY
    private void T1() {

        if(error)
            return;

        if(tokens.get(i).getTipo()==TipoToken.COMMA){
            match(TipoToken.COMMA);
            T();
        }

    }

    private void match(TipoToken t){

        System.out.println(""+i);

        if(tokens.get(i).getTipo() == t){
            i++;
            if(i==tokens.size()+1)
                finale = true;
        }else
            error = true;

    }

}
