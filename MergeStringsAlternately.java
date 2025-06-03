/*
 * Problem Statement:
 * You are given two strings word1 and word2. Merge the strings by adding
 * letters in alternating order, starting with word1. If a string is longer than
 * the other, append the additional letters onto the end of the merged string.
 * Return the merged string.
 * 
 * Example 1:
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * 
 * Example 2:
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * 
 * Example 3:
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * 
 * Constraints:
 * 1 <= word1.length, word2.length <= 100
 * word1 and word2 consist of lowercase English letters.
 */

public class MergeStringsAlternately {

	/*
	 * Vineeth's Brute-Force Solution Thought Process:
	 * One thing is clear, I have to iterate through both strings and merge them
	 * in an alternating fashion. Okay, there are only three cases possible:
	 * 1. Both strings are of equal length.
	 * 2. word1 is shorter than word2.
	 * 3. word2 is shorter than word1.
	 * 
	 * Alright, handling case 1 is straightforward. I can use a loop to iterate
	 * through both strings and append characters alternately.
	 * For handling cases 2 and 3, I can still use a loop but I need to check the
	 * length of the shorter string. So basically, I will iterate through
	 * the length of the longer string and append characters from both strings until
	 * the shorter string runs out of characters. After that, I can append the
	 * remaining characters from the longer string.
	 * 
	 * TC: O(max(len1, len2)) // For the loop
	 * SC: O(len1 + len2) // For the result string
	 * Here len1 and len2 are the lengths of word1 and word2 respectively.
	 */
	public String bruteForce(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		String ans = "";

		if (len1 == len2) {
			// Case 1: Both strings are of equal length
			for (int i = 0; i < len1; i++) {
				ans += word1.charAt(i);
				ans += word2.charAt(i);
			}
		} else if (len1 < len2) {
			// Case 2: word1 is shorter than word2
			for (int i = 0; i < len2; i++) {
				if (i < len1) {
					ans += word1.charAt(i);
				}
				ans += word2.charAt(i);
			}
		} else {
			// Case 3: word2 is shorter than word1
			for (int i = 0; i < len1; i++) {
				ans += word1.charAt(i);
				if (i < len2) {
					ans += word2.charAt(i);
				}
			}
		}
		return ans;
	}

	/*
	 * Vineeth's Better Solution Thought Process:
	 * Okay so the logic here is the same as before, but I can optimize it a bit
	 * by using StringBuilder instead of string concatenation. Why would I do that?
	 * Because string concatenation creates a new string every time, which is
	 * inefficient. When I use StringBuilder, I can append characters without
	 * creating new strings, which is more efficient. While returning the final
	 * result, I can simply call toString() on the StringBuilder.
	 * 
	 * TC: O(max(len1, len2)) // For the loop
	 * SC: O(len1 + len2) // For the StringBuilder
	 * Here len1 and len2 are the lengths of word1 and word2 respectively.
	 */
	public String better(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		StringBuilder ans = new StringBuilder();

		if (len1 == len2) {
			// Case 1: Both strings are of equal length
			for (int i = 0; i < len1; i++) {
				ans.append(word1.charAt(i));
				ans.append(word2.charAt(i));
			}
		} else if (len1 < len2) {
			// Case 2: word1 is shorter than word2
			for (int i = 0; i < len2; i++) {
				if (i < len1) {
					ans.append(word1.charAt(i));
				}
				ans.append(word2.charAt(i));
			}
		} else {
			// Case 3: word2 is shorter than word1
			for (int i = 0; i < len1; i++) {
				ans.append(word1.charAt(i));
				if (i < len2) {
					ans.append(word2.charAt(i));
				}
			}
		}
		return ans.toString();
	}

	/*
	 * Vineeth's Optimal Solution Thought Process:
	 * Here, I will allocate the capacity of the StringBuilder to the sum of the
	 * lengths of both strings. This way, I can avoid unnecessary resizing and
	 * improve performance. Next, I will find out the maximum length of the two
	 * strings and iterate through the characters, appending them to the
	 * StringBuilder.
	 * 
	 * TC: O(max(len1, len2)) // For the loop
	 * SC: O(len1 + len2) // For the StringBuilder
	 * Here len1 and len2 are the lengths of word1 and word2 respectively.
	 */
	public String optimal(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		StringBuilder ans = new StringBuilder(len1 + len2); // Allocate capacity

		int maxLength = Math.max(len1, len2);

		// All three cases can be handled in a single loop
		for (int i = 0; i < maxLength; i++) {
			if (i < len1) {
				ans.append(word1.charAt(i));
			}
			if (i < len2) {
				ans.append(word2.charAt(i));
			}
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		MergeStringsAlternately solution = new MergeStringsAlternately();

		// Test cases
		System.out.println(solution.bruteForce("abc", "pqr")); // Output: "apbqcr"
		System.out.println(solution.better("ab", "pqrs")); // Output: "apbqrs"
		System.out.println(solution.optimal("abcd", "pq")); // Output: "apbqcd"
	}
}