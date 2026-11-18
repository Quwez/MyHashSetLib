package ru.vsu.cs.oop.eliseev_k_i.my_hash_set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        buckets = new LinkedList[16];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        for (Entry<K, V> entry : buckets[index]) {
            if (Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
        }
        buckets[index].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        for (Entry<K, V> entry : buckets[index]) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        for (Entry<K, V> entry : buckets[index]) {
            if (Objects.equals(entry.key, key)) {
                return true;
            }
        }
        return false;
    }

    public void remove(K key) {
        int index = getIndex(key);
        for (int i = 0; i < buckets[index].size(); i++) {
            if (Objects.equals(buckets[index].get(i).key, key)) {
                buckets[index].remove(i);
                size--;
                return;
            }
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    public Iterator<K> keyIterator() {
        return new Iterator<K>() {
            private int bucketIndex = 0;
            private int entryIndex = 0;
            private int returnedCount = 0;

            @Override
            public boolean hasNext() {
                return returnedCount < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (bucketIndex < buckets.length && entryIndex >= buckets[bucketIndex].size()) {
                    bucketIndex++;
                    entryIndex = 0;
                }
                K key = buckets[bucketIndex].get(entryIndex).key;
                entryIndex++;
                returnedCount++;
                return key;
            }
        };
    }

    public String keysToString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<K> it = keyIterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(entry.key).append("=").append(entry.value);
                first = false;
            }
        }
        sb.append("}");
        return sb.toString();
    }
}