package app;

import java.util.HashMap;
import java.util.HashSet;

public class WordEntity implements Comparable<WordEntity>{
    private String word;
    private HashMap<String, HashSet<String>> fileLineMap = new HashMap<>();
    private int occurrences = 0;

    public WordEntity(String word, String file, String line) {
        this.word = word;

        HashSet<String> lineList = new HashSet<>();
        lineList.add(line);

        fileLineMap.put(file, lineList);

        occurrences++;
    }

    public void insertOccurrence(String file, String line) {
        if (!fileLineMap.containsKey(file)) {
            HashSet<String> lineList = new HashSet<>();
            lineList.add(line);
            fileLineMap.put(file, lineList);
        } else {
            fileLineMap.get(file).add(line);
        }

        occurrences++;
    }

    public String getWord() {
        return word;
    }

    public HashMap<String, HashSet<String>> getFileLineMap() {
        return fileLineMap;
    }

    public int getOccurrences() {
        return occurrences;
    }

    @Override
    public int compareTo(WordEntity o) {
        return word.compareTo(o.word);
    }
}
