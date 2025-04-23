package com.anavi.actingcoach;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PromptGenerator {

    private static final List<String> startPrompts = new ArrayList<>();
    private static final List<String> endPrompts = new ArrayList<>();
    private static final Random random = new Random();

    static {
        startPrompts.add("Start packing! We're going to Paris.");
        startPrompts.add("This is not what we agreed on.");
        startPrompts.add("You forgot the most important part.");
        startPrompts.add("What do you mean you can't move?");

        endPrompts.add("I guess we were never meant to be after all.");
        endPrompts.add("Now you know what you have to do.");
        endPrompts.add("I think I'll only pay for half of that.");
        endPrompts.add("After which we can go for some ice cream.");
    }

    public static String getRandomStartPrompt() {
        return startPrompts.get(random.nextInt(startPrompts.size()));
    }

    public static String getRandomEndPrompt() {
        return endPrompts.get(random.nextInt(endPrompts.size()));
    }

    public static void addStartPrompt(String prompt) {
        if (prompt != null && !prompt.isBlank()) {
            startPrompts.add(prompt);
        }
    }

    public static void addEndPrompt(String prompt) {
        if (prompt != null && !prompt.isBlank()) {
            endPrompts.add(prompt);
        }
    }

    public static List<String> getAllStartPrompts() {
        return new ArrayList<>(startPrompts);
    }

    public static List<String> getAllEndPrompts() {
        return new ArrayList<>(endPrompts);
    }
}
