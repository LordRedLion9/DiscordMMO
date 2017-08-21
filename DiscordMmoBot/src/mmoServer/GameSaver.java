package mmoServer;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;


public class GameSaver {
    
    File gameFile;

    public boolean saveGame(ServerMain server) {
        gameFile = new File("server.sav");

        try {
            if (!gameFile.exists()) {
                gameFile.createNewFile();
            }
            
            FileOutputStream file = new FileOutputStream(gameFile);
            ObjectOutputStream object = new ObjectOutputStream(file);
            
            object.writeObject(server);
            
            object.close();
            file.close();
            
            System.out.println("Server state saved!");
            return true;

        } catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    
    public ServerMain loadGame(){
        ServerMain server = null;
        try {
            
            if (gameFile.exists()){
                FileInputStream file = new FileInputStream(gameFile);
                ObjectInputStream object = new ObjectInputStream(file);
                
                server = (ServerMain)object.readObject();
                
                file.close();
                object.close();
                System.out.println("Game found and loaded.");
            
            } else {
                gameFile.createNewFile();
                server = new ServerMain();
                System.out.println("Game not found, creating new file.");
            }
            
        } catch (Exception e) {}
        
        if (server == null) {
            server = new ServerMain();
            System.out.println("Game save not compatible, creating new game.");
            
            try {
            gameFile.createNewFile();
            System.out.println("New game created.");
            } catch (Exception e) {}
            
        }
        return server;
    }
    
    
    
    
}
