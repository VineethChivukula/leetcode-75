/*
 * Problem Statement:
 * There are n kids with candies. You are given an integer array candies, where
 * each candies[i] represents the number of candies the ith kid has, and an
 * integer extraCandies, denoting the number of extra candies that you have.
 * 
 * Return a boolean array result of length n, where result[i] is true if, after
 * giving the ith kid all the extraCandies, they will have the greatest number
 * of candies among all the kids, or false otherwise.
 * 
 * Note that multiple kids can have the greatest number of candies.
 * 
 * Example 1:
 * Input: candies = [2,3,5,1,3], extraCandies = 3
 * Output: [true,true,true,false,true]
 * 
 * Example 2:
 * Input: candies = [4,2,1,1,2], extraCandies = 1
 * Output: [true,false,false,false,false]
 * 
 * Example 3:
 * Input: candies = [12,1,12], extraCandies = 10
 * Output: [true,false,true]
 * 
 * Constraints:
 * n == candies.length
 * 2 <= n <= 100
 * 1 <= candies[i] <= 100
 * 1 <= extraCandies <= 50
 */

import java.util.ArrayList;
import java.util.List;

public class KidsWiththeGreatestNumberofCandies {
	/*
	 * Vineeth's Brute-Force Solution Thought Process:
	 * The problem is asking us to determine if a kid can have the greatest number
	 * of candies after giving him or her the extra candies among all the kids. The
	 * process is straightforward:
	 * 1. For each kid, calculate the total number of candies they would have after
	 * receiving the extra candies.
	 * 2. Compare this total with the maximum number of candies any kid has.
	 * 3. If the total is equal to the maximum, mark that kid as true and add it to
	 * the result list, otherwise mark it as false and add it to the result list.
	 * 
	 * TC: O(n^2) - For each kid, we iterate through the list to find the maximum
	 * number of candies.
	 * SC: O(1) - We use a list to store the results, but do not consider it
	 * as additional space since it is part of the output.
	 */
	public List<Boolean> bruteForce(int[] candies, int extraCandies) {
		List<Boolean> result = new ArrayList<>();
		int len = candies.length;

		// Iterate through each kid
		for (int i = 0; i < len; i++) {
			int temp = candies[i]; // Store the original number of candies for the current kid
			candies[i] += extraCandies;

			// Find the maximum number of candies among all kids after giving extra candies
			int max = candies[0];
			for (int j = 1; j < len; j++) {
				if (candies[j] > max) {
					max = candies[j];
				}
			}

			// Check if the current kid has the maximum number of candies
			if (max == candies[i]) {
				result.add(true);
			} else {
				result.add(false);
			}

			candies[i] = temp; // Restore the original number of candies for the next iteration
		}

		return result;
	}

	/*
	 * Optimal Solution Thought Process:
	 * I have thought for a while but I couldn't come up with a better solution
	 * than the above one. So, I have looked at the solution and I will be
	 * explaining it here. The idea is to find out how many candies are needed
	 * by a kid to have the greatest number of candies among all the kids. To
	 * achieve this:
	 * 1. First, find the maximum number of candies any kid has.
	 * 2. Then, for each kid, check if the number of candies they would have after
	 * receiving the extra candies is greater than or equal to the maximum number.
	 * 3. If it is, mark that kid as true in the result list, otherwise mark it as
	 * false.
	 * TC: O(n) - We only need to iterate through the list a couple of times.
	 * SC: O(1) - We use a list to store the results, but do not consider it
	 * as additional space since it is part of the output.
	 */
	public List<Boolean> optimal(int[] candies, int extraCandies) {
		List<Boolean> result = new ArrayList<>();
		int max = Integer.MIN_VALUE;

		// Find the maximum number of candies any kid has
		for (int candy : candies) {
			if (candy > max) {
				max = candy;
			}
		}

		// Check if each kid can have the greatest number of candies after receiving
		// extra candies
		for (int candy : candies) {
			if (candy + extraCandies >= max) {
				result.add(true);
			} else {
				result.add(false);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		KidsWiththeGreatestNumberofCandies solution = new KidsWiththeGreatestNumberofCandies();

		// Test cases
		System.out.println(solution.bruteForce(new int[] { 2, 3, 5, 1, 3 }, 3)); // [true, true, true, false, true]
		System.out.println(solution.optimal(new int[] { 4, 2, 1, 1, 2 }, 1)); // [true, false, false, false, false]
	}
}