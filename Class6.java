import java.util.Arrays;

public class Class6 {

	public static void main(String[] args) {
		// Find sqrt of a number
		// 1. Math.sqrt
		// 2. O(N)
		// 3. O(N/2)
		// 4. Binary search O(logN)
		findSqrt(25);
		// ar = [2,2,4,4,3,3,5,5,7,8,8,1,1];
		// ans : 7
		// Solution 1 : XOR O(N)
		// 2. iterate and find frequency O(N)
		// 3. Hashmap O(N+N)
		// 4. Binary Search O(logN)
		int[] ar = { 2, 2, 4, 4, 3, 3, 5, 5, 7, 8, 8, 1, 1 };
		findNonRepeatingNum(ar);

		// Workers problem
		// min of (max of workers dist)
		// tasks: [8,19,7,13,15,8,14,2,6,17,3]
		int[] tasks = { 8, 19, 7, 13, 15, 8, 14, 2, 6, 17, 3 };
		int numOfWorkers = 3;
		findMinOfAllMaxs(tasks, numOfWorkers);

		// K relatives should be placed at diff houses whose plot nos are given
		// Dist b/w two houses is dist between 2 plots
		// Maximize the min dist
		int[] dist = { 2, 5, 25, 30, 32, 41, 45, 49, 53, 61, 70, 102 };
		int relatives = 3;
		// findMaxOfAllMins(dist,relatives); // Find floor

		// FInding the median using binary search
		int[] A = { 2, 5, 25, 30, 32, 41 };
		int[] B = { 4, 7, 17, 18, 29 };
		findMedian(A, B); // more conditions to work on

	}

	static int findBFloor(int[] ar, int q) {
		// max(ar[i]<=q) BSFloor
		Arrays.sort(ar);
		int low = 0;
		int hi = ar.length - 1;
		int ans = Integer.MIN_VALUE;
		while (low <= hi) {
			int mid = (low + hi) / 2;
			if (ar[mid] <= q) {
				ans = mid;
				low = mid + 1;
			} else
				hi = mid - 1;
		}
		return ans+1;
	}
	
	private static void findMedian(int[] A, int[] B) {
		int low = Math.min(A[0],B[0]);
		int high = Math.max(A[A.length-1], B[B.length-1]);
		while(low < high) {
			int mid = (low+high)/2;
			int Afloor = findBFloor(A, mid);
			int Bfloor = findBFloor(B, mid) ;
			int Aceil = A.length-Afloor;
			int Bceil = B.length-Bfloor ;
			int left = Afloor + Bfloor;
			int right = Aceil + Bceil;
			if(left==right) {System.out.print(mid); break;}
			else if (left < right) low = mid+1;
			else high = mid-1;
		}

	}

	private static void findMinOfAllMaxs(int[] tasks, int numOfWorkers) {
		int low = maxOfAr(tasks);
		int hi = sumOfAr(tasks);
		int mid = (low + hi) / 2;
		int ans = -1;
		while (low <= hi) {
			mid = (low + hi) / 2;
			if (valid(tasks, mid, numOfWorkers)) {
				ans = mid;
				hi = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		System.out.println("Minimum time required " + ans);
	}

	private static boolean valid(int[] tasks, int mid, int numOfWorkers) {
		int count = 0;
		int sum = 0;
		for (int i = 0; i < tasks.length; i++) {
			sum = sum + tasks[i];
			if (sum >= mid) {
				count++;
				sum = tasks[i];
			}
		}
		if (sum > 0)
			count++;
		if (count > numOfWorkers)
			return false;
		return true;
	}

	private static int sumOfAr(int[] tasks) {
		int sum = 0;
		for (int i = 0; i < tasks.length; i++) {
			sum = sum + tasks[i];
		}
		return sum;
	}

	private static int maxOfAr(int[] tasks) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < tasks.length; i++) {
			max = Math.max(max, tasks[i]);
		}
		return max;
	}

	// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14
	// -8 -8 2 2 5 5 13 17 17 25 25 31 31 40 40
	private static void findNonRepeatingNum(int[] ar) {
		int low = 0;
		int hi = ar.length - 1;
		int mid = (low + hi) / 2;
		while (low <= hi) {
			mid = (low + hi) / 2;
			if (mid % 2 == 0) {
				if (ar[mid] == ar[mid + 1])
					low = mid + 1;
				else
					hi = mid - 1;
			} else if (mid % 2 != 0) {
				if (ar[mid] == ar[mid - 1])
					low = mid + 1;
				else
					hi = mid - 1;
			}
		}
		System.out.println("Non repeating number is " + ar[mid]);
	}

	private static void findSqrt(int N) {
		int low = 0;
		int hi = N;
		while (low <= hi) {
			int mid = (low + hi) / 2;
			if (mid * mid == N) {
				System.out.println("Square root of N is " + mid);
				return;
			} else if (mid * mid < N)
				low = mid + 1;
			else
				hi = mid - 1;
		}
		return;
	}

}
