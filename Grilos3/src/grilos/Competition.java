package grilos;


public class Competition {
	
	public static final int cricketsAmount = 6;
	public static final int maxDistance = 200;
	
	public static void main(String[] args) {
		Crickets[] crickets = new Crickets[cricketsAmount];
		
		for (int i = 0; i < crickets.length; i++) {
			crickets[i] = new Crickets("Grilo_0"+(i+1), maxDistance);
			crickets[i].start();
		}
	}

}
