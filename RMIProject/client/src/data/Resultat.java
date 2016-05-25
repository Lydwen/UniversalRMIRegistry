package data;

import api.Donnee;

/**
 * Created by dallagnol on 04/04/16.
 */
public class Resultat extends Donnee{
    private int anInt;

    public Resultat(){
        System.out.println("mais je suis vide");
    }

    public Resultat(int anInt){
        System.out.println("coucou je suis plein");
        this.anInt = anInt;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    @Override
    public String toString() {
        return "hello, I'm a result and this is an Int : " + anInt;
    }
}
