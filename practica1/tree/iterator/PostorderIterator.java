package practica1.tree.iterator;

import java.util.ArrayList;
import java.util.Iterator;

import practica1.tree.Position;
import practica1.tree.Tree;


public class PostorderIterator<E> implements Iterator<Position<E>>{

	private final Tree<E> tree;
	private final ArrayList<Position<E>> recorrido;
	
	public PostorderIterator(Tree<E> tree){
		this.tree = tree;
		this.recorrido = postOrd(tree.root());
	}
	
	public PostorderIterator(Tree<E> tree, Position<E> pos){
		this.tree= tree;
		this.recorrido = postOrd(pos);
	}
	
	private ArrayList<Position<E>> postOrd(Position<E> pos){
		ArrayList<Position<E>> recor = new ArrayList<>();
		postOrdAux(pos, recor);
		return recor;
	}
	
	private void postOrdAux(Position<E> pos, ArrayList<Position<E>> recor){
		//Inicializamos para recorrer
		ArrayList<Position<E>> hijos = new ArrayList<>();
		for(Position<E> elem: tree.children(pos)){
			hijos.add(elem);
		}
		//Si tiene hijos
		if(hijos.size() > 0){
			//Recorremos los hijos desde el primero hasta el último
			// recursivamente
			for(Position<E> elem: hijos){
				postOrdAux(elem, recor);
			}
		}
		//Guardamos la raíz en el array solución "recor"
		recor.add(pos);
	}
	
	@Override
	public boolean hasNext() {
		return (recorrido.size() > 0);
	}


	@Override
	public Position<E> next() {
		return recorrido.remove(0);
	}
	

}
