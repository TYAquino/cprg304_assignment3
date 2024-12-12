package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {

	private BSTreeNode<E> root;
	private int size;
	
	public BSTree() {
		this.root = null;
		this.size = 0;
	}
	
	@Override
	public BSTreeNode<E> getRoot() throws NullPointerException {
		if (root == null) {
			throw new NullPointerException("The tree is empty :(");
		}
		return root;
	}

	@Override
	public int getHeight() {
		return getHeight(root);
	}
	
	private int getHeight(BSTreeNode<E> node) {
		if (node == null) {
			return 0;
		}
	    return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(E entry) throws NullPointerException {
		if (entry == null) {
			throw new NullPointerException("Entry can't be null");
		}
		return search(entry) != null;
	}

	@Override
	public BSTreeNode<E> search(E entry) throws NullPointerException {
		if (entry == null) {
			throw new NullPointerException("Entry can't be null");
		}
		return search(root, entry);
	}
	
	private BSTreeNode<E> search(BSTreeNode<E> node, E entry) {
		if (node == null || node.getData().compareTo(entry) == 0) {
			return node;
		}
		if (entry.compareTo(node.getData()) < 0) {
			return search(node.getLeft(), entry);
		}
		return search(node.getRight(), entry);
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		if (newEntry == null) {
			throw new NullPointerException("Entry can't be null");
		}
		if (root == null) {
			root = new BSTreeNode<>(newEntry);
			size++;
			return true;
		}
		return add(root, newEntry);
	}
	
	private boolean add(BSTreeNode<E> node, E newEntry) {
		int comparison = newEntry.compareTo(node.getData());
        if (comparison == 0) {
            return false;
        } else if (comparison < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } else {
                return add(node.getLeft(), newEntry);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } else {
                return add(node.getRight(), newEntry);
            }
        }
	}

	@Override
	public BSTreeNode<E> removeMin() {
		if (isEmpty()) {
            return null;
        }
        BSTreeNode<E>[] result = removeMin(root, null);
        root = result[1];
        size--;
        return result[0];
	}
	
	private BSTreeNode<E>[] removeMin(BSTreeNode<E> node, BSTreeNode<E> parent) {
		if (node.getLeft() == null) {
			if (parent != null) {
				parent.setLeft(node.getRight());
			}
			return new BSTreeNode[] {node, parent};
		}
		return removeMin(node.getLeft(), node);
	}

	@Override
	public BSTreeNode<E> removeMax() {
		if (isEmpty()) {
            return null;
        }
        BSTreeNode<E>[] result = removeMax(root, null);
        root = result[1];
        size--;
        return result[0];
	}
	
	private BSTreeNode<E>[] removeMax(BSTreeNode<E> node, BSTreeNode<E> parent) {
        if (node.getRight() == null) {
            if (parent != null) {
                parent.setRight(node.getLeft());
            }
            return new BSTreeNode[] {node, parent};
        }
        return removeMax(node.getRight(), node);
    }
	

	@Override
	public Iterator<E> inorderIterator() {
		ArrayList<E> elements = new ArrayList<>();
		inorderTraversal(root, elements);
		return new TreeIterator<>(elements);
	}
	
	private void inorderTraversal(BSTreeNode<E> node, ArrayList<E> elements) {
		if (node != null) {
            inorderTraversal(node.getLeft(), elements);
            elements.add(node.getData());
            inorderTraversal(node.getRight(), elements);
        }
	}

	@Override
	public Iterator<E> preorderIterator() {
		ArrayList<E> elements = new ArrayList<>();
        preorderTraversal(root, elements);
        return new TreeIterator<>(elements);
	}
	
	private void preorderTraversal(BSTreeNode<E> node, ArrayList<E> elements) {
        if (node != null) {
            elements.add(node.getData());
            preorderTraversal(node.getLeft(), elements);
            preorderTraversal(node.getRight(), elements);
        }
    }

	@Override
	public Iterator<E> postorderIterator() {
		ArrayList<E> elements = new ArrayList<>();
        postorderTraversal(root, elements);
        return new TreeIterator<>(elements);
	}
	
	private void postorderTraversal(BSTreeNode<E> node, ArrayList<E> elements) {
        if (node != null) {
            postorderTraversal(node.getLeft(), elements);
            postorderTraversal(node.getRight(), elements);
            elements.add(node.getData());
        }
    }
	
	private static class TreeIterator<E> implements Iterator<E> {
        private final ArrayList<E> elements;
        private int current;

        public TreeIterator(ArrayList<E> elements) {
            this.elements = elements;
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < elements.size();
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator.");
            }
            return elements.get(current++);
        }
    }

}
