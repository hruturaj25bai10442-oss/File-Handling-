import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.*;
import java.text.SimpleDateFormat;

/**
 * FileOperations
 * ----------------
 * A collection of static utility methods that demonstrate common
 * file-handling tasks in Java using both the classic java.io API
 * and the modern java.nio.file API.
 */
public class FileOperations {

    /** Create a new empty file. Returns true if the file was created. */
    public static boolean createFile(String path) throws IOException {
        File file = new File(path);

        // Make sure parent directories exist
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        return file.createNewFile();
    }

    /** Write text to a file, overwriting any existing content. */
    public static void writeFile(String path, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        }
    }

    /** Append text to the end of a file (creates the file if it doesn't exist). */
    public static void appendToFile(String path, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(content);
            writer.newLine();
        }
    }

    /** Read and return the entire contents of a file as a String. */
    public static String readFile(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            boolean first = true;
            while ((line = reader.readLine()) != null) {
                if (!first) sb.append(System.lineSeparator());
                sb.append(line);
                first = false;
            }
        }
        return sb.toString();
    }

    /** Read a file line by line and return the lines as a List. */
    public static List<String> readLines(String path) throws IOException {
        return Files.readAllLines(Paths.get(path));
    }

    /** Delete a file. Returns true if deletion succeeded. */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        return file.exists() && file.delete();
    }

    /** Copy a file from source to destination (overwrites destination if it exists). */
    public static void copyFile(String source, String destination) throws IOException {
        Path srcPath = Paths.get(source);
        Path destPath = Paths.get(destination);

        Path parent = destPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }

    /** Move (or rename) a file from source to destination. */
    public static void moveFile(String source, String destination) throws IOException {
        Path srcPath = Paths.get(source);
        Path destPath = Paths.get(destination);

        Path parent = destPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.move(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
    }

    /** Check whether a file or directory exists. */
    public static boolean exists(String path) {
        return Files.exists(Paths.get(path));
    }

    /** Create a directory (including any necessary parent directories). */
    public static boolean createDirectory(String path) throws IOException {
        File dir = new File(path);
        if (dir.exists()) return false;
        return dir.mkdirs();
    }

    /** List all files (not directories) inside a directory. */
    public static List<String> listFiles(String directoryPath) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(directoryPath))) {
            return stream
                    .filter(Files::isRegularFile)
                    .map(p -> p.getFileName().toString())
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    /** Recursively search a directory for files matching a given extension (e.g. ".txt"). */
    public static List<String> searchByExtension(String directoryPath, String extension) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(directoryPath))) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(extension.toLowerCase()))
                    .map(Path::toString)
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    /** Get basic metadata (size, last modified, type) about a file. */
    public static String getFileInfo(String path) throws IOException {
        Path p = Paths.get(path);
        BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder info = new StringBuilder();
        info.append("Name          : ").append(p.getFileName()).append(System.lineSeparator());
        info.append("Absolute Path : ").append(p.toAbsolutePath()).append(System.lineSeparator());
        info.append("Size (bytes)  : ").append(attrs.size()).append(System.lineSeparator());
        info.append("Is Directory  : ").append(attrs.isDirectory()).append(System.lineSeparator());
        info.append("Created       : ").append(sdf.format(new Date(attrs.creationTime().toMillis()))).append(System.lineSeparator());
        info.append("Last Modified : ").append(sdf.format(new Date(attrs.lastModifiedTime().toMillis())));

        return info.toString();
    }

    /** Count the number of lines in a text file. */
    public static long countLines(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            return lines.count();
        }
    }
}
