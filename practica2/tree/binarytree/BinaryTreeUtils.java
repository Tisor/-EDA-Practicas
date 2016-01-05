package practica2.tree.binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

import practica1.tree.Position;
import practica2.tree.binarytree.LinkedBinaryTree.BTNode;

public class BinaryTreeUtils<E> {

    private BinaryTree<E> binTree;
    
    public BinaryTreeUtils(BinaryTree<E> binTree) {
        this.binTree = binTree;
    }

    /**
     * Given a tree the method returns a new tree where all left children become
     * right children and vice versa
     * @return the mirror of the tree
     */
    public BinaryTree<E> mirror() {
        throw new UnsupportedOperationException("the method is not yet implemented!!");
    }

    /**
     * Determines whether the element e is the tree or not
     * @param e the element to check
     * @return TRUE if e is in the tree, FALSE otherwise
     */
    public boolean contains(E e) {
        throw new UnsupportedOperationException("the method is not yet implemented!!");
    }

    /**
     * Determines the level of a node in the tree
     * @param p the position to check
     * @return the level of the position in the tree
     */
    public int level(Position<E> p) {
        throw new UnsupportedOperationException("the method is not yet implemented!!");
    }
}
