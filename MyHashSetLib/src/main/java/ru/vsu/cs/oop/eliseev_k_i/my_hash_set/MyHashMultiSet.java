package ru.vsu.cs.oop.eliseev_k_i.my_hash_set;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class MyHashMultiSet<T> implements Iterable<T> {
    private static class Node<T> {
        T value;
        int count;

        Node(T value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    private LinkedList<Node<T>>[] buckets;
    private int totalSize;

    @SuppressWarnings("unchecked")
    public MyHashMultiSet() {
        buckets = new LinkedList[16];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        totalSize = 0;
    }

    private int getIndex(T element) {
        if (element == null) {
            return 0;
        }
        return (element.hashCode() & 0x7fffffff) % buckets.length;
    }

    public void add(T element) {
        int index = getIndex(element);
        for (Node<T> node : buckets[index]) {
            if (Objects.equals(node.value, element)) {
                node.count++;
                totalSize++;
                return;
            }
        }
        buckets[index].add(new Node<>(element, 1));
        totalSize++;
    }

    public boolean remove(T element) {
        int index = getIndex(element);
        for (int i = 0; i < buckets[index].size(); i++) {
            Node<T> node = buckets[index].get(i);
            if (Objects.equals(node.value, element)) {
                if (node.count > 1) {
                    node.count--;
                } else {
                    buckets[index].remove(i);
                }
                totalSize--;
                return true;
            }
        }
        return false;
    }

    public int count(T element) {
        int index = getIndex(element);
        for (Node<T> node : buckets[index]) {
            if (Objects.equals(node.value, element)) {
                return node.count;
            }
        }
        return 0;
    }

    public boolean contains(T element) {
        return count(element) > 0;
    }

    public int size() {
        return totalSize;
    }

    public boolean isEmpty() {
        return totalSize == 0;
    }

    public void clear() {
        for (LinkedList<Node<T>> bucket : buckets) {
            bucket.clear();
        }
        totalSize = 0;
    }

    @Override
    public Iterator<T> iterator() {
        LinkedList<T> all = new LinkedList<>();
        for (LinkedList<Node<T>> bucket : buckets) {
            for (Node<T> node : bucket) {
                for (int i = 0; i < node.count; i++) {
                    all.add(node.value);
                }
            }
        }
        return Collections.unmodifiableList(all).iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (LinkedList<Node<T>> bucket : buckets) {
            for (Node<T> node : bucket) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(node.value).append(" (x").append(node.count).append(")");
                first = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}