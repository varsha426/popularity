/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.itasca.geu.model;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class PopularityGraphDM {
    
    private ArrayList<GraphData> graphData = new ArrayList<GraphData>();
    
    
    
    
    public void add(GraphData data){
    graphData.add(data);
    
    }
    
    public GraphData[] getAsArray(){
    
        int len = graphData.size();
        GraphData[] gda = new GraphData[len];
        graphData.toArray(gda);
        return gda;
    }
    
}
