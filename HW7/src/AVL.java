import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Pranshav Thakkar
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add a null Collection t"
                    + "o an AVL.");
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

    /**
     * Helper method to calculate the balance factor of a node
     *
     * @param node the node that we want the balance factor of
     * @return the balance factor of the node as an integer
     */
    private int balanceFactor(AVLNode<T> node) {
        if (node.getLeft() != null && node.getRight() != null) {
            return (node.getLeft().getHeight() - node.getRight().getHeight());
        } else if (node.getLeft() != null) {
            return node.getLeft().getHeight() + 1;
        } else if (node.getRight() != null) {
            return (-1 - node.getRight().getHeight());
        } else {
            return 0;
        }
    }

    /**
     * Helper method that checks if the tree is unbalanced and makes the necess
     * ary rotations if it is
     *
     * @param node the root of the tree or subtree that we want to balance
     * @return returns the new root of the tree
     */
    private AVLNode<T> balanceTree(AVLNode<T> node) {
        if (node.getBalanceFactor() == 2) {
            if (node.getLeft().getBalanceFactor() == -1) {
                node.setLeft(leftRotation(node.getLeft()));
            }
            return rightRotation(node);
        } else if (node.getBalanceFactor() == -2) {
            if (node.getRight().getBalanceFactor() == 1) {
                node.setRight(rightRotation(node.getRight()));
            }
            return leftRotation(node);
        }
        return node;
    }

    /**
     * Helper method that performs a left rotation on a tree or subtree
     *
     * @param node the root of the tree or subtree that needs a left rotation
     * @return the new root of the tree
     */
    private AVLNode<T> leftRotation(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getRight();
        node.setRight(newRoot.getLeft());
        newRoot.setLeft(node);
        newRoot.setHeight(height(newRoot));
        newRoot.setBalanceFactor(balanceFactor(newRoot));
        node.setHeight(height(node));
        node.setBalanceFactor(balanceFactor(node));
        return newRoot;
    }

    /**
     * Helper method that performs a right rotation on a tree or subtree
     *
     * @param node the root of the tree or subtree that needs a right rotation
     * @return the new root of the tree
     */
    private AVLNode<T> rightRotation(AVLNode<T> node) {
        AVLNode<T> newRoot = node.getLeft();
        node.setLeft(newRoot.getRight());
        newRoot.setRight(node);
        newRoot.setHeight(height(newRoot));
        newRoot.setBalanceFactor(balanceFactor(newRoot));
        node.setHeight(height(node));
        node.setBalanceFactor(balanceFactor(node));
        return newRoot;
    }


    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add a null element "
                    + "to an AVL");
        }
        root = add(data, root);
    }

    /**
     * Helper method to add a node using the subtree update method
     *
     * @param data the data to be added to a new node
     * @param node node that is the root of the current subtree being looked at
     * @return a node that is the root of its subtree that updates the previous
     * subtree
     */
    private AVLNode<T> add(T data, AVLNode<T> node) {
        if (node == null) {
            node = new AVLNode<T>(data);
            node.setHeight(0);
            node.setBalanceFactor(0);
            size = size + 1;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(add(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(add(data, node.getRight()));
        }
        node.setHeight(height(node));
        node.setBalanceFactor(balanceFactor(node));
        node = balanceTree(node);
        node.setHeight(height(node));
        node.setBalanceFactor(balanceFactor(node));
        return node;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You cannot delete a null eleme"
                    + "nt from an AVL.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("The data was not found"
                    + " because the AVL is empty.");
        } else {
            T toReturn = get(data);
            if (toReturn == null) {
                throw new java.util.NoSuchElementException("The data was not f"
                        + "ound in the AVL.");
            } else {
                root = remove(data, root);
            }
            return toReturn;
        }
    }

    /**
     * Helper method to remove a node using the subtree update method
     *
     * @param data the data of the node to be removed
     * @param node node that is the root of the current subtree being looked at
     * @return a node that is the root of its subtree that updates the previous
     * subtree
     */
    private AVLNode<T> remove(T data, AVLNode<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft()));
            node.setBalanceFactor(balanceFactor(node));
            node = balanceTree(node);
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight()));
            node.setBalanceFactor(balanceFactor(node));
            node = balanceTree(node);
        } else {
            if (node.getLeft() != null && node.getRight() != null) {
                node.setData(predecessor(node.getLeft()).getData());
                node.setLeft(remove(node.getData(), node.getLeft()));
                //size = size - 1;
            } else if (node.getRight() == null && node.getLeft() == null) {
                node = null;
                size = size - 1;
            } else if (node.getRight() == null) {
                node = node.getLeft();
                size = size - 1;
            } else {
                node = node.getRight();
                size = size - 1;
            }
        }
        if (node != null) {
            node.setHeight(height(node));
            node.setBalanceFactor(balanceFactor(node));
            node = balanceTree(node);
            node.setHeight(height(node));
            node.setBalanceFactor(balanceFactor(node));
        }
        return node;
    }

    /**
     * Helper method for finding the predecessor of a node that is being
     * removed
     * @param pred the node where we start looking for the predecessor
     * @return the node that is the predecessor
     */
    private AVLNode<T> predecessor(AVLNode<T> pred) {
        if (pred.getRight() != null) {
            return predecessor(pred.getRight());
        } else {
            return pred;
        }
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("You cannot get a null value fr"
                    + "om an AVL.");
        }
        if (root == null) {
            throw new java.util.NoSuchElementException("The data was not found"
                    + "because the AVL is empty.");
        } else {
            return get(data, root);
        }
    }

    /**
     * Helper method for getting an element from the AVL
     *
     * @param data the data that we want to find in the AVL
     * @param node the node that is currently being looked at
     * @return the data that we wanted and found in the tree
     */
    private T get(T data, AVLNode<T> node) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                throw new java.util.NoSuchElementException("The data is not in"
                        + "the AVL.");
            } else {
                return get(data, node.getLeft());
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                throw new java.util.NoSuchElementException("The data is not in"
                        + "the AVL.");
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
                    + "null element in an AVL.");
        }
        if (root == null) {
            return false;
        } else {
            return contains(data, root);
        }
    }

    /**
     * Helper method for checking if an element is in the AVL
     *
     * @param data the data to search for in the AVL
     * @param node the node that is currently being checked
     * @return a boolean value that indicates whether the data is in the AVL or
     * not
     */
    private boolean contains(T data, AVLNode<T> node) {
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
     * Helper method to complete the preorder traversal of an AVL
     *
     * @param node the node currently being looked at
     * @param preOrderList the data of the preorder traversal
     */
    private void preOrder(AVLNode<T> node, List<T> preOrderList) {
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
     * Helper method to complete the postorder traversal of an AVL
     *
     * @param node the node currently being looked at
     * @param postOrderList the data of the postorder traversal
     */
    private void postOrder(AVLNode<T> node, List<T> postOrderList) {
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
     * Helper method to complete the inorder traversal of an AVL
     *
     * @param node the node currently being looked at
     * @param inOrderList the data of the inorder traversal
     */
    private void inOrder(AVLNode<T> node, List<T> inOrderList) {
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
        java.util.Queue<AVLNode<T>> levelQueue = new LinkedList<AVLNode<T>>();
        levelQueue.add(root);
        while (!(levelQueue.isEmpty())) {
            AVLNode<T> removed = levelQueue.remove();
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
     * Helper method to find the height of an AVL.
     *
     * @param node the node currently being looked at
     * @return an integer that represents the height of the AVL
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(height(node.getLeft()), height(node.getRight()))
                    + 1;
        }
    }
    
    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
