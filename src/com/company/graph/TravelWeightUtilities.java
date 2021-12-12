package com.company.graph;

import java.util.*;

public class TravelWeightUtilities {
    //广度优先遍历有权图的边
    public static List<Edge> getEdges(List<List<Edge>> g,int entry){
        var edges = new ArrayList<Edge>(); //存储访问的边列表
        var visited = new HashSet<Edge>(); //是否访问过的边
        var q = new LinkedList<Integer>(); //节点队列
        q.offer(entry); //先把入口放进去
        while (!q.isEmpty()){
            Integer from = q.poll();
            for(Edge edge:g.get(from)){
                if(visited.add(edge)){//如果加进去的话，说明还没有访问
                    edges.add(edge); //把边放进去
                    q.offer(edge.to); //把边到达的节点放进去
                }
            }
        }
        return edges;
    }
    //深度优先遍历有权图的所有边
    public static void getEdges(List<List<Edge>> g, int from , List<Edge> edges, Set<Edge> visited){
        for(var edge:g.get(from)){
            if(visited.add(edge)){
                edges.add(edge);
                getEdges(g,edge.to,edges,visited);
            }
        }
    }
    //递推深度优先遍历所有有权图的边
    public  static List<Edge> getTravelEdges(List<List<Edge>> g, int entry){
        var edges = new ArrayList<Edge>(); //存储边
        var visited = new HashSet<Edge>(); //访问过的边
        var stack = new Stack<Iterator<Edge>>(); //与节点相连的边的可迭代
        stack.push(g.get(entry).iterator());
        while(!stack.isEmpty()){
            Iterator<Edge> iterator = stack.peek();
            if(!iterator.hasNext()){
                stack.pop();
            }else{
                Edge edge = iterator.next();
                if(visited.add(edge)){
                    edges.add(edge);
                    stack.push(g.get(edge.to).iterator());
                }
            }
        }
        return edges;
    }
    //测试有权图的连通性
    public static boolean isConected(List<List<Edge>> g,int entry,int target){
        var visited = new HashSet<Edge>();
        var q = new LinkedList<Integer>();
        q.offer(entry);
        while(!q.isEmpty()){
            int from = q.poll();
            for(var edge:g.get(from)){
                if(visited.add(edge)){
                    if(edge.to==target) return true;
                    q.offer(edge.to);
                }
            }
        }
        return false;
    }

}
