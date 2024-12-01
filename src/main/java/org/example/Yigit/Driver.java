package org.example.project2;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
   public static void main(String[] args) {
      FileSystem fileSystem = new FileSystem();

      try {
         fileSystem.initialize("C:\\Users\\PC\\Downloads\\myfiles.txt");
      } catch (Exception e) {
         return;
      }

      Scanner scanner = new Scanner(System.in);

      while (true) {
         System.out.println("\nCurrent Directory Structure:");
         try {
            fileSystem.showCurrentStructure(fileSystem.getRoot(), 0);
         } catch (Exception e) {
            System.err.println("Error displaying current structure: " + e.getMessage());
         }

         System.out.println("""
                     1. Add Directory
                     2. Add File
                     3. Delete Directory
                     4. Delete File
                     5. Search by Name
                     6. Search by Extension
                     7. Display Path
                     8. List Contents
                     9. Exit
                 """);
         System.out.print("Enter your choice: ");

         int choice = scanner.nextInt();
         scanner.nextLine();

         if (choice == 9) {
            System.out.println("Exiting...");
            break;
         }

         try {
            switch (choice) {
               case 1 -> addDirectory(fileSystem, scanner);
               case 2 -> addFile(fileSystem, scanner);
               case 3 -> deleteDirectory(fileSystem, scanner);
               case 4 -> deleteFile(fileSystem, scanner);
               case 5 -> searchByName(fileSystem, scanner);
               case 6 -> searchByExtension(fileSystem, scanner);
               case 7 -> displayPath(fileSystem, scanner);
               case 8 -> listContents(fileSystem, scanner);
               default -> System.out.println("Invalid choice. Please try again.");
            }
         } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
         }
      }

      scanner.close();
   }

   public static void addDirectory(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter parent directory name: ");
      String parentDirName = scanner.nextLine();
      Directory parent = findDirectoryByName(fileSystem, parentDirName);
      if (parent == null) return;

      System.out.print("Enter new directory name: ");
      String newDirName = scanner.nextLine();
      System.out.print("Enter access level (USER/SYSTEM): ");
      String accessLevel = scanner.nextLine();

      try {
         fileSystem.addDirectory(parent, newDirName, accessLevel);
      } catch (Exception e) {
         System.err.println("Error adding directory: " + e.getMessage());
      }
   }

   private static void addFile(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter parent directory name: ");
      String parentDirName = scanner.nextLine();
      Directory parent = findDirectoryByName(fileSystem, parentDirName);
      if (parent == null) return;

      System.out.print("Enter file name: ");
      String fileName = scanner.nextLine();
      System.out.print("Enter file extension: ");
      String extension = scanner.nextLine();
      System.out.print("Enter file size (in bytes): ");
      int size = scanner.nextInt();
      scanner.nextLine();
      System.out.print("Enter access level (USER/SYSTEM): ");
      String accessLevel = scanner.nextLine();

      try {
         fileSystem.addFile(parent, fileName, extension, size, accessLevel);
      } catch (Exception e) {
         System.err.println("Error adding file: " + e.getMessage());
      }
   }

   private static void deleteDirectory(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter parent directory name: ");
      String parentDirName = scanner.nextLine();
      Directory parent = findDirectoryByName(fileSystem, parentDirName);
      if (parent == null) return;

      System.out.print("Enter directory name to delete: ");
      String dirName = scanner.nextLine();
      Directory toDelete = findDirectoryByName(parent, dirName);
      if (toDelete == null) return;

      try {
         fileSystem.deleteDirectory(parent, toDelete);
      } catch (Exception e) {
         System.err.println("Error deleting directory: " + e.getMessage());
      }
   }

   private static void deleteFile(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter parent directory name: ");
      String parentDirName = scanner.nextLine();
      Directory parent = findDirectoryByName(fileSystem, parentDirName);
      if (parent == null) return;

      System.out.print("Enter file name to delete: ");
      String fileName = scanner.nextLine();
      File toDelete = findFileByName(parent, fileName);
      if (toDelete == null) return;

      try {
         fileSystem.deleteFile(parent, toDelete);
      } catch (Exception e) {
         System.err.println("Error deleting file: " + e.getMessage());
      }
   }

   private static void searchByName(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter name to search: ");
      String name = scanner.nextLine();
      System.out.print("Enter initial directory name: ");
      String initialDirName = scanner.nextLine();
      Directory startDir = findDirectoryByName(fileSystem, initialDirName);
      if (startDir == null) return;

      try {
         fileSystem.searchByName(name, startDir).forEach(e -> System.out.println(e.getDetails()));
      } catch (Exception e) {
         System.err.println("Error searching by name: " + e.getMessage());
      }
   }

   private static void searchByExtension(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter extension to search: ");
      String extension = scanner.nextLine();
      System.out.print("Enter initial directory name: ");
      String initialDirName = scanner.nextLine();
      Directory startDir = findDirectoryByName(fileSystem, initialDirName);
      if (startDir == null) return;

      try {
         fileSystem.searchByExtension(extension, startDir).forEach(e -> System.out.println(e.getDetails()));
      } catch (Exception e) {
         System.err.println("Error searching by extension: " + e.getMessage());
      }
   }

   private static void displayPath(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter entity name: ");
      String entityName = scanner.nextLine();
      Directory startDir = findDirectoryByName(fileSystem, "root");

      try {
         FileSystemEntity entity = fileSystem.searchByName(entityName, startDir).stream().findFirst().orElse(null);
         if (entity == null) {
            System.out.println("Entity not found.");
            return;
         }

         System.out.println("Path: " + fileSystem.displayPath(entity, fileSystem.getRoot(), ""));
      } catch (Exception e) {
         System.err.println("Error displaying path: " + e.getMessage());
      }
   }

   private static void listContents(FileSystem fileSystem, Scanner scanner) {
      System.out.print("Enter directory name: ");
      String dirName = scanner.nextLine();
      Directory dir = findDirectoryByName(fileSystem, dirName);
      if (dir == null) return;

      try {
         fileSystem.listContents(dir);
      } catch (Exception e) {
         System.err.println("Error listing contents: " + e.getMessage());
      }
   }

   private static Directory findDirectoryByName(FileSystem fileSystem, String name) {
      try {
         return fileSystem.searchByName(name, fileSystem.getRoot()).stream().filter(e -> e instanceof Directory).map(e -> (Directory) e).findFirst().orElse(null);
      } catch (Exception e) {
         System.err.println("Error finding directory by name: " + e.getMessage());
         return null;
      }
   }

   private static Directory findDirectoryByName(Directory parent, String name) {
      try {
         for (FileSystemEntity entity : parent.getEntities()) {
            if (entity instanceof Directory && entity.getName().equalsIgnoreCase(name)) {
               return (Directory) entity;
            }
         }
         return null;
      } catch (Exception e) {
         System.err.println("Error finding directory by name: " + e.getMessage());
         return null;
      }
   }

   private static File findFileByName(Directory parent, String name) {
      try {
         for (FileSystemEntity entity : parent.getEntities()) {
            if (entity instanceof File && entity.getName().equalsIgnoreCase(name)) {
               return (File) entity;
            }
         }
         return null;
      } catch (Exception e) {
         System.err.println("Error finding file by name: " + e.getMessage());
         return null;
      }
   }
}