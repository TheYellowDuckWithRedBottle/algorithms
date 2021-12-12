package com.company.graph;

import java.util.*;

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
    //广度优先遍历 无权图
    public static List<Integer> getVertices(List<List<Integer>> g ,int entry){
        var vertices = new ArrayList<Integer>(); //
        var visited = new HashSet<Integer>();//遍历过的
        var q = new LinkedList<Integer>();//队列中的
        vertices.add(entry);
        visited.add(entry);
        q.offer(entry);
        while(!q.isEmpty()){
            var from = q.poll();//当q不为空的时候把q 中的节点拿出来
            for(var to: g.get(from)){ //遍历这个节点可以到达的链接节点
               if(visited.contains(to)) continue; //如果遍历过了，就不管了
                vertices.add(to); //把到达过的节点放进去
                visited.add(to);
                q.offer(to);
            }
        }
        return vertices;
    }
    //先序深度优先遍历 无权图
    public static void preGetVertices(List<List<Integer>> g, int from, List<Integer> vertices, Set<Integer> visited){
        if(!visited.add(from)) return; //如果遍历过的里面加入过的话，就递归结束返回，否则往下走
        vertices.add(from); //把第一个节点放进去
        for(var to: g.get(from)){ //图里面取出来第一个继续遍历
            preGetVertices(g,to,vertices,visited);
        }
    }
    //后序深度优先遍历 无权图
    public static void postGetVertices(List<List<Integer>> g, int from , List<Integer> vertices, Set<Integer> visited){
        if(!visited.add(from)) return;
        for(var to: g.get(from)){
            postGetVertices(g,to,vertices,visited);
        }
        vertices.add(from);
    }
    //递推深度优先
    public static List<Integer> getDeepVertices(List<List<Integer>> g,int entry){
        var vertices = new ArrayList<Integer>();
        var visited = new HashMap<Integer,Iterator<Integer>>();
        var stack = new Stack<Integer>();
        //记录哪些节点访问过了，记录这个节点的下一个节点迭代到了哪
        visited.put(entry, g.get(entry).iterator());
        vertices.add(entry);
        stack.push(entry);
        //以上为准备
        while(!stack.isEmpty()){
            Integer top = stack.peek(); //获取栈顶入口的顶点
            Iterator<Integer> iterator= visited.get(top); //获取入口顶点的可到达iterator
            if(!iterator.hasNext()){ //如果没有可以到达的节点，就把这个访问的入口弹出
                stack.pop();
            }else{
                int to = iterator.next(); //获取到达的节点
                if(!visited.containsKey(to)){ //看visited里是不是包含了到达的节点，如果不包含
                    visited.put(to,g.get(to).iterator());//把他放进去已访问过的节点列表
                    vertices.add(to); //把访问到的节点放进vertices
                    stack.push(to);  //stack栈也放入
                }
            }
        }
        return vertices;
    }
}

