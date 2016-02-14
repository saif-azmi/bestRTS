package core;

import entity.Unit;
import graph.Graph;
import graph.GraphNode;
import sceneElements.SpriteImage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hung on 13/02/16.
 */
public class UnitSpawner {

    private ArrayList<Unit> unitPool;
    private int unitSpawnedCount = 0;
    private int totalSpawnables = 10;
    private GameRunTime runTime;
    Random rndSearchGen;

    private String[] names;
    private String[] descriptions;

    public UnitSpawner(GameRunTime runTime)
    {
        names = new String[] {"Banshee", "Demon", "Death knight"};
        descriptions = new String[] {"Depth First Search", "Breadth First Search", "A* Search", "Selection Sort", "Insertion Sort", "Bubble Sort"};
        rndSearchGen = new Random(System.currentTimeMillis());
        unitPool = new ArrayList<>();

        this.runTime = runTime;

        Graph graph = runTime.getEngine().getGraph();
        Renderer renderer = runTime.getRenderer();
        // this should be passed in
        GraphNode goal = graph.getNodes().get(31);

        for (unitSpawnedCount = 0; unitSpawnedCount < totalSpawnables; unitSpawnedCount++)
        {
            CreateUnit(graph, renderer, goal);
            //unit end//
            /*runTime.getEngine().getEntities().add(unit);
            runTime.getRenderer().drawEntity(unit);
            unit.setCurrentPixel(sprite.getX(), sprite.getY());*/
        }
    }

    private Unit CreateUnit(Graph graph, Renderer renderer, GraphNode goal) {

        SpriteImage sprite = new SpriteImage("http://imgur.com/FAt5VBo.png", null);
        sprite.setOnMouseClicked(e ->
        {
            sprite.requestFocus();
        });

        // doing random for now, could return sequence of numbers representing units wanted
        int index = rndSearchGen.nextInt(3);

        Unit unit = new Unit(unitSpawnedCount, names[index], graph.nodeWith(new GraphNode(0, 10)), sprite, Unit.Search.values()[index], Unit.Sort.values()[index], graph, goal ,renderer);
        sprite.setEntity(unit);
        unit.setCurrentPixel(sprite.getX(),sprite.getY());
        unitPool.add(unit);

        return unit;
    }

    public void spawnUnit()
    {
        Unit newUnit;

        if (unitPool.size() > 0)
            newUnit = unitPool.remove(0);
        else
        {
            newUnit = CreateUnit(runTime.getEngine().getGraph(),runTime.getRenderer(), runTime.getEngine().getGraph().getNodes().get(31));
        }

        runTime.getEngine().getEntities().add(newUnit);
        runTime.getRenderer().drawEntity(newUnit);
    }

    public void despawnUnit(Unit unit)
    {
        unitPool.add(unit);
        //remove from list here?
    }
}
