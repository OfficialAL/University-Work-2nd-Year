package lab1;

public class TenGreenBottles {

	public static void main(String[] args) {
		for(int n = 10; n > 0; n--) {
			String word = (n == 1) ? "bottle" : "bottles";
			System.out.println(n + " green " + word + ", standing on the wall");
			System.out.println();
			System.out.println(n + " green " + word + ", standing on the wall");
			System.out.print("And if one green bottle ");
			System.out.println("should accidentally fall");
			System.out.println();
			
			int next = n-1;
			String nextWord = (next == 1) ? " bottle " : " bottles ";
			
			if (next > 0) {
				System.out.println();
				System.out.printf("There'll be " + next + " green" + nextWord, ", standing on the wall");
				System.out.println();
			} else {
				System.out.println("There'll be no green bottles standing on the wall.");
			}
		}
		
	}
}
