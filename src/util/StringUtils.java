package util;

public class StringUtils {
    // Verifica se a String � null ou vazia ou s� tem espa�os em branco
    public static boolean isNullOrBlank(String s) {
        return (s == null || s.trim().equals(""));
    }

    // Verifica se a String � null ou vazia
    // Pode ser utilizado como suporte em APIs menores que 9 do android onde n�o est� disponivel o met�do de String isEmpty()
    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.equals(""));
    }
	

}
