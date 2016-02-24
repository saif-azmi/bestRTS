package entity;

import core.GameRunTime;
import graph.GraphNode;
import javafx.scene.input.MouseEvent;
import sceneElements.SpriteImage;

/**
 * @author : Anh
 * @project : bestRTS
 * @date : 24/02/16
 */
public class Base extends Entity {

    public Base(int id, String name, GraphNode position, SpriteImage sprite) {
        super(id, name, position, sprite);
    }

    @Override
    public void update() { }
}