package Exceptions.FirstEx;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.io.*;
import java.nio.file.*;
import java.util.InputMismatchException;
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
     * create a file
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

    /**
     * delete a file
     * @param filename
     */
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

    /**
     * write to file some text
     * @param filename
     * @param text
     * @param append if true - text append to file, false - rewrite it
     */
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
                    if (parts.length < 2) {
                        System.out.println("Invalid format. Indicate the filename.");
                        break;
                    }

                    String[] specialCommand = line.split("\"");
                    String filename = (specialCommand.length == 3) ? specialCommand[2].trim()
                            : specialCommand[1].trim();

                    String text = (specialCommand.length == 3) ? specialCommand[1]
                            : "";

                    System.out.println("Append (true) or rewrite (false)?");
                    try {
                        boolean bool  = scanner.nextBoolean();
                        writeToFile(filename, text, bool);
                    } catch (InputMismatchException e) {
                        System.out.println("Only \"true\" or \"false\".");
                    }
                    break;
                }

                default: {
                    System.out.println("Unknown command.");
                }

                case "help": {
                    System.out.println("help");
                    break;
                }

            }
        }
    }


}

