package com.seekerr.simulator.robot.env;

/**
 *
 * @author  wkerr
 */
public class WallCollisionInfo {
    
    public int robotId;
    public float[] distances;
    
    /** Creates a new instance of CollisionInfo */
    public WallCollisionInfo(int id, float[] distances) {
        this.robotId = id;
        this.distances = distances;
    }
    
}
