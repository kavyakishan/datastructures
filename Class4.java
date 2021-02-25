
public class Class4 {

	public static void main(String[] args) {
		int ar[] = { 2, -1, -9, 8, 5, 4, 10, 5, 6, 16, 9 };
		
		bubbleSort(ar);
		System.out.println("");

		selectionSort(ar);
		System.out.println("");

		insertionSort(ar);
		System.out.println("");

		int ar1[] = { 2, 1, 1, 3, 3, 5, 3, 3, 5, 2, 5, 3, 7, 10 };
		int range = 10;
		System.out.println(maxVotes(ar1, range));

		countSort(ar1, range);
		System.out.println("");

		int m = -10;
		int M = 10;
		int[] ar2 = { -9, -9, 1, 1, 4, 2, 3, 4, 5, 1, 10 };
		countSortRange(ar2, m, M);
		System.out.println("");

		int[] A = { 3, 5, 10, 12, 21, 37, 40 };
		int[] B = { -9, -2, 6, 15 };
		printSortedAr(A, B);
		System.out.println("");

		int low = 0;
		int hi = ar.length - 1;
		mergeSort(ar, low, hi);
		printArray(ar);
		System.out.println("");

	}

	static void printArray(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i] + " ");
		}

	}

	private static void mergeSort(int[] ar, int low, int hi) {
		if(low == hi)  return;
		int mid = (low+hi)/2;
		mergeSort(ar,low,mid);
		mergeSort(ar,mid+1,hi);
		merge(ar,low,mid,hi);

	}

	private static void merge(int[] ar, int low, int mid, int hi) {
		int[] temp = new int[hi-low+1];
		int i = low;
		int j = mid+1;
		int k=0;
		while(i<=mid && j<=hi) {
			if(ar[i] <= ar[j])
				temp[k++] = ar[i++];
			else
				temp[k++] = ar[j++];
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

	private static void printSortedAr(int[] a, int[] b) {
		int p1 =0; 
		int p2 =0;
		while(p1< a.length && p2 < b.length) {
			if(a[p1] <= b[p2])
				System.out.print(a[p1++] + " ");
			else 
				System.out.print(b[p2++] + " ");
		}
		while(p1<a.length) {
			System.out.print(a[p1++] + " ");
		}
		while(p2<b.length) {
			System.out.print(b[p2++] + " ");
		}
			
	}

	private static void countSortRange(int[] ar, int m, int M) {
		int range = M-m+1;
		int[] countAr = new int[range];
		for(int i = 0 ; i< ar.length; i++) {
			countAr[ar[i]-m]++;
		}
		for(int i = 1; i <countAr.length; i++) {
			for(int j = 0;j < countAr[i]; j++) {
				System.out.print(i+m + " ");	
			}
		}

	}

	private static void countSort(int[] ar, int range) {
		int[] countAr = new int[range+1];
		for(int i = 0 ; i< ar.length; i++) {
			countAr[ar[i]]++;
		}
		for(int i = 1; i <countAr.length; i++) {
			for(int j = 0;j < countAr[i]; j++) {
				System.out.print(i + " ");	
			}
		}
	}

	private static int maxVotes(int[] ar, int range) {
		int[] countAr = new int[range+1];
		for(int i = 0 ; i< ar.length; i++) {
			countAr[ar[i]]++;
		}
		int maxVotes = -1;
		int maxCandidate = 0;
		for(int i =1 ; i<=range; i++) {
			if(countAr[i] > maxVotes) {
				maxVotes = countAr[i];
				maxCandidate = i;
			}
		}
		return maxCandidate;
	}

	private static void insertionSort(int[] ar) {
		for (int i = 1; i < ar.length; i++) {
			int num = ar[i];
			int j = i - 1;
			while (j >= 0 && num < ar[j]) {
				ar[j + 1] = ar[j];
				j--;
			}
			ar[j + 1] = num;
		}
		printArray(ar);
	}

	private static void selectionSort(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < ar.length; j++) {
				if (ar[j] <= ar[minIndex]) {
					minIndex = j;
				}
			}
			swap(ar, i, minIndex);
		}
		printArray(ar);
	}

	private static void bubbleSort(int[] ar) {
		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar.length - 1; j++) {
				if (ar[j] > ar[j + 1]) {
					swap(ar, j, j + 1);
				}
			}
		}
		printArray(ar);

	}

	static void swap(int[] ar, int j, int i) {
		int temp = ar[j];
		ar[j] = ar[i];
		ar[i] = temp;
		return;
	}

}
