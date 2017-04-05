/*
* Grupo 008
* fc43486 TP2
* fc43637 TP4
* fc43638 TP4
*/

/**
 * Class that represents a position in space, comprising three coordinates
 */
public class SpacePoint {

	private BigInt xx;
	private BigInt yy;
	private BigInt zz;

/**
* Creates a new SpacePoint referring the origin 
*/
public SpacePoint() {
	xx = new BigInt();
	yy = new BigInt();
	zz = new BigInt();
}

/**
* Creates a new SpacePoint using given x, y and z values
* @param x the x-axis coordinate
* @param y the y-axis coordinate
* @param z the z-axis coordinate
*/
public SpacePoint(BigInt x, BigInt y, BigInt z) {
	xx = x;
	yy = y;
	zz = z;
}

/**
* Returns the x-axis projection of this coordinate
* @return the x-axis coordinate
*/
public BigInt getX() {
	return xx;
}

/**
* Returns the y-axis projection of this coordinate
* @return the y-axis coordinate
*/
public BigInt getY() {
	return yy;
}

/**
* Returns the z-axis projection of this coordinate
* @return the z-axis coordinate
*/
public BigInt getZ() {
	return zz;
}

/**
* Translates the SpacePoint using the given values
* @param dx the value to add to the x coordinate
* @param dy the value to add to the y coordinate
* @param dz the value to add to the z coordinate
*/
public void translate(BigInt dx, BigInt dy, BigInt dz) {
	xx.add(dx);
	yy.add(dy);
	zz.add(dz);
}

/**
* Determines the distance between this SpacePoint and some other
* @param other the other SpacePoint which distance to this 
* SpacePoint is calculated 
* @return the distance between this and the other SpacePoints
*/
public BigInt dist(SpacePoint other) {
	BigInt novoX = new BigInt(xx.toString());
	BigInt novoY = new BigInt(yy.toString());
	BigInt novoZ = new BigInt(zz.toString());
	BigInt distancia = new BigInt();

	// |x1 - x2| + |y1 - y2| + |z1 - z2|
	novoX.sub(other.xx); // novoX - xx
	novoY.sub(other.yy);
	novoZ.sub(other.zz);

	novoX.abs(); // |novoX - xx|
	novoY.abs();
	novoZ.abs();

	distancia.add(novoX);
	distancia.add(novoY);
	distancia.add(novoZ);

	return distancia;
}

/**
* Creates and returns a textual representation of this SpacePoint
* @return the textual representation
* The textual representation corresponds to three distinct lines each
* beginning by "x: ", "y: " and "z: " and finishing with the respective value
*/
public String toString() {
	return  "x: " + xx.toString() + "\n" +
			"y: " + yy.toString() + "\n" +
			"z: " + zz.toString();
	}
}