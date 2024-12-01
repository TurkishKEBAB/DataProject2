package org.example.project2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSystem {
   private final Directory root;

   public FileSystem() {
      root = new Directory("root", "USER");
   }

   public Directory getRoot() {
      return root;
   }

   public void initialize(String filePath) {
      try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
         String line;
         Directory currentDir = root;

         while ((line = br.readLine()) != null) {
            if (line.startsWith("\t")) {
               int indentLevel = line.lastIndexOf("\t") + 1;
               String[] parts = line.trim().split("##");
               String name = parts[0];

               if (parts.length == 1) { // Directory
                  Directory newDir = new Directory(name, "USER");
                  currentDir.addEntity(newDir);
                  currentDir = newDir;
               } else if (parts.length == 4) { // File
                  try {
                     int size = Integer.parseInt(parts[2]);
                     String accessLevel = parts[3];
                     File newFile = new File(name, "", size, accessLevel);
                     currentDir.addEntity(newFile);
                  } catch (NumberFormatException e) {
                     System.err.println("Invalid size format for file: " + name);
                  }
               } else {
                  System.err.println("Invalid line format: " + line);
               }
            } else {
               currentDir = root;
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void showCurrentStructure(Directory dir, int indent) {
      for (FileSystemEntity entity : dir.getEntities()) {
         for (int i = 0; i < indent; i++) {
            System.out.print("\t");
         }
         System.out.println(entity.getDetails());
         if (entity instanceof Directory) {
            showCurrentStructure((Directory) entity, indent + 1);
         }
      }
   }

   public void addDirectory(Directory parent, String name, String accessLevel) {
      if (!"USER".equals(parent.getAccessLevel())) {
         System.out.println("Cannot add directory: Parent directory access level is not USER.");
         return;
      }
      Directory newDir = new Directory(name, accessLevel);
      parent.addEntity(newDir);
   }

   public void addFile(Directory parent, String name, String extension, int size, String accessLevel) {
      if (!"USER".equals(parent.getAccessLevel())) {
         System.out.println("Cannot add file: Parent directory access level is not USER.");
         return;
      }
      File newFile = new File(name, extension, size, accessLevel);
      parent.addEntity(newFile);
   }

   public void deleteDirectory(Directory parent, Directory toDelete) {
      if (!"USER".equals(toDelete.getAccessLevel())) {
         System.out.println("Cannot delete directory: Access level is not USER.");
         return;
      }
      parent.removeEntity(toDelete);
   }

   public void deleteFile(Directory parent, File toDelete) {
      if (!"USER".equals(toDelete.getAccessLevel())) {
         System.out.println("Cannot delete file: Access level is not USER.");
         return;
      }
      parent.removeEntity(toDelete);
   }

   public List<FileSystemEntity> searchByName(String name, Directory startDir) {
      List<FileSystemEntity> results = new ArrayList<>();
      for (FileSystemEntity entity : startDir.getEntities()) {
         if (entity.getName().equalsIgnoreCase(name)) {
            results.add(entity);
         }
         if (entity instanceof Directory) {
            results.addAll(searchByName(name, (Directory) entity));
         }
      }
      return results;
   }

   public List<FileSystemEntity> searchByExtension(String extension, Directory startDir) {
      List<FileSystemEntity> results = new ArrayList<>();
      for (FileSystemEntity entity : startDir.getEntities()) {
         if (entity instanceof File && ((File) entity).getExtension().equalsIgnoreCase(extension)) {
            results.add(entity);
         }
         if (entity instanceof Directory) {
            results.addAll(searchByExtension(extension, (Directory) entity));
         }
      }
      return results;
   }

   public String displayPath(FileSystemEntity entity, Directory currentDir, String path) {
      if (entity.equals(currentDir)) {
         return path + "\\" + currentDir.getName();
      }
      for (FileSystemEntity child : currentDir.getEntities()) {
         if (child instanceof Directory) {
            String result = displayPath(entity, (Directory) child, path + "\\" + currentDir.getName());
            if (result != null) {
               return result;
            }
         } else if (child.equals(entity)) {
            return path + "\\" + currentDir.getName() + "\\" + child.getName();
         }
      }
      return null;
   }

   public void listContents(Directory dir) {
      for (FileSystemEntity entity : dir.getEntities()) {
         System.out.println(entity.getDetails());
      }
   }
}
