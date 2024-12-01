import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BPlusTree <T>{
    Directory root;
    public ArrayList list = new ArrayList<File>();
    
    public void createDirectory(String name, Directory in, File inn){
        if(root == null){
            Directory directory = new Directory(name, in);
            directory.parent = in;
            in.list.add(directory);
            System.out.println(name + " directory added");
            root = directory;
            directory.parent = null;
            //directory.parent.size = directory.parent.size + directory.size;
        }
        else{
            if(in.accessLevel == true){
                System.out.println("The aceess level of this directory is SYSTEM, you can not create a directory in here");
            }
            else{
                Directory directory = new Directory(name, inn);
                directory.parent = in;
                in.list.add(directory);
                System.out.println(name + " directory added");
                //directory.parent.size = directory.parent.size + directory.size;
            }
        }
        
    }
    
    public void deleteDirectory(String name3, Directory in, File inn){
        if(root == null){
            System.out.println("Here is nothing");
        }
        else{
            int a = 0;
            boolean deletable = false;
            try{
                for(int i = 0; i < in.list.size(); i++){
                    if(name3.equals(((Directory)in.list.get(i)).name)){
                        a = i;
                        deletable = true;
                    }
                }
            
                if(deletable){
                    if(((Directory)(in.list.get(a))).accessLevel){
                        System.out.println("This directory has SYSTEM level access, you can not delete it");
                    }
                    else{
                        System.out.println((((Directory)(in.list.get(a)))).name + " deleted");
                        File df = new File((((Directory)(in.list.get(a)))).path);
                        df.delete();
                        //((Directory)(in.list.get(a))).parent.size = ((Directory)(in.list.get(a))).parent.size + ((Directory)(in.list.get(a))).size;
                        in.list.remove(a);
                    }
                }
                else{
                    System.out.println("There is not any directory named " + name3);
                }
            }
            catch(java.lang.ClassCastException q){
            }
        }
    }
    
    public void createFilee(String name, String place, String type, Directory to, File too) throws IOException{
        if(root == null){
            System.out.println("You can not create a file firstly");
        }
        else {
            if(to.accessLevel == true){
                System.out.println("The aceess level of this directory is SYSTEM, you can not create a directory in here");
            }
            else{
                Filee file = new Filee(name, type, too);
                to.list.add(file);
                System.out.println(name + " file added");
                //file.parent.size = file.parent.size + file.size;
            }
        }
    }
    
    public void deleteFile(String name, Directory in){
        if(root == null){
            System.out.println("Here is nothing");
        }
        else{
            int a = 0;
            boolean deletable = false;
            for(int i = 0; i < in.list.size(); i++){
                try{
                    if(name.equals(((Filee)(in.list.get(i))).name)){
                        a = i;
                        deletable = true;
                    }
                }
                catch(java.lang.ClassCastException q){
                }
                
            }
            Filee chosen = ((Filee)(in.list.get(a)));
            if(deletable){
                if(chosen.accessLevel){
                    System.out.println("This file has SYSTEM level access, you can not delete it");
                }
                else{
                    System.out.println(chosen.name + " deleted");
                    in.list.remove(a);
                    File df = new File(chosen.path);
                    //chosen.parent.size = chosen.parent.size - chosen.size;
                    df.delete();
                }
            }
            else{
                System.out.println("There is not any file named " + name);
            }
        }
    }
    
    
    public void next(String name, Directory in){
        int a = 0;
        if(in.accessLevel == true){
            System.out.println("you can not go into this file because this file has SYSTEM level access");
        }
        else{
            for(int i = 0; i < in.list.size(); i++){
                if(name == ((Directory)(in.list.get(i))).name){
                    a = i;
                }
            }
            Directory chosen = ((Directory)(in.list.get(a)));
            in = chosen;
        }
    }
    
    public void searchNameD(String directoryName6, String name6, Directory current){
        boolean directoryFounded = false;
        if(current.name.equals(directoryName6)){
            directoryFounded = true;
        }
        if(directoryFounded){
            for(int i = 0; i < current.list.size(); i++){
                //System.out.println(current.list.get(i).toString());
                if(current.list.get(i).toString().contains(name6)){
                    System.out.println(current.list.get(i).toString());
                }
                try{
                    searchName(directoryName6, name6, (Directory) current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
                
            } 
        }
        else{
            for(int i = 0; i < current.list.size(); i++){
                try{
                    searchName(directoryName6, name6, (Directory)current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
            }
        }
    }
    
    public void searchName(String directoryName6, String name6, Directory current){
        boolean directoryFounded = true;
        if(current.name.equals(directoryName6)){
            directoryFounded = true;
        }
        if(directoryFounded){
            for(int i = 0; i < current.list.size(); i++){
                //System.out.println(current.list.get(i).toString());
                if(current.list.get(i).toString().contains(name6)){
                    System.out.println(current.list.get(i).toString());
                }
                try{
                    searchName(directoryName6, name6, (Directory) current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
                
            } 
        }
        else{
            for(int i = 0; i < current.list.size(); i++){
                try{
                    searchName(directoryName6, name6, (Directory)current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
            }
        }
    }
    
    public void searchExtensionD(String directoryName7, String name7, Directory current){
        boolean directoryFounded = false;
        if(current.name.equals(directoryName7)){
            directoryFounded = true;
        }
        if(directoryFounded){
            for(int i = 0; i < current.list.size(); i++){
                //System.out.println(current.list.get(i).toString());
                if(((Directory)(current.list.get(i))).type.equals(name7)){
                    System.out.println(current.list.get(i).toString());
                }
                try{
                    searchExtension(directoryName7, name7, (Directory) current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
                
            } 
        }
        else{
            for(int i = 0; i < current.list.size(); i++){
                try{
                    searchExtension(directoryName7, name7, (Directory)current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
            }
        }
    }
    
    public void searchExtension(String directoryName7, String name7, Directory current){
        boolean directoryFounded = true;
        if(current.name.equals(directoryName7)){
            directoryFounded = true;
        }
        if(directoryFounded){
            for(int i = 0; i < current.list.size(); i++){
                //System.out.println(current.list.get(i).toString());
                try{
                    if(((Directory)(current.list.get(i))).type.equals(name7)){
                        System.out.println(current.list.get(i).toString());
                    }
                }catch(java.lang.ClassCastException l){
                }
                
                try{
                    searchName(directoryName7, name7, (Directory) current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
                
            } 
        }
        else{
            for(int i = 0; i < current.list.size(); i++){
                try{
                    searchName(directoryName7, name7, (Directory)current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
            }
        }
    }
    
    public void display(String name, Directory current){
        displayPathD(name, current);
        displayPathF(name, current);
    }
    
    public void displayPathD(String name8, Directory current){
        //searchToString(name7, current);
        for(int i = 0; i < current.list.size(); i++){
            //System.out.println(current.list.get(i).toString());
            try{
                
                if(current.list.get(i).toString().equals(name8)){
                    System.out.println(((Directory)current.list.get(i)).path);
                }
                
                if(((Filee)(current.list.get(i))).toString().equals(name8)){
                    System.out.println(((Filee) current.list.get(i)).path);
                }
                
                if(current.list.get(i).toString().equals(name8)){
                    System.out.println(current.list.get(i).toString());
                }
                
            }catch(java.lang.ClassCastException l){
            }        
            try{
                displayPathD(name8, (Directory) current.list.get(i));
            }
            catch(java.lang.ClassCastException q){
            }        
        }
    }
    
    public void displayPathF(String name8, Directory current){
        //searchToString(name7, current);
        for(int i = 0; i < current.list.size(); i++){
            //System.out.println(current.list.get(i).toString());
            try{
                /*
                if(current.list.get(i).toString().equals(name8)){
                    System.out.println(((Directory)current.list.get(i)).path);
                }
                */
                if(((Filee)(current.list.get(i))).toString().equals(name8)){
                    System.out.println(((Filee) current.list.get(i)).path);
                }
                
                if(current.list.get(i).toString().equals(name8)){
                    System.out.println(current.list.get(i).toString());
                }
                
            }catch(java.lang.ClassCastException l){
            }        
            try{
                displayPathF(name8, (Directory) current.list.get(i));
            }
            catch(java.lang.ClassCastException q){
            }        
        }
    }
    
    public void searchToString(String name7, Directory current){
            for(int i = 0; i < current.list.size(); i++){
                //System.out.println(current.list.get(i).toString());
                try{
                    if(current.list.get(i).toString().equals(name7)){
                        System.out.println(((Directory) current.list.get(i)).path);
                    }
                    if(((Filee)(current.list.get(i))).toString().equals(name7)){
                        System.out.println(((Filee) current.list.get(i)).path);
                    }
                }catch(java.lang.ClassCastException l){
                }
                
                try{
                    displayPathF(name7, (Directory) current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
                
            } 
            for(int i = 0; i < current.list.size(); i++){
                try{
                    searchToString(name7, (Directory) current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
            }
    }
    
    public void displayContents(String name9, Directory current){
        boolean directoryFounded = true;
        if(directoryFounded){
            for(int i = 0; i < current.list.size(); i++){
                if(current.list.get(i).toString().equals(name9)){
                    try{
                        System.out.println(current.list.get(i).toString());
                        System.out.println("name is: " + ((Directory) current.list.get(i)).name);
                        System.out.println("last modification date is: " + ((Directory) current.list.get(i)).lastModificationDate);
                        //System.out.println("size is: " + ((Directory) current.list.get(i)).size);
                        System.out.println("access level is : " + ((Directory) current.list.get(i)).accessLevel + "    false means USER level, true mens SYSTEM level");
                        System.out.println("path is: " + ((Directory) current.list.get(i)).path);
                        System.out.println("type is: " + ((Directory) current.list.get(i)).type);
                        System.out.println("name is: " + ((Directory) current.list.get(i)).name);
                        System.out.println("parent is: " + ((Directory) current.list.get(i)).parent);
                    }catch(java.lang.ClassCastException ı){
                    }
                }
                try{
                    if(((Filee)(current.list.get(i))).equals(name9)){
                        try{
                            System.out.println(current.list.get(i).toString());
                            System.out.println("name is: " + ((Directory) current.list.get(i)).name);
                            System.out.println("last modification date is: " + ((Directory) current.list.get(i)).lastModificationDate);
                            //System.out.println("size is: " + ((Directory) current.list.get(i)).size);
                            System.out.println("access level is : " + ((Directory) current.list.get(i)).accessLevel + "    false means USER level, true mens SYSTEM level");
                            System.out.println("path is: " + ((Directory) current.list.get(i)).path);
                            System.out.println("type is: " + ((Directory) current.list.get(i)).type);
                            System.out.println("name is: " + ((Directory) current.list.get(i)).name);
                            System.out.println("parent is: " + ((Directory) current.list.get(i)).parent);
                        }catch(java.lang.ClassCastException s){
                        }  
                    }
                }catch(java.lang.ClassCastException u){
                }
                
                
                try{
                    displayContents(name9, (Directory) current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
                
            } 
        }
        else{
            for(int i = 0; i < current.list.size(); i++){
                try{
                    displayContents(name9, (Directory)current.list.get(i));
                }
                catch(java.lang.ClassCastException q){
                }
            }
        }
    }
    
    public void changeAccessLevel(Directory in){
        if(in.accessLevel == true){
            in.accessLevel = false;
        }
        else{
            in.accessLevel = true;
        }
    }
}