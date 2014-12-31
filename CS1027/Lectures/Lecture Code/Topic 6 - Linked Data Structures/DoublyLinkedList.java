public class DoublyLinkedList<E>{
    private DoublyLinkedList<E> next;
    private DoublyLinkedList<E> previous;
    private E element;
    
    public DoublyLinkedList(){
        next = null;
        previous = null;
        element = null;
    }
    public DoublyLinkedList (E elem){
        next = null;
        previous = null;
        element = elem;
    }
    public DoublyLinkedList<E> getNext(){
        return next;
    }
    public DoublyLinkedList<E> getPrevious(){
        return previous;
    }
    public void setNext (DoublyLinkedList<E> node){
        next = node;
    }
    public void setPrevious (DoublyLinkedList<E> node){
        previous = node;
    }
    public E getElement(){
        return element;
    }
    public void setElement (E elem){
        element = elem;
    }
}

