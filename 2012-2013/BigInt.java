/*
* Grupo 008
* fc43486 TP2
* fc43637 TP4
* fc43638 TP4
*/

/**
 * Class that represents an integer of unbounded capacity
 */
public class BigInt {

	private String bigInt;

/**
* Creates a new BigInt with value 0
*/
public BigInt() {
	bigInt = "0";
}

/**
* Creates a new BigInt with a value obtained from a String.
* @param text the textual representation of the number
* The operation requires that (is only defined if) isValid(text)
*/
public BigInt(String text) {
	bigInt = text;
}

/**
* Checks if a String is a sequence of digits '0' to '9', 
* possibly preceded by '-', with no limits whatsoever
* @param text the textual representation to analyze
* @return true if the String is a valid textual 
* representation of an integer, false otherwise
*/
public static boolean isValid(String text) {
	boolean eValido = false;
	// Usou-se o metodo isDigit da classe Character
	if(Character.isDigit(text.charAt(0)) || text.charAt(0) == '-') 
		eValido = true;

	for(int i = 1; i < text.length(); i++) {
		//se nao for um digito (numero inteiro entre 0 e 9) passa a false
		if(!Character.isDigit(text.charAt(i)))
			eValido = false;
	}
	return eValido;
}

/**
* Checks if this BigInt is equal to some other BigInt 
* @param other the BigInt to which this object is compared to
* @return true if the BigInts are equal, false otherwise
*/
public boolean equals(BigInt other) {
	return bigInt.equals(other.bigInt);
}

/**
* Checks if this BigInt is bigger than some other BiInt 
* @param other the BigInt to which this object is compared to
* @return true if this BigInt is greater than other, false otherwise
*/
public boolean bigger(BigInt other) {
	long esteBigInt  = Long.parseLong(bigInt);
	long outroBigInt = Long.parseLong(other.bigInt);

	return esteBigInt > outroBigInt;
}

/**
* Checks if this BigInt is smaller than some other BiInt 
* @param other the BigInt to which this object is compared to
* @return true if this BigInt is smaller than other, false otherwise
*/
public boolean smaller(BigInt other) {
	long esteBigInt  = Long.parseLong(bigInt);
	long outroBigInt = Long.parseLong(other.bigInt);

	return esteBigInt < outroBigInt;
}

/**
* Adds to this BigInt the value given by some other BigInt
* @param other the value added to this BigInt
*/
public void add(BigInt other) {

	long esteBigInt  = Long.parseLong(bigInt);
	long outroBigInt = Long.parseLong(other.bigInt);

	long resultado = esteBigInt + outroBigInt;

	bigInt = Long.toString(resultado);
}

/**
* Subtracts to this BigInt the value given by some other BigInt
* @param other the value subtracted to this BigInt
*/
public void sub(BigInt other) {

	long esteBigInt  = Long.parseLong(bigInt);
	long outroBigInt = Long.parseLong(other.bigInt);

	long resultado = esteBigInt - outroBigInt;
	
	bigInt = Long.toString(resultado);
}

/**
* Modifies this BigInt so as to store the absolute value 
*/
public void abs() {

	long moduloBigInt  = Long.parseLong(bigInt);
	if(moduloBigInt < 0)
		moduloBigInt = moduloBigInt * (-1);

	bigInt = Long.toString(moduloBigInt);
}

/**
* Returns a textual representation of the BigInt
* @return the text that represents the value stored in the BigInt
* The textual representation corresponds to the number itself
*/
public String toString() {
	return bigInt;

	}
}