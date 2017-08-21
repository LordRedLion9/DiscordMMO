package mmoServer;


/**
 *
 * @author PC
 */
public class main {
    
    static GameSaver saver;
    static ServerMain server = null;
    
    public static void main(String[] args) {
        saver = new GameSaver();
        
        server = saver.loadGame();
        
        server.run();
    }
    public static void stop(){
        saver.saveGame(server);
    }
}
