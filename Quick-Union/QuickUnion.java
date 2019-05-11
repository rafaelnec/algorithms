/* *****************************************************************************
 *  Name:    Rafael Neves Moraes
 *
 *  Description:  Quick Union Algorithm.
 *
 *  Written:       8/05/2019
 *
 *  % javac QuickUnion.java
 *  % java QuickUnion exemple.txt
 *
 *  Formatter: 
 *      <file> : 
 *          n -> number of lines
 *          row col -> list of rows and cols
 *          Ex.:
 *               20
 *                 7  11
 *                18  11
 *                12   5
 *                 9   5
 *                 5   9
 **************************************************************************** */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class QuickUnion 
{
	
	private int[] id;
	private int[] sz;
	private int tIem;
	private int t;
	
	public QuickUnion(int n) 
	{
		id 	= new int[n];
		sz 	= new int[n];
		t 	= n - 1;
		tIem = n;

		for(int i = 0; i < n; i++) {
			id[i] = i;	
			sz[i] = 1;
		}
	}

	public boolean connected(int p, int q) 
	{
		validateRowCol(p, q);
		return root(p) == root(q);
	}
	
	public void union(int p, int q) 
	{
		validateRowCol(p, q);
		int i = root(p);
		int j = root(q);

		if(i == j) return;
		if(sz[i] < sz[j]) 	{ id[i] = j; sz[j] += sz[i]; }
		else 				{ id[j] = i; sz[i] += sz[j]; } 

		t -= 1;
	}

	public int[] getId() 
	{
		return id;
	}

	public boolean allConnected() 
	{
		if(t <= 0)
			return true;
		return false;
	}

	public int getTotalGroups() 
	{
		return t + 1;
	}

	private int root(int i) 
	{
		i -= 1;

		while (i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		} 

		return i;
	}

	private void validateRowCol(int row, int col) 
	{
        if(row <= 0 || row > tIem )
            throw new IllegalArgumentException("row value " + String.valueOf(row) + " out of bounds!");
        if(col <= 0 || col > tIem )
            throw new IllegalArgumentException("col value " + String.valueOf(col) + " out of bounds!");
    }

	public static void main(String[] args) 
	{

		try {
            
            FileReader fileReader 			= new FileReader(args[0]);
            BufferedReader bufferedReader 	= new BufferedReader(fileReader);
            
            int n = Integer.valueOf(bufferedReader.readLine());

            if(n <= 0) 
            	throw new IllegalArgumentException("First file argument must be gran than 0!");

            QuickUnion qn = new QuickUnion(n);
			String line = null;

            while((line = bufferedReader.readLine()) != null) {

            	String[] item = Arrays.stream(line.split(" ")).filter(x -> !x.isEmpty()).toArray(String[]::new);

            	int row = Integer.parseInt(item[0]);
            	int col = Integer.parseInt(item[1]);
            	qn.union(row,col);

            }   

            bufferedReader.close();

            System.out.println(String.format("End array: %s", Arrays.toString(qn.getId())));
            System.out.println(String.format("Total of groups: %s", qn.getTotalGroups()));  
            System.out.println(String.format("All poins is connected? %s", qn.allConnected()));

        }
        catch(FileNotFoundException e) {
            System.out.println("Unable to open file");                
        }
        catch(IOException e) {
            System.out.println("Error reading file");                  
        }
		
	}

}