/*
 * Problem Statement:
 * For two strings s and t, we say "t divides s" if and only if s = t + t + t +
 * ... + t + t (i.e., t is concatenated with itself one or more times). Given
 * two strings str1 and str2, return the largest string x such that x
 * divides both str1 and str2.
 * 
 * Example 1:
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * 
 * Example 2:
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * 
 * Example 3:
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 * 
 * Constraints:
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of uppercase English letters.
 */

public class GreatestCommonDivisorofStrings {
	/*
	 * Vineeth's Brute-Force Solution Thought Process:
	 * If I observe the problem definition, it seems that the gcd will be repeated
	 * in both strings. Okay, so the gcd's length should be less than or equal to
	 * the minimum length of str1 and str2 and that's for sure. I can do one thing,
	 * I will consider initial gcd as the shorter string and then check if it
	 * divides both strings. Next, I will keep on reducing the length of the
	 * gcd by one character at a time and check again. If I find a gcd that divides
	 * both strings, I will return it. If I reach a point where the gcd length is 0,
	 * then I will return an empty string. Okay, the approach sounds good but how
	 * do I check if a string divides another string? I will simply compare the
	 * character at each index of the string with the corresponding character in the
	 * gcd string. So, at any point, if the characters mismatch, I will conclude
	 * that the gcd does not divide the string. Also, while comparing if I reach
	 * the end of the string and all characters matched, then I will conclude that
	 * the gcd divides the string. There is one more thing to consider, if the
	 * string that is being checked has been completely traversed and the gcd string
	 * has not been completely traversed, then I will conclude that the current gcd
	 * does not divide the string.
	 * TC: O(min(len1, len2) * (len1 + len2)) // For the loop and checking
	 * characters
	 * SC: O(1) // For the gcd string
	 */
	public String bruteForce(String str1, String str2) {
		String gcd = str1.length() <= str2.length() ? str1 : str2;

		while (gcd.length() > 0) {
			// Check if gcd divides both str1 and str2
			if (isDivisible(str1, gcd) && isDivisible(str2, gcd)) {
				return gcd;
			}

			// Reduce the length of gcd by 1
			gcd = gcd.substring(0, gcd.length() - 1); // Reduce the length of gcd by 1
		}

		return ""; // If no gcd found, return empty string
	}

	private boolean isDivisible(String str, String gcd) {
		int gcdIndex = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != gcd.charAt(gcdIndex)) {
				return false; // Mismatch found, gcd does not divide str
			}
			gcdIndex++;
			if (gcdIndex == gcd.length()) {
				gcdIndex = 0; // Reset gcd index to start over
			}
		}

		return gcdIndex == 0; // If we reached the end of str and gcdIndex is 0, gcd divides str
	}

	/*
	 * Vineeth's Better Solution Thought Process:
	 * Okay so instead of checking character by character, I will check substring by
	 * subsstring. Before that we need to check if the string length is divisible by
	 * the gcd length. Why? Because the string length will be a multiple of the gcd
	 * length according to the problem statement.
	 * TC: O(min(len1, len2) * (len1 + len2)) // For the loop and checking
	 * substrings
	 * SC: O(1) // For the gcd string
	 */
	public String better(String str1, String str2) {
		String gcd = str1.length() <= str2.length() ? str1 : str2;

		while (gcd.length() > 0) {
			// Check if gcd divides both str1 and str2
			if (isDivisibleBetter(str1, gcd) && isDivisibleBetter(str2, gcd)) {
				return gcd;
			}

			// Reduce the length of gcd by 1
			gcd = gcd.substring(0, gcd.length() - 1); // Reduce the length of gcd by 1
		}

		return ""; // If no gcd found, return empty string
	}

	private boolean isDivisibleBetter(String str, String gcd) {
		int gcdLength = gcd.length();

		if (str.length() % gcdLength != 0) {
			return false; // If str length is not divisible by gcd length, gcd cannot divide str
		}

		// Check if str can be formed by repeating gcd
		for (int i = 0; i < str.length(); i += gcdLength) {
			if (!str.substring(i, i + gcdLength).equals(gcd)) {
				return false; // Mismatch found, gcd does not divide str
			}
		}

		return true; // If we reached here, gcd divides str
	}

	/*
	 * Optimal Solution Thought Process:
	 * I have thought for a while but I couldn't come up with a better solution
	 * than the above one. So, I have looked at the solution and I will be
	 * explaining it here. There are few important observations:
	 * If there exists a string x that divides both str1 and str2, then x must
	 * also divide str1 + str2 and str2 + str1. I'll explain this with few examples:
	 * Example 1:
	 * str1 = "ABCABC", str2 = "ABC"
	 * str1 + str2 = "ABCABCABC", str2 + str1 = "ABCABCABC"
	 * So, "ABC" divides both strings as well as the concatenated strings.
	 * 
	 * Example 2:
	 * str1 = "ABABAB", str2 = "ABAB"
	 * str1 + str2 = "ABABABABAB", str2 + str1 = "ABABABABAB"
	 * So, "AB" divides both strings as well as the concatenated strings.
	 * 
	 * Here, you can see that when combining the strings, the resulting strings are
	 * the same. So, this will be our initial check. You might be wondering what
	 * happens when combining two strings does not yield the same result. It is
	 * simple, it means that there is no common divisor.
	 * Example 1:
	 * str1 = "LEET", str2 = "CODE"
	 * str1 + str2 = "LEETCODE", str2 + str1 = "CODELEET"
	 * So, there is no common divisor string that divides both strings.
	 * 
	 * Here, we can see that if str1 + str2 is not equal to str2 + str1, then there
	 * is no common divisor string that divides both strings.
	 * 
	 * Okay, lets say that str1 + str2 is equal to str2 + str1. So, gcd exists. Now,
	 * how do we find it?
	 * Alright, a simple question: what is the gcd of 6 and 3?
	 * its 3, right? because 6 -> 3 + 3
	 * What is the gcd of 6 and 4?
	 * its 2, right? because 6 -> 2 + 2 + 2, 4 -> 2 + 2
	 * 
	 * Now, think in terms of strings.
	 * For example, if str1 = "ABABAB" and str2 = "ABAB", len1 = 6, len2 = 4.
	 * The gcd of 6 and 4 is 2, which means the gcd string will be of length 2.
	 * So, we can find the gcd string by taking the substring of length gcd(len1,
	 * len2) from either str1 or str2. Let's say we take it from str1, so the gcd
	 * string will be "AB".
	 * TC: O(len1 + len2) // For the concatenation and substring operations
	 * SC: O(1) // No extra space used for gcd string
	 */
	public String optimal(String str1, String str2) {
		if ((str1 + str2).equals(str2 + str1)) {
			int len1 = str1.length();
			int len2 = str2.length();
			int gcdLength = gcd(len1, len2);
			return str1.substring(0, gcdLength); // Take substring of length gcd(len1, len2) from str1
		}
		return ""; // No common divisor string exists
	}

	private int gcd(int a, int b) {
		while (b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a; // Return the gcd of a and b
	}

	public static void main(String[] args) {
		GreatestCommonDivisorofStrings gcd = new GreatestCommonDivisorofStrings();

		// Test cases
		System.out.println(gcd.bruteForce("ABCABC", "ABC")); // Output: "ABC"
		System.out.println(gcd.better("ABABAB", "ABAB")); // Output: "AB"
		System.out.println(gcd.optimal("LEET", "CODE")); // Output: ""
	}
}