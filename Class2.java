
public class Class2 {

	static int M = 1000000007;

	public static void main(String[] args) {
		System.out.println(numberOfDivisors(12));
		int[] ar = { 1, 1, 2, 3, 7, 7, 4, 4, 8, 6, 5, 5, 6, 8, 2 };
		System.out.println(findTheNonRepeatingNum(ar));
		System.out.println(pow(3, 21));
		int[] ar2 = { 1, 1, 2, 3, 7, 7, 4, 4, 8, 6, 5, 5, 6, 8, 2, 10 };
		findTheNonRepeatingNum2(ar2);
		int[] ar3 = { 3, 1, 1, 9, 3, 1, 3, 9, 9, 5 };
		findTheNonRepeatingNum3(ar3);
	}

	private static void findTheNonRepeatingNum3(int[] ar) {
		int ans = 0;
		for (int i = 0; i <= 30; i++) {
			int count = 0;
			for (int j = 0; j < ar.length; j++) {
				if (Class1.checkBit(ar[j], i))
					count++;
			}
			if (count % 3 != 0)
				ans = ans | (1 << i);
		}
		System.out.print(ans);
	}

	private static void findTheNonRepeatingNum2(int[] ar) {
		int xorRes = 0;
		for (int i = 0; i < ar.length; i++) {
			xorRes = xorRes ^ ar[i];
		}
		int lastSetBit = xorRes & (~(xorRes - 1));
		int x = 0;
		int y = 0;
		for (int i = 0; i < ar.length; i++) {
			if ((lastSetBit & ar[i]) == lastSetBit)
				x = x ^ ar[i];
			else
				y = y ^ ar[i];
		}
		System.out.println(x + " " + y);
	}

	private static long pow(int a, int b) {
		long ans = 1;
		int x = a;
		/*
		 * for (int i = 0; i <= 31; i++) { if (Class1.checkBit(b, i)) { ans = (ans *
		 * x)%M; } x = (x * x)%M;
		 * 
		 * }
		 */
		while (b != 0) {
			if ((b & 1) == 1) {
				ans = ans * x;
			}
			b = b >> 1;
			x = x * x;
		}
		return ans;
	}

	private static int findTheNonRepeatingNum(int[] ar) {
		int n = 0;
		for (int i = 0; i < ar.length; i++) {
			n = n ^ ar[i];
		}
		return n;
	}

	private static int numberOfDivisors(int N) {
		int count = 0;
		for (int i = 1; i <= N / i; i++) {
			if (N % i == 0) {
				if (i == N / i) {
					count++;
				} else
					count = count + 2;
			}
		}
		return count;
	}

}
