package org.example.project2;

import java.time.LocalDateTime;

public abstract class FileSystemEntity {
   private String name;
   private LocalDateTime lastModificationDate;
   private int size;
   private String accessLevel;
   private Directory parent;

   public FileSystemEntity(String name, String accessLevel) {
      this.name = name;
      this.lastModificationDate = LocalDateTime.now();
      this.size = 0;
      this.accessLevel = accessLevel;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public LocalDateTime getLastModificationDate() {
      return lastModificationDate;
   }

   public void setLastModificationDate(LocalDateTime lastModificationDate) {
      this.lastModificationDate = lastModificationDate;
   }

   public int getSize() {
      return size;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public String getAccessLevel() {
      return accessLevel;
   }

   public void setAccessLevel(String accessLevel) {
      this.accessLevel = accessLevel;
   }

   public Directory getParent() {
      return parent;
   }

   public void setParent(Directory parent) {
      this.parent = parent;
   }

   public String getDetails() {
      return String.format("%s - %d bytes - %s - Last Modified: %s", name, size, accessLevel, lastModificationDate);
   }
}
