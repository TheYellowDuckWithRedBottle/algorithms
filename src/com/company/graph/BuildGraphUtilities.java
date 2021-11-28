package com.company.graph;

import java.util.ArrayList;
import java.util.List;

public class BuildGraphUtilities {
    public static ArrayList<List<Edge>> buildWeightGraph(int vCount, int[][] weightRaw) {
        var g = new ArrayList<List<Edge>>();
        for(var i=0;i<vCount;i++){
            g.add(new ArrayList<>());
        }
        for(var edge:weightRaw){
            var newEdge = new Edge(edge[0],edge[1],edge[2]);
            g.get(edge[0]).add(newEdge);
        }
        return g;
    }

    public static List<List<Integer>> buildUnWeightGraph(int vCount, int[][] unweightRaw) {
        var g = new ArrayList<List<Integer>>();
        for(var i = 0;i<vCount;i++){
            g.add(new ArrayList<>());
        }
        for(var edge:unweightRaw){
            g.get(edge[0]).add(edge[1]);
        }
        return g;
    }
    public static int[][] buildGraph(boolean isWeighted,int vCount,int[][] raw){
        var g= new int[vCount][vCount];
        for(var edge:raw){
            g[edge[0]][edge[1]]=isWeighted?edge[2]:1;
        }
        return g;
    }
}

