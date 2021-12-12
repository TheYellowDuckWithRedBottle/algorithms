package com.company.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TravelUnweightUtilities {
    //广度优先遍历
    public static boolean isConnected(List<List<Integer>> g ,int entry , int target){
        var visited = new HashSet<Integer>();
        var q = new LinkedList<Integer>();
        visited.add(entry);
        q.offer(entry);
        while(!q.isEmpty()){
            Integer from = q.poll();
            for(var to : g.get(from)){
                if(to==target) {
                    return true;
                }
                if(visited.add(to)) {
                    q.offer(to);
                }
            }
        }
        return false;
    }
    public static boolean isConnected(List<List<Integer>> g, int from , int target, Set<Integer> visited){
        if(!visited.add(from)){
           return false;
        }
        for(var to:g.get(from)){
            if(to==target||isConnected(g,to,target,visited)){
                return true;
            }
        }
        return false;
    }
}
