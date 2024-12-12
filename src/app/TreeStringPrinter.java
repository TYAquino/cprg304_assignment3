package app;

import java.util.HashMap;
import java.util.HashSet;

public class TreeStringPrinter {
    public static void printPfMode(WordEntity wordEntity) {
        StringBuilder builder = new StringBuilder();
        String word = wordEntity.getWord();
        HashMap<String, HashSet<Integer>> fileLineMap = wordEntity.getFileLineMap();

        builder.append("=== ").append(word).append(" ===").append("   ");
        builder.append("Found in file(s): ");

        fileLineMap.forEach((key, value) -> {
            builder.append(key).append(", ");
        });

        System.out.println(builder.toString());
    }

    public static void printPlMode(WordEntity wordEntity) {
        StringBuilder builder = new StringBuilder();
        String word = wordEntity.getWord();
        HashMap<String, HashSet<Integer>> fileLineMap = wordEntity.getFileLineMap();

        builder.append("=== ").append(word).append(" ===").append("   ");

        fileLineMap.forEach((key, value) -> {
            builder.append("Found in file: ").append(key).append(" on lines: ");
            Object[] setArray = value.toArray();
            for (int i = 0; i < setArray.length; i++) {
                builder.append(setArray[i]).append(", ");
            }
        });

        System.out.println(builder);
    }

    public static void printPoMode(WordEntity wordEntity) {
        StringBuilder builder = new StringBuilder();
        String word = wordEntity.getWord();
        HashMap<String, HashSet<Integer>> fileLineMap = wordEntity.getFileLineMap();

        builder.append("=== ").append(word).append(" ===").append("   ");

        builder.append(" Number of entries: ").append(wordEntity.getOccurrences()).append("   ");

        fileLineMap.forEach((key, value) -> {
            builder.append("Found in file: ").append(key).append(" on lines: ");
            Object[] setArray = value.toArray();
            for (int i = 0; i < setArray.length; i++) {
                builder.append(setArray[i]).append(", ");
            }
        });

        System.out.println(builder);
    }
}
