
public class Class1 {

	public static void main(String[] args) {	
		System.out.println(checkBit(25,0));
		System.out.println(countSetBits(25));
		//If x = 3, y = 4 => generate1 should return 11000
		System.out.println(generate1(3,4));
		//If x = 3, y = 2 => generate2 should return 11100
		System.out.println(generate2(3,2));
		// N & N-1 => RESETS least significant bit
		System.out.println(countSetBits2(25));
	}

	private static int countSetBits2(int N) {
		int count = 0;
		while(N > 0) {
		    count++;
			N = N & N-1;
		}
		return count;
	}

	private static int generate2(int x, int y) {
		return (( 1<<x ) - 1 )<<y;
	}

	private static int generate1(int x, int y) {
		return ((1<<x) | (1<<y));
	}

	private static int countSetBits(int N) {
		int count = 0;
		for(int i = 0; i<=31; i++) {
			if(checkBit(N,i)) count++;
		}
		return count;
	}

	public static boolean checkBit(int N, int i) {   
		return ((N>>i) & 1) == 1;
	}

}
