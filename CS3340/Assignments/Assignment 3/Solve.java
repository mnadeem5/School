import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solve {
	public static void main(String[] args) {
		if (args.length != 1)
			System.out.println("Invalid number of arguments!");
		else {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(args[0])));

				int i, j, w, size = Integer.parseInt(br.readLine()) + 1;
				int[] values;
				int edges[][] = new int[size][size];
				String line;
				UnionFind vertices = new UnionFind(size);
				PriorityQueue<Edge> pq = new PriorityQueue<Edge>(1,
						edgeComparator);

				
				//reads the files in
				while ((line = br.readLine()) != null) {
					values = getValues(line);

					i = values[0];
					j = values[1];
					w = values[2];

					vertices.make_set(i);
					vertices.make_set(j);

					pq.add(new Edge(i, j, w));

					edges[i][j] = w;
					edges[j][i] = w;
				}
				br.close();

				//prints out the graph
				System.out.println("Vertex i:\t Edges (i, j, w)");
				for (i = 1; i < size; i++) {
					System.out.print("Vertex " + i + ":\t");
					for (j = 0; j < size; j++)
						if (edges[i][j] != 0)
							System.out.print("(" + i + ", " + j + ", "
									+ edges[i][j] + ")\t");
					System.out.println();
				}
				//Solves  using Kruskals algo and prints the result
				System.out.println("\nEdges (i, j, w)");
				int mst = 1;
				Edge edge;
				while (mst < size - 1) {
					edge = pq.poll();

					if (vertices.find_set((i = edge.getI())) != vertices
							.find_set((j = edge.getJ()))) {
						vertices.union_sets(i, j);
						System.out.println("Edge " + mst + ": " + "\t(" + i
								+ ", " + j + ", " + edge.getW() + ")");
						mst++;
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("The file " + args[0]
						+ " was not found in the directory.");
			} catch (IOException e) {
				System.out.println("Input exception, file is corrupted.");
			}
		}
	}

	private static int[] getValues(String line) {
		int[] values = new int[3];
		int ctr = 0, ptr = 0;
		String temp;

		while (ctr < 3) {
			temp = "";
			while (ptr < line.length() && line.charAt(ptr) != ' ')
				temp += line.charAt(ptr++);
			if (temp.length() == 0)
				ptr++;
			else
				values[ctr++] = Integer.parseInt(temp);
		}
		return values;
	}

	public static Comparator<Edge> edgeComparator = new Comparator<Edge>() {
		public int compare(Edge e1, Edge e2) {
			return e1.getW() - e2.getW();
		}
	};
}