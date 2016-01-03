package practica1.tree.iterator;


import java.util.ArrayList;
import java.util.Iterator;
import practica1.tree.Position;
import practica1.tree.Tree;

public class FrontIterator<E> implements Iterator<Position<E>> {

	private final Tree<E> tree;
	private final ArrayList<Position<E>> recorrido;
	
	public FrontIterator(Tree<E> tree){
		this.tree = tree;
		this.recorrido = inOrd(tree.root());
	}
	
	public FrontIterator(Tree<E> tree, Position<E> node){
		this.tree = tree;
		this.recorrido = inOrd(node);
	}
	
	private ArrayList<Position<E>> inOrd(Position<E> pos){
		ArrayList<Position<E>> recor = new ArrayList<>();
		this.inOrdAux(pos, recor);
		return recor;
	}
	
	private void inOrdAux(Position<E> pos, ArrayList<Position<E>> recorrido){
		// Inicializamos el recorrido
		Position<E> nodeAux = pos;
		ArrayList<Position<E>> hijos = new ArrayList<>();
		for(Position<E> elem: tree.children(nodeAux)){
			hijos.add(elem);
		}
		
		//Comprobamos si tiene hijos, y si es asi, se lo eliminamos y llamamos recursivamente al subarbol hijo
		if(hijos.size() > 0){
			Position<E> nodePointer = hijos.remove(0);
			inOrdAux(nodePointer, recorrido);
		}
		
		//Mostramos la raíz
		recorrido.add(nodeAux);
		
		
		//Recorremos el resto. Si resulta que el nodo, solo tenía un nodo hijo, esta parte no se realizará
		//ya que eliminamos el primer nodo
		if(hijos.size() > 0){
			for(Position<E> elem: hijos){
				inOrdAux(elem, recorrido);
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
