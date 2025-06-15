/*
 * Some stuff I want to share with you [You can skip this if you want]:
 * So I have tried this problem for quite a long time and unable to get the idea
 * of how to solve it because of my overthinking. You can call me a loser for
 * that. Although I got pretty close to the idea, but guess what, that's my life
 * basically. I over complicate things and then I get stuck and lose my
 * confidence at the end. I feel so bad that I have to look at the solution
 * and then I realize that it was so simple. There is something inside me that
 * growls "Even that guy can solve it, why can't you?",
 * "This is the reason why you are not getting a job at product-based
 * companies, you are not able to solve simple problems like this.", and "Look
 * at that guy, he probably solved it in 5 minutes, and now he/she is at
 * Amazon/Microsoft/Oracle, and you are here, struggling with this". I know
 * there is no point in thinking like this, but I can't help it. I know there
 * are people who have high IQ and can solve problems like this in a
 * jiffy, but I am not one of them. I always feel leetcode is for genius
 * people. I know there are people who can solve tough problems when they see
 * them at the first time. Dude, my friends are like that, thats why they are in
 * good companies and start ups. The friends who are in my college think I am
 * smart and can solve problems like this. Bro, have you ever been in a class
 * with 99% of the people being smarter than you? I have. I have been in a
 * class where I was the dumbest person back when I was in 11th and 12th. I was
 * at my prime in 10th [or so I thought], but when I entered 11th, my confidence
 * was shattered and I kept making mistakes in exams. Funny shit is that I was
 * unable to even understand concepts and when I wanted to ask teachers about
 * it, I felt like what others would think of me being a dumb person. The exams
 * were difficult but the same friends who were with me managed to get good
 * scores and eventually got into good universities. I think they have
 * seen something like "light" at that time and that's why they were able to
 * achieve what they wanted. Man, when will I reach my light. I think about it
 * everyday. I kinda feel depressed about it. Can someone pull me out of this
 * please? I want to reach my light too. I also want to walk beside my friends. 
 * I don't want to be alone. [Fuck, the tears started coming already]
 */

/*
 * Problem Statement:
 * You have a long flowerbed in which some of the plots are planted, and some
 * are not. However, flowers cannot be planted in adjacent plots.
 * Given an integer array flowerbed containing 0's and 1's, where 0 means empty
 * and 1 means not empty, and an integer n, return true if n new flowers can be
 * planted in the flowerbed without violating the no-adjacent-flowers rule and
 * false otherwise.
 * 
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: true
 * 
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: false
 * 
 * Constraints:
 * 1 <= flowerbed.length <= 2 * 10^4
 * flowerbed[i] is 0 or 1.
 * There are no two adjacent flowers in flowerbed.
 */

public class CanPlaceFlowers {
	/*
	 * Optimal Solution Thought Process:
	 * The idea here is to check if I can plant the flower at the current position
	 * by checking the previous and next positions. So, basically, at a position
	 * i, I can plant a flower if:
	 * 1. flowerbed[i] is 0 (the current position is empty)
	 * 2. flowerbed[i-1] is 0 (the previous position is empty or i is the first
	 * position)
	 * 3. flowerbed[i+1] is 0 (the next position is empty or i is the last
	 * position)
	 * 
	 * At last, I will check if the planted flowers count is greater than or
	 * equal to n. If it is, I will return true, otherwise false.
	 * TC: O(m) where m is the length of the flowerbed array.
	 * SC: O(1) since we are not using any extra space.
	 */
	public boolean optimal(int[] flowerbed, int n) {
		int planted = 0;
		for (int i = 0; i < flowerbed.length; i++) {
			// Can I plant at position i?
			if (canPlant(flowerbed, i)) {
				flowerbed[i] = 1; // Plant it!
				planted++;

				if (planted == n) { // Got enough flowers?
					return true;
				}
			}
		}

		return planted >= n;
	}

	private boolean canPlant(int[] flowerbed, int i) {
		// Check if current position is empty
		if (flowerbed[i] == 1) {
			return false; // Can't plant here
		}

		// Check previous position
		if (i > 0 && flowerbed[i - 1] == 1) {
			return false; // Can't plant here, previous is occupied
		}

		// Check next position
		if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1) {
			return false; // Can't plant here, next is occupied
		}

		return true; // Can plant here
	}

	public static void main(String[] args) {
		CanPlaceFlowers solution = new CanPlaceFlowers();

		// Test cases
		System.out.println(solution.optimal(new int[] { 1, 0, 0, 0, 1 }, 1)); // true
		System.out.println(solution.optimal(new int[] { 1, 0, 0, 0, 1 }, 2)); // false
	}
}