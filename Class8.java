
public class Class8 {

	public static void main(String[] args) {
		// check if str1 and str2 ar anagrams or not
		String str1 = "axyyb";
		String str2 = "xybay";
		// Sol1 : sort str1 str2 and iterate and check each char O(2logN+N)
		// Sol2 : Hash Map O(N+N+N, 2N)
		// Sol3 : count array O(N+N+26,26+26)
		// Sol4 : only one count array O(N+26,26)
		checkIfAnagrams(str1,str2);
		//---------------------------------
		// count how many anagramic groups are there..
		// N(MlogM) + NMlogN + NM
		// N(MlogM) + N*M , N*M
		//----------------------------------
		// Length of the largest palindrome in a string
		// Brute force : for every substring N2 check if it is palindrome with 2 pointer tech O(N2 * N ) 
		// O(N2) APPROACH
		// cabad
		String str = "ayyyaxbbxayayaxbyybxaxxxa";
		largestPalindromeLength(str);
		//----------------------------------------
		// find a smaller string in a containing all characters in b. Find the length.
		String a = "ayyabbbxaaayaxbbaxaxxb";
		String b  = "xxyab";
		
		//1. for every substring, check if it contains b  O(N2 * (N+N*M) , N)
		//2. using count array O(M+ N2(N+26), 26+26)
		//3. using count array plus carry forward  O(M+N2(1+26), 26+26)
		//4, p1 = 0, p2++ until valid.. once valid.. p1++ O(M+ (N+N)*26, 26+26)
		//5. Binary search
		
		//implementing sol 4
		findLengthOfSmallerSubstring(a,b);
		//--------------------------------------------
		// Rabin Karp algo
		//--------------------------------------------
		//Rolling Hash
		String strA = "axyybyaaxab";
		String strB = "byaax";
		System.out.println(findIfBIsSubstringOfA(strA, strB));

	}

	private static boolean findIfBIsSubstringOfA(String strA, String strB) {
		int p = 13;
		int LP = 13;
	    int TP = 13;
	    int hashB = 0;
	    int hashA = 0;
	    for(int i = 0; i< strB.length(); i++) {
	    	hashA = hashA + strA.charAt(i) * LP;
	    	hashB = hashB + strB.charAt(i) * LP;
	    	LP = LP*p ;
	    }
	    if(hashA == hashB ) return true;
	    for(int i = strB.length(); i<strA.length(); i++) {
	    	hashB = hashB * p;
	    	hashA = hashA - strA.charAt(i-strB.length())*TP + strA.charAt(i)*LP;
	    	if(hashA == hashB) return true;
	    	LP = LP*p;
	    	TP = TP*p;
	    }
		return false;
	}

	private static void findLengthOfSmallerSubstring(String a, String b) {
		int p1 = 0;
		int p2 = 1;
		int ans = Integer.MAX_VALUE;
		int[] countB = new int[26];
		
		for(int i = 0 ; i<b.length() ; i++) {
			countB[b.charAt(i)-97]++;
		}
		while (p2 < a.length()) {
			if(isValid(countB,a,p1,p2)) {
				ans = Math.min(ans, p2-p1+1);
				p1++;
			} else {
				p2++;
			}
		}
		System.out.println(ans);
	}

	static boolean isValid(int[] countB, String a, int p1, int p2) {
		int[] countA = new int[26];
		for(int i = p1; i<=p2; i++) {
			countA[a.charAt(i) - 97]++; 
		}
		for(int i = 0; i<=25; i++) {
			if(countA[i] < countB[i]) return false;
		}
		return true;
	}

	private static void largestPalindromeLength(String str) {
		int ans = Integer.MIN_VALUE;
		int ansP1 = 0;
		int ansP2 = 0;
		for(int i = 0; i< str.length(); i++) {
			int p1 = i;
			int p2 = i;
			while(p1>=0 &&  p2<str.length() && str.charAt(p1) == str.charAt(p2) ) {
				p1--;
				p2++;
			}
			if(ans< p2-p1+1) {
				ans = Math.max(ans, p2-p1-1);
				ansP1 = p1+1;
				ansP2 = p2;
			}
			p1 = i;
			p2 = i+1;
			while(p1>=0 &&  p2<str.length() && str.charAt(p1) == str.charAt(p2) ) {
				p1--;
				p2++;
			}
			if(ans< p2-p1+1) {
				ans = Math.max(ans, p2-p1-1);
				ansP1 = p1+1;
				ansP2 = p2;
			}
		}
		
		System.out.println("Largest palindrome length " + ans);
		System.out.println("Largest palindrome " + str.substring(ansP1,ansP2));
	}

	private static void checkIfAnagrams(String str1, String str2) {
		int[] countAr = new int[26];
		for(int i = 0; i< str1.length(); i++) {
			countAr[str1.charAt(i)-97]++;
			countAr[str2.charAt(i)-97]--;
		}
		for(int i = 0; i<26; i++) {
			if(countAr[i]>0) {
				System.out.println(" Not anagrams ");
				return;
			}
		}
		System.out.println(" Anagrams ");
	}

}
