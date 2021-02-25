import java.util.Arrays;
import java.util.HashMap;

public class Class5 {

	static int count = 0;
	
	public static void main(String[] args) {
		// i<j && ar[i]>ar[j] find number of elements that fall into this above category
		int[] ar = {8,5,13,7,-2,11,1,-8,2,12};
		mergeSort(ar,0,ar.length-1);
		System.out.println(count);

		// Check if sum of any two elements is equal to k :  ar[i] + ar[j] ==k
		int[] ar1 = {8,5,23,7,-2,11,22};
		int k = 16;
		System.out.println(checkSumK(ar1,k));
		
		// Sort an array with 0's and 1's
		int ar2[] = { 0,0,1,1,0,1,0,0,1,1,0,1,1,1,0};
		sortArr(ar2);
		
		// Check if sum of any 3 elements is equal to k :  ar[i] + ar[j] + ar[k] == k
		System.out.println(check3SumK(ar1,k));
		
		System.out.println(linearSearch(ar1,k));
		System.out.println(binarySearch(ar1,k));
		System.out.println(binarySearchRecur(ar1,k,0,ar1.length-1));
		
		System.out.println(checkSumKBinarySearch(ar1,k));
		
		
		int ar3[] = {8,-1,-9,7,2,12,2};
		int q[] = {6,-4,2,14,-12};
		// max(ar[i]<=q) & min(ar[i]>=q)
		findFloorAndCeil(ar3,q);
		
		// find the count of every element of query array in ar4 
		int ar4[] = {10,4,4,4,5,4,2,6,-1,-1,2,2,2,-1,2,2,4,2,5,10};
		int query[] = {2,6,14,8,10};
		findCount(ar4,query);
	}

	private static void findFloorAndCeil(int[] ar3, int[] q) {
		Arrays.sort(ar3);
		for(int i = 0; i<q.length; i++) {
			findBFloor(ar3,q[i]);
			findBCeil(ar3,q[i]);
		}
		
	}

	private static void findCount(int[] ar, int[] query) {
		Arrays.sort(ar);
		for(int i = 0; i<query.length; i++) {
			int floor = findBFloorIdx(ar,query[i]);
			int ceil = findBCeilIdx(ar,query[i]);
			if(floor == -1 || ceil == -1) {
				System.out.println(query[i] + " is not found");
			} else
				System.out.println(query[i] + " count is " + (ceil-floor));		
		}	
	}
	
	private static int findBCeilIdx(int[] ar, int q) {
		Arrays.sort(ar);
		int low = 0;
		int hi = ar.length-1;
		int ansInd = -1;
		while(low <= hi) {
			int mid = (low+hi)/2;
			if(ar[mid]==q) {
				ansInd = mid;
				hi = mid-1;
			} else if(ar[mid] > q) {
				hi = mid-1;
			} else {
				low = mid+1;
			}
		}
		return ansInd;
	}

	// max(ar[i]<=q)
	private static int findBFloorIdx(int[] ar, int q) {
		Arrays.sort(ar);
		int low = 0;
		int hi = ar.length-1;
		int ansInd = -1;
		while(low <= hi) {
			int mid = (low+hi)/2;
			if(ar[mid]==q) {
				ansInd = mid;
				low = mid+1;
			} else if(ar[mid] < q) {
				low = mid+1;
			} else {
				hi = mid-1;
			}
		}
		return ansInd;
	}

	private static void findBCeil(int[] ar, int q) {
		Arrays.sort(ar);
		int low = 0;
		int hi = ar.length-1;
		int ans = Integer.MAX_VALUE;
		while(low <= hi) {
			int mid = (low+hi)/2;
			if(ar[mid] >= q) {
				ans = Math.min(ans, ar[mid]);
				hi = mid-1;
			} else {
				low = mid+1;
			}
		}
		System.out.println("Bceil " +ans);
		
	}

	// max(ar[i]<=q)
	private static void findBFloor(int[] ar, int q) {
		Arrays.sort(ar);
		int low = 0;
		int hi = ar.length-1;
		int ans = Integer.MIN_VALUE;
		while(low <= hi) {
			int mid = (low+hi)/2;
			if(ar[mid] <= q) {
				ans = Math.max(ans,ar[mid]);
				low = mid+1;
			} else {
				hi = mid-1;
			}
		}
		System.out.println("Bfloor " +ans);
	}

	private static boolean checkSumKBinarySearch(int[] ar, int k) {
		for(int i=0; i< ar.length; i++) {
			if(binarySearchRecur(ar, k, i+1, ar.length-1)) return true;
		}
		return false;
	}

	private static boolean binarySearch(int[] ar, int k) {
		int low = 0;
		int hi = ar.length-1;
		while(low<=hi) {
			int mid = (low+hi)/2;
			if(ar[mid] == k) return true;
			else if (ar[mid] < k ) low = mid+1;
			else hi = mid-1;
		}
		return false;
	}

	private static boolean binarySearchRecur(int[] ar, int k, int low , int hi) {
		if(low>=hi) return false;
		int mid = (low+hi)/2;
		if(ar[mid] == k) return true;
		else if (ar[mid] < k) return binarySearchRecur(ar,k,mid+1,hi);
		else return binarySearchRecur(ar,k,low,mid-1);
	}

	private static boolean linearSearch(int[] ar1, int k) {
		for(int i = 0; i< ar1.length; i++) {
			if(ar1[i] == k) return true;
		}
		return false;
	}

	private static boolean check3SumK(int[] ar, int k) {
		for(int i = 0; i<ar.length; i++) {
			int sumFind = k - ar[i];
			if(checkSumKBinarySearchFor3Sum(ar,sumFind,i)) return true;
			
		}
		return false;
	}

	private static boolean checkSumKBinarySearchFor3Sum(int[] ar, int k, int idx) {
		for(int i=idx; i< ar.length; i++) {
			if(binarySearchRecur(ar, k, i+1, ar.length-1)) return true;
		}
		return false;
	}

	
	private static void sortArr(int[] ar) {
		int p1 =0 ;
		int p2 = ar.length-1;
		while(p1<=p2) {
			if(ar[p1] == 0) p1++;
			else if( ar[p2] == 1) p2--;
			else if(ar[p1] > ar[p2]) {
				Class4.swap(ar,p1,p2);
			};
		}
		Class4.printArray(ar);
	}

	private static boolean checkSumK(int[] ar, int k) {
		Arrays.sort(ar);
		int p1 =0 ;
		int p2 = ar.length-1;
		while(p1<=p2) {
			if(ar[p1] + ar[p2] == k) return true;
			else if( ar[p1] + ar[p2] < k) p1++;
			else p2--;
		}
		return false;
	}

	private static void mergeSort(int[] ar, int low, int hi) {
		if(low == hi ) return;
		int mid = (low+hi)/2;
		mergeSort(ar,low,mid);
		mergeSort(ar,mid+1,hi);
		merge(ar,low,hi,mid);
	}

	private static void merge(int[] ar, int low, int hi, int mid) {
		int[] temp = new int[hi-low+1];
		int i = low;
		int j = mid+1;
		int k=0;
		while(i<= mid && j<=hi) {
			if(ar[i] > ar[j]) {
				count = count + mid -i +1;
				temp[k++] = ar[j++];				
			} else {
				temp[k++] = ar[i++];
			}
		}
		while(i<=mid) {
			temp[k++] = ar[i++];
		}
		while(j<=hi) {
			temp[k++] = ar[j++];
		}
		k = 0;
		for(i = low; i<=hi; i++) {
			ar[i]= temp[k++];
		}
	}

}
