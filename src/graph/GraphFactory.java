/*
 *   GraphFactory
 * 
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *   Last Update: 2012/12/09
 * 
 */

package graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class GraphFactory {
	
	// For Unit Test
	public static void main(String[] args) throws IOException {

		BufferedReader r = new BufferedReader(new FileReader("graph.txt"));
		AdjMatrixGraph g = GraphFactory.read(r);
		System.out.println("numVertex: " + g.getNumVertex());
		System.out.println("numEdges: " + g.getNumEdges());
		
		g.setVertexName(0, "WAS");
		g.setVertexName(1, "BOS");
		g.setVertexName(2, "NYY");
		g.setVertexName(3, "LOS");
		g.setVertexName(4, "TPE");
		
		g.printCostMatrix();
	}
	
	// Get random undirected(symmetric) graph
	public static AdjMatrixGraph getRandomUndirectedGraph(int numVertex) {
		int maxEdgeCost = 5;
		Random rnd = new Random();
		AdjMatrixGraph g = getRandomGraph(numVertex, maxEdgeCost, rnd);
		for (int i=1; i<numVertex; i++)
			for (int j=0; j<i; j++)
				g.setEdgeCost(i, j, g.getEdgeCost(j, i));
		return g;
	}
	
	public static AdjMatrixGraph getRandomUndirectedGraph(int numVertex, long seed) {
		int maxEdgeCost = 5;
		AdjMatrixGraph g = getRandomGraph(numVertex, maxEdgeCost, seed);
		for (int i=1; i<numVertex; i++)
			for (int j=0; j<i; j++)
				g.setEdgeCost(i, j, g.getEdgeCost(j, i));
		return g;
	}
	
	public static AdjMatrixGraph getRandomUndirectedGraph(int numVertex, int maxEdgeCost, long seed) {
		Random rnd = new Random(seed);
		AdjMatrixGraph g = getRandomGraph(numVertex, maxEdgeCost, rnd);
		for (int i=1; i<numVertex; i++)
			for (int j=0; j<i; j++)
				g.setEdgeCost(i, j, g.getEdgeCost(j, i));
		return g;
	}
	
	// Get random (directed) graph
	public static AdjMatrixGraph getRandomGraph(int numVertex) {
		int maxEdgeCost = 5;
		Random rnd = new Random();
		return getRandomGraph(numVertex, maxEdgeCost, rnd);
	}
	
	public static AdjMatrixGraph getRandomGraph(int numVertex, long seed) {
		int maxEdgeCost = 5;
		return getRandomGraph(numVertex, maxEdgeCost, seed);
	}
	
	public static AdjMatrixGraph getRandomGraph(int numVertex, int maxEdgeCost, long seed) {
		Random rnd = new Random(seed);
		return getRandomGraph(numVertex, maxEdgeCost, rnd);
	}
	
	private static AdjMatrixGraph getRandomGraph(int numVertex, int maxEdgeCost, Random rnd) {
		AdjMatrixGraph g = new AdjMatrixGraph(numVertex);
		int tmpCost;
		for (int i=0; i<numVertex; i++) {
			for (int j=0; j<numVertex; j++) {
				tmpCost = (int)(rnd.nextInt(maxEdgeCost));
				if (tmpCost > 0 && i!=j) g.setEdgeCost(i, j, tmpCost);
			}
		}
		return g;
	}
	
	
	/*
	 *   Reads and returns a graph defined by lines of texts:
	 *   1. The first line contains only an integer representing the number of vertices
	 *   2. The following lines contain the edges represented by three columns i:Integer, j:Integer, edge_cost:Double delimited by spaces
	 *      Where i is the start node and j is the end node of the edge
	*/
	public static AdjMatrixGraph readFromFile(String filePath) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(filePath));
		return read(r);
	}
	
	public static AdjMatrixGraph read(BufferedReader r) throws IOException {
		//return new Graph(20);
		String line;
		
		String [] edge = new String [3];
		int i, j;
		double edgeCost;
		int numVertex;
		numVertex = Integer.parseInt(r.readLine());
		AdjMatrixGraph g = new AdjMatrixGraph(numVertex);
				
		while (true) {
			
			line = r.readLine();
			if (line == null) break;
			edge = line.split(" ", 3);
			i = Integer.parseInt(edge[0]);
			j = Integer.parseInt(edge[1]);
			edgeCost = Double.parseDouble(edge[2]);
			g.setEdgeCost(i, j, edgeCost);
			
		}
		
		return g;
		
	}
	
}
