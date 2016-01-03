package practica1.tree.iterator;

import java.util.Iterator;

import practica1.tree.Position;
import practica1.tree.Tree;

public class PreorderIteratorFactory<E> implements TreeIteratorFactory<E> {

	@Override
	public Iterator<Position<E>> createIterator(Tree<E> tree) {
		return new PreorderIterator<>(tree);
	}

	@Override
	public Iterator<Position<E>> createIterator(Tree<E> tree, Position<E> pos) {
		return new PreorderIterator<>(tree, pos);
	}

	
}
