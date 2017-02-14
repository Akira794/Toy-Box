/* FizzBuzz_RC.java*/

public class FizzBuzz_RC {

	public static void main(String[] args) {
	
		FizzBuzz_RC fb;
		fb = new FizzBuzz_RC();
		fb.fizzbuzz(100);
	}
	
	void fizzbuzz(int n){
	
	if(n > 1)
		fizzbuzz(n-1);

	if(n % 3 == 0 && n % 5 == 0)
		System.out.println("Fizz Buzz");
	
	else if(n % 3 == 0)
		System.out.println("Fizz");

	else if(n % 5 == 0)
		System.out.println("Buzz");

	else
		System.out.println(n);

	}
}

