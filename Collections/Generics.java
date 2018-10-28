//- Metodos genericos
class Dog extends Animal {}
class Cat extends Animal {}
class Bird extends Animal {}

doc.checkAnimals(dogs); // send a List<Dog>
doc.checkAnimals(cats); // send a List<Cat>
doc.checkAnimals(birds); // send a List<Bird>

public void addAnimal(List<Animal> animals)

public void addAnimal(List<? extends Animal> animals)	//Cualquier suptipo generico de Animal (IS)
public void addAnimal(List<? super Dog> animals)		// Acepta Dog o cualquier cosa arriba en su arbol de herencia 
public void addAnimal(List<?> list)						// Acepta cualquier cosa

List<?> foo = new ArrayList<? extends Animal>();		//you cannot use wildcard notation in the object creation

//-Generic Declarations, es un marcador para el tipo que se pasara
public interface List<E>	//<your type goes here>

//-wilcard ?

public class Rental {
	private List rentalPool;
	private int maxNum;
	public Rental(int maxNum, List rentalPool) {
		this.maxNum = maxNum;
		this.rentalPool = rentalPool;
	}
	public Object getRental() {
		// blocks until there's something available
		return rentalPool.get(0);
	}
	public void returnRental(Object o) {
		rentalPool.add(o);
	}
}


import java.util.*;
	public class CarRental extends Rental {
	public CarRental(int maxNum, List<Car> rentalPool) {
		super(maxNum, rentalPool);
	}
	public Car getRental() {
		return (Car) super.getRental();
	}
	public void returnRental(Car c) {
		super.returnRental(c);
	}
	
	public void returnRental(Object o) {
		if (o instanceof Car) {
			super.returnRental(o);
		} else {
			System.out.println("Cannot add a non-Car");
			// probably throw an exception
		}
	}
	}


import java.util.*;
public class RentalGeneric<T> { // "T" is for the type
	// parameter
	private List<T> rentalPool; // Use the class type for the
								// List type
	private int maxNum;
	public RentalGeneric( int maxNum, List<T> rentalPool) { // constructor takes a
															// List of the class type
		this.maxNum = maxNum;
		this.rentalPool = rentalPool;
	}
	public T getRental() { // we rent out a T
						   // blocks until there's something available
		return rentalPool.get(0);
	}
	public void returnRental(T returnedThing) { // and the renter
		// returns a T
		rentalPool.add(returnedThing);
	}
}

//- You can use more than one parameterized type in a single class definition:

public class UseTwo<T, X> {
	T one;
	X two;
	
	UseTwo(T one, X two) {
		this.one = one;
		this.two = two;
	}
	
	T getT() { return one; }
	X getX() { return two; }
	
	// test it by creating it with <String, Integer>
	public static void main (String[] args) {
	
	UseTwo<String, Integer> twos =
		new UseTwo<String, Integer>("foo", 42);
		String theT = twos.getT(); // returns a String
		int theX = twos.getX(); // returns Integer, unboxes to int
	}
}
