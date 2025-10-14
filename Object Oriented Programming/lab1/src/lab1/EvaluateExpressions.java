package lab1;

/**
 * Practice class for evaluating various Java expressions
 * This code intentionally contains repetitive operations for learning purposes
 */
public class EvaluateExpressions {

	// Suppress warnings for unused variables and duplicate code - this is practice code
	@SuppressWarnings({"unused", "duplicates", "ConstantConditions"})
	public static void main(String[] args) {
		System.out.println("Arithmetic Operations");
		System.out.println(1+1);
		System.out.println(5-2*3);
		System.out.println((5-2)*3);
		System.out.println(4.5+6.7);
		System.out.println(3-2.1);
		System.out.println(6/2);
		System.out.println(7/2);
		System.out.println(7.0/2.0);
		System.out.println(8%2);
		System.out.println(9%2);
		System.out.println();
		
		System.out.println("Comparison Operations");
		// Note: The following expressions are intentionally simple for practice purposes
		boolean x = (1+1 ==2);
		System.out.println(1+1 ==2);
		System.out.println(x);
		boolean y = (1+1!=3);
		System.out.println(1+1!=3);
		System.out.println(y);
		boolean z = (1<3);
		System.out.println(1<3);
		System.out.println(z);
		boolean aa = (1>3);
		System.out.println(1>3);
		System.out.println(aa);
		boolean ab = (3<=3);
		System.out.println(3<=3);
		System.out.println(ab);
		boolean ac = (3>=1);
		System.out.println(3>=1);
		System.out.println(ac);
		System.out.println();
		//These end up being the same a funny waste of time to boolean to same problem
		
		System.out.println(true&&false);
		System.out.println(true||false);
		System.out.println(!false);
		System.out.println();
		
		System.out.println("String Operations");
		System.out.println("Hello"+"World!");
		System.out.println("Catch"+"22");
		System.out.println("A piece of string".length());
		System.out.println("ABCDEFG".charAt(3));
		System.out.println("MXVIII".toLowerCase());
		System.out.println("Yellow Submarine".startsWith("Yellow"));
		System.out.println();
		
		System.out.println("Type Conversions");
		System.out.println((double)5);
		System.out.println((int)5.3);
		System.out.println((int)'a');
		System.out.println((char)120);
		System.out.println(String.valueOf(1234));
		System.out.println(Integer.parseInt("5678"));
		System.out.println(Double.parseDouble("3.14159"));
		System.out.println();
		
	
		
		
	}

}
