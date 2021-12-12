package com.company.graph;

import java.util.*;

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
    //双源遍历是否互通(从起点和终点往中间走，如果两个visited中出现重复，说明有连接)
    public static boolean isBothConected(List<List<Integer>> g ,int entry ,int target){
        Set<Integer> visited1 = new HashSet<>(),visited2 = new HashSet<>();
        Queue<Integer> q1 = new LinkedList<>(),q2 = new LinkedList<>();
        visited1.add(entry);
        visited2.add(target);
        q1.offer(entry);
        q2.offer(target);
        while (!q1.isEmpty()&&!q2.isEmpty()){
            var from1 = q1.poll();
            for(var to:g.get(from1)){
                if(visited2.contains(to)) {
                    return true;
                }
                if(visited1.add(to)){
                    q1.offer(to);
                }
            }
            var from2 = q2.poll();
            for(var to:g.get(from2)){
                if(visited1.contains(to)){
                    return true;
                }
                if(visited2.add(to)){
                    q2.offer(to);
                }
            }
        }
        return false;
    }

    //对于双源的优化
    public static boolean isBothConnected(List<List<Integer>> g, int entry,int target){
        Set<Integer> visited1 = new HashSet<>(),visited2= new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        visited1.add(entry);
        visited2.add(target);
        q.offer(entry);
        q.offer(target);
        while(!q.isEmpty()){
            var from = q.poll();
            if(visited1.contains(from)){
                for(var to:g.get(from)){
                    if(visited2.contains(to)){
                        return true;
                    }
                    if(visited1.add(to)){
                        q.offer(to);
                    }
                }
            }else{
                for(var to:g.get(from)){
                    if(visited1.contains(to)) return true;
                    if(visited2.add(to)) q.offer(to);
                }
            }
        }
        return false;
    }
}
