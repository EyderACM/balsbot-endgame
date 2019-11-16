package database;

import java.io.File;
import java.util.Scanner;

public class ReadFile {
    private Scanner scanner;

    public void openFile(){
        try {
            scanner = new Scanner(new File("/Users/rod/Desktop/POO/Java/Database/src/database/config.txt"));
        }catch (Exception e){
            System.out.println("Could not find");
        }
    }

    public String readUser(){
        String data ;
        String conf ;
        while (scanner.hasNext()){
            data = scanner.next();
            conf = scanner.next();

            if (data.equals("user")){return conf;}
        }
        return null;
    }

    public String readPassword(){
        String data ;
        String conf = null;
        while (scanner.hasNext()){
            data = scanner.next();
            conf = scanner.next();

            if (data.equals("password")){return conf;}
        }
        return conf;
    }

    public void closeFile(){
        scanner.close();
    }
}
