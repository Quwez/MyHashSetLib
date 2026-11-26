package ru.vsu.cs.oop.eliseev_k_i.my_hash_set;

public class MyHashMultiSet<T> {
    private SimpleHashMap<T, Integer> data;
    private int totalSize;

    public MyHashMultiSet() {
        this.data = new SimpleHashMap<>();
        this.totalSize = 0;
    }

    public void add(T element) {
        Integer currentCount = data.get(element);
        if (currentCount == null) {
            data.put(element, 1);
        } else {
            data.put(element, currentCount + 1);
        }
        totalSize++;
    }

    public boolean remove(T element) {
        Integer currentCount = data.get(element);
        if (currentCount == null) {
            return false;
        }
        if (currentCount == 1) {
            data.remove(element);
        } else {
            data.put(element, currentCount - 1);
        }
        totalSize--;
        return true;
    }

    public int count(T element) {
        Integer currentCount = data.get(element);
        return currentCount == null ? 0 : currentCount;
    }

    public boolean contains(T element) {
        return data.containsKey(element);
    }

    public int size() {
        return totalSize;
    }

    public int uniqueSize() {
        return data.size();
    }
}