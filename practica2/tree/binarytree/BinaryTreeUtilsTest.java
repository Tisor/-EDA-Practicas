package practica2.tree.binarytree;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

import practica1.tree.Position;
import practica1.tree.iterator.PreorderIteratorFactory;
import practica1.tree.iterator.TreeIteratorFactory;

public class BinaryTreeUtilsTest {

	@Test
	public void mirrorTest() {
        LinkedBinaryTree<String> t = new LinkedBinaryTree<String>();
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        
        TreeIteratorFactory<String> iter = new PreorderIteratorFactory<String>();
        t.setIterator(iter);
        
        String cadena = "";
        for(Position<String> e: t){
        	cadena += e.getElement();
        }
        assertEquals(cadena, "123456");
        
        BinaryTreeUtils<String> utils = new BinaryTreeUtils<>(t);
        BinaryTree<String> tMirror = utils.mirror();
        
        LinkedBinaryTree<String> tMirrorAux = (LinkedBinaryTree<String>) tMirror;
        tMirrorAux.setIterator(iter);
        
        String cadena2 = "";
        for(Position<String> e: tMirror){
        	cadena2 += e.getElement();
        }
        
        assertEquals(cadena2, "146523");		
	}
	
	@Test
	public void containsTest() {
        LinkedBinaryTree<String> t = new LinkedBinaryTree<String>();
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
		
        BinaryTreeUtils<String> utils = new BinaryTreeUtils<>(t);
        
        assertTrue(utils.contains("3"));
        assertFalse(utils.contains("12"));
	}
	
	@Test
	public void levelTest(){
        LinkedBinaryTree<String> t = new LinkedBinaryTree<String>();
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        Position<String> p7 = t.insertRight(p6, "7");
		
        BinaryTreeUtils<String> utils = new BinaryTreeUtils<>(t);
        
        assertEquals(utils.level(p4), 1);
        assertEquals(utils.level(p6), 2);
        assertEquals(utils.level(p7), 3);
        assertEquals(utils.level(p5), 2);
	}

}
