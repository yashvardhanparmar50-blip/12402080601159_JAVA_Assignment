import java.util.*;

// ──────────────────────────────────────────────────────────────
// Q3: CRUD Operations using Collection API
//     (ArrayList, HashMap, TreeMap)
// ──────────────────────────────────────────────────────────────

public class Q3_CollectionCRUD {

    // ── 1. ArrayList CRUD ─────────────────────────────────────
    static void arrayListDemo() {
        System.out.println("\n===== ArrayList CRUD =====");
        List<String> list = new ArrayList<>();

        // Create
        list.add("Alice");
        list.add("Bob");
        list.add("Charlie");
        System.out.println("After Insert : " + list);

        // Read
        System.out.println("Element at 1 : " + list.get(1));

        // Update
        list.set(1, "Benjamin");
        System.out.println("After Update : " + list);

        // Delete
        list.remove("Charlie");
        System.out.println("After Delete : " + list);

        // Iterate
        System.out.print("Iterate      : ");
        for (String s : list) System.out.print(s + " ");
        System.out.println();
    }

    // ── 2. HashMap CRUD ───────────────────────────────────────
    static void hashMapDemo() {
        System.out.println("\n===== HashMap CRUD =====");
        Map<Integer, String> map = new HashMap<>();

        // Create
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        System.out.println("After Insert : " + map);

        // Read
        System.out.println("Key 2        : " + map.get(2));

        // Update
        map.put(2, "Blueberry");
        System.out.println("After Update : " + map);

        // Delete
        map.remove(3);
        System.out.println("After Delete : " + map);

        // Check existence
        System.out.println("ContainsKey 1: " + map.containsKey(1));

        // Iterate entries
        System.out.println("Iterate      :");
        for (Map.Entry<Integer, String> e : map.entrySet())
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
    }

    // ── 3. TreeMap CRUD (sorted by key) ──────────────────────
    static void treeMapDemo() {
        System.out.println("\n===== TreeMap CRUD (sorted) =====");
        Map<String, Integer> tmap = new TreeMap<>();

        // Create
        tmap.put("Mango",    3);
        tmap.put("Apple",    7);
        tmap.put("Banana",   2);
        tmap.put("Cherry",   5);
        System.out.println("After Insert (sorted): " + tmap);

        // Read
        System.out.println("Key 'Banana' : " + tmap.get("Banana"));

        // Update
        tmap.put("Banana", 10);
        System.out.println("After Update : " + tmap);

        // Delete
        tmap.remove("Mango");
        System.out.println("After Delete : " + tmap);

        // First / Last (TreeMap-specific)
        TreeMap<String, Integer> sorted = (TreeMap<String, Integer>) tmap;
        System.out.println("First key    : " + sorted.firstKey());
        System.out.println("Last key     : " + sorted.lastKey());
    }

    public static void main(String[] args) {
        arrayListDemo();
        hashMapDemo();
        treeMapDemo();
    }
}
