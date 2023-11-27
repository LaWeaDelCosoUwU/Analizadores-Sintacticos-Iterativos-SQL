package src.AnalizadorSintacticoDescendenteIterativo;

import src.AnalizadorLexicoSQL.TipoToken;
import src.AnalizadorLexicoSQL.Token;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class ASDI {

    private final List<Token> tokens;
    private Map<String, Map<String, String>> tabla = new HashMap<>();


    public ASDI(List<Token> tokens){

        this.tokens = tokens;

        //  NoTer\Ter  | SELECT | DISTINCT | * | id | . | , | FROM | $ |
        //tabla.put(" ", crearFila("SELECT", "DISTINCT", "*", "id", ".", ",", "FROM", "$"));
        tabla.put("Q", crearFila("SELECT D FROM T", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"));
        tabla.put("D", crearFila("ERROR", "DISTINCT D", "P", "P", "ERROR", "ERROR", "ERROR", "ERROR"));
        tabla.put("P", crearFila("ERROR", "ERROR", "*", "A", "ERROR", "ERROR", "ERROR", "ERROR"));
        tabla.put("A", crearFila("ERROR", "ERROR", "ERROR", "A2 A1", "ERROR", "ERROR", "ERROR", "ERROR"));
        tabla.put("A1", crearFila("ERROR", "ERROR", "ERROR", "ERROR", "ERROR", ", A", "EPS", "ERROR"));
        tabla.put("A2", crearFila("ERROR", "ERROR", "ERROR", "id A3", "ERROR", "ERROR", "ERROR", "ERROR"));
        tabla.put("A3", crearFila("ERROR", "ERROR", "ERROR", "ERROR", ". id", "EPS", "EPS", "ERROR"));
        tabla.put("T", crearFila("ERROR", "ERROR", "ERROR", "T1 T2", "ERROR", "ERROR", "ERROR", "ERROR"));
        tabla.put("T1", crearFila("ERROR", "ERROR", "ERROR", "ERROR", "ERROR", ", T", "ERROR", "EPS"));
        tabla.put("T2", crearFila("ERROR", "ERROR", "ERROR", "id T3", "ERROR", "ERROR", "ERROR", "ERROR"));
        tabla.put("T3", crearFila("ERROR", "ERROR", "ERROR", "id", "EPS", "ERROR", "ERROR", "EPS"));

    }

    private Map<String, String> crearFila(String... valores) {
        Map<String, String> fila = new HashMap<>();
        fila.put("SELECT", valores[0]);
        fila.put("DISTINCT", valores[1]);
        fila.put("*", valores[2]);
        fila.put("id", valores[3]);
        fila.put(".", valores[4]);
        fila.put(",", valores[5]);
        fila.put("FROM", valores[6]);
        fila.put("$", valores[7]);
        return fila;
    }

    public void analizar(){

        int i = 0,j = 0;
        Stack<String> pila = new Stack<>();
        String column = "", busquedaTabla = "", objetoPila = "";
        String[] cadenaDividida;
        pila.push("$");
        pila.push("Q");


        while(!pila.peek().equals("$")) {

            if (tokens.get(i).getTipo() == TipoToken.SELECT){
                column = "SELECT";
            }else if (tokens.get(i).getTipo() == TipoToken.DISTINCT){
                column = "DISTINCT";
            }else if (tokens.get(i).getTipo() == TipoToken.STAR) {
                column = "*";
            }else if (tokens.get(i).getTipo() == TipoToken.IDENTIFIER) {
                column = "id";
            }else if (tokens.get(i).getTipo() == TipoToken.DOT){
                column = ".";
            }else if(tokens.get(i).getTipo() == TipoToken.COMMA){
                column = ",";
            }else if (tokens.get(i).getTipo() == TipoToken.FROM){
                column = "FROM";
            }else{
                column = "$";
            }

            objetoPila = pila.pop();
            busquedaTabla = tabla.get(objetoPila).get(column);

            if(objetoPila.equals(busquedaTabla))
                i++;

            if(busquedaTabla.equals("ERROR")){
                System.out.println("No jala");
                return;
            } else if(busquedaTabla.equals("EPS")){

            }else{

                if(busquedaTabla.indexOf(' ') != -1){

                    cadenaDividida = busquedaTabla.split(" ");

                    for(j=cadenaDividida.length; j>0; j--)
                        pila.push(cadenaDividida[j-1]);

                }else{

                    pila.push(busquedaTabla);

                }

            }

        }

    }

}
