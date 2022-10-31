package Sorting;

public interface SetInterface<E> {
    /*
     * Add element if not already present
     * return true if added, false if it
     * is already in the set
     */
    boolean add (E element);
    /*
     * clear
     * Makes the set empty
     */
    void clear ();
    /*
     * return true if the element is
     * in the set, false otherwise
     */
    boolean contains (E object);
    /*
     * returns true if the set contains
     * no elements, false otherwise
     */
    boolean isEmpty ();
    /*
     * removes the element from the set
     * and returns true if successful
     * returns false if the element was not
     * found in the set
     */
    boolean remove (E object);
    /*
     * returns the number of elements in the set
     */
    int size ();
}