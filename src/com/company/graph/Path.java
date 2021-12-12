package com.company.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Path {
    //bfs式的路径搜索
    public static List<List<Integer>> getPaths(List<List<Integer>> g, int start ,int end){
        var paths = new ArrayList<List<Integer>>();//存放路径的集合
        var visited = new HashSet<Integer>();
        var path = new ArrayList<Integer>(); //准备存放的路径
        visited.add(start);
        path.add(start);
        var vq = new LinkedList<HashSet<Integer>>(); //遍历过的路径
        var pq = new LinkedList<ArrayList<Integer>>();
        vq.offer(visited);
        pq.offer(path);
        while(!vq.isEmpty()){
            visited = vq.poll();
            path = pq.poll();
            var from = path.get(path.size()-1);
            for(var to :g.get(from)){
                if(visited.contains(to)) continue;//有环舍弃
                var visitedExt = new HashSet<>(visited);
                var pathExt = new ArrayList<>(path);
                visitedExt.add(to);
                pathExt.add(to);
                if(to == end){
                    paths.add(pathExt);
                }else{
                    vq.offer(visitedExt);
                    pq.offer(pathExt);
                }
            }
        }
        return paths;
    }

}
