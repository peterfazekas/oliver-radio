package hu.radio.domain.service;

import java.util.List;
import java.util.stream.IntStream;

public class MessageHelper {

    private static String merged;

    public static String mergeMessages(List<String> messages) {
        merged = messages.get(0);
        messages.forEach(MessageHelper::mergeMessage);
        return merged;
    }

    private static void mergeMessage(String message) {
        StringBuilder mergedMessage = new StringBuilder();
        IntStream.range(0, merged.length())
                .forEach(i -> mergedMessage.append(getCharacter(i, message, merged)));
        merged = mergedMessage.toString();
    }

    private static char getCharacter(int i, String message, String merged) {
        return message.charAt(i) != '#' ? message.charAt(i) : merged.charAt(i);
    }

    public static String getAccumulatedData(String message) {
        String[] words = message.split(" ");
        String firstWord = words[0];
        String result = "Nincs információ";
        if (firstWord.contains("/")) {
            String[] items = firstWord.split("/");
            if (szame(items[0]) && szame(items[1])) {
                int sum = Integer.parseInt(items[0]) + Integer.parseInt(items[1]);
                result = "A megfigyelt egyedek száma: " + sum;
            }
        }
        return result;
    }

    private static boolean szame(String szo) {
        boolean valasz = true;
        for (int i = 0; i < szo.length(); i++) {
            if (szo.charAt(i) < '0' || szo.charAt(i) > '9') {
                valasz = false;
            }
        }
        return valasz;
    }
}

