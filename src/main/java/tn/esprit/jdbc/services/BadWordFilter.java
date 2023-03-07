package tn.esprit.jdbc.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BadWordFilter {
    public static final List<String> BAD_WORDS = new ArrayList<>(Arrays.asList("no", "bad", "word"));

    public static String filter(String input) {
        for (String word : BAD_WORDS) {
            String patternString = "(?i)\\b" + word + "\\b|(?i)\\b" + word + "[a-zA-Z]*\\b|(?i)\\b[a-zA-Z]*" + word + "[a-zA-Z]*\\b";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(input);
            StringBuilder sb = new StringBuilder();
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                for (int i = 0; i < end - start; i++) {
                    sb.append("*");
                }
            }
            input = matcher.replaceAll(sb.toString());
        }
        return input;
    }
}
