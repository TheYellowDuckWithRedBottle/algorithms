package com.company;

import com.company.graph.BuildGraphUtilities;
import com.company.graph.Edge;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int [][] unweightRaw = {{0,1},{0,2},{0,3},{1,4},{2,1},{2,3},{3,4},{4,2}};
        int [][] weightRaw = {{0,1,1},{0,2,1},{0,3,1},{1,4,1},{2,1,1},{2,3,1},{3,4,1},{4,2,1}};

        var unweightGraph = BuildGraphUtilities.buildUnWeightGraph(5,unweightRaw);
        var weightGraph = BuildGraphUtilities.buildWeightGraph(5,weightRaw);
    }


}
