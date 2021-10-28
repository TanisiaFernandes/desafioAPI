package br.com.treinamentoAPI.utils;

public class Utils {

    public static String getSchemaBasePath(String pack, String nameSchema){
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/" +
                "treinamentoAPI/tests/"
                + pack
                + "/schema/"
                + nameSchema
                + ".json";
    }
}
