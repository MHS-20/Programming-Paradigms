package utils;

public class Logger {
    public static void logPrintString(final String content){
        System.out.format("[Print] %s\n",content);
    }

    public static void logPrintStrings(final String[] contents){
        for (String content: contents){
            System.out.format("[Print] %s\n",content);
        }
    }
}
