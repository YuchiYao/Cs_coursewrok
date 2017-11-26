package lab9;

import java.util.*;

public class MyHashMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    class Node<K, V> {
        K key;
        V value;

        Node(K k, V val) {
            key = k;
            value = val;
        }

        Node() {
        }
    }


    private static final int INI_CAPACITY = 8;
    private int size;                                       // The number of key-value pairs
    private int capacity;                                       // The size of hash table
    private List<Node<K,V> >[] st;                       // Array of linked-list symbol tables
    private double loadF;
    private Set<K> HashSet;

    /** Constructor : INPUT (), (InitialSize), (initialSize,loadFactor) */
    public MyHashMap(){
        size =0;
        capacity =INI_CAPACITY;
        loadF= 2;
        constructor();
    }
    public MyHashMap(int initialSize){
        size =0;
        capacity =initialSize;
        loadF= 2;
        constructor();
    }
    public MyHashMap(int initialSize, double loadFactor){
        size =0;
        capacity =initialSize;
        loadF= loadFactor;
        constructor();
    }
    /** Help to initialize all the instance in the constructor */
    void constructor(){
        st = new LinkedList [capacity];
        for(int i = 0; i< capacity; i++){
            st[i] = new LinkedList<>();
        }
        HashSet = new HashSet<>();
    }
    /** Set variable to zero and instance to null */
    @Override
    public void clear(){
        size =0;
        capacity =0;
        st= null;
        HashSet = null;
        loadF = 0;
    }

    public int toHash(K k){
        return (k.hashCode()&0x7fffffff) % capacity;
    }

     //Returns true if this map contains a mapping for the specified key.
    @Override
    public boolean containsKey(K k){
        if(getNode(k)!=null)return true;
        else return false;
    }

     //Returns the value to which the specified key is mapped, or null if this
     //* map contains no mapping for the key.
    @Override
    public V get(K k){
        return (V) getNode(k).value;
    }

    private Node getNode(K k){
        if (k == null) throw new IllegalArgumentException("The key cannot be null");
        int hashKey = toHash(k);
        for(Node item: st[hashKey]){
            if(item.key == k) return item;
        }
        return null;
    }

     //Returns the number of key-value mappings in this map.
    @Override
    public int size(){return size;}

     /**Associates the specified value with the specified key in this map.
      * If the value is null, return; */
    @Override
    public void put(K k, V val){
        if (k == null) throw new IllegalArgumentException("The key cannot be null");
        if (val ==null) return;
        Node temp = getNode(k);
        if(temp ==null){
            int hashNumber = toHash(k);
            st[hashNumber].add(new Node<>(k,val));
            size+=1;
            HashSet.add(k);
            if(size/capacity>loadF){
                resize();
            }
        }else {
            temp.value = val;
        }
    }

    public void resize(){
        MyHashMap<K, V> newMap=new MyHashMap(capacity*2,loadF);

        LinkedList<Node<K, V> > a;
        for(int i=0;i<st.length;i++){
            a = (LinkedList<Node<K, V>>) st[i];
            for (Node item:a){
                newMap.put((K) item.key,(V) item.value);
            }
        }
        this.st = newMap.st;
        this.capacity = newMap.capacity;
    }

    // Returns a Set view of the keys contained in this map.
    @Override
    public Set<K> keySet(){
        return HashSet;
    }

     //Removes the mapping for the specified key from this map if present.
     //* Not required for Lab 8. If you don't implement this, throw an
     //* UnsupportedOperationException.
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

     //Removes the entry for the specified key only if it is currently mapped to
     //* the specified value. Not required for Lab 8. If you don't implement this,
     //* throw an UnsupportedOperationException.
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return HashSet.iterator();
    }

    public static void main (String args[]){
        MyHashMap<String,Integer> a = new MyHashMap<>();
        a.put("a",1);
        a.put("b",1);
        a.put("c",1);
        a.put("d",1);
        a.put("e",1);
        a.put("f",1);
        a.put("g",1);
        a.put("h",1);
        a.put("j",1);
        a.put("k",1);
        a.put("l",1);
        a.put("m",1);
        a.put("n",1);
        a.put("o",1);
        a.put("p",1);
        a.put("q",1);
        a.put("r",1);
        a.put("s",1);
        a.put("t",1);
        a.put("u",1);
        a.put("v",1);
        a.put("w",1);
        a.put("x",1);
        a.put("y",1);
        a.put("z",10);
        Iterator B = a.iterator();
        B.next();
        B.next();
        System.out.print(B.next());
    }

}
