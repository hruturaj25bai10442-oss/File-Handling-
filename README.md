# File-Handling-
This project demonstrates file handling in Java by creating, reading, writing, updating, and managing files. It helps understand how Java programs can store and retrieve data efficiently using built-in file handling classes and methods.
Java File Handling Project

# Java File Handling Project

A simple, self-contained Java console application that demonstrates common
file-handling operations using both the classic `java.io` API and the
modern `java.nio.file` API.

## Features

The project supports the following operations, available through an
interactive command-line menu:

| # | Operation | Description |
|---|-----------|-------------|
| 1 | Create a file | Creates a new empty file (and any missing parent folders) |
| 2 | Write to a file | Overwrites a file with new content |
| 3 | Append to a file | Adds a new line to the end of a file |
| 4 | Read a file | Prints the full contents of a file |
| 5 | Delete a file | Deletes a file |
| 6 | Copy a file | Copies a file to a new location |
| 7 | Move / rename a file | Moves or renames a file |
| 8 | List files in a directory | Lists all files inside a folder |
| 9 | Search by extension | Recursively finds files with a given extension |
| 10 | File info | Shows size, created/modified timestamps, etc. |
| 11 | Create a directory | Creates a new folder (including parents) |
| 12 | Count lines | Counts the number of lines in a text file |

## Project Structure


FileHandlingProject/
├── README.md
└── src/
    ├── FileOperations.java   # Reusable static methods for file I/O
    └── Main.java             # Interactive CLI that uses FileOperations


- **`FileOperations.java`** contains all the reusable logic (create, read,
  write, append, delete, copy, move, list, search, get info, count lines).
  You can import and reuse this class directly in your own projects.
- **`Main.java`** is a demo console application (menu-driven) that shows
  how to call each method in `FileOperations`.

## Requirements

- Java Development Kit (JDK) 8 or later (tested with JDK 21).

## How to Compile

From the project root folder:

```bash
javac -d bin src/*.java
```

This compiles the source files and places the `.class` files in a `bin/`
folder.

## How to Run

bash
java -cp bin Main


You'll see an interactive menu like this:

=================================================
   JAVA FILE HANDLING PROJECT - DEMO CONSOLE APP
=================================================

Choose an operation:
 1. Create a new file
 2. Write to a file (overwrite)
 3. Append to a file
 4. Read a file
 5. Delete a file
 6. Copy a file
 7. Move / rename a file
 8. List files in a directory
 9. Search files by extension
10. Show file info (size, dates, etc.)
11. Create a directory
12. Count lines in a file
 0. Exit
>


Follow the prompts to try out each operation. File paths can be relative
(e.g. `data/sample.txt`) or absolute.

### Example session


> 1
Enter file path to create: data/sample.txt
File created: data/sample.txt

> 2
Enter file path to write to: data/sample.txt
Enter content: Hello, File Handling in Java!
Content written to data/sample.txt

> 4
Enter file path to read: data/sample.txt
----- File Content -----
Hello, File Handling in Java!
-------------------------
```

## Using `FileOperations` in Your Own Code

Since all methods are `public static`, you can call them directly without
creating an instance:

```java
FileOperations.createFile("output/log.txt");
FileOperations.writeFile("output/log.txt", "First entry");
FileOperations.appendToFile("output/log.txt", "Second entry");
String content = FileOperations.readFile("output/log.txt");
System.out.println(content);
```

## Notes

- All methods throw `IOException` for I/O errors, which the calling code
  (in `Main.java`) catches and reports.
- `createFile`, `copyFile`, and `moveFile` automatically create any missing
  parent directories.
- `searchByExtension` walks the directory tree recursively (including
  subfolders).

## License

Free to use and modify for learning or personal/commercial projects.
