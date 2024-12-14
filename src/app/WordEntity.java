package app;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class WordEntity implements Comparable<WordEntity>, Serializable  {
    private String word;
    private HashMap<String, HashSet<Integer>> fileLineMap = new HashMap<>();
    private int occurrences = 0;

    public WordEntity(String word, String file, int line) {
        this.word = word;

        HashSet<Integer> lineList = new HashSet<>();
        lineList.add(line);

        fileLineMap.put(file, lineList);

        occurrences++;
    }

    public void insertOccurrence(String file, Integer line) {
        if (!fileLineMap.containsKey(file)) {
            HashSet<Integer> lineList = new HashSet<>();
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

    public HashMap<String, HashSet<Integer>> getFileLineMap() {
        return fileLineMap;
    }

    public int getOccurrences() {
        return occurrences;
    }

    @Override
    public int compareTo(WordEntity o) {
        return word.toLowerCase().compareTo(o.word.toLowerCase());
    }
}
