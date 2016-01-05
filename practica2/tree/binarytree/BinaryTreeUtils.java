package practica2.tree.binarytree;



import practica1.tree.Position;

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
        BinaryTree<E> aux;
        
        //Como no sabemos que tipo de arbol se nos va a pasar, quizás el usuario
        //quiera que le devolvamos un arbol de la misma clase que el que está utilizando
        if (this.binTree instanceof ArrayBinaryTree){
        	aux = new ArrayBinaryTree<E>(this.binTree.size());
        }
        else if(this.binTree instanceof LinkedBinaryTree){
        	aux = new LinkedBinaryTree<E>();
        }
        else{
        	aux = null;
        }
        
        // Lo normal sería lanzar una excepción si se nos pasa un arbol incorrecto
        //pero por no cambiar la cabecera del programa, si recibe un arbol incorrecto
        // no devolvera nada, es decir null
        
        if (aux == null){
        	return null;
        }
        else{
            //Primero añadimos la raíz
            Position<E> root = this.binTree.root();
            aux.addRoot(root.getElement());
            Position<E> elemIzq = this.binTree.left(root);
            Position<E> elemDer = this.binTree.right(root);
            Position<E> newRoot = aux.root();
            if(elemIzq != null){
            	mirrorAux(elemIzq, newRoot, true, aux);
            }
            if(elemDer != null){
            	mirrorAux(elemDer, newRoot, false, aux);
            }
            return aux;
        }  
    }
    
    private void mirrorAux(Position<E> elem, Position<E> parent, boolean isLeft, BinaryTree<E> aux){
    	E data = elem.getElement();
    	Position<E> elemIzq = null;
    	Position<E> elemDer = null;
    	if (this.binTree.hasLeft(elem)){
    		elemIzq = this.binTree.left(elem);
    	}
    	if((this.binTree.hasRight(elem))){
        	elemDer = this.binTree.right(elem);
    	}
    	Position<E> newParent;
    	//Añadimos el nodo, si resulta que en el arbol anterior
    	//estaba a la izquierda, en el nuevo estara a la derecha
    	if(isLeft){
    		newParent = aux.insertRight(parent, data);
    	}
    	else{
    		newParent = aux.insertLeft(parent, data);
    	}
    	//Llamamos recursivamente a los siguientes nodos
    	if(elemIzq != null){
    		mirrorAux(elemIzq, newParent, true, aux);
    	}
    	if(elemDer != null){
    		mirrorAux(elemDer, newParent, false, aux);
    	}
    }
    
    

    /**
     * Determines whether the element e is the tree or not
     * @param e the element to check
     * @return TRUE if e is in the tree, FALSE otherwise
     */
    public boolean contains(E e) {
        Position<E> auxRoot = this.binTree.root();
    	E data = this.binTree.root().getElement();
        Position<E> leftTree;
        Position<E> rightTree;
        if ((this.binTree.hasLeft(auxRoot)) && (this.binTree.hasRight(auxRoot))){
        	leftTree = this.binTree.left(this.binTree.root());
        	rightTree = this.binTree.right(this.binTree.root());
        	return (e == data) || containsAux(leftTree, e) || containsAux(rightTree, e);
        }
        else if((this.binTree.hasLeft(auxRoot))){
        	leftTree = this.binTree.left(this.binTree.root());
        	return (e == data) || containsAux(leftTree, e);
        }
        else if(((this.binTree.hasRight(auxRoot)))){
        	rightTree = this.binTree.right(this.binTree.root());
        	return (e == data) || containsAux(rightTree, e);
        }
        else{
        	return (e == data);
        }
    	// Primero comprobamos el nodo raíz y despues mediante un método auxiliar
        // vamor recorriendo recursivamente el arbol hasta encontrarlo
        
    }
    
    private boolean containsAux(Position<E> tree, E e){
    	E data = tree.getElement();
    	Position<E> leftTree;
    	Position<E> rightTree;
    	if ((this.binTree.hasLeft(tree)) && (this.binTree.hasRight(tree))){
    		leftTree = this.binTree.left(tree);
    		rightTree = this.binTree.right(tree);
    		return (e == data) || containsAux(leftTree, e) || containsAux(rightTree, e);
    	}
    	else if((this.binTree.hasLeft(tree))){
    		leftTree = this.binTree.left(tree);
    		return (e == data) || containsAux(leftTree, e);
    	}
    	else if(((this.binTree.hasRight(tree)))){
    		rightTree = this.binTree.right(tree);
    		return (e == data) || containsAux(rightTree, e);
    	}
    	else{
    		return (e == data);
    	}

    }
    

    public int level(Position<E> p) {
    	if(!this.binTree.isEmpty()){
    		return levelAux(this.binTree.root(), p, 0);
    	}
    	else{
    		return 0;
    	}
    	
    }
    
    public int levelAux(Position<E> node, Position<E> p, int level){
    	Position<E> leftNode = null;
    	Position<E> rightNode = null;
    	int newLevel1 = 0, newLevel2 = 0;
    	if(node == p){
    		return level;
    	}
    	else{
        	if(this.binTree.hasLeft(node)){
        		leftNode = this.binTree.left(node);
        		newLevel1 = levelAux(leftNode, p, level + 1);
        	}
        	if(this.binTree.hasRight(node)){
        		rightNode = this.binTree.right(node);
            	newLevel2 = levelAux(rightNode, p, level + 1);
        	}
        	
        	if (newLevel1 != 0){
        		return newLevel1;
        	}
        	else if (newLevel2 != 0){
        		return newLevel2;
        	}
        	else{
        		return 0;
        	}
    	}

    }
}
