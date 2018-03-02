package psn;


import java.util.Map;

import factory.SommetFactory;
import robot.Robot;

public class LevelTwoScenario {

	public void execute() {
		Robot robot = new Robot();
		Graphe A = new Graphe(Main.FICHIER_GRAPHE, Main.FICHIER_ORIENTATION);

		int[][] matrix = A.getMatAdj();
		Map<Integer, Sommet> sommets = SommetFactory.getInstances(matrix);
		Sommet sommetDep = sommets.get(1);
		// On donne un point d'arrivée connecté au sommetDep
		Sommet sommetArr = sommetDep.getSuccesseurs().get(0);

		while(true) {
			if(robot.getX() == sommetArr.getX() && robot.getY() == sommetArr.getY()) {
				break;
			}

			switch(robot.getSensor().getState()) {
			case HORS_PISTE:
				// machin
				break;
			case PISTE:
				// machin
				break;
			case INTERSECTION:
				// machin
				break;
			}
		}
	}
}
