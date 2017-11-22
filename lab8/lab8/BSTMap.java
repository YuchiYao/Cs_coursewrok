package lab8;

import java.util.*;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private class Entry {

        /** Stores KEY as the key in th¸ key-value pair, VAL as the value, and
         *  RIGHT AND LEFT as the next node in the BSF. */
        Entry(K k, V v, Entry rightchild, Entry leftchild) {
            key = k;
            val = v;
            right = rightchild;
            left = leftchild;
        }
        Entry(K k, V v,int s){
            key = k;
            val= v;
            size = s;
        }

        /** Returns the Entry in this BSF of key-value pairs whose key
         *  is equal to KEY, or null if no such Entry exists. */
        Entry get(K k) {
            if (k == null) throw new IllegalArgumentException("calls get() with a null key");
            if (k != null && k.equals(key)) {
                return this;
            }else if (k.compareTo(key) > 0 && right  !=null){  // Input k smaller than key
                return right.get(k);
            }
            else if (k.compareTo(key) < 0 && left  !=null){    // Input k larger than key
                return left.get(k);
            }else {
                return null;
            }
        }

        /** Stores the key of the key-value pair of this node in the BST. */
        K key;
        /** Stores the value of the key-value pair of this node in the BST. */
        V val;
        /** Stores the left and right childern of this Entry in the BST. */
        Entry right =null;
        Entry left =null;
        /** Stores the size of childern of this Entry in the BST. */
        int size=0;

    }

    /**
     * The data for the BSTMap
     */
    private Entry root;
    private Entry lastRemoveElement = null;
    //private LinkedList <K>keyInorder;

    /** Iniitalization of an empty entry */
    public BSTMap(){
    }
    /** Base on the key, find the Entry of that key */
    public Entry getKey(K k){
        return root.get(k);
    }
    /** If no key in this list, put the new value under that key, if it does have
     * the key updata the new value for this key
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value){
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root=addKey(key,value,root);
    }

    /** Add a new key with the value. If key exits, reset the value. If not,add new
     * key at the right position */
    public Entry addKey(K k, V value, Entry x){
        if (x==null) return new Entry(k,value,1);
        int cmp = k.compareTo(x.key);
        if (cmp<0) x.left = addKey(k,value,x.left);
        else if (cmp>0) x.right = addKey(k,value,x.right);
        else x.val= value;
        x.size = 1 +size(x.left) +size(x.right);
        return x;
    }
    /** Clear the whole BST */
    @Override
    public void clear(){
        if (root == null) return;
        root.size = 0;
        root =null;
    }
    /** Check contain the key or not */
    @Override
    public boolean containsKey(K key){
        if (root == null) return false;
        Entry keyFound= root.get(key);
        if (keyFound==null) return false;
        else return true;
    }
    /** Find the key and return its value. If no key inside, return null*/
    @Override
    public V get(K key){
        if (root == null) return null;
        Entry keyFound= root.get(key);

        if (keyFound==null) return null;
        else return keyFound.val;
    }
    /** Return the size of the BST*/
    @Override
    public int size(){
        return size(root);
    }
    private int size (Entry x){
        if (x==null) return 0;
        else return x.size;
    }
    /** Print all the key in increasing order */
    public void printInOrder(){
        printInOrder(root);
    }

    private void printInOrder(Entry entry){
        if (entry == null){
            return;
        }
        printInOrder(entry.left);
        System.out.print(entry.key);
        printInOrder(entry.right);
    }


    /** All throw */
    @Override
    public Set<K> keySet(){
        Set<K> keys = new HashSet<>();
        makeSet( root,keys);
        return keys;
    }
    private void makeSet(Entry entry,Set<K>keys){
        if (entry == null){
            return;
        }
        makeSet(entry.left,keys);
        keys.add(entry.key);
        makeSet(entry.right,keys);
    }
    /** Remove the key element */
    public void delete(K key){
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root=delete(key,root);
    }

    private Entry delete(K key, Entry x){
        if (x ==null) return null;
        int cmd = key.compareTo(x.key);
        if (cmd ==0) {
            /*
            if ( x.right== null && x.left != null) {
                lastRemoveElement =x;
                x= x.left;
            }else if ( x.left == null && x.right != null){
                lastRemoveElement =x;
                x= x.right;
            }else if (x.right== null && x.left == null){
                lastRemoveElement = new Entry(x.key,x.val,x.right,x.left);
                x= null;
            }else {
                Entry a = x;
                lastRemoveElement =a;
                x= min(a.right);
                x.right = deleteMin(a.right);
                x.left = a.left;
            }
            */
            /**Better implementation from library*/
            if (x.right == null){
                lastRemoveElement =x;
                return x.left;
            }
            if (x.left  == null) {
                lastRemoveElement =x;
                return x.right;
            }
            Entry t = x;
            lastRemoveElement =t;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;

        }
        else if (cmd <0) x.left=delete(key,x.left);
        else x.right=delete(key,x.right);

        x.size = size(x.right)+size(x.left) +1;
        return x;
    }
    /** Remove the key element and return removed element value*/
    @Override
    public V remove(K key){
        delete(key);
        return lastRemoveElement.val;
    }

    /**Find the minimum key of the BST*/
    public Entry min ( Entry x){
        if (x.left ==null ) return x;
        return min(x.left);

    }
    /** Delete the left most node below the x and keep the structure same*/
    private Entry deleteMin(Entry x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) +1;
        return x;
    }
    /** Require to use the iterator in list*/
    @Override
    public Iterator<K> iterator() {
        LinkedList<K> keyList = new LinkedList();
        helper(root,keyList);
        return keyList.iterator();
    }
    private void helper(Entry entry,LinkedList<K> a){
        if (entry == null){
            return;
        }
        helper(entry.left,a);
        a.addFirst(entry.key);
        helper(entry.right,a);
    }

    @Override
    public V remove(K key,V value){
        throw new UnsupportedOperationException();
    }



}
