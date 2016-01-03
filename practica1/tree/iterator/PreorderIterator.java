package practica1.tree.iterator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import practica1.tree.Position;
import practica1.tree.Tree;

public class PreorderIterator<E> implements Iterator<Position<E>> {

	private Tree<E> tree;
	private Deque<Position<E>> stack;
	
	public PreorderIterator(Tree<E> tree){
		this.stack = new ArrayDeque<Position<E>>();
		this.tree = tree;
		stack.push(tree.root());
	}
	
	public PreorderIterator(Tree<E> tree, Position<E> pos){
		this.stack = new ArrayDeque<Position<E>>();
		this.tree = tree;
		stack.push(pos);
	}
	
	
	@Override
	public boolean hasNext() {
		return !(stack.isEmpty());
	}

	@Override
	public Position<E> next() {
		
		// Pila auxiliar para colocar en el orden correcto los elementos en la pila principal
		Deque<Position<E>> auxChildren =  new ArrayDeque<>();
		Position<E> node = stack.pop();
		for(Position<E> elem: tree.children(node)){
			auxChildren.push(elem);
		}
		
		//Metemos los elementos en la pila, de derecha a izquierda, usando la pila auxiliar
		for(Position<E> elem: auxChildren){
			stack.push(elem);
		}
		return node;
	}

	
}
