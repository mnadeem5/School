import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class binaryImage {
	public static void main(String[] args) {
		int[][] image = null;
		boolean[][] imageBool = null;
		int[] sets = null;
		int[] chars = null;
		if (args.length != 1)
			System.out.println("Wrong use! (Ex. binaryUnion filename.txt)");
		else {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						new FileInputStream(args[0])));
				image = new int[71][71];
				imageBool = new boolean[71][71];

				/**
				 * Reads the file line by line and stores every '+' as a 1 and
				 * every ' ' as a 0 into an integer matrix.
				 */
				String str;
				for (int i = 0; in.ready(); i++) {
					str = in.readLine();
					for (int j = 0; j < str.length(); j++)
						if (str.charAt(j) == '+') {
							image[i][j] = 1;
							imageBool[i][j] = true;
						}
				}
				in.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found!");
			} catch (IOException e) {
				System.out.println("Can't read file.");
			}
		}

		// Prints part a.
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				System.out.print(image[i][j]);
			}
			System.out.println();
		}
		System.out.println();

		// creates the Union Find and connects its components.
		uandf imageSet = new uandf(image.length * image.length + 1);
		for (int i = 0; i < image.length; i++)
			for (int j = 0; j < image.length; j++)
				if (image[i][j] == 1) {
					imageSet.make_set((i * image.length + j) + 1);
					if (j > 0 && image[i][j - 1] == 1)
						imageSet.union_sets(i * image.length + (j - 1) + 1, i
								* image.length + j + 1);
					if (i > 0 && image[i - 1][j] == 1)
						imageSet.union_sets((i - 1) * image.length + j + 1, i
								* image.length + j + 1);
				}

		sets = new int[imageSet.final_sets()];
		chars = new int[sets.length];

		// Prints part b.
		int z;
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				z = imageSet.find_set(i * image.length + j + 1) + 96;
				if (z == 96)
					System.out.print(' ');
				else {
					System.out.print((char) z);
					sets[z - 97]++;
				}
			}
			System.out.println();
		}
		System.out.println();

		// sorts the lists.
		int[] sortedSets = new int[sets.length];
		for (int i = 0; i < sets.length; i++)
			sortedSets[i] = sets[i];
		for (int i = 0; i < sortedSets.length; i++)
			chars[i] = sortedSets[i];
		Arrays.sort(sortedSets);

		// Prints part c.
		for (int i = 0; i < sortedSets.length; i++)
			for (int j = 0; j < chars.length; j++)
				if (sortedSets[i] == chars[j]) {
					System.out.println((char) (j + 97) + "\t  \t"
							+ sortedSets[i]);
					chars[j] = -1;
					break;
				}
		System.out.println();

		// Prints part d.
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				z = imageSet.find_set(i * image.length + j + 1) + 96;
				if (z == 96 || sets[z - 97] == 1)
					System.out.print(' ');
				else
					System.out.print((char) z);
			}
			System.out.println();
		}
		System.out.println();
	}
}
