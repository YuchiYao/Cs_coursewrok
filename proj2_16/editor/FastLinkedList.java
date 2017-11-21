package editor;


import javafx.scene.text.Text;

public class FastLinkedList<E> {
    /**
     * The Sentinel is only connected with the first point. It has no connection between
     * the last elements.
     *
     */

    private Node sentinel;
    private int currentPos;
    private Node currentNode;


    public FastLinkedList(){
        sentinel = new Node(sentinel,null,sentinel);
        currentPos = 0;
        currentNode = sentinel;
    }

    void addChar(char a ){
        Node<Text> newnode = new Node (null,new Text(Character.toString(a)),null);
        final Node pre = currentNode.prev;
        final Node next = currentNode.next;
        currentNode.next = newnode;
        newnode.prev  = currentNode;
        newnode.next = next;
        currentNode = newnode;
        currentPos+=1;

    }
    void deleteChar(){

        final Node pre = currentNode.prev;
        final Node next = currentNode.next;
        pre.next = next;
        currentNode.next =null;
        currentNode.prev =null;
        currentNode.item =null;
        currentNode = pre;
        currentPos-=1;
    }

    int getCurrentPos(){
        return currentPos;
    }



    private static class Node<E> {  // Main used for Text file
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public static void main(String[] args){
        FastLinkedList<Text> a = new FastLinkedList<Text>();
        a.addChar('a');
        a.addChar('b');
        a.addChar('c');
        a.deleteChar();
        a.deleteChar();
        a.addChar('b');
        a.addChar('c');

    }
}
