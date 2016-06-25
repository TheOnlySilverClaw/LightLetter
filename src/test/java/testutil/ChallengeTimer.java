package testutil;

public class ChallengeTimer {

	private long start;
	private boolean running;
	
	public ChallengeTimer() {
		start = 0;
		running = false;
	}
	
	public void start() {
		if(running) {
			throw new IllegalStateException("Already running!");
		}
		start = System.nanoTime();
	}
	
	public long stop() {
		if(running) {
			running = false;
		} else {
			throw new IllegalStateException("Not running, yet!");
		}
		return System.nanoTime() - start;
	}
}
