import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of a binary search tree.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add a null Collection t"
                    + "o a BST.");
        }
        for (T item : data) {
            if (item == null) {
                throw new IllegalArgumentException("You should not have any nu"
                        + "ll elements in the Collection you are trying to add"
                        );
            }
            add(item);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add a null element "
                    + "to a BST");
        }
        if (root == null) {
            root = new BSTNode<T>(data);
            size = size + 1;
        } else {
            add(data, root);
        }
    }

    /**
     * Helper method for adding an element to the BST
     *
     * @param data the data to be added to the BST
     * @param node the node that is currently being looked at in the BST
     */
    private void add(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<T>(data));
                size = size + 1;
            } else {
                add(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<T>(data));
                size = size + 1;
            } else {
                add(data, node.getRight());
            }
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You cannot delete a null eleme"
                    + "nt from a BST.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("The data was not found"
                    + " because the BST is empty.");
        } else {
            return remove(data, root, null);
        }
    }

    /**
     * Helper method for removing an element from the BST
     *
     * @param data the data to be removed from the BST
     * @param node the node currently being looked at
     * @param parent the parent node of the node that is currently being looked
     *               at
     * @return the data of the element that was removed
     */
    private T remove(T data, BSTNode<T> node, BSTNode<T> parent) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                throw new java.util.NoSuchElementException("The data is not in"
                        + " the BST.");
            } else {
                return remove(data, node.getLeft(), node);
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                throw new java.util.NoSuchElementException("The data is not in"
                        + "the BST.");
            } else {
                return remove(data, node.getRight(), node);
            }
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                if (node == root) {
                    T toRemove = node.getData();
                    root = null;
                    size = size - 1;
                    return toRemove;
                }
                if (node == parent.getLeft()) {
                    T toRemove = node.getData();
                    parent.setLeft(null);
                    size = size - 1;
                    return toRemove;
                } else {
                    T toRemove = node.getData();
                    parent.setRight(null);
                    size = size - 1;
                    return toRemove;
                }
            } else if (node.getLeft() == null && node.getRight() != null) {
                if (node == root) {
                    T toRemove = node.getData();
                    root = node.getRight();
                    size = size - 1;
                    return toRemove;
                } else if (node == parent.getLeft()) {
                    T toRemove = node.getData();
                    parent.setLeft(node.getRight());
                    size = size - 1;
                    return toRemove;
                } else {
                    T toRemove = node.getData();
                    parent.setRight(node.getRight());
                    size = size - 1;
                    return toRemove;
                }
            } else if (node.getLeft() != null && node.getRight() == null) {
                if (node == root) {
                    T toRemove = node.getData();
                    root = node.getLeft();
                    size = size - 1;
                    return toRemove;
                } else if (node == parent.getLeft()) {
                    T toRemove = node.getData();
                    parent.setLeft(node.getLeft());
                    size = size - 1;
                    return toRemove;
                } else {
                    T toRemove = node.getData();
                    parent.setRight(node.getLeft());
                    size = size - 1;
                    return toRemove;
                }
            } else {
                BSTNode<T> predecessor = predecessor(node.getLeft());

                if (node == root) {
                    T toRemove = node.getData();
                    node.setData(predecessor.getData());
                    removeDuplicatePredecessor(node.getLeft(), node,
                            predecessor.getData());
                    size = size - 1;
                    return toRemove;
                } else if (node == parent.getLeft()) {
                    T toRemove = node.getData();
                    node.setData(predecessor.getData());
                    removeDuplicatePredecessor(node.getLeft(), node,
                            predecessor.getData());
                    size = size - 1;
                    return toRemove;
                } else {
                    T toRemove = node.getData();
                    node.setData(predecessor.getData());
                    removeDuplicatePredecessor(node.getLeft(), node,
                            predecessor.getData());
                    size = size - 1;
                    return toRemove;
                }
            }
        }
    }

    /**
     * Method that finds and returns the predecessor of the node to remove
     *
     * @param pred the node that is checked whether it is the predecessor or no
     *             t
     * @return the predecessor of the node to remove
     */
    private BSTNode<T> predecessor(BSTNode<T> pred) {
        if (pred.getRight() != null) {
            return predecessor(pred.getRight());
        } else {
            return pred;
        }
    }

    /**
     * Method that removes the duplicate predecessor after the data has been sw
     * apped with the node to be removed
     *
     * @param node the node currently being looked at
     * @param parent the parent of the node being looked at
     * @param data the data of the duplicate predecessor
     */
    private void removeDuplicatePredecessor(BSTNode<T> node, BSTNode<T> parent,
                                            T data) {
        while (node.getData() != data) {
            if (node.getData().compareTo(data) < 0) {
                parent = node;
                node = node.getRight();
            } else {
                parent = node;
                node = node.getLeft();
            }
        }
        if (node == parent.getLeft()) {
            parent.setLeft(node.getLeft());
        } else {
            parent.setRight(node.getLeft());

        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You cannot get a null value fr"
                    + "om a BST.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("The data was not found"
                    + "because the BST is empty.");
        } else {
            return get(data, root);
        }
    }

    /**
     * Helper method for getting an element from the BST
     *
     * @param data the data that we want to find in the BST
     * @param node the node that is currently being looked at
     * @return the data that we wanted and found in the tree
     */
    private T get(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                throw new java.util.NoSuchElementException("The data is not in"
                        + "the BST.");
            } else {
                return get(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                throw new java.util.NoSuchElementException("The data is not in"
                        + "the BST.");
            } else {
                return get(data, node.getRight());
            }
        } else {
            return node.getData();
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You can't check if there is a "
            + "null element in a BST.");
        }
        if (root == null) {
            return false;
        } else {
            return contains(data, root);
        }
    }

    /**
     * Helper method for checking if an element is in the BST
     *
     * @param data the data to search for in the BST
     * @param node the node that is currently being checked
     * @return a boolean value that indicates whether the data is in the BST or
     * not
     */
    private boolean contains(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                return false;
            } else {
                return contains(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                return false;
            } else {
                return contains(data, node.getRight());
            }
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> preOrderList = new ArrayList<T>();
        if (root == null) {
            return preOrderList;
        } else {
            preOrder(root, preOrderList);
        }
        return preOrderList;
    }

    /**
     * Helper method to complete the preorder traversal of a BST
     *
     * @param node the node currently being looked at
     * @param preOrderList the data of the preorder traversal
     */
    private void preOrder(BSTNode<T> node, List<T> preOrderList) {
        if (node != null) {
            preOrderList.add(node.getData());
            preOrder(node.getLeft(), preOrderList);
            preOrder(node.getRight(), preOrderList);
        }
    }

    @Override
    public List<T> postorder() {
        List<T> postOrderList = new ArrayList<T>();
        if (root == null) {
            return postOrderList;
        } else {
            postOrder(root, postOrderList);
        }
        return postOrderList;
    }

    /**
     * Helper method to complete the postorder traversal of a BST
     *
     * @param node the node currently being looked at
     * @param postOrderList the data of the postorder traversal
     */
    private void postOrder(BSTNode<T> node, List<T> postOrderList) {
        if (node != null) {
            postOrder(node.getLeft(), postOrderList);
            postOrder(node.getRight(), postOrderList);
            postOrderList.add(node.getData());
        }
    }

    @Override
    public List<T> inorder() {
        List<T> inOrderList = new ArrayList<T>();
        if (root == null) {
            return inOrderList;
        } else {
            inOrder(root, inOrderList);
        }
        return inOrderList;
    }

    /**
     * Helper method to complete the inorder traversal of a BST
     *
     * @param node the node currently being looked at
     * @param inOrderList the data of the inorder traversal
     */
    private void inOrder(BSTNode<T> node, List<T> inOrderList) {
        if (node != null) {
            inOrder(node.getLeft(), inOrderList);
            inOrderList.add(node.getData());
            inOrder(node.getRight(), inOrderList);
        }
    }

    @Override
    public List<T> levelorder() {
        List<T> levelOrderList = new ArrayList<T>();
        if (root == null) {
            return levelOrderList;
        }
        java.util.Queue<BSTNode<T>> levelQueue = new LinkedList<BSTNode<T>>();
        levelQueue.add(root);
        while (!(levelQueue.isEmpty())) {
            BSTNode<T> removed = levelQueue.remove();
            if (removed != null) {
                levelOrderList.add(removed.getData());
                levelQueue.add(removed.getLeft());
                levelQueue.add(removed.getRight());
            }
        }
        return levelOrderList;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return height(root);
        }
    }

    /**
     * Helper method to find the height of a BST.
     *
     * @param node the node currently being looked at
     * @return an integer that represents the height of the BST
     */
    private int height(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(height(node.getLeft()), height(node.getRight()))
                    + 1;
        }
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
