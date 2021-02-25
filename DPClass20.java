
public class DPClass20 {
	static int[] dpFib = new int[10000];
	static int[] dpFibOptimized = new int[3];
	static int[] tilesOptimized = new int[6];
	static int[] diceOptimized = new int[7];
	static {
		for (int i = 0; i < 10000; i++) {
			dpFib[i] = -1;
		}
		for (int i = 0; i < 3; i++) {
			dpFibOptimized[i] = -1;
		}
	}

	public static void main(String[] args) {
		// Find Nth fibonacci seq
		System.out.println(fibIterative(10));
		System.out.println(fibRecursive(10));
		System.out.println(fibRecursiveDP(10));
		System.out.println(fibDPOptimized(10));

		// climb the steps 1 step at a time or 2 steps at a time
		System.out.println(fibStepsOptimized(4));

		// Imagine a floor 5xN tiles of size 5x1 we have infinite no.of tiles.
		System.out.println(tilesOptimized(6));

		// Imagine a floor 5xN tiles of size 5x1 we have infinite no.of tiles.
		// Tiles colored in black and white.
		System.out.println(tilesColoredOptimized(6));

		// Dice 6 faced, sum of all the rolls is 14, count the number of ways
		System.out.println(diceSum(14));

		int[] costAr = new int[7];
		costAr[1] = 7;
		costAr[2] = 4;
		costAr[3] = 2;
		costAr[4] = 4;
		costAr[5] = 3;
		costAr[6] = 11;
		// optimal substructure, min cost to reach the sum
		System.out.println(minCostSum(14, costAr));
		
		// Houses and Colors cost
		int[] red = {4,2,7,3,5,7};
		int[] green = {5,15,6,14,6,12};
		int[] blue = {7,12,4,2,3,8};
		
		// Minimum cost to paint all the houses such that no two adjacent houses can have the same color
		System.out.println(minCostToPaint(6, red, green, blue));
		
		int[] machineA = {2,12,7,6,5,17,9};
		int[] machineB = {5,4,2,1,4,2,6};
		int tasks = 7;
		int costToSwitch = 9;
		//Min cost to complete all the tasks using machine A nd B. there is a cost to switch between machines
		System.out.println(minCostToFinishTasks(machineA, machineB, tasks, costToSwitch));
		
		//Maximum Subarray Sum
		int[] ar = {9,-4,-1,7,15,-20,-13,11,14,8,-2,-3,6,-3};
		
		// O(N*N*N) => for every subarray, iterate and calc sum 
		// O(N*N) => carry forward sum technique
		// O(N) => prefix sum, whenever you find a negative number, reset the sum
		
		System.out.println(maximumSubarraySum(ar));
		
		
	}

	private static int maximumSubarraySum(int[] ar) {
		int[] dp = new int[ar.length];
		dp[0] = ar[0];
		int max = dp[0];
		for(int i = 1; i<ar.length; i++ ) {
			dp[i] = Math.max(0, dp[i-1]) + ar[i];
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	private static int minCostToFinishTasks(int[] machineA, int[] machineB, int tasks, int costToSwitch) {
		int[] dpA = new int[tasks];
		int[] dpB = new int[tasks];
		
		dpA[0]= machineA[0];
		dpB[0]= machineB[0];
		
		for(int i =1; i<tasks; i++) {
			dpA[i] = Math.min(dpA[i-1], dpB[i-1]+costToSwitch) + machineA[i];
			dpB[i] = Math.min(dpB[i-1], dpA[i-1]+costToSwitch) + machineB[i];
		}
		return Math.min(dpA[tasks-1], dpB[tasks-1]);
	}

	private static int minCostToPaint(int N, int[] red, int[] green, int[] blue) {
		int[] dpR = new int[N];
		int[] dpG = new int[N];
		int[] dpB = new int[N];
		dpR[0]= red[0];
		dpG[0]= green[0];
		dpB[0] = blue[0];
		for(int i = 1;i <N;i++) {
			dpR[i] = Math.min(dpB[i-1],dpG[i-1]) + red[i];
			dpG[i] = Math.min(dpR[i-1],dpB[i-1]) + green[i];
			dpB[i] = Math.min(dpR[i-1],dpG[i-1]) + blue[i];
		}
		return Math.min(Math.min(dpR[N-1], dpB[N-1]),dpG[N-1]);
	}

	private static int minCostSum(int N, int[] costAr) {
		diceOptimized[1] = 7;
		diceOptimized[2] = 4;
		diceOptimized[3] = 2;
		diceOptimized[4] = 4;
		diceOptimized[5] = 3;
		diceOptimized[6] = 11;
		// 7 - 6
		// 8 - 5 => 5+3
		// 9 - 7
		// 10 - 6
		// 11 - 7 => 8+3
		// 12 - 9
		// 13 - 8
		// 14 => 5+3+3 + 3 => 3+2+2+2=9
		for (int i = 7; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= 6; j++) {
				min = Math.min(min, diceOptimized[(i - j) % 7] + costAr[j]);
			}
			diceOptimized[i % 7] = min;
		}
		return (int) (diceOptimized[N % 7]);
	}

	private static int diceSum(int N) {
		diceOptimized[1] = 1;
		diceOptimized[2] = 1;
		diceOptimized[3] = 1;
		diceOptimized[4] = 1;
		diceOptimized[5] = 1;
		diceOptimized[6] = 1;
		for (int i = 7; i <= N; i++) {
			diceOptimized[i % 7] = diceOptimized[(i - 1) % 7] + diceOptimized[(i - 2) % 7] + diceOptimized[(i - 3) % 7]
					+ diceOptimized[(i - 4) % 7] + diceOptimized[(i - 5) % 7] + diceOptimized[(i - 6) % 7];
		}
		return (int) (diceOptimized[N % 7]);
	}

	private static int tilesColoredOptimized(int N) {
		tilesOptimized[1] = 1;
		tilesOptimized[2] = 1;
		tilesOptimized[3] = 1;
		tilesOptimized[4] = 1;
		tilesOptimized[5] = 2;
		for (int i = 6; i <= N; i++) {
			tilesOptimized[i % 6] = tilesOptimized[(i - 1) % 6] + tilesOptimized[(i - 5) % 6];
		}
		return (int) (tilesOptimized[N % 6] * Math.pow(2, N));
	}

	private static int tilesOptimized(int N) {
		tilesOptimized[1] = 1;
		tilesOptimized[2] = 1;
		tilesOptimized[3] = 1;
		tilesOptimized[4] = 1;
		tilesOptimized[5] = 2;
		for (int i = 6; i <= N; i++) {
			tilesOptimized[i % 6] = tilesOptimized[(i - 1) % 6] + tilesOptimized[(i - 5) % 6];
		}
		return tilesOptimized[N % 6];
	}

	private static int fibStepsOptimized(int N) {
		dpFibOptimized[0] = 1;
		dpFibOptimized[1] = 1;
		for (int i = 2; i <= N; i++) {
			dpFibOptimized[i % 3] = dpFibOptimized[(i - 1) % 3] + dpFibOptimized[(i - 2) % 3];
		}
		return dpFibOptimized[N % 3];
	}

	private static int fibDPOptimized(int N) {
		dpFibOptimized[0] = 0;
		dpFibOptimized[1] = 1;
		for (int i = 2; i <= N; i++) {
			dpFibOptimized[i % 3] = dpFibOptimized[(i - 1) % 3] + dpFibOptimized[(i - 2) % 3];
		}
		return dpFibOptimized[N % 3];
	}

	private static int fibRecursiveDP(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		if (dpFib[N] == -1)
			dpFib[N] = fibRecursiveDP(N - 1) + fibRecursiveDP(N - 2);
		return dpFib[N];
	}

	private static int fibRecursive(int N) {
		if (N == 0)
			return 0;
		if (N == 1)
			return 1;
		return fibRecursive(N - 1) + fibRecursive(N - 2); // Overlapping sub problem
	}

	private static int fibIterative(int N) {
		int[] ar = new int[N + 1]; // dp states
		ar[0] = 0; // base conditions
		ar[1] = 1;
		for (int i = 2; i <= N; i++) {
			ar[i] = ar[i - 1] + ar[i - 2]; // dp expression
		}
		return ar[N]; // where is the answer?
	}
}
