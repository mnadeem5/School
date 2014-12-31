public class UnionFind {
	private int[] parent;
	private int[] rank;

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
	}

	public void make_set(int i) {
		parent[i] = i;
	}

	public void union_sets(int i, int j) {
		i = find_set(i);
		j = find_set(j);
		if (rank[i] > rank[j])
			parent[j] = parent[i];
		else if (rank[i] < rank[j])
			parent[i] = parent[j];
		else {
			parent[j] = parent[i];
			rank[i]++;
		}
	}

	public int find_set(int node) {
		if (parent[node] != node)
			return (parent[node] = find_set(parent[node]));
		else
			return node;
	}
}
