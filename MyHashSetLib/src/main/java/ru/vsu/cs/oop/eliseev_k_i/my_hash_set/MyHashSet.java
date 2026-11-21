package ru.vsu.cs.oop.eliseev_k_i.my_hash_set;

import java.util.Iterator;

public class MyHashSet<T> implements Iterable<T> {
    private SimpleHashMap<T, Boolean> data;

    public MyHashSet() {
        this.data = new SimpleHashMap<>();
    }

    public boolean add(T element) {
        if (data.containsKey(element)) {
            return false;
        }
        data.put(element, true);
        return true;
    }

    public boolean remove(T element) {
        if (!data.containsKey(element)) {
            return false;
        }
        data.remove(element);
        return true;
    }

    public boolean contains(T element) {
        return data.containsKey(element);
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    public void clear() {
        data.clear();
    }

    @Override
    public Iterator<T> iterator() {
        return data.keyIterator();
    }

    @Override
    public String toString() {
        return data.keysToString();
    }
}