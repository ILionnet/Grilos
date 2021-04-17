package grilos;

import java.util.ArrayList;
import java.util.List;

public class Competition {
	
	public static boolean hasWinner = false;
	
	public static int winner = -1;
	
	public static final int cricketsAmount = 6;
	public static final int maxDistance = 200;
	
	public static List<Crickets> list = new ArrayList<>();
	public static List<List<Crickets>> team = new ArrayList<>();
	public static List<Integer> teamJumps = new ArrayList<>();
	public static List<Integer> teamDistance = new ArrayList<>();
	
	public static int teamsAmount = (int)cricketsAmount/3 + cricketsAmount%3;
	public static List<Integer> teamsPoints = new ArrayList<>();
	
	public static void main(String[] args) {
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
				teamsPoints.add(0);
				teamJumps.add(0);
				teamDistance.add(0);
				list = new ArrayList<>();
				crickets[i].setName("Grilo_0"+(i+1));
				list.add(crickets[i]);
				memberLimit += 2;
				
			}
			
			
			crickets[i].start();
		}
		team.add(list);
		teamsPoints.add(0);
		teamJumps.add(0);
		teamDistance.add(0);
		list = new ArrayList<>(); //Dúvida: list.Clear() removia os itens que eram passados para a lista tea. Pq?
		
	}
	
	public static void teamPoint(int teamNumber, int jumps, int jumpedDistanceSum) {
		System.out.println("Participante da equipe "+ (teamNumber + 1));
		teamsPoints.set(teamNumber, teamsPoints.get(teamNumber) + 1);
		teamJumps.set(teamNumber, teamJumps.get(teamNumber) + jumps);
		teamDistance.set(teamNumber, teamDistance.get(teamNumber) + jumpedDistanceSum);
		
		
		if(teamsPoints.get(teamNumber) == 3 && !hasWinner) {
			winner = teamNumber+1;
			//System.out.println("Equipe "+ (teamNumber+1) + " foi vitoriosa!");
			hasWinner = true;
			
		}
		
	}
	
	public static void announceWinner() {
		
		for(int i = 0; i < team.size(); i++) {
			System.out.println("Time " + (i+1) + ": Total de pulos: " + teamJumps.get(i) + " - Distância Percorrida: " + teamDistance.get(i));
		}
		/*System.out.println("Time 1:" + teamJumps.get(0));
		System.out.println("Equipe 2 pulou " + teamJumps.get(1));*/
		System.out.println("Time " + winner + " foi o vencedor");
		
	}

}
