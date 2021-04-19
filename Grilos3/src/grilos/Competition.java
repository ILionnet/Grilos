package grilos;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Competition {
	
	public static boolean hasWinner = false;
	
	public static int winner = -1;
	
	public static int cricketsAmount = 6;
	public static int maxDistance = 200;
	
	public static List<Crickets> list = new ArrayList<>();
	public static List<List<Crickets>> team = new ArrayList<>();
	public static List<Integer> teamJumps = new ArrayList<>();
	public static List<Integer> teamDistance = new ArrayList<>();
	
	public static List<List<Integer>> tempRanks = new ArrayList<>();
	
	public static int teamsAmount = (int)cricketsAmount/3 + cricketsAmount%3;
	public static List<Integer> teamsPoints = new ArrayList<>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Digite a quantidade de participantes: ");
		cricketsAmount = sc.nextInt();
		
		System.out.print("Digite a distância a ser percorrida: ");
		maxDistance = sc.nextInt();
		
		Crickets[] crickets = new Crickets[cricketsAmount];		
		
		int runnersAllocated = 0;
		int memberLimit = 3;
		
		
		for (int i = 0; i < crickets.length; i++) {
			crickets[i] = new Crickets(i, "Grilo_0"+(i+1), maxDistance);
			
			
			if(runnersAllocated < memberLimit) {
				crickets[i].setName("Grilo_0"+(i+1));
				list.add(crickets[i]);
				runnersAllocated = runnersAllocated + 1;
			}
			
			else {
				team.add(list);
				//teamsPoints.add(0);
				teamJumps.add(0);
				teamDistance.add(0);
				list = new ArrayList<>();
				crickets[i].setName("Grilo_0"+(i+1));
				list.add(crickets[i]);
				memberLimit += 2;
				
			}
			
			
			
		}
		team.add(list);
		//teamsPoints.add(0);
		teamJumps.add(0);
		teamDistance.add(0);
		list = new ArrayList<>(); //Dúvida: list.Clear() removia os itens que eram passados para a lista team. Pq?
		
		for (int i = 0; i < crickets.length; i++) {
			crickets[i].start();
		}
		
	}
	
	public static void teamPoint(int teamNumber, int jumps, int jumpedDistanceSum) {
		//teamsPoints.set(teamNumber, teamsPoints.get(teamNumber) + 1);
		try {
			teamJumps.set(teamNumber, teamJumps.get(teamNumber) + jumps);
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println(team);
			//System.out.println(e);
		}
		
		teamDistance.set(teamNumber, teamDistance.get(teamNumber) + jumpedDistanceSum);
	}
	
	public static void defineWinner(int teamNumber) {
		if(!hasWinner) {
			winner = teamNumber+1;
			hasWinner = true;
		}
		
		
	}
	
	public static void announceWinner() {
		
		for(int i = 0; i < team.size(); i++) {
			System.out.println("Time " + (i+1) + ": Total de pulos: " + teamJumps.get(i) + " - Distância Percorrida: " + teamDistance.get(i));
		}
		System.out.println("Time " + winner + " foi o vencedor");
		
	}

}
