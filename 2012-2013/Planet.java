/*
* Grupo 008
* fc43486 TP2
* fc43637 TP4
* fc43638 TP4
*/

/**
 * Class that represents a planet, comprising a name and a position in space
 */
public class Planet {

		private String nomePlaneta;
		private SpacePoint posicao;

/**
* Creates a new Planet with the given name
* @param name the name of the Planet
*/
public Planet(String name) {
	nomePlaneta = name;
	posicao = new SpacePoint();
}

/**
* Creates a new Planet with the given name and position in space
* @param name the name of the Planet
* @param pos the position in space of the Planet
*/
public Planet(String name, SpacePoint pos) {
	nomePlaneta = name;
	posicao = pos;
}

/**
* Moves the Planet in the three axis according to some given values
* @param dx the value to translate in the x-axis
* @param dy the value to translate in the y-axis
* @param dz the value to translate in the z-axis
*/
public void moves(BigInt dx, BigInt dy, BigInt dz) {
	posicao.translate(dx, dy, dz);
}

/**
* Determines the distance between this Planet and a given SpacePoint 
* @param p the SpacePoint which distance with respect to this Planet 
* is determined
* @return the distance between the Planet and the SpacePoint
*/
public BigInt dist(SpacePoint p) {

	BigInt distancia = posicao.dist(p);

	return distancia;
}

/**
* Returns the name of this Planet
* @return the name of this Planet
*/
public String name() {
	return nomePlaneta;
}

/**
* Returns the position in space of this Planet
* @return the position in space of this Planet
*/
public SpacePoint position() {
	return posicao;
}

/**
* Creates and returns a textual representation of this Planet
* @return the textual representation
* The textual representation corresponds to four distinct lines
* the first beginning with "Planet: "and finishing with the name 
* of the planet while the remaining three lines are given by the 
* textual representation of a SpacePoint 
*/
public String toString() {
	return "Planet: " + nomePlaneta + "\n" + posicao.toString() + "\n";
	}
}