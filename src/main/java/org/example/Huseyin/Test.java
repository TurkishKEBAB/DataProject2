
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        BPlusTree bpt = new BPlusTree();
        bpt.list.add(bpt.root);
        System.out.println("FILE SYSTEM HAS OPENED");
        File c = new File("C:");
        Directory MyDocuments = new Directory("MyDocuments", c);
        File MyDocumentss = new File(MyDocuments.path);
        bpt.root = MyDocuments;
        Directory in = MyDocuments;
        File inn = MyDocumentss;
        
                
        Scanner scn = new Scanner(System.in);
        
        boolean system = true;
        while(system){
            MyDocuments.delete();
            System.out.println("\nYOU ARE IN " + in.path);
            System.out.println("Directories and Files\n");
            
            for(int i = 0; i < in.list.size(); i++){
                System.out.println((in.list.get(i)).toString());
            }
            System.out.println("\n");
            options();
            //data(in, inn);
            String option = scn.next();
            
            switch(option){
                case "createD":
                    System.out.println("What is the name of directory?");
                    String name = scn.next();
                    bpt.createDirectory(name, in, inn);
                    break;
                    
                case "createF":
                    System.out.println("What is the name of the file?");
                    String name2 = scn.next();
                    String place2 = in.path;
                    Directory to2 = in;
                    File to22 = inn;
                    System.out.println("What is the type of the file");
                    String type2 = scn.next();
                    bpt.createFilee(name2, place2, type2, to2, to22);
                    break;
                    
                case "deleteD":
                    System.out.println("What is the name of the directory you want to delete");
                    String name3 = scn.next();
                    bpt.deleteDirectory(name3, in, inn);
                    break;
                
                case "deleteF":
                    System.out.println("What is the name of the file you want to delete");
                    String name4 = scn.next();
                    bpt.deleteFile(name4, in);
                    break;
                    
                case "next":
                    try{
                        System.out.println("What is the name the directory you want to go into");
                        System.out.println("\nYOU ARE IN " + in.path);
                        System.out.println("Directories and Files\n");
                        for(int i = 0; i < in.list.size(); i++){
                            System.out.println((in.list.get(i)).toString());
                        }
                        String name5 = scn.next();
                        int a = 0;
                        for(int i = 0; i < in.list.size(); i++){
                            try{
                                if(name5.equals(((Directory)(in.list.get(i))).name)){
                                    a = i;
                                }
                            }
                            catch(java.lang.ClassCastException t){
                            }
                        }
                    
                        Directory chosen = ((Directory)(in.list.get(a)));
                        in = chosen;
                        File chosenn = new File(in.path);
                        inn = chosenn;
                    }
                    catch(java.lang.ClassCastException u){
                    }
                    break;
                    
                case "back":
                    in = in.parent;
                    inn = inn.getParentFile();
                    break;
                    
                case "searchN":
                    System.out.println("What is the name of the directory you want to search in");
                    String directoryName6 = scn.next();
                    System.out.println("What is the name of the file or directory you want to search");
                    String name6 = scn.next();
                    System.out.println("FOUNDED DIRECTORIES AND FILES");
                    bpt.searchNameD(directoryName6, name6, bpt.root);
                    break;
                    
                case "searchE":
                    System.out.println("What is the name of the directory you want to search in");
                    String directoryName7 = scn.next();
                    System.out.println("What is the extension of the File you want to search");
                    String name7 = scn.next();
                    System.out.println("FOUNDED FILES WITH EXTENSION " + name7);
                    bpt.searchExtension(directoryName7, name7, bpt.root);
                    break;
                    
                case "path":
                    System.out.println("What is the name of the file or directory you want see its path");
                    String name8 = scn.next();
                    bpt.display(name8, bpt.root);
                    break;
                    
                case "list":
                    System.out.println("What is the name of the file or directory you want to see its contents");
                    String name9 = scn.next();
                    bpt.displayContents(name9, bpt.root);
                    break;
                    
                case "change":
                    System.out.println("What is the password");
                    String password = scn.next();
                    if(password == "0000"){
                        bpt.changeAccessLevel(in);
                    }
                    else{
                        System.out.println("wrong password");
                    }
                    break;
                    
                case "off":
                    system = false;
                    break;
                    
            }
            System.out.println("_________________________________________________");
        }//while
    
    }//main
    
    public static void options(){
        System.out.println("Write 'createD' to create a directory in here");
        System.out.println("Write 'createF' to create a file in here");
        System.out.println("Write 'deleteD' to delete that directory from here");
        System.out.println("Write 'deleteF' to delete that file from here");
        System.out.println("Write 'next' to go into directories");
        System.out.println("Write 'back' to go to back");
        System.out.println("Write 'searchN' to search a file in particular directory by using name");
        System.out.println("Write 'searchE' to search a file with particular extension");
        System.out.println("Write 'path' to see the path of the file or directory");
        System.out.println("Write 'list' to see the contents of a file");
        System.out.println("Write 'change' to change the access level of the file you in");
    }
    
    public static void data(Directory in, File inn){
        System.out.println("in path = " + in.path);
        System.out.println("in parent = " + in.parent);
        System.out.println("inn path = " + inn.getPath());
        System.out.println("inn parent = " + inn.getParent());
    }

}
