import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Directory extends File{
    public String name;
    public Date lastModificationDate;
    public long size;
    public Boolean accessLevel = false;//true for SYSTEM and false for USER
    public String path;
    public ArrayList list = new ArrayList<File>();
    public Directory parent;
    public String place = "C:\\MyDocuments";
    //public String place;
    public String type = "Directory";
    public boolean hasSystemAccessLevel = false;
    

    public Directory(String name, File inn) {
        super(inn.getPath());
        this.name = name;
        lastModificationDate = new Date();
        this.path = inn.getPath() + "\\"  + name;
        File file = new File(path);
        file.mkdir();
        this.list = new ArrayList<>();
    }
    
    
    public String getName() {
        return name;
    }

    public Boolean getAccessLevel() {
        return accessLevel;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
