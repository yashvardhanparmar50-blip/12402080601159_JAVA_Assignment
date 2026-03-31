import java.util.*;

// ──────────────────────────────────────────────────────────────
// Q4: Sort Book Objects using Comparable & Comparator
// ──────────────────────────────────────────────────────────────

// Comparable → natural order (by title alphabetically)
class Book implements Comparable<Book> {
    String title;
    String author;
    double price;
    int    year;

    Book(String title, String author, double price, int year) {
        this.title  = title;
        this.author = author;
        this.price  = price;
        this.year   = year;
    }

    // Natural order: by title (A–Z)
    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return String.format("%-30s | %-15s | ₹%6.2f | %d",
                title, author, price, year);
    }
}

// ── External Comparators ──────────────────────────────────────

// Sort by price ascending
class PriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return Double.compare(a.price, b.price);
    }
}

// Sort by year descending (newest first)
class YearComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        return Integer.compare(b.year, a.year);  // reversed
    }
}

// Sort by author name, then by title (chained comparator)
class AuthorThenTitleComparator implements Comparator<Book> {
    @Override
    public int compare(Book a, Book b) {
        int cmp = a.author.compareTo(b.author);
        return (cmp != 0) ? cmp : a.title.compareTo(b.title);
    }
}

public class Q4_BookSorting {

    static void printHeader(String label) {
        System.out.println("\n── " + label + " ──");
        System.out.printf("%-30s | %-15s | %-8s | %s%n",
                "Title", "Author", "Price", "Year");
        System.out.println("-".repeat(68));
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>(Arrays.asList(
            new Book("The Alchemist",        "Paulo Coelho",   350.00, 1988),
            new Book("Clean Code",           "Robert Martin",  750.00, 2008),
            new Book("Atomic Habits",        "James Clear",    499.00, 2018),
            new Book("Deep Work",            "Cal Newport",    399.00, 2016),
            new Book("1984",                 "George Orwell",  250.00, 1949),
            new Book("Brave New World",      "Aldous Huxley",  299.00, 1932)
        ));

        // ── 1. Natural order via Comparable (title A–Z) ──────
        Collections.sort(books);                 // uses compareTo()
        printHeader("Sorted by Title (Comparable – natural order)");
        books.forEach(System.out::println);

        // ── 2. Comparator – price ascending ──────────────────
        books.sort(new PriceComparator());
        printHeader("Sorted by Price ↑ (PriceComparator)");
        books.forEach(System.out::println);

        // ── 3. Comparator – year descending ──────────────────
        books.sort(new YearComparator());
        printHeader("Sorted by Year ↓ (YearComparator)");
        books.forEach(System.out::println);

        // ── 4. Chained comparator (author → title) ───────────
        books.sort(new AuthorThenTitleComparator());
        printHeader("Sorted by Author → Title (chained Comparator)");
        books.forEach(System.out::println);

        // ── 5. Lambda comparator – price descending ──────────
        books.sort((a, b) -> Double.compare(b.price, a.price));
        printHeader("Sorted by Price ↓ (Lambda Comparator)");
        books.forEach(System.out::println);
    }
}
