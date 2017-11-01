public interface Deque<typename_list>  {

    public boolean isEmpty();
    public int size ();
    public void addFirst(typename_list Item);
    public void addLast(typename_list Item);
    public typename_list removeFirst();
    public typename_list removeLast();
    public typename_list get(int index);
    public void printDeque();

    public static void main(String[] args) {
        ArrayDeque a = new ArrayDeque();
        LinkedListDeque b = new LinkedListDeque();
        a.addFirst(1);
        a.addLast(2);
        a.removeFirst();
        a.printDeque();
        b.addFirst(1);
        b.addLast(2);
        b.printDeque();
    }

}
