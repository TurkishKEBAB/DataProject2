import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Filee extends File{
    public String name;
    public Date lastModificationDate;
    public long size;
    public Boolean accessLevel = false;//true for SYSTEM and false for USER
    public String path;
    public String type;
    public Directory parent;
    public String place = "C:\\MyDocuments";
    public ArrayList list = new ArrayList<File>();

   
    

    public Filee(String name, String type, File inn) throws IOException {
        super(name, type);
        this.name = name;
        this.type = type;
        this.path = inn.getPath() + "\\" + name + "." + type;
        lastModificationDate = new Date();
        File file = new File(path);
        file.createNewFile();
    }
    
    
    
    
    
    public String getName() {
        return name;
    }

    public Boolean getAccessLevel() {
        return accessLevel;
    }
    
    @Override
    public String toString() {
        return name + "." + type;
    }
}
