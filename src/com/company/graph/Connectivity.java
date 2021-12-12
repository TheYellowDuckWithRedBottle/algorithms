package com.company.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Connectivity {
    //构造正向图和反向图
    public  static List<List<Integer>> buildReverseGraph(int vCount,int[][] raw,boolean isTranspose){
        var g = new ArrayList<List<Integer>>();
        for(var v = 0;v<vCount;v++){
            g.add(new ArrayList<>());
        }
        for(var r:raw){
            if(isTranspose){
                g.get(r[1]).add(r[0]);
            }else{
                g.get(r[0]).add(r[1]);
            }
        }
        return g;
    }
    //以后续DFS方式将顶点收入列表
    public static void collect(List<List<Integer>> g, int from , Set<Integer> visited, List<Integer> vertices){
        if(!visited.add(from)) return;
        for(var to:g.get(from)){
            collect(g,to,visited,vertices);
        }
        vertices.add(from);
    }
    //两次DFS
    public static List<List<Integer>> getSCCs(int vCount,int[][] raw){
        var g = buildReverseGraph(vCount,raw ,false);
        var tg = buildReverseGraph(vCount,raw,true);

        var visited = new HashSet<Integer>();
        var vertices = new ArrayList<Integer>();
        for(var v = 0;v<vCount;v++){
            if(!visited.contains(v)){
                collect(g,v,visited,vertices);
            }
        }
        //收集强连接组件
        visited.clear();
        var sccList = new ArrayList<List<Integer>>();
        for(var i= vertices.size()-1;i>=0;i--){
            var v = vertices.get(i);
            if(visited.contains(v)) continue;
            var scc = new ArrayList<Integer>();
            collect(tg,v,visited,scc);
            sccList.add(scc);
        }
        return sccList;
    }
}
