package practica1.tree.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import practica1.tree.Position;
import practica1.tree.Tree;

public class PreorderIteratorRec<E> implements Iterator<Position<E>> {

	private final Tree<E> tree;
	private final ArrayList<Position<E>> recorrido;
	
	public PreorderIteratorRec(Tree<E> tree){
		this.tree = tree;
		this.recorrido = preOrd(tree.root());
	}
	
	public PreorderIteratorRec(Tree<E> tree, Position<E> pos){
		this.tree = tree;
		this.recorrido = preOrd(pos);
	}
	
	private ArrayList<Position<E>> preOrd(Position<E> pos){
		ArrayList<Position<E>> recor = new ArrayList<>();
		preOrdAux(pos, recor);
		return recor;
	}
	
	private void preOrdAux(Position<E> pos, ArrayList<Position<E>> recor){
		//Inicializamos para recorrer
		ArrayList<Position<E>> hijos = new ArrayList<>();
		// Guardamos la raíz en el array solución "recor"
		recor.add(pos);
		for(Position<E> elem: tree.children(pos)){
			hijos.add(elem);
		}
		// Si el nodo raíz tiene hijos
		if(hijos.size() > 0){
			//Recorremos los hijos desde el primero hasta el último
			// recursivamente
			for(Position<E> elem: hijos){
				preOrdAux(elem, recor);
			}
		}
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
