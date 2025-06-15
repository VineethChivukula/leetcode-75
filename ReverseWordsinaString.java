/*
 * Problem Statement:
 * A word is defined as a sequence of non-space characters. The words in s will
 * be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between
 * two words. The returned string should only have a single space separating the
 * words. Do not include any extra spaces.
 * 
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * 
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * 
 * Example 3:
 * Input: s = "a good   example"
 * Output: "example good a"
 * 
 * Constraints:
 * 1 <= s.length <= 10^4
 * s contains English letters (upper-case and lower-case), digits, and spaces
 * ' '.
 * There is at least one word in s.
 */

public class ReverseWordsinaString {
	/*
	 * Vineeth's Brute-Force Solution Thought Process:
	 * Looking at the problem, we have to keep the following things in check:
	 * 1. We need to reverse the order of words in the string.
	 * 2. We need to handle leading, trailing, and multiple spaces between words.
	 * 3. We need to ensure that the output string has only a single space between
	 * the words.
	 * 
	 * Okay, the very first step is to split the string into words. But, there are
	 * spaces in between words. In Java, we can use the `split` method to split
	 * the string by spaces. Interestingly, if we use `split(" ")`, it will only
	 * split by single spaces. What if there are multiple spaces? Alright, keeping
	 * this aside, we can also have spaces at the beginning and end of the
	 * string. First, let's take care of the leading and trailing spaces. We can
	 * use the `trim` method to remove them. Now, we need to split the
	 * string by spaces of any number in between. To do such a thing, we can use
	 * `split("\\s+")`, which will split the string by one or more spaces. This
	 * "\\s+" is what is called a regular expression (regex) in Java. It matches one
	 * or more whitespace characters. Let me explain it a bit more:
	 * - normally \s matches any whitespace character (space, tab, newline, etc.)
	 * - the + means "one or more" of the preceding element, so \s+ matches one or
	 * more whitespace characters. But why a backslash at the beginning? In Java,
	 * the backslash is an escape character. Why are we escaping it? Dude, do you
	 * need to match a backslash in the string? No, we have to match the spaces.
	 * Okay, then why can't we use a simple "s+". Well, s+ would match the
	 * character 's' followed by one or more characters. Then how about " +"? Uhh,
	 * this would work as well my marvelous over thinker!. Anyway, since we split
	 * the string into words, next step is to reverse the order of the words.
	 * We can use a simple loop to iterate through the words array in reverse order
	 * and append them to a StringBuilder. Finally, we can return the StringBuilder
	 * as a string.
	 * TC: O(n) - where n is the length of the string, since we are iterating
	 * through the string once to split it and then again to reverse the order of
	 * the words.
	 * SC: O(n) - Since we are using a StringBuilder to build the result string.
	 */
	public String bruteForce(String s) {
		// Trim leading and trailing spaces
		s = s.trim();

		// Split the string by one or more spaces
		String[] words = s.split("\\s+");

		// Reverse the order of words
		StringBuilder sb = new StringBuilder();
		for (int i = words.length - 1; i >= 0; i--) {
			sb.append(words[i]);
			if (i != 0) {
				sb.append(" "); // Add a space between words
			}
		}

		return sb.toString();
	}

	/*
	 * Vineeth's Better Solution Thought Process:
	 * The solution above is efficient, but I can optimize it further by using
	 * String.join to join the words with a single space. This way, we can avoid
	 * using a StringBuilder and make the code cleaner.
	 * TC: O(n) - where n is the length of the string, since we are iterating
	 * through the string once to split it and then again to join the words.
	 * SC: O(n) - Since we are using a String to build the result string.
	 */
	public String better(String s) {
		// Trim leading and trailing spaces
		s = s.trim();

		// Split the string by one or more spaces
		String[] words = s.split("\\s+");

		// Join the words in reverse order with a single space
		String result = String.join(" ", reverseArray(words));

		return result;
	}

	private String[] reverseArray(String[] arr) {
		String[] reversed = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			reversed[i] = arr[arr.length - 1 - i];
		}
		return reversed;
	}

	/*
	 * Optimized Solution Thought Process:
	 * This approach is very interesting. We can reverse the entire string first,
	 * then reverse each word in the reversed string. This way, we can achieve the
	 * desired result without using any extra space for a StringBuilder or an array.
	 * For example, if we have the string "the sky is blue":
	 * We first reverse the entire string to get "eulb si yks eht".
	 * Then, we reverse each word in the reversed string to get "blue is sky the".
	 * Finally, we can clean up any extra spaces that may have been introduced
	 * during the reversal process.
	 * TC: O(n) - where n is the length of the string, since we are iterating
	 * through the string once to reverse it and then again to reverse each word.
	 * SC: O(1) - Since we are not using any extra space except for the
	 * StringBuilder.
	 */
	public String optimal(String s) {
		char[] chars = s.toCharArray();
		int n = chars.length;

		// Step 1: Reverse the entire string
		reverse(chars, 0, n - 1);

		// Step 2: Reverse each word back to correct order
		int start = 0;
		for (int i = 0; i <= n; i++) {
			if (i == n || chars[i] == ' ') {
				reverse(chars, start, i - 1);
				start = i + 1;
			}
		}

		// Step 3: Clean up extra spaces
		return cleanSpaces(chars);
	}

	private void reverse(char[] chars, int left, int right) {
		while (left < right) {
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;
		}
	}

	private String cleanSpaces(char[] chars) {
		int n = chars.length;
		int i = 0, j = 0;

		while (j < n) {
			// Skip leading spaces
			while (j < n && chars[j] == ' ')
				j++;

			// Copy non-space characters
			while (j < n && chars[j] != ' ') {
				chars[i++] = chars[j++];
			}

			// Skip trailing spaces and add single space
			while (j < n && chars[j] == ' ')
				j++;
			if (j < n)
				chars[i++] = ' ';
		}

		return new String(chars, 0, i);
	}
}