public class uandf
{
	private int [] parent;
	private int [] rank;
	private boolean finalSet=false;
	
	public uandf(int n)
	{
		parent=new int[n];rank=new int [n];
	}
	
	public void make_set(int i)
	{
		parent[i]=i;
	}
	
	public void union_sets(int i, int j)
	{
		i=find_set(i);j=find_set(j);
		if (rank[i]>rank[j])parent[j]=parent[i];
		else if (rank[i]<rank[j])parent[i]=parent[j];
		else
		{
			parent[j]=parent[i];
			rank[i]++;
		}
	}
	
	public int find_set(int node) 
	{
		if (finalSet==false)
		{
			if (parent[node]!=node)return (parent[node]=find_set(parent[node]));
			else return node;
		}
		else return parent[node];
	}
	
	public int final_sets()
	{	
		for (int i=1; i<parent.length; i++)if(parent[i]!=0)find_set(i);
		int ctr=1;
		for (int i=1; i<parent.length; i++)
		{
			if (parent[i]==i)
			{
				parent[i]=ctr++;
				rank[i]=1;
			}else rank[i]=0;
		}
		for (int i=1; i<parent.length; i++)if (rank[i]==0&&parent[i]>0)parent[i]=parent[parent[i]];
		finalSet=true;
		return ctr-1;
	}
}
