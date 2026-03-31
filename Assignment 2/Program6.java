import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

// ──────────────────────────────────────────────────────────────
// Q6: Display All Files of a Given Directory using File class
// ──────────────────────────────────────────────────────────────

public class Q6_DirectoryListing {

    static final SimpleDateFormat SDF =
            new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    // ── Flat listing of one directory ──────────────────────────
    static void listDirectory(String path) {
        File dir = new File(path);

        if (!dir.exists()) {
            System.out.println("Path does not exist: " + path);
            return;
        }
        if (!dir.isDirectory()) {
            System.out.println("Not a directory: " + path);
            return;
        }

        System.out.println("\nListing: " + dir.getAbsolutePath());
        System.out.println("=".repeat(70));
        System.out.printf("%-5s %-40s %-12s %-8s %s%n",
                "Type", "Name", "Size(bytes)", "Hidden", "Last Modified");
        System.out.println("-".repeat(70));

        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("(Empty directory)");
            return;
        }

        for (File f : files) {
            String type     = f.isDirectory() ? "[DIR]" : "[FILE]";
            long   size     = f.isFile() ? f.length() : 0;
            String hidden   = f.isHidden() ? "Yes" : "No";
            String modified = SDF.format(new Date(f.lastModified()));
            System.out.printf("%-5s %-40s %-12d %-8s %s%n",
                    type, f.getName(), size, hidden, modified);
        }

        System.out.println("-".repeat(70));
        System.out.println("Total entries: " + files.length);
    }

    // ── Recursive listing (files only) ────────────────────────
    static void listRecursive(File dir, String indent) {
        File[] entries = dir.listFiles();
        if (entries == null) return;

        for (File f : entries) {
            if (f.isDirectory()) {
                System.out.println(indent + "📁 " + f.getName() + "/");
                listRecursive(f, indent + "   ");
            } else {
                System.out.println(indent + "📄 " + f.getName()
                        + " (" + f.length() + " bytes)");
            }
        }
    }

    public static void main(String[] args) {
        // ── Change this to any valid path on your machine ──────
        String targetPath = System.getProperty("user.dir");  // current directory

        // Flat listing
        listDirectory(targetPath);

        // Recursive tree view
        System.out.println("\n── Recursive Tree View ──");
        File root = new File(targetPath);
        System.out.println("📁 " + root.getName() + "/");
        listRecursive(root, "   ");

        // ── Bonus: file statistics ─────────────────────────────
        File dir = new File(targetPath);
        File[] all = dir.listFiles();
        if (all != null) {
            long fileCount = 0, dirCount = 0, totalSize = 0;
            for (File f : all) {
                if (f.isDirectory()) dirCount++;
                else { fileCount++; totalSize += f.length(); }
            }
            System.out.println("\n── Summary ──");
            System.out.println("Files      : " + fileCount);
            System.out.println("Directories: " + dirCount);
            System.out.println("Total size : " + totalSize + " bytes");
        }
    }
}
