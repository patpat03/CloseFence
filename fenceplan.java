import java.util.*;
import java.io.*;
public class fenceplan
{
	static ArrayList[] edges;
	static int[][] coordinates;
	static int min_x = 0;
	static int max_x =0;
	static int min_y =0;
	static int max_y =0;
	public static void main(String[] args) throws Exception
	{
		Scanner scan = new Scanner(new File("fenceplan.in"));
		PrintWriter print = new PrintWriter(new File("fenceplan.out"));
		int n = scan.nextInt();
		int m = scan.nextInt();
		int per = Integer.MAX_VALUE;
		coordinates = new int[n][2];
		edges = new ArrayList[n];
		boolean[] taken  = new boolean[n];
		for(int i=0; i<coordinates.length; i++)
		{
			coordinates[i][0] = scan.nextInt();
			coordinates[i][1] = scan.nextInt();
		}
		for(int i=0; i<edges.length; i++){
			edges[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<m; i++)
		{
			int from = scan.nextInt()-1;
			int to = scan.nextInt()-1;
			edges[from].add(to);
			edges[to].add(from);
		}
		for(int i=0; i<edges.length; i++)
		{
			 min_x = Integer.MAX_VALUE;
			 max_x = 0;
			 min_y = Integer.MAX_VALUE;
			 max_y = 0;
			 if(edges[i].size()>0 && !taken[i])
			 {
				bfs(i, taken);
			 	int temp = 2 * (max_x - min_x + max_y - min_y);
			 	if(temp < per)
			 	{
			 		per = temp;
			 	}
			 }	
		}
		print.println(per);
		print.close();
		scan.close();
	}
	static void bfs(int from, boolean[] taken)
	{
			taken[from] = true;
			System.out.println(coordinates[from][0] + "->" + coordinates[from][1]);	
			if(coordinates[from][0]>max_x)
			{
				max_x = coordinates[from][0];
			}
			if(coordinates[from][1]>max_y)
			{
				max_y = coordinates[from][1];
			}
			if(coordinates[from][0]<min_x)
			{
				min_x = coordinates[from][0];
				//System.out.println("min: " + min_x);

			}
			if(coordinates[from][1]<min_y)
			{
				min_y = coordinates[from][1];
			}
			for(Integer next: (ArrayList<Integer>)edges[from])
			{
					if(!taken[next])
					{
						bfs(next, taken);
					}
			}
	}

}