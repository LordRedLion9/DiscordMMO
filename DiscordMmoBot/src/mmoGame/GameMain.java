package mmoGame;

public class GameMain implements Runnable{
	
	boolean running;
	boolean playing;
	
	public void run(){
		
		GameRunner runner = new GameRunner(this);
		Thread t = new Thread(runner);
		t.start();
		
	}
	
	
	public void gameUpdate(){
		
		//Game tick update stuff here. Things that happen from this should be time based things.
		
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