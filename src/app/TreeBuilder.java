package app;

import implementations.BSTree;
import implementations.BSTreeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreeBuilder {
    private String filepath;
    private BSTree<WordEntity> tree;

    public TreeBuilder(String filepath, BSTree<WordEntity> tree) {
        this.filepath = filepath;
        this.tree = tree;
    }

    public void build() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepath));

        Integer currentLine = 0;

        while(scanner.hasNextLine()) {
            currentLine++;

            String line = scanner.nextLine();
            String trimmedLine = line.replaceAll("[.,!?;:'\"()-]", "");
            String[] words = trimmedLine.split(" ");

            for(int i = 0; i < words.length; i++) {
                if(words[i] != "") {
                    insertWord(words[i], currentLine);
                }
            }
        }
    }

    private void insertWord(String word, Integer line) {
        WordEntity wordEntity = new WordEntity(word, filepath, line);
        BSTreeNode<WordEntity> foundEntity = tree.search(wordEntity);
        if(foundEntity == null) {
            tree.add(wordEntity);
        } else {
            foundEntity.getData().insertOccurrence(filepath, line);
        }
    }
}
