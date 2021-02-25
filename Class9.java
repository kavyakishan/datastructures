import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Class9 {

	public static void main(String[] args) {
		// Normal count primes O(N root N)
		System.out.println(countPrimes(1500));

		// Sieve of Erasthomas O(N(1/2 + 1/3 + 1/5 + 1/7 ... ))
		System.out.println(countPrimesBySieve(1500));

		// Sieve of Erasthomas Improved (O(root N ...)
		System.out.println(countPrimesBySieveImproved(1500));

		// Segmented Sieve. Count primes in a range
		System.out.println(countPrimesInARange(101, 150));

		// --------------------------------
		// Game theory => Check Notes
		// --------------------------------

		// find if there is a subset whose sum is K
		// 1. generate subsets using bits approach 2 pow N * N
		// 2. recursion 2 pow N
		// 1<=N<=40
		// subsets in first half.. subsets in second half and then a+b =k approach
		int[] ar = { 8, -1, 15, 19, 3, 2 };
		System.out.println(findSubsetSumExists(ar, 18));

		// Given some queries.. i,j,k add k in indexes from [i,j]
		// Q : {(1,3,5),(2,4,-2),(6,7,9)}
		// Output1. sum of all the elements
		// Output2. array should be the ans. after all the queries are executed.

		// 1. Brute force (Q*N + N , N)
		// 2. (Q*1,1)
		System.out.println(arraySumAfterQueryExec());
		// 1. Brute force (Q*N,1)
		// 2. (Q*1 + N, 1)
		arrayAfterQueryExec();

		// Fill array B such that each index of arr B is multiplication of
		// all the elements in ar A except that particular index.
		int[] A = { 5, 8, -1, 2, 15, -8, 3, 17 };
		// 1. O(N2) -> Brute force
		// 2. O(N+N) -> product of all the numbers divided by the element at that index
		// 3. O(N+N+N,N+N) -> Prefix product and suffix product

	}

	private static void arrayAfterQueryExec() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] ar = new int[n];
		int q = in.nextInt();
		int sum = 0;
		while (q-- > 0) {
			int i = in.nextInt();
			int j = in.nextInt();
			int k = in.nextInt();
			ar[i] = k;
			ar[j + 1] = -1 * k;
		}
		System.out.print(ar[0] + " ");
		for (int i = 1; i < n; i++) {
			ar[i] = ar[i] + ar[i + 1];
			System.out.println(ar[i] + " ");
		}

	}

	private static int arraySumAfterQueryExec() {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		int sum = 0;
		while (q-- > 0) {
			int i = in.nextInt();
			int j = in.nextInt();
			int k = in.nextInt();
			sum = sum + (j - i + 1) * k;
		}
		return sum;
	}

	private static boolean findSubsetSumExists(int[] ar, int k) {
		boolean isExists = false;
		int size = ar.length;
		HashSet<Integer> first = new HashSet<Integer>();
		// List<Integer> second = new ArrayList<Integer>();
		isExists = subsets(ar, 0, size / 2, 0, first, k);
		if (isExists)
			return true;
		isExists = checksumsubsets(ar, size / 2, size, 0, first, k);
		if (isExists)
			return true;

		return isExists;
	}

	private static boolean subsets(int ar[], int idx, int size, int sum, HashSet<Integer> set, int k) {
		if (idx == size) {
			set.add(sum);
			if (sum == k)
				return true;
			return false;
		}
		return (subsets(ar, idx + 1, size, sum + ar[idx], set, k) || subsets(ar, idx + 1, size, sum, set, k));
	}

	private static boolean checksumsubsets(int ar[], int idx, int size, int sum, HashSet<Integer> set, int k) {
		if (idx == size) {
			if (set.contains(k - sum) || k == sum)
				return true;
			return false;
		}
		return (checksumsubsets(ar, idx + 1, size, sum + ar[idx], set, k)
				|| checksumsubsets(ar, idx + 1, size, sum, set, k));
	}

	private static int countPrimesInARange(int a, int b) {
		int count = 0;
		List<Integer> L = sieveAlgo(100);
		boolean[] primes = new boolean[b - a + 1];
		for (int i = 0; i < b - a + 1; i++) {
			primes[i] = true;
		}
		count++;
		for (int i = 0; i < L.size() && L.get(i) * L.get(i) <= b; i++) {
			int x = smallestMultipleInTheRange(a, b, L.get(i));
			if (primes[x - a]) {
				for (int j = x; j <= b; j = j + L.get(i)) {
					if (primes[j - a]) {
						primes[j - a] = false;
						count++;
					}
				}
			}
		}
		return b - a + 1 - count;
	}

	private static int smallestMultipleInTheRange(int a, int b, int i) {
		int x = a % i == 0 ? a : a + (i - a % i);
		return x;
	}

	private static List sieveAlgo(int N) {
		List<Integer> L = new ArrayList<Integer>();
		boolean[] primes = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			primes[i] = true;
		}
		primes[1] = false;
		for (int i = 2; i <= N; i++) {
			if (primes[i]) {
				L.add(i);
				for (int j = i * i; j <= N; j = j + i) {
					if (primes[j]) {
						primes[j] = false;
					}
				}
			}
		}
		return L;
	}

	private static int countPrimesBySieveImproved(int N) {
		int count = 0;
		boolean[] primes = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			primes[i] = true;
		}
		primes[1] = false;
		count++;
		for (int i = 2; i * i <= N; i++) {
			if (primes[i]) {
				for (int j = i * 2; j <= N; j = j + i) {
					if (primes[j]) {
						primes[j] = false;
						count++;
					}
				}
			}
		}
		return N - count;
	}

	private static int countPrimesBySieve(int N) {
		int count = 0;
		boolean[] primes = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			primes[i] = true;
		}
		primes[1] = false;
		count++;
		for (int i = 2; i <= N; i++) {
			if (primes[i]) {
				for (int j = i * 2; j <= N; j = j + i) {
					if (primes[j]) {
						primes[j] = false;
						count++;
					}
				}
			}
		}
		return N - count;
	}

	private static int countPrimes(int N) {
		int count = 0;
		for (int i = 2; i <= N; i++) {
			if (findDivisors(i) == 2)
				count++;
		}
		return count;
	}

	private static int findDivisors(int N) {
		int numOfDivisors = 0;
		for (int i = 1; i <= N / i; i++) {
			if (N % i == 0) {
				if (N / i == i)
					numOfDivisors = numOfDivisors + 1;
				else
					numOfDivisors = numOfDivisors + 2;
			}
		}
		return numOfDivisors;
	}

}
