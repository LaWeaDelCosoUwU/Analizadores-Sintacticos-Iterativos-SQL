package src.AnalizadorSintacticoConAutomata;

import src.AnalizadorLexicoSQL.TipoToken;
import src.AnalizadorLexicoSQL.Token;

import java.util.List;

public class ASCA {

    private final List<Token> tokens;

    public ASCA(List<Token> tokens) {

        this.tokens = tokens;

    }

    public void analizar() {

        int i = 0, estado = 0;
        boolean error = false;

        while((tokens.get(i).getTipo()!=TipoToken.EOF)&&(!error)){

            switch (estado){

                case 0:

                    if(tokens.get(i).getTipo()==TipoToken.SELECT) {
                        estado = 1;
                        i++;
                    }else{
                        estado = 12;
                        System.out.println("Moviendose al estado: "+estado);
                    }


                    break;

                case 1:

                    if(tokens.get(i).getTipo()==TipoToken.DISTINCT) {
                        estado = 2;
                        i++;
                    }else if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER) {
                        estado = 3;
                        i++;
                    }else if(tokens.get(i).getTipo()==TipoToken.STAR){
                        estado = 4;
                        i++;
                    }else
                        estado = 12;


                    break;

                case 2:

                    if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
                        estado = 3;
                        i++;
                    }else
                        estado = 12;


                    break;

                case 3:

                    if(tokens.get(i).getTipo()==TipoToken.DOT) {
                        estado = 5;
                        i++;
                    } else if (tokens.get(i).getTipo()==TipoToken.COMMA) {
                        estado = 6;
                        i++;
                    } else if(tokens.get(i).getTipo()==TipoToken.FROM){
                        estado = 8;
                        i++;
                    }else
                        estado = 12;

                    break;

                case 4:

                    if(tokens.get(i).getTipo()==TipoToken.FROM){
                        estado = 8;
                        i++;
                    }else
                        estado = 12;

                    break;

                case 5:

                    if (tokens.get(i).getTipo() == TipoToken.IDENTIFIER) {
                        estado = 7;
                        i++;
                    }else
                        estado = 12;

                    break;

                case 6:

                    if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER) {
                        estado = 3;
                        i++;
                    }else
                        estado = 12;

                    break;

                case 7:

                    if(tokens.get(i).getTipo()==TipoToken.COMMA) {
                        estado = 6;
                        i++;
                    }else if(tokens.get(i).getTipo()==TipoToken.FROM){
                        estado = 8;
                        i++;
                    }else
                        estado = 12;

                    break;

                case 8:

                    if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
                        estado = 9;
                        i++;
                    }else
                        estado = 12;

                    break;

                case 9:

                    if(tokens.get(i).getTipo()==TipoToken.COMMA) {
                        estado = 10;
                        i++;
                    }else
                        estado = 12;

                    break;

                case 10:

                    if(tokens.get(i).getTipo()==TipoToken.IDENTIFIER){
                        estado = 9;
                        i++;
                    }else
                        estado = 12;

                    break;

                default:
                    System.out.println("No Jala");
                    error = true;
                    break;

            }

        }

        if(!error){
            System.out.println("Si Jala ASCA");
        }

    }

}
