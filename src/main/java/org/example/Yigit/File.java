package org.example.project2;

public class File extends FileSystemEntity {
   private final String extension;

   public File(String name, String extension, int size, String accessLevel) {
      super(name, accessLevel);
      this.extension = extension;
      setSize(size);
   }

   public String getExtension() {
      return extension;
   }

   @Override
   public String getDetails() {
      return String.format("%s.%s - %d bytes - %s - Last Modified: %s",
              getName(), extension, getSize(), getAccessLevel(), getLastModificationDate());
   }
}
