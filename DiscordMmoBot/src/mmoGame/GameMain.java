package mmoGame;

import mmoServer.User;

public class GameMain implements Runnable{
	
	boolean running = false;
	
	Location spawn;


	public void run(){
		
		running = true;

		spawn = new Location();
		spawn.setDesc(

				"The blank white expanse of the testing zone stretches infinitely all around you."

		);

		GameRunner runner = new GameRunner(this);
		Thread t = new Thread(runner);
		t.start();
		
	}
	
	
	public void gameUpdate(){
		
		//Game tick update stuff here. Things that happen from this should be time based things.
		
		
	}

	//OTHER METHODS

	public void teleportChar(Character c, Location l){

		//straight up change of location
		//remove char from current loc
		//set Chars location to l
		//add char to l

	}

	public void createNewChar(User user){

		PlayerCharacter newChar = new PlayerCharacter();
		newChar.setName(user.getName()); //temp name, user can change later
		user.setChar(newChar);

		newChar.setLocation(spawn);
		spawn.addChar(newChar);
		
	}

	public void spawnItem(Item i, Location l){
	    l.addItem(i);
    }
	
	
}

class GameRunner implements Runnable{

	GameMain main;
	
	public GameRunner(GameMain gameMain) {
		main = gameMain;
	}

	@Override
	public void run() {
			
		long lastTime = System.currentTimeMillis();
				
		while (true){
			long nowTime = System.currentTimeMillis();
			long unprocessedTicks = (nowTime - lastTime) / 1000; //One unprocessed tick for every second
					
			if (unprocessedTicks >= 1){
				main.gameUpdate();
				unprocessedTicks = 0;
				lastTime = nowTime;
			}
		}
	}
	
}