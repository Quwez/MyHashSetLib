package ru.vsu.cs.oop.eliseev_k_i.my_hash_set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyHashSet<T> implements Iterable<T> {
    private LinkedList<T>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new LinkedList[16];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        size = 0;
    }

    private int getIndex(T element) {
        if (element == null) {
            return 0;
        }
        return (element.hashCode() & 0x7fffffff) % buckets.length;
    }

    public boolean add(T element) {
        int index = getIndex(element);
        for (T item : buckets[index]) {
            if (Objects.equals(item, element)) {
                return false;
            }
        }
        buckets[index].add(element);
        size++;
        return true;
    }

    public boolean remove(T element) {
        int index = getIndex(element);
        for (int i = 0; i < buckets[index].size(); i++) {
            if (Objects.equals(buckets[index].get(i), element)) {
                buckets[index].remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    public boolean contains(T element) {
        int index = getIndex(element);
        for (T item : buckets[index]) {
            if (Objects.equals(item, element)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (LinkedList<T> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int bucketIndex = 0;
            private int itemIndex = 0;
            private int returnedCount = 0;

            @Override
            public boolean hasNext() {
                return returnedCount < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (bucketIndex < buckets.length && itemIndex >= buckets[bucketIndex].size()) {
                    bucketIndex++;
                    itemIndex = 0;
                }
                T element = buckets[bucketIndex].get(itemIndex);
                itemIndex++;
                returnedCount++;
                return element;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}