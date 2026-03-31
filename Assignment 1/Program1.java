import java.util.*;

class ArrayStringOps {
    int[] arr;
    String str;

    ArrayStringOps(int[] arr, String str) {
        this.arr = arr;
        this.str = str;
    }

    void displayArray() {
        System.out.println("Array Elements:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    void reverseString() {
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println("\nReversed String: " + reversed);
    }
}

 class Main1 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        String str = "Hello";

        ArrayStringOps obj = new ArrayStringOps(arr, str);
        obj.displayArray();
        obj.reverseString();
    }
}