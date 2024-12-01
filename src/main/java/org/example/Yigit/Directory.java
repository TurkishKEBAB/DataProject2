package org.example.project2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemEntity {
   private final List<FileSystemEntity> entities;

   public Directory(String name, String accessLevel) {
      super(name, accessLevel);
      this.entities = new ArrayList<>();
   }

   public List<FileSystemEntity> getEntities() {
      return entities;
   }

   public void addEntity(FileSystemEntity entity) {
      entities.add(entity);
      entity.setParent(this);
      updateSizeAndLastModificationDate();
   }

   public void removeEntity(FileSystemEntity entity) {
      entities.remove(entity);
      updateSizeAndLastModificationDate();
   }

   private void updateSizeAndLastModificationDate() {
      setSize(entities.stream().mapToInt(FileSystemEntity::getSize).sum());
      setLastModificationDate(entities.stream()
              .map(FileSystemEntity::getLastModificationDate)
              .max(LocalDateTime::compareTo)
              .orElse(LocalDateTime.now()));
      setAccessLevel(entities.stream().allMatch(e -> e.getAccessLevel().equals("SYSTEM")) ? "SYSTEM" : "USER");
   }

   @Override
   public String getDetails() {
      return String.format("Directory: %s - %d bytes - %s - Last Modified: %s",
              getName(), getSize(), getAccessLevel(), getLastModificationDate());
   }
}
