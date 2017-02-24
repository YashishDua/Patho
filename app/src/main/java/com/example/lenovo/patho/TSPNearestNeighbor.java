package com.example.lenovo.patho;

import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Lenovo on 30-11-2016.
 */

public class TSPNearestNeighbor {
    private int numberOfNodes;
    private Stack<Integer> stack;
    public ArrayList<Integer> result;

    public ArrayList<Integer> getResult() {
        return result;
    }

    public TSPNearestNeighbor()
    {
        stack = new Stack<>();
        result = new ArrayList<>();
    }

    public void tsp(int adjacencyMatrix[][])
    {
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        result.add(1);

        while (!stack.isEmpty())
        {
            element = stack.peek();
            i = 0;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes)
            {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
                {
                    if (min > adjacencyMatrix[element][i])
                    {
                        min = adjacencyMatrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag)
            {
                visited[dst] = 1;
                stack.push(dst);
                Log.i("----",dst + "\t");
                result.add(dst);
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }

}
