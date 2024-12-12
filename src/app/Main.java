package app;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        WordEntity word = new WordEntity("Hello", "example.txt", "4");
        word.insertOccurrence("example.txt", "17");
        word.insertOccurrence("example2.txt", "9");

        HashMap<String, HashSet<String>> map = word.getFileLineMap();

        map.forEach((key, value) -> {
            Object[] setArray = value.toArray();
            StringBuilder lineString = new StringBuilder();

            for(int i = 0; i < setArray.length; i++){
                if(i == setArray.length - 1){
                    lineString.append(setArray[i]);
                } else {
                    lineString.append(setArray[i]).append(", ");
                }
            }

            System.out.println(key + ": " + lineString.toString());
        });
        System.out.println("Word occurences: " + word.getOccurrences());
    }
}
