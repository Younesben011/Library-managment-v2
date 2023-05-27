package com.example.librarymanagement;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Routes {
    Map<String, Stage> stageMap = new HashMap<>();
    Routes(){

    }
    public void addRout(Stage stage ,String StageName){
        stageMap.put(StageName,stage);
    }
    public Map getAllRoutes(){
        return stageMap;
    }
    public Stage getRoute(String StageName){
        return stageMap.get(StageName);
    }



}
