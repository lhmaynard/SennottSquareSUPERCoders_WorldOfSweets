/**
 * SpaceFinder.java
 * SennottSquareSUPERCoders
 *
 * Leonard Maynard	git- lhmaynard
 * Zachary Mell		git- zacharymell
 * Kevin Moore		git- KMoore21
 * Brandon Palonis	git- brandonp728
 *
 * World of Sweets Project
 */
package project;
import java.util.ArrayList;
 
public class SpaceFinder{
  ArrayList<Integer> redSpaces;
  ArrayList<Integer> yellowSpaces;
  ArrayList<Integer> blueSpaces;
  ArrayList<Integer> greenSpaces;
  ArrayList<Integer> orangeSpaces;

  public SpaceFinder(){
    redSpaces = new ArrayList<Integer>();
    yellowSpaces = new ArrayList<Integer>();
    blueSpaces = new ArrayList<Integer>();
    greenSpaces = new ArrayList<Integer>();
    orangeSpaces = new ArrayList<Integer>();
  }

  public void addRed(int space){
    redSpaces.add(space);
  }

  public void addYellow(int space){
    yellowSpaces.add(space);
  }

  public void addBlue(int space){
    blueSpaces.add(space);
  }

  public void addGreen(int space){
    greenSpaces.add(space);
  }

  public void addOrange(int space){
    orangeSpaces.add(space);
  }

  public int getNextRed(int curSpace){
    for(int i = 0; i < redSpaces.size(); i++){
      if(redSpaces.get(i) > curSpace) return redSpaces.get(i);
    }
    return -1; //replace with grandmas house
  }

  public int getNextYellow(int curSpace){
    for(int i = 0; i < yellowSpaces.size(); i++){
      if(yellowSpaces.get(i) > curSpace) return yellowSpaces.get(i);
    }
    return -1; //replace with grandmas house
  }

  public int getNextBlue(int curSpace){
    for(int i = 0; i < blueSpaces.size(); i++){
      if(blueSpaces.get(i) > curSpace) return blueSpaces.get(i);
    }
    return -1; //replace with grandmas house
  }

  public int getNextGreen(int curSpace){
    for(int i = 0; i < greenSpaces.size(); i++){
      if(greenSpaces.get(i) > curSpace) return greenSpaces.get(i);
    }
    return -1; //replace with grandmas house
  }

  public int getNextOrange(int curSpace){
    for(int i = 0; i < orangeSpaces.size(); i++){
      if(orangeSpaces.get(i) > curSpace) return orangeSpaces.get(i);
    }
    return -1; //replace with grandmas house
  }
}
