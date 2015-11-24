/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.co.itasca.geu.bl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import in.co.itasca.geu.Main;
import in.co.itasca.geu.model.GraphData;
import in.co.itasca.geu.model.Popularity;
import in.co.itasca.geu.model.PopularityGraphDM;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author student
 */
public class PopularityBLModel {
    
    
    private String getFileName(boolean male, boolean female , String year){
    StringBuffer sb = new StringBuffer();
      sb.append("/resources/");
           if (male){
           sb.append("male_cy");
             }else if (female){
     sb.append("female_cy");
             
             }
           
           sb.append(year);
           sb.append("_top.csv");
           String fileName = sb.toString();
           return fileName;
    }
    public Popularity[] getData(boolean male, boolean female, boolean both, String year, Integer rank){
        try {
            
        Popularity[] pModels=null;
        if (female){
            String fileName = getFileName(male, female, year);
              pModels = getModelFromFile(fileName, rank);

        } else if (male){
             String fileName = getFileName(male, female, year);
              pModels = getModelFromFile(fileName, rank);
        } else if (both){
         String mfileName = getFileName(true, false, year);
            Popularity[]  mpModels = getModelFromFile(mfileName, rank);
           String ffileName= getFileName(false, true, year);
           Popularity[] fpModels = getModelFromFile(ffileName, rank);
           Collection<Popularity> coll = new ArrayList<Popularity>();
            for (int i = 0; i < mpModels.length; i++) {
                Popularity pmd= mpModels[i];
                coll.add(pmd);
            }
            
            for (int i = 0; i < fpModels.length; i++) {
                Popularity pmd= fpModels[i];
                coll.add(pmd);
            }
            
            int len = coll.size();
            pModels = new Popularity[len];
            coll.toArray(pModels);
            
            
        }
        
        
        
    
            
         return pModels;
           
            
            
            } catch (Exception e) {
            
            int i =0;
        }
           
           
           return null;
      
   
    }
    
    private Popularity[] getModelFromFile (String fileName, Integer rank){
       Popularity[] pModels=null;
        try {
            
        Collection<Popularity> coll = new ArrayList<Popularity>();
        InputStream myin = getClass().getResourceAsStream(fileName);
           Scanner sc = new Scanner(myin);
           String line = sc.nextLine();
            while (sc.hasNext()) {
                String next = sc.next();
                String[] values = next.split(",");
                Popularity pmodel = new Popularity();
                String[] nme = values[0].split("\"");
                pmodel.setName(nme[1]);
                
                     String[] val = values[1].split("\"");
                pmodel.setAmount(val[1]);
                
                     String[] pos = values[2].split("\"");
                pmodel.setPosition(pos[1]);
                
                 if(rank==0)
                coll.add(pmodel);
                else if (Integer.parseInt(pmodel.getPosition())<=rank.intValue() )
                  coll.add(pmodel);
        
               
                 
            }
            int len = coll.size();
            pModels= new Popularity[len];
            coll.toArray(pModels);
            } catch (Exception e) {
        }
        return pModels;
    }

    public  HashMap<String, PopularityGraphDM> getSearchedName(String year, String name, boolean sexChoiceofMale) {
        
        
        HashMap<String, PopularityGraphDM> collection = new HashMap<String, PopularityGraphDM>();
        try {
            

        int yearInt = Integer.parseInt(year);
        
        
        while(yearInt<=2013){
        Popularity[] popularNameofYear = getPopularNames(  name,   String.valueOf(yearInt), sexChoiceofMale);
            try {
                
           
            for (int i = 0; i < popularNameofYear.length; i++) {
                Popularity popularity = popularNameofYear[i];
                String dmName = popularity.getName();
               PopularityGraphDM pgdm = collection.get(dmName);
               if(pgdm==null)
                   pgdm= new PopularityGraphDM();
                GraphData gd = new GraphData();
                gd.setName(String.valueOf(yearInt));
                String amt = popularity.getAmount();
                gd.setAmount(amt);
                
                pgdm.add(gd);
                    collection.put(dmName  , pgdm);
        
            }
             } catch (Exception e) {
            }
        
        yearInt++;
        
        }
        return  collection;
        
                } catch (Exception e) {
                    return null;
                    
        }
            }

    private Popularity[] getPopularNames(String name, String year, boolean  sexChoiceforMale) {
        try {
     
        
        Collection<Popularity> collection = new ArrayList<Popularity>();
        Popularity[] models  =null;
        if(sexChoiceforMale){
          models  = getData(true, false, false, year, new Integer(0));
        } else {
             models  = getData(false, true, false, year, new Integer(0));
        }
        for (int i = 0; i < models .length; i++) {
            Popularity popularity = models[i];
            if(popularity.getName().toUpperCase().contains(name.toUpperCase())){
            collection.add(popularity);
            }
            
        }
        
        int len = collection.size();
        Popularity[] datas= new Popularity[len];
        collection.toArray(datas);
        return datas;
              
        } catch (Exception e) {
            return null;
        } 
            }
    
    
    
    
}
