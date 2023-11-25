package src.AnalizadorLexicoSQL;

// Enumeración que define los tipos de tokens posibles
public enum TipoToken {
    
    // Tokens de un sólo caracter
    COMMA, DOT, STAR,

    // Tokens de palabra reservada
    SELECT, FROM, DISTINCT,

    // Literales
    IDENTIFIER,

    // Token de fin de archivo
    EOF
    
}
