package app;

import implementations.BSTree;
import utilities.Iterator;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Invalid number of arguments. Please provide a file name and a print type.");
            System.exit(0);
        }

        BSTree<WordEntity> wordTree = new BSTree<>();
        TreeBuilder builder = new TreeBuilder("res/" + args[0].replaceAll("[<>]", ""), wordTree);
        try {
            builder.build();
        } catch (FileNotFoundException e) {
            System.out.println("File" + args[0] +  "not found");
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
                System.out.println("Unknown print type " + args[1] + '.' + "Please choose one of: -pf, -pl, -po.");
                System.exit(0);
        }
    }
}
