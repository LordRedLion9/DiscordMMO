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
	
	public void createNewChar(User user){

		PlayerCharacter newChar = new PlayerCharacter();
		newChar.setLocation(spawn);
		user.setChar(newChar);
		
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