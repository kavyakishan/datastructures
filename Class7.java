import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Class7 {
	public static void main(String[] args) {
		// a+b == k using hashmap O(N+N*1)
		int[] ar = { 7, 8, 8, 15, -1, -8, 19, 32 };
		int k = 16;
		findIfSumExists(ar, k);
		// With Set O(N)
		findIfSumExistsWithSet(ar, k);

		/*--------------------------------*/
		// Find Maximum subarray(contiguous) sum
		// Bruteforce O(N^3)
		// Carry forward technique O(N2)
		int[] ar1 = { 8, 1, 4, 14, -9, -8, 6, 17, -12, -9, 10, 15 };
		findMaxSubArraySum(ar1);

		/*--------------------------------*/
		// Find length of largest subarray whose elements can be rearranged to get a
		// contiguous order
		int[] ar2 = { 11, 9, 10, 5, 3, 4, 1, 2, 6, 21, 24, 23, 7, 8 };
		// no duplicates in ar, no duplicates in subarray O(N2)
		findLengthOfLargestContiguousSubarray(ar2);
		// duplicates in ar, no duplicates in subarray O(N2)
		int[] ar3 = { 11, 9, 10, 5, 3, 4, 1, 2, 6, 6, 21, 24, 24, 23, 7, 8 };
		findLengthOfLargestContiguousSubarray2(ar3);
		// duplicates in ar, duplicates in subarray O(N2)
		int[] ar4 = { 11, 9, 10, 5, 3, 4, 1, 2, 6, 6, 21, 24, 24, 23, 7, 8 };
		findLengthOfLargestContiguousSubarray3(ar4);

	}

	private static void findLengthOfLargestContiguousSubarray3(int[] ar) {
		int maxLen = -1;
		for(int i = 0 ; i<ar.length ; i++) {
			Set<Integer> hs = new HashSet<Integer>();
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for(int j = i; j<ar.length; j++) {
				hs.add(ar[j]);
				int size = hs.size();
				max = Math.max(max, ar[j]);
				min = Math.min(min,ar[j]);
				if(size == max-min+1) {
					maxLen = Math.max(maxLen, j-i+1);
				}
			}
		}
		System.out.println("findLengthOfLargestContiguousSubarray3 "+ maxLen);
	}

	private static void findLengthOfLargestContiguousSubarray2(int[] ar) {
		int maxLen = -1;
		for(int i = 0 ; i<ar.length ; i++) {
			Set<Integer> hs = new HashSet<Integer>();
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for(int j = i; j<ar.length; j++) {
				if(hs.contains(ar[j])) break;
				else hs.add(ar[j]);
				int size = j-i+1;
				max = Math.max(max, ar[j]);
				min = Math.min(min,ar[j]);
				if(size == max-min+1) {
					if(maxLen<size) {
						maxLen = size;
					}
				}
			}
		}
		System.out.println("findLengthOfLargestContiguousSubarray2 "+ maxLen);
	}

	private static void findLengthOfLargestContiguousSubarray(int[] ar) {
		int maxLen = -1;
		for(int i = 0 ; i<ar.length ; i++) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for(int j = i; j<ar.length; j++) {
				max = Math.max(max,ar[j]);
				min = Math.min(min,ar[j]);
				int size = j-i+1;
				if(max-min+1 == size) {
					maxLen = Math.max(maxLen, size);
				}
			}
		}
		System.out.println("findLengthOfLargestContiguousSubarray "+ maxLen);
	}

	private static void findMaxSubArraySum(int[] ar) {
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < ar.length; i++) {
			int sum = 0;
			for (int j = i; j < ar.length; j++) {
				sum = sum + ar[j];
				maxSum = Math.max(maxSum, sum);
			}
		}
		System.out.println(maxSum);
	}

	private static void findIfSumExistsWithSet(int[] ar, int k) {
		Set<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < ar.length; i++) {
			if (hs.contains(k - ar[i])) {
				System.out.println("Sum with " + k + " exists");
				return;
			}
			hs.add(ar[i]);
		}

	}

	private static void findIfSumExists(int[] ar, int k) {
		Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < ar.length; i++) {
			if (!hm.containsKey(ar[i]))
				hm.put(ar[i], 1);
			else
				hm.put(ar[i], (hm.get(ar[i])) + 1);
		}
		for (int i = 0; i < ar.length; i++) {
			if (hm.containsKey(k - ar[i])) {
				if (hm.get(k - ar[i]) >= 2) {
					System.out.println("Sum with " + k + " exists");
					return;
				}
			}
		}

	}
}
