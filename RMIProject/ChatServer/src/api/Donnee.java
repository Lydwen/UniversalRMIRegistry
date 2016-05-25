package api;

import java.io.Serializable;

/**
 * A data class which is Serializable.
 * It forces to implements at least the toString method
 * Created by dallagnol on 23/05/16.
 */
public abstract class Donnee implements Serializable{
    public abstract String toString();
}
