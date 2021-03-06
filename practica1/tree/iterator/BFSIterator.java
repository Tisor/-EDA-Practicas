
package practica1.tree.iterator;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import practica1.tree.Position;
import practica1.tree.Tree;

/**
 * Generic iterator for trees
 * 
 * @author A. Duarte, J. V�lez, J. S�nchez-Oro
 * @param <E> the type of elements stored in the tree
 */
public class BFSIterator<E> implements Iterator<Position<E>> {

    private final Queue<Position<E>> nodeQueue;
    private final Tree<E> tree;

    public BFSIterator(Tree<E> tree, Position<E> start) {
        nodeQueue = new ArrayDeque<>();
        this.tree = tree;
        nodeQueue.add(start);
    }   
    
    public BFSIterator(Tree<E> tree) {
        nodeQueue = new ArrayDeque<>();
        this.tree = tree;
        nodeQueue.add(tree.root());
    }   
    
    @Override
    public boolean hasNext() {
        return (nodeQueue.size() != 0);
    }

    @Override
    public Position<E> next() {
        Position<E> aux = nodeQueue.remove();
        for (Position<E> node : tree.children(aux)) {
            nodeQueue.add(node);
        }
        return aux;
    }
    
}
