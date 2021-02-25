
public class Class3 {

	public static void main(String[] args) {
		// check if sum of the elements in the subset is K or not
		int[] ar = { 3, 19, -2, 15, -9, 18, 26, 5 };
		System.out.println(checkSumK(ar, 12));
		int[] ar2 = { 1, 2, 3, 4 };
		System.out.println(optimize1(ar2));
		System.out.println(optimize2(ar2));
		/*****Recursion*****/
		System.out.println(fact(7));
		System.out.println(fib(10));
		//Sum of arithmetic a = 1, d=5, N = 10
		System.out.println(sumOfArthmetic(1,5,2));
		//Towers of Hanoi N = 3, src =1 , temp = 2, dest=3
		towersOfHanoi(3,1,2,3);
		
		System.out.println(pow(3,4));
		System.out.println(Math.pow(3,4));
		
		// Check if subset sum is k or not
		System.out.println(checkSumKRecursion(ar, 12, 0, ar.length, 0));
		
		int N = 6;
		char str[] = new char[N];
		int open = 0;
		int closed = 0; 
		int idx = 0;
		generateValidParanthesisLexicographically(N, str, open, closed, idx);
		
		String[] dict = {"sm", "a", "inter","views", "s", "art", "smart", "views" };
		String partitionStr = "smartinterviews";
		System.out.println(partition(partitionStr,dict,0));
	}

	private static boolean partition(String partitionStr, String[] dict, int idx) {
		for(int i = idx; i<partitionStr.length(); i++) {
			if(checkIfStrInDict(partitionStr,idx,i,dict) && partition(partitionStr,dict,i+1)) return true;
		}
		return false;
	}

	private static boolean checkIfStrInDict(String partitionStr, int idx, int end, String[] dict) {
		String str = partitionStr.substring(idx,end);
		for(int i = 0 ; i<dict.length; i++) {
			if(str.equals(dict[i])) return true;
		}
		return false;
	}

	private static void generateValidParanthesisLexicographically(int N, char[] str, int open, int closed, int idx) {
		if(idx  == N ) System.out.println(str);
		if(open< N/2) {
			str[idx] = '(';
			generateValidParanthesisLexicographically(N, str, open+1, closed, idx+1);
		}
		if(open>closed) {
			str[idx] = ')';
			generateValidParanthesisLexicographically(N, str, open, closed+1, idx+1);
		}
	}

	private static boolean checkSumKRecursion(int[] ar, int k, int idx, int N, int sum) {
		if(idx == N) return sum == k;
		return checkSumKRecursion(ar,k,idx+1,N,sum+ar[idx]) || checkSumKRecursion(ar,k,idx+1,N,sum);
	}

	private static int pow(int a, int b) {
		int prod = 1;
		if(b == 0) return 1;
		if(b%2 == 0 )  {
			prod = pow(a,b/2); 
			return prod*prod;
		} else {
			prod = pow(a,b/2); 
			return prod*prod*a;
		}
	}

	private static void towersOfHanoi(int N, int src, int temp, int dest) {
		if(N == 0 ) return;
		towersOfHanoi(N-1,src,dest,temp);
		System.out.println(N + " " + src + "->" + dest );
		towersOfHanoi(N-1,temp,src,dest);
	}

	private static int sumOfArthmetic(int a, int d, int N) {
		if(N==0) return 0;
		return a + sumOfArthmetic(a+d,d,N-1);
	}

	private static int fib(int N) {
		if(N==1) return 0;
		if(N==2) return 1;
		return fib(N-1) + fib(N-2);
	}

	private static int fact(int N) {
		if(N == 0) return 1;
		return N * fact(N-1);
	}

	private static int optimize2(int[] ar) {
		
		int ans = 0;
		int N = ar.length;
		/*
		 * for(int i = 0; i<N; i++) {
		 *  for(int j = 0; j<N; j++) {
		 *   ans = ans+(ar[i]^ar[j]); 
		 *   } 
		 *  }
		 */
		for(int i = 0; i<=30; i++) {
			int set = 0;
			for(int j =0 ; j < N; j++) {
				if(Class1.checkBit(ar[j],i)) set++;
			}
			ans = ans + (1<<i) * set * (N-set) * 2; 
		}
		return ans;
	}

	private static int optimize1(int[] ar) {
		int ans = 0;
		int N = ar.length;
		/*
		 * for(int i = 0; i<N; i++) { 
		 * for(int j = 0; j<N; j++) {
		 *  ans = ans^(ar[i]+ar[j]); 
		 * } 
		 * }
		 */
		for (int i = 0; i < N; i++) {
			ans = ans ^ (2 * ar[i]);
		}
		return ans;
	}

	// O(2 pow N * N)
	private static boolean checkSumK(int[] ar, int k) {
		for (int i = 0; i <= Math.pow(2, ar.length) - 1; i++) {
			int sum = 0;
			for (int j = 0; j < ar.length; j++) {
				if (Class1.checkBit(i, j))
					sum = sum + ar[j];
			}
			if (sum == k)
				return true;
		}
		return false;
	}
}
