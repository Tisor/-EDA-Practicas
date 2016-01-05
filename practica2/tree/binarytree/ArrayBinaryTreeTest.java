package practica2.tree.binarytree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import practica1.tree.Position;
import practica1.tree.iterator.FrontIterator;
import practica1.tree.iterator.PreorderIteratorFactory;
import practica1.tree.iterator.TreeIteratorFactory;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class ArrayBinaryTreeTest {

	 /**
     * Test of size method, of class ArrayBinaryTree.
     */
    @Test
    public void testSize() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        
        assertEquals(t.size(), 6);
        
    }

    /**
     * Test of isEmpty method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsEmpty() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        assertTrue(t.isEmpty());
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        t.remove(p6);
        t.remove(p5);
        t.remove(p4);
        t.remove(p3);
        t.remove(p2);
        t.remove(p1);
        assertTrue(t.isEmpty());
    }

    /**
     * Test of isInternal method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsInternal() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertTrue(t.isInternal(p1));
        assertTrue(t.isInternal(p2));
        assertFalse(t.isInternal(p3));
    }

    /**
     * Test of isLeaf method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsLeaf() {
    	ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertFalse(t.isLeaf(p1));
        assertFalse(t.isLeaf(p2));
        assertTrue(t.isLeaf(p3));
    }

    /**
     * Test of isRoot method, of class ArrayBinaryTree.
     */
    @Test
    public void testIsRoot() {
    	ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertTrue(t.isRoot(p1));
        assertFalse(t.isRoot(p6));
    }

    /**
     * Test of hasLeft method, of class ArrayBinaryTree.
     */
    @Test
    public void testHasLeft() {
    	ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertTrue(t.hasLeft(p1));
        assertFalse(t.hasLeft(p6));
    }

    /**
     * Test of hasRight method, of class ArrayBinaryTree.
     */
    @Test
    public void testHasRight() {
       	ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertTrue(t.hasRight(p1));
        assertFalse(t.hasRight(p2));
    }

    /**
     * Test of root method, of class ArrayBinaryTree.
     */
    @Test
    public void testRoot() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p = t.addRoot("+");
        t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        t.insertLeft(h, "3");
        Position<String> n5 = t.insertRight(h, "5");
        assertEquals(t.root(), p);
        assertNotSame(t.root(), h);
    }

    /**
     * Test of left method, of class ArrayBinaryTree.
     */
    @Test
    public void testLeft() {
       	ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertEquals(t.left(p4), p5);
        assertEquals(t.left(p1), p2);
    }

    /**
     * Test of right method, of class ArrayBinaryTree.
     */
    @Test
    public void testRight() {
       	ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertEquals(t.right(p4), p6);
        assertEquals(t.right(p1), p4);
    }
    
    /**
     * Test of parent method, of class ArrayBinaryTree.
     */
    @Test
    public void testParent() {
       	ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertEquals(t.parent(p6), p4);
        assertEquals(t.parent(p5), p4);
    }

    /**
     * Test of children method, of class ArrayBinaryTree.
     */
    @Test
    public void testChildren() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        Position<String> h = t.insertRight(p, "*");
        List<Position<String>> myChildren = new ArrayList<>();
        myChildren.add(n2);
        myChildren.add(h);
        Iterator<Position<String>> myIt = myChildren.iterator();
        for (Position<String> child : t.children(p)) {
            Position<String> next = myIt.next();
            assertEquals(child, next);
        }
    }

    /**
     * Test of iterator method, of class ArrayBinaryTree.
     */
    @Test
    public void testIterator() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
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
    }

    /**
     * Test of replace method, of class ArrayBinaryTree.
     */
    @Test
    public void testReplace() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        t.replace(p5, "*");
        assertEquals(p5.getElement(), "*");
    }

    /**
     * Test of sibling method, of class ArrayBinaryTree.
     */
    @Test
    public void testSibling() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        assertEquals(t.sibling(p2),p4);
        assertEquals(t.sibling(p5), p6);
    }

    /**
     * Test of addRoot method, of class ArrayBinaryTree.
     */
    @Test
    public void testAddRoot() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p = t.addRoot("+");
        assertEquals(t.root(), p);
    }

    /**
     * Test of insertLeft method, of class ArrayBinaryTree.
     */
    @Test
    public void testInsertLeft() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p = t.addRoot("+");
        Position<String> n2 = t.insertLeft(p, "2");
        assertEquals(t.left(p), n2);
    }

    /**
     * Test of insertRight method, of class ArrayBinaryTree.
     */
    @Test
    public void testInsertRight() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p = t.addRoot("+");
        Position<String> h = t.insertRight(p, "*");
        assertEquals(t.right(p), h);
    }

    /**
     * Test of remove method, of class ArrayBinaryTree.
     */
    @Test
    public void testRemove() {
        ArrayBinaryTree<String> t1 = new ArrayBinaryTree<String>(100);
        Position<String> p = t1.addRoot("+");
        Position<String> q = t1.insertLeft(p, "2");
        Position<String> h = t1.insertRight(p, "*");
        
        t1.remove(q);
        assertEquals(t1.size(), 2);
        
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p1 = t.addRoot("1");
        Position<String> p2 = t.insertLeft(p1, "2");
        Position<String> p3 = t.insertLeft(p2, "3");
        Position<String> p4 = t.insertRight(p1, "4");
        Position<String> p5 = t.insertLeft(p4, "5");
        Position<String> p6 = t.insertRight(p4, "6");
        
        t.remove(p2);
        assertEquals(t.size(), 5);
        
        TreeIteratorFactory<String> iter = new PreorderIteratorFactory<String>();
        t.setIterator(iter);
        
        String cadena = "";
        for(Position<String> e: t){
        	cadena += e.getElement();
        }
        
        assertEquals(cadena, "13456");
        
        ArrayBinaryTree<String> t2 = new ArrayBinaryTree<String>(100);
        Position<String> p11 = t2.addRoot("1");
        Position<String> p22 = t2.insertLeft(p11, "2");
        Position<String> p33 = t2.insertRight(p11, "3");
        Position<String> p44 = t2.insertLeft(p22, "4");
        Position<String> p55 = t2.insertRight(p22, "5");
        Position<String> p66 = t2.insertRight(p33, "6");
        Position<String> p77 = t2.insertLeft(p55, "7");
        Position<String> p88 = t2.insertRight(p55, "8");
        Position<String> p99 = t2.insertLeft(p66, "9");
        Position<String> p100 = t2.insertRight(p66, "10");
        
        t2.remove(p33);
        
        t2.setIterator(iter);
        
        String cadena2 = "";
        for(Position<String> e: t2){
        	cadena2 += e.getElement();
        }
        
        assertEquals(cadena2, "1245786910");
    }

    /**
     * Test of swapElements method, of class ArrayBinaryTree.
     */
    @Test
    public void testSwapElements() {
        ArrayBinaryTree<String> t = new ArrayBinaryTree<String>(100);
        Position<String> p = t.addRoot("+");
        Position<String> p1 = t.insertLeft(p, "2");
        Position<String> p2 = t.insertRight(p, "3");
        t.swapElements(p1, p2);
        String salida = "";
        for (Position<String> e : t) {
            salida += e.getElement();
        }
        assertEquals(salida, "+32");
    }
	
}
