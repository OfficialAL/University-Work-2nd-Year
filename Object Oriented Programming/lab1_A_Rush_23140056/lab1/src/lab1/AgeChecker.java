package lab1;

public class AgeChecker {

	public static void main(String[] args) {
		double age = 19.5;
		
		if(age >= 68) {
			System.out.println("You're a Pensioner");
		} else if(age >= 18) {
			System.out.println("You're an Adult");
		} else if(age >=12 && (age <18)) {
			System.out.println("You're a Young Adult");
		} else if(age >=5 && (age <12)) {
			System.out.println("You're a Child");
		} else if(age >3 && (age >=5)) {
			System.out.println("You're a Toddler");
		} else if(age <3) {
			System.out.println("You're an Infant");
		} else if(age >0) {
			System.out.println("Invalid age please use 0-99 range");
		} else {
			System.out.println("You're a Baby");
		}

	}
}
