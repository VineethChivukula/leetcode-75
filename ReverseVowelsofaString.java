/*
 * Problem Statement:
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower
 * and upper cases, more than once.
 * 
 * Example 1:
 * Input: s = "hello"
 * Output: "holle"
 * 
 * Example 2:
 * Input: s = "leetcode"
 * Output: "leotcede"
 * 
 * Example 3:
 * Input: s = "IceCreAm"
 * Output: "AceCreIm"
 * 
 * Constraints:
 * 1 <= s.length <= 3 * 10^5
 * s consist of printable ASCII characters.
 */

public class ReverseVowelsofaString {
	/*
	 * Vineeth's Brute-Force Solution Thought Process:
	 * The first time I read the problem, I immediately thought about using two
	 * pointers. The idea is very simple:
	 * 1. Start one pointer at the beginning of the string and another at the end.
	 * 2. Move the left pointer forward until it points to a vowel.
	 * 3. Move the right pointer backward until it points to a vowel.
	 * 4. Swap the vowels at these two pointers.
	 * 5. Repeat steps 2-4 until the left pointer is greater than or equal to the
	 * right pointer.
	 * In Java, Strings are immutable, so I will use a StringBuilder to build the
	 * result string.
	 * TC: O(n) - where n is the length of the string, since we are iterating
	 * through
	 * the string once.
	 * SC: O(1) - Since we are not using any extra space except for the
	 * StringBuilder.
	 */
	public String bruteForce(String s) {
		StringBuilder sb = new StringBuilder(s);
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			if (isVowelBrute(sb.charAt(left))) {
				if (isVowelBrute(sb.charAt(right))) {
					// Swap the vowels
					swap(sb, left, right);
					left++;
					right--;
				} else {
					right--; // Move right pointer backward until a vowel is found
				}
			} else {
				left++; // Move left pointer forward until a vowel is found
			}
		}

		return sb.toString();
	}

	private boolean isVowelBrute(char c) {
		char[] vowels = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
		for (char vowel : vowels) {
			if (c == vowel) {
				return true;
			}
		}
		return false;
	}

	private void swap(StringBuilder sb, int left, int right) {
		char temp = sb.charAt(left);
		sb.setCharAt(left, sb.charAt(right));
		sb.setCharAt(right, temp);
	}

	/*
	 * Better Solution Thought Process:
	 * The solution above is efficient, but I can optimize it further by modifying
	 * the isVowelBrute method. We will use indexOf to check if the character
	 * is a vowel. But, with what string should I check? "aeiouAEIOU". We know that
	 * indexOf returns -1 if the character is not found in the string. So, if the
	 * character is a vowel, it will return the index of that vowel, which is
	 * always greater than or equal to 0. If the character is not a vowel, it will
	 * return -1.
	 * TC: O(n) - where n is the length of the string, since we are iterating
	 * through the string once.
	 * SC: O(1) - Since we are not using any extra space except for the
	 * StringBuilder.
	 */

	public String better(String s) {
		StringBuilder sb = new StringBuilder(s);
		int left = 0;
		int right = s.length() - 1;

		while (left < right) {
			if (isVowelBetter(sb.charAt(left))) {
				if (isVowelBetter(sb.charAt(right))) {
					// Swap the vowels
					swap(sb, left, right);
					left++;
					right--;
				} else {
					right--; // Move right pointer backward until a vowel is found
				}
			} else {
				left++; // Move left pointer forward until a vowel is found
			}
		}

		return sb.toString();
	}

	private boolean isVowelBetter(char c) {
		return "aeiouAEIOU".indexOf(c) >= 0; // Check if the character is a vowel
	}

	/*
	 * Optimal Solution Thought Process:
	 * Well, it's actually the same as the better solution, but instead of creating
	 * a string with all the vowels, simple conditions can be used to check if
	 * the character is a vowel. This is because the number of vowels is very small,
	 * and using conditions is more efficient than creating a string
	 * and checking if the character is in that string. Also, we won't need to
	 * create a StringBuilder, instead we can use a char array to store the
	 * characters of the string. Also, we need not create a separate
	 * swap method, we can simply swap the characters in the char array.
	 * TC: O(n) - where n is the length of the string, since we are iterating
	 * through the string once.
	 * SC: O(1) - Since we are not using any extra space except for the
	 * char array.
	 */
	public String optimal(String s) {
		char[] chars = s.toCharArray();
		int left = 0;
		int right = chars.length - 1;

		while (left < right) {
			if (isVowelOptimal(chars[left])) {
				if (isVowelOptimal(chars[right])) {
					// Swap the vowels
					char temp = chars[left];
					chars[left] = chars[right];
					chars[right] = temp;
					left++;
					right--;
				} else {
					right--; // Move right pointer backward until a vowel is found
				}
			} else {
				left++; // Move left pointer forward until a vowel is found
			}
		}

		return new String(chars);
	}

	private boolean isVowelOptimal(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
				c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
	}
}