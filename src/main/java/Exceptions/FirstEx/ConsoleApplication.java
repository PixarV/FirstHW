package Exceptions.FirstEx;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

/*
for *nix systems
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsoleApplication {
    ConsoleApplication() {}

    static Path currentDirectory = Paths.get("").toAbsolutePath();

    /**
     * shows absolute path to your current directory
     */
    private static void pwd() {
        System.out.println(currentDirectory);
    }

    /**
     * change current directory to directory described with path
     * @param path
     */
    private static void cd(Path path) {
        if (!Files.isDirectory(path)) {
            System.out.println("Directory doesn't exist.");
            return;
        }
        currentDirectory = (path.isAbsolute()) ? path : path.toAbsolutePath();
        pwd();
    }

    /**
     * shows files and directories in current directory
     */
    private static void ls() {
        try {
            Files.walk(currentDirectory, 1)
                    .forEach(path ->System.out.printf("%s ", path.getName(path.getNameCount()-1)));
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with file or parent directory", e);
        }
        System.out.println("");
    }

    /**
     * create a file (if
     * @param filename
     */
    private static void touch(String filename) {
        Path filepath = Paths.get(currentDirectory.toString(), filename);
        try {
            Files.createFile(filepath);
        } catch (FileAlreadyExistsException e) {
            System.out.println("File "+filename+" already exist!");
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with file or parent directory", e);
        }
    }

    private static void rm(String filename) {
        Path filepath = Paths.get(currentDirectory.toString(), filename);
        try {
            Files.delete(filepath);
        } catch (NoSuchFileException e) {
            System.out.println("File "+filename+" doesn't exist!");
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with file or parent directory", e);
        }
    }

    private static void writeToFile(String filename, String text, boolean append) {
        Path filepath = Paths.get(currentDirectory.toString(), filename);

        if (!Files.exists(filepath) || !Files.isRegularFile(filepath)) {
            System.out.println("File "+filename+" doesn't exist!");
            return;
        }

        try (BufferedWriter writer = (append) ? Files.newBufferedWriter(filepath, StandardOpenOption.APPEND)
                : Files.newBufferedWriter(filepath)) {
          writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException("Smh wrong with file or parent directory", e);
        }
    }

    private static void console(String command, String argument) {

    }


    public static void main(String... args) {
//        pwd();
//        cd(Paths.get("src/main/java/OOP/"));
//        cd(Paths.get("/home/ritt/IdeaProjects/FirstHW"));
//        ls();
//
//        touch("probe");
//        writeToFile("probe", "first", true);
//        writeToFile("probe", "second", false);
//        rm("probe");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0].toLowerCase();

            switch (command) {
                case "pwd": {
                    pwd();
                    break;
                }

                case "cd": {
                    if (parts.length == 1) {
                        System.out.println("Invalid format. Indicate the path.");
                        break;
                    }
                    cd(Paths.get(parts[1]));
                    break;
                }

                case "ls": {
                    ls();
                    break;
                }

                case "touch": {
                    if (parts.length == 1) {
                        System.out.println("Invalid format. Indicate the filename.");
                        break;
                    }
                    touch(parts[1]);
                    break;
                }

                case "rm": {
                    if (parts.length == 1) {
                        System.out.println("Invalid format. Indicate the filename.");
                        break;
                    }
                    rm(parts[1]);
                    break;
                }

                case "wr": {
//                    if (parts.length == 1) {
//                        System.out.println("Invalid format. Indicate the filename.");
//                        break;
//                    }

                    String[] specialCommand = line.split("\"");
                    String filename = specialCommand[0].trim();



                    rm(parts[1]);
                    break;
                }

                default: {

                }

                case "help": {
                    break;
                }
            }
        }
    }


}

