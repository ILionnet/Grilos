package grilos;


public class Crickets extends Thread {
	
	private static int finishes = 0;
	
	private String name;
	private int number;
	private int jumps;
	private int jumpedDistance;
	private int jumpedDistanceSum;
	private int maxDistance;
	private int lastJump;
	private static int placement;
	private static final int restIncrement = 200;
	private static final int jumpIncrement = 30;
	
	private static Object monitor = new Object();
	private static Object notifyMonitor = new Object();
	
	public Crickets(int number, String name, int maxDistance) {
		this.name = name;
		this.maxDistance = maxDistance;
		this.number = number;
	}
	
	private void jump() {
		jumps++;
		lastJump = (int)(Math.random() * jumpIncrement);
		jumpedDistance += lastJump;
		
		if(jumpedDistance > maxDistance)
			jumpedDistance = maxDistance;
		
		jumpedDistanceSum += lastJump;
	}
	
	private void notifyJump() {
		synchronized (notifyMonitor) {
			Competition.teamPoint((int)(number/3), jumps, lastJump);			
		}
		
		System.out.println(name + " pulou " + lastJump + " cm   e j� percorreu " + jumpedDistance + " cm");
	}
	
	private void rest() {
		int time = (int)(Math.random() * restIncrement);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void finishLine() {
		synchronized (monitor) {
			placement++;
			finishes++;
			System.out.println("O " + name + " foi o " + placement + "� colocado com " + jumps + " pulos");
			Competition.defineWinner((int)number/3);
			//Competition.teamPoint((int)(number/3), jumps, jumpedDistanceSum);
			//if(finishes == Competition.cricketsAmount) {
				//Competition.announceWinner();
			//}
		}
	}
	
	
	@Override
	public void run() {
		while (jumpedDistance < maxDistance && finishes == 0) {
			jump();
			notifyJump();
			if(jumpedDistance < maxDistance) {
				rest();
			}
			else {
				finishLine();
			}
				
		}
		//finishLine();
	}

}
