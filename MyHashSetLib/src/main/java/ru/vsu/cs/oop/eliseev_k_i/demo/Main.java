package ru.vsu.cs.oop.eliseev_k_i.demo;

import ru.vsu.cs.oop.eliseev_k_i.my_hash_set.MyHashMultiSet;
import ru.vsu.cs.oop.eliseev_k_i.my_hash_set.MyHashSet;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MyHashSet<String> set = new MyHashSet<>();
        MyHashMultiSet<String> multiSet = new MyHashMultiSet<>();

        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("1. Работа с MyHashSet");
            System.out.println("2. Работа с MyHashMultiSet");
            System.out.println("3. Быстрая авто-демонстрация");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                runHashSetMenu(set);
            } else if (choice.equals("2")) {
                runHashMultiSetMenu(multiSet);
            } else if (choice.equals("3")) {
                runDemoScenario();
            } else if (choice.equals("0")) {
                System.out.println("Выход из программы.");
                break;
            } else {
                System.out.println("Неверный пункт меню.");
            }
        }
    }

    private static void runHashSetMenu(MyHashSet<String> set) {
        while (true) {
            System.out.println("\n--- Меню MyHashSet ---");
            System.out.println("1. Добавить элемент");
            System.out.println("2. Удалить элемент");
            System.out.println("3. Проверить наличие элемента");
            System.out.println("4. Показать размер и содержимое");
            System.out.println("5. Вывести элементы через for-each");
            System.out.println("6. Очистить множество");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Введите строку: ");
                    String toAdd = scanner.nextLine();
                    if (set.add(toAdd)) {
                        System.out.println("Элемент добавлен.");
                    } else {
                        System.out.println("Такой элемент уже есть в множестве.");
                    }
                    break;
                case "2":
                    System.out.print("Введите строку для удаления: ");
                    String toRemove = scanner.nextLine();
                    if (set.remove(toRemove)) {
                        System.out.println("Элемент удалён.");
                    } else {
                        System.out.println("Элемент не найден.");
                    }
                    break;
                case "3":
                    System.out.print("Введите строку для поиска: ");
                    String toFind = scanner.nextLine();
                    System.out.println(set.contains(toFind) ? "Элемент есть в множестве." : "Элемента нет.");
                    break;
                case "4":
                    System.out.println("Размер: " + set.size() + ", пустое: " + set.isEmpty());
                    System.out.println("Содержимое: " + set);
                    break;
                case "5":
                    System.out.print("Элементы (for-each): ");
                    for (String item : set) {
                        System.out.print(item + " ");
                    }
                    System.out.println();
                    break;
                case "6":
                    set.clear();
                    System.out.println("Множество очищено.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
    }

    private static void runHashMultiSetMenu(MyHashMultiSet<String> multiSet) {
        while (true) {
            System.out.println("\n--- Меню MyHashMultiSet ---");
            System.out.println("1. Добавить элемент");
            System.out.println("2. Удалить одно вхождение элемента");
            System.out.println("3. Проверить наличие и количество элемента");
            System.out.println("4. Показать размер и содержимое");
            System.out.println("5. Вывести элементы через for-each");
            System.out.println("6. Очистить мультимножество");
            System.out.println("0. Назад");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.print("Введите строку: ");
                    String toAdd = scanner.nextLine();
                    multiSet.add(toAdd);
                    System.out.println("Элемент добавлен (текущее количество: " + multiSet.count(toAdd) + ").");
                    break;
                case "2":
                    System.out.print("Введите строку для удаления: ");
                    String toRemove = scanner.nextLine();
                    if (multiSet.remove(toRemove)) {
                        System.out.println("Одно вхождение удалено (осталось: " + multiSet.count(toRemove) + ").");
                    } else {
                        System.out.println("Элемент не найден.");
                    }
                    break;
                case "3":
                    System.out.print("Введите строку для проверки: ");
                    String toFind = scanner.nextLine();
                    if (multiSet.contains(toFind)) {
                        System.out.println("Элемент есть, количество: " + multiSet.count(toFind));
                    } else {
                        System.out.println("Элемента нет (количество: 0).");
                    }
                    break;
                case "4":
                    System.out.println("Всего элементов: " + multiSet.size() + ", пустое: " + multiSet.isEmpty());
                    System.out.println("Содержимое: " + multiSet);
                    break;
                case "5":
                    System.out.print("Элементы с учётом повторений (for-each): ");
                    for (String item : multiSet) {
                        System.out.print(item + " ");
                    }
                    System.out.println();
                    break;
                case "6":
                    multiSet.clear();
                    System.out.println("Мультимножество очищено.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Неверный пункт меню.");
            }
        }
    }

    private static void runDemoScenario() {
        System.out.println("\n--- Авто-демонстрация MyHashSet ---");
        MyHashSet<String> demoSet = new MyHashSet<>();
        demoSet.add("apple");
        demoSet.add("banana");
        demoSet.add("apple"); // дубликат не добавится
        demoSet.add(null);
        System.out.println("Содержимое: " + demoSet + ", размер: " + demoSet.size());
        System.out.println("Есть 'banana': " + demoSet.contains("banana"));
        demoSet.remove("banana");
        System.out.print("После удаления 'banana' (for-each): ");
        for (String s : demoSet) {
            System.out.print(s + " ");
        }
        System.out.println();

        System.out.println("\n--- Авто-демонстрация MyHashMultiSet ---");
        MyHashMultiSet<String> demoMulti = new MyHashMultiSet<>();
        demoMulti.add("apple");
        demoMulti.add("apple");
        demoMulti.add("banana");
        demoMulti.add(null);
        System.out.println("Содержимое: " + demoMulti + ", всего элементов: " + demoMulti.size());
        System.out.println("Количество 'apple': " + demoMulti.count("apple"));
        demoMulti.remove("apple");
        System.out.println("После удаления одного 'apple': " + demoMulti + ", count='apple': " + demoMulti.count("apple"));
        System.out.print("Обход for-each: ");
        for (String s : demoMulti) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}