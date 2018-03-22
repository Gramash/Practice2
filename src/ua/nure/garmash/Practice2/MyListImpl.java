package ua.nure.garmash.Practice2;

import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {

    private Object initialArray[] = new Object[0];

    @Override
    public void add(Object e) {
        Object tempArray[] = new Object[initialArray.length + 1];
        for (int i = 0; i < initialArray.length; i++) {
            tempArray[i] = initialArray[i];
        }
        tempArray[tempArray.length - 1] = e;
        initialArray = tempArray;
    }

    @Override
    public void clear() {
        initialArray = new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < initialArray.length; i++) {
            if (initialArray[i] == null || initialArray[i].equals(o)) {
                Object tempArray[] = new Object[initialArray.length - 1];
                if (initialArray.length == 1) {
                    initialArray = tempArray;
                    return true;
                }
                if (i != initialArray.length - 1) {
                    System.arraycopy(initialArray, i + 1, initialArray, i, initialArray.length - i - 1);
                }
                System.arraycopy(initialArray, 0, tempArray, 0, initialArray.length - 1);
                initialArray = tempArray;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return initialArray;
    }

    @Override
    public int size() {
        int elementCount = 0;

        for (int i = 0; i < initialArray.length; i++) {
            if (initialArray[i] != null) {
                elementCount++;
            }
        }
        return elementCount;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < initialArray.length; i++) {
            if (initialArray[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
        int count = 0;
        for (int i = 0; i < initialArray.length; i++) {
            if (c.contains(initialArray[i])) {
                count++;
            }
        }
        return count == c.size();
    }

    @Override
    public ListIterator listIterator() {
        return new ListIteratorImpl();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int pointer = 0;
        boolean nextUsed = false;
        boolean removeUsed = false;

        // returns true if the iteration has more elements
        @Override
        public boolean hasNext() {
            return initialArray.length > pointer;
        }

        // returns the next element in the iteration
        @Override
        public Object next() {
            nextUsed = true;
            removeUsed = false;
            new ListIteratorImpl().setPrevUsed(false);

            return initialArray[pointer++];
        }

        // removes from the underlying collection the last element
        //returned by this iterator
        @Override
        public void remove() { // 1 2 3 4
            if (nextUsed && !removeUsed) {
                MyListImpl.this.remove(initialArray[--pointer]);
            } else {
                throw new IllegalStateException();
            }
            removeUsed = true;
            nextUsed = false;
        }
    }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {
        boolean prevUsed = false;

        void setPrevUsed(boolean a) {
            prevUsed = a;
        }

        @Override
        public boolean hasPrevious() {
            return pointer > 0;
        }

        @Override
        public Object previous() { // 1 2 3 (3) 4
            nextUsed = false;
            return initialArray[--pointer];
        }

        @Override
        public void set(Object e) {

        }

        @Override
        public void remove() { // 1 2 3 4
            if (nextUsed && !prevUsed) {
                super.remove();
            } else {
                if (!removeUsed) {
                    MyListImpl.this.remove(initialArray[pointer]);
                } else {
                    throw new IllegalStateException();
                }
                removeUsed = true;
                nextUsed = false;
                prevUsed = false;
            }
        }

    }

    @Override
    public String toString() {
        System.out.print("[");
        for (int i = 0; i < initialArray.length; i++) {
            System.out.print(initialArray[i] + (i == initialArray.length - 1 ? "" : ", "));
        }
        System.out.print("]");

        return "";
    }
}
