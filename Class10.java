import java.util.Arrays;

public class Class10 {
	public static void main(String[] args) {
		// Search an element in the rotated array(rotate a sorted array)
		int[] ar = { 21, 34, 36, 42, -9, -4, -1, 8, 15 };
		/*
		 * 1. O(N) iterate thru the ar 2. O(N + logN) find the point of rotation and
		 * then binary search 3. O(logN + logN ) find the point of rotation by BS and
		 * then BS for element 4. O(logN) single binary search
		 */

		System.out.println(findElementInAr(ar, 21));

		// Find the first missing number
		int[] ar1 = { 2, 3, 4, 10, 21, 37 };
		// 1. Iterate
		// 2. Binary Search
		System.out.println(findFirstMissingNum(ar1));
		int[] ar2 = { 2, 3, 4, 5, 10, 21, 37 };
		System.out.println(findFirstMissingNumRelative(ar2));

		// Find the first missing positive Integer
		int[] ar3 = { -9, -4, -1, 1, 2, 3, 4, 5, 10, 21, 37 };
		System.out.println(findFirstMissingPositiveInt(ar3));
		// ( one more solution => put everything into set and find the number is present
		// in set or not [1,N+1])

		// Given an array of 0's and 1's FIND THE LENGTH OF longest subarray containing
		// equal number of 0's and 1's
		int[] ar4 = { 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1 };
		System.out.println(findLengthOfLongestSubarray(ar4));
		// Done recently.. find prefix sum array.. and use 2 hashmaps or with a single
		// hashmap

		int[] A = { 8, -1, -15, 21, 15, 32, 17 };
		int[] B = { 16, -8, 10, 27, 5, 1, 4 };
		int[] C = { 12, 4, -14, 24, 18, -2, -6 };
		// o (3nlongn +3n);
		System.out.println("Min Diff " + getMinDiffOfMaxAndMin(A, B, C));

		// 3 types of matrix find an element problems. (step search or stair case
		// search)

		// water problem
		// Right max, leftmax and iterated find (min of rm,lm) - a[i]

	}

	private static int getMinDiffOfMaxAndMin(int[] a, int[] b, int[] c) {
		int minDiff = Integer.MAX_VALUE;
		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);
		int p1 = 0, p2 = 0, p3 = 0;
		while (p1 < a.length && p2 < b.length && p3 < c.length) {
			minDiff = Math.min(minDiff,
					Math.max(Math.max(a[p1], b[p2]), c[p3]) - Math.min(Math.min(a[p1], b[p2]), c[p3]));
			if (Math.min(Math.min(a[p1], b[p2]), c[p3]) == a[p1])
				p1++;
			else if (Math.min(Math.min(a[p1], b[p2]), c[p3]) == b[p2])
				p2++;
			else
				p3++;
		}
		return minDiff;
	}

	private static int findLengthOfLongestSubarray(int[] ar) {
		int maxLength = Integer.MIN_VALUE;

		return maxLength;
	}

	// 0 , 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
	// -9, -4, -1, 1, 2, 3, 4, 5, 10, 21, 37
	private static int findFirstMissingPositiveInt(int[] ar) {
		int low = 0;
		int high = ar.length - 1;
		int missingNum = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (ar[mid] > 0 && ((mid - ar[mid] + 1 >= 0 && mid - ar[mid] + 1 < ar.length && mid - ar[mid] + 1 != 1)
					|| mid - ar[mid] + 1 < 0)) {
				missingNum = mid;
				high = mid - 1;
			} else
				low = mid + 1;
		}
		return ar[missingNum - 1] + 1;
	}

	private static int findFirstMissingNumRelative(int[] ar) {
		int low = 0;
		int high = ar.length;
		int missingNum = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (ar[mid] > ar[0] + mid + 1) {
				missingNum = mid;
				high = mid - 1;
			} else
				low = mid + 1;
		}
		return ar[missingNum - 1] + 1;
	}

	// 0, 1, 2, 3, 4, 5
	// 2, 3, 4, 10, 21, 37
	private static int findFirstMissingNum(int[] ar) {
		int low = 0;
		int high = ar.length;
		int missingNum = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (ar[mid] > mid + 1) {
				missingNum = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return missingNum == 0 ? 1 : ar[missingNum - 1] + 1;
	}

	private static int search(int arr[], int l, int h, int key) {
		if (l > h)
			return -1;

		int mid = (l + h) / 2;
		if (arr[mid] == key)
			return mid;

		/* If arr[l...mid] first subarray is sorted */
		if (arr[l] <= arr[mid]) {
			/*
			 * As this subarray is sorted, we can quickly check if key lies in half or other
			 * half
			 */
			if (key >= arr[l] && key <= arr[mid])
				return search(arr, l, mid - 1, key);
			/*
			 * If key not lies in first half subarray, Divide other half into two subarrays,
			 * such that we can quickly check if key lies in other half
			 */
			return search(arr, mid + 1, h, key);
		}

		/*
		 * If arr[l..mid] first subarray is not sorted, then arr[mid... h] must be
		 * sorted subarry
		 */
		if (key >= arr[mid] && key <= arr[h])
			return search(arr, mid + 1, h, key);

		return search(arr, l, mid - 1, key);
	}

	// 0, 1, 2, 3, 4, 5, 6, 7, 8
	// 21, 34, 36, 42, -9, -4, -1, 8, 15
	private static boolean findElementInAr(int[] ar, int k) {
		boolean found = false;
		int low = 0;
		int hi = ar.length - 1;
		int midPoint = (low + hi) / 2;

		while (low <= hi) {
			int mid = (low + hi) / 2;
			if (ar[mid] == k)
				return true;
			else {
				if (mid < midPoint) {
					if (ar[mid] > k && k >= ar[low])
						hi = mid - 1;
					else
						low = mid + 1;
				} else {
					if (ar[mid] < k && k < ar[hi]) {
						low = mid + 1;
					} else
						hi = mid - 1;
				}
			}
		}
		return found;
	}
}
