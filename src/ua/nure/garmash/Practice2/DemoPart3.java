package ua.nure.garmash.Practice2;

import java.util.Iterator;

public class DemoPart3 {
    public static void main(String[] args) {

        System.out.println("==== Part3");

        MyList list = new MyListImpl();
        ListIterator lit = ((ListIterable) list).listIterator();

        // Elemenet
        list.add("Element1"); // 1 2 3 4
        list.add("Element2");
        list.add("Element3");
        list.add("Element4");

        while (lit.hasNext()) {
            System.out.print(lit.next() + " ");
        }
        System.out.println();
        lit.previous();
        lit.remove();
        while (lit.hasPrevious()) {
            System.out.print(lit.previous() + " ");
        }


//        while (lit.hasPrevious()) {
//            lit.previous();
//        }
//        System.out.println();
//
//        System.out.println("next is " + lit.next());
//        lit.remove();
//        System.out.println("removing next...\n");
//
//        while (lit.hasNext()) {
//            System.out.print(lit.next() + " ");
//        }
//        System.out.println("\n");
//        System.out.println("previous is " + lit.previous());
//        lit.remove();
//        System.out.println("removing previous \n");
//
//        while (lit.hasPrevious()) {
//            System.out.print(lit.previous() + " ");
//        }
//

    }
}


