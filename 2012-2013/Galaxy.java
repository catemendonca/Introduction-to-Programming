/*
* Grupo 008
* fc43486 TP2
* fc43637 TP4
* fc43638 TP4
*/

/**
 * Class that represents a galaxy, comprising a name and a set of Planets
 */
public class Galaxy {

	private String nomeGalaxia;
	private Planet[] planetas;
	private int nPlanetas;

/** * Creates a new Galaxy with no associated Planets and a given name
* @param name the name of the Galaxy
*/
public Galaxy(String name) {
	nomeGalaxia = name;
	planetas    = new Planet[5000];
	nPlanetas   = 0;
}

/**
* Checks if this Galaxy has a planet with some given name
* @param planetName the name of the planet
* @return true is the Galaxy has a Planet named planetName, false otherwise 
*/
public boolean planetExists(String planetName) {
	boolean planetaExiste = false;

	for(int i = 0; i < nPlanetas; i++) {
		if(planetas[i].name().equals(planetName))
			planetaExiste = true;

	}
	
	return planetaExiste;
}

/**
* Checks if this Galaxy has no room for more planets
* @return true if the Galaxy has no room for more planets, false otherwise
*/
public boolean complete() {
	return nPlanetas == 5000;
}

/**
* Adds a planet with a given name and position in space to this Galaxy
* @param planetName the name of the planet to add
* @param x the x-axis coordinate of the planet to add
* @param y the y-axis coordinate of the planet to add
* @param z the z-axis coordinate of the planet to add
* The operation requires that (is only defined if) 
* !planetExists(planetName) && !complete()
*/
public void addPlanet(String planetName, BigInt x, BigInt y, BigInt z) {
	if(nPlanetas < 5000) { // E necessario ou o cliente tem de saber?
		SpacePoint posicao = new SpacePoint(x,y,z);
		planetas[nPlanetas] = new Planet(planetName, posicao);
		nPlanetas ++;
	}
}

/**
* Returns the number of planets in this Galaxy
* @return the number of planets in this Galaxy
*/
public int size() {
	return nPlanetas;
}

/**
* Determines the index of nomePlaneta
* @param nomePlaneta the name of the planet
* @return index of nomePlaneta
*/
private int indicePlaneta(String nomePlaneta) {
	int indice = 0;
	
	for(int i = 0; i < nPlanetas; i++) {
		if(planetas[i].equals(nomePlaneta))
			indice = i;
}
	
return indice;

}

/**
* Determines the distance to travel between two planets in this Galaxy
* @param originPlanet the name of the origin planet 
* @param destinationPlanet the name of the destination planet
* @return the distance between planets originPlanet and destinationPlanet
* The operation requires that (is only defined if)
* planetExists(originPlanet) && planetExists(destinationPlanet)
*/
public BigInt distance(String originPlanet, String destinationPlanet) {
	int indicePlanetaOrigem;
	int indicePlanetaDestino;

	indicePlanetaOrigem = indicePlaneta(originPlanet);
	indicePlanetaDestino = indicePlaneta(destinationPlanet);

	BigInt distancia;
	distancia = planetas[indicePlanetaOrigem].dist(planetas[indicePlanetaDestino].position());

	return distancia;
}

/**
* Moves a given planet of the galaxy in the three axis according to 
* some given values
* @param name the planet to move
* @param dx the value to translate in the x-axis
* @param dy the value to translate in the y-axis
* @param dz the value to translate in the z-axis
* The operation requires that (is only defined if) planetExists(name)
*/
public void movePlanet(String name, BigInt dx, BigInt dy, BigInt dz) {
	if(planetExists(name)) 
		planetas[indicePlaneta(name)].moves(dx, dy, dz);

}

/**
* Obtains a textual representation of the planet with a given name
* @param planetName the name of the planet which info is obtained  
* @returns the textual representation of the planet named planetName
* The operation requires that (is only defined if) planetExists(planetName)
*/
public String planetInfo(String planetName) {
	return planetas[indicePlaneta(planetName)].toString();
}

/**
* Returns the name of the Galaxy
* @return the name of the Galaxy
*/
public String name() {
	return nomeGalaxia;
}

/**
* Creates and returns a textual representation of this Galaxy
* @return the textual representation
* The textual representation consists in a sequence of lines
* the first beginning with "Galaxy : " and finishing with the name of 
* the Galaxy, the second line beginning with "Number of Planets: " and
* finishing with the number of planets, while the remaining lines are 
* given by the textual description of all the planets in the Galaxy
*/

public String toString() {
	StringBuilder representacao = new StringBuilder("Galaxy : " + nomeGalaxia + "\n" + 
															"Number of Planets: " + nPlanetas 
															+ "\n"); 

	for(int i = 0; i < nPlanetas; i++) {
		representacao.append(planetas[i].toString());
}
	
	return representacao.toString();

	}
}