package app;

import implementations.BSTree;
import utilities.Iterator;

import java.io.*;

public class WordTracker {
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid number of arguments. Please provide a file name and a print type.");
            System.exit(0);
        }

        final String REPOSITORY_FILE = "repository.ser";
        
        // If repository file exists, restore the words tree
        BSTree<WordEntity> wordTree;
        File repositoryFile = new File(REPOSITORY_FILE);

        if (repositoryFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repositoryFile))) {
                wordTree = (BSTree<WordEntity>) ois.readObject();
                System.out.println("Tree restored from repository file.");
            } catch (IOException | ClassNotFoundException e) {
                wordTree = new BSTree<>();
            }
        } else {
            wordTree = new BSTree<>();
        }

        TreeBuilder builder = new TreeBuilder("res/" + args[0].replaceAll("[<>]", ""), wordTree);
        try {
            builder.build();
        } catch (FileNotFoundException e) {
            System.out.println("File " + args[0] + " not found.");
            System.exit(0);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPOSITORY_FILE))) {
            oos.writeObject(wordTree);
            System.out.println("Tree saved to repository file.");
        } catch (IOException e) {
            System.out.println("Error saving tree: " + e.getMessage());
        }

        Iterator iterator = wordTree.inorderIterator();

        switch (args[1]) {
            case "-pf":
                while (iterator.hasNext()) {
                    WordEntity entity = (WordEntity) iterator.next();
                    TreeStringPrinter.printPfMode(entity);
                }
                break;
            case "-pl":
                while (iterator.hasNext()) {
                    WordEntity entity = (WordEntity) iterator.next();
                    TreeStringPrinter.printPlMode(entity);
                }
                break;
            case "-po":
                while (iterator.hasNext()) {
                    WordEntity entity = (WordEntity) iterator.next();
                    TreeStringPrinter.printPoMode(entity);
                }
                break;
            default:
                System.out.println("Unknown print type " + args[1] + ". Please choose one of: -pf, -pl, -po.");
                System.exit(0);
        }
    }
}
