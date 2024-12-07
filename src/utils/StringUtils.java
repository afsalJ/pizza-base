package utils;

public class StringUtils {

    public static String capitalize(String str){
        str = str.toLowerCase();
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
