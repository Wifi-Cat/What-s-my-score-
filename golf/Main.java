package golf;

import java.util.Scanner;

public class Main {
	private static final int NAME_LEN = 10;
	private static final int NUM_GOLFERS = 4;
	private static final int NUM_HOLES = 18;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Get golfer names
		String[] golfers = new String[NUM_GOLFERS];
		for (int i = 0; i < golfers.length; i++) {
			String name = sc.next();
			while (NAME_LEN - name.length() != 0) {
				name = " " + name;
			}
			golfers[i] = name;
		}
		// Print golfer names
		for (int i = 0; i < NAME_LEN; i++) {
			char a = golfers[0].charAt(i);
			char b = golfers[1].charAt(i);
			char c = golfers[2].charAt(i);
			char d = golfers[3].charAt(i);
			if (a == ' ' && b == ' ' && c == ' ' && d == ' ') {
				continue;
			} else {
				System.out.printf("%10s%5s%5s%5s\n", golfers[0].charAt(i),
						golfers[1].charAt(i), golfers[2].charAt(i),
						golfers[3].charAt(i));
			}
		}
		// Print a line of dashes
		for (int i = 0; i < 25; i++) {
			System.out.printf("-");
		}
		System.out.println();

		// Fix the scores
		int[] total = new int[NUM_GOLFERS];
		{
			// initialize
			int hole = 1;
			int[] current = new int[NUM_GOLFERS];
			for (int j = 0; j < current.length; j++) {
				current[j] = sc.nextInt();
				total[j] += current[j];
			}
			// Print the scores
			System.out.printf("%5d%5d%5d%5d%5d\n", hole, current[0],
					current[1], current[2], current[3]);

			// do it for the rest
			for (hole = 2; hole <= NUM_HOLES; hole++) {
				int[] order = getOrder(current);
				for (int i = 0; i < order.length; i++) {
					current[order[i]] = sc.nextInt();
					total[order[i]] += current[order[i]];
				}
				// Print the scores
				System.out.printf("%5d%5d%5d%5d%5d\n", hole, current[0],
						current[1], current[2], current[3]);
			}
		}
		// Print a line of dashes
		for (int i = 0; i < 25; i++) {
			System.out.printf("-");
		}
		System.out.println();
		// Print the scores
		System.out.printf("Total%5d%5d%5d%5d\n", total[0], total[1], total[2],
				total[3]);

		sc.close();
	}

	/**
	 * 
	 * @param scores
	 * @return the indices of the elements where the elements appear smallest to
	 *         largest
	 */
	private static int[] getOrder(int[] scores) {
		int[] indices = new int[NUM_GOLFERS];
		int[] scoreClone = new int[NUM_GOLFERS];
		for (int i = 0; i < scoreClone.length; i++) {
			scoreClone[i] = scores[i];
		}
		for (int i = 0; i < indices.length; i++) {
			indices[i] = i;
		}
		for (int i = 0; i < scoreClone.length; i++) {
			for (int j = 0; j < scoreClone.length; j++) {
				if (scoreClone[i] < scoreClone[j]) {
					// swap
					int tmp = scoreClone[i];
					scoreClone[i] = scoreClone[j];
					scoreClone[j] = tmp;
					// do indices too
					tmp = indices[i];
					indices[i] = indices[j];
					indices[j] = tmp;
				}
			}
		}
		return indices;
	}
}
