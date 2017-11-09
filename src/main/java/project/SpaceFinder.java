import java.util.ArrayList;
public class SpaceFinder{
  ArrayList<Integer> redSpaces;
  ArrayList<Integer> yellowSpaces;
  ArrayList<Integer> blueSpaces;
  ArrayList<Integer> greenSpaces;
  ArrayList<Integer> orangeSpaces;
  int cake;
  int candyCorn;
  int chocolate;
  int lollipop;
  int wrapped;
  int grandma;

  public SpaceFinder(){
    redSpaces = new ArrayList<Integer>();
    yellowSpaces = new ArrayList<Integer>();
    blueSpaces = new ArrayList<Integer>();
    greenSpaces = new ArrayList<Integer>();
    orangeSpaces = new ArrayList<Integer>();
    cake=-1;
    candyCorn=-1;
    chocolate=-1;
    lollipop=-1;
    wrapped=-1;
    grandma=-1;
  }

  public int findSpace(int space, int card){
    int temp;
    int ret=-1;
    switch(card){
      case 0:
        ret = getNextRed(space);
        break;
      case 1:
        ret = getNextYellow(space);
        break;
      case 2:
        ret = getNextBlue(space);
        break;
      case 3:
        ret = getNextGreen(space);
        break;
      case 4:
        ret = getNextOrange(space);
        break;
      case 5:
        temp = getNextRed(space);
        ret = getNextRed(temp);
        break;
      case 6:
        temp = getNextYellow(space);
        ret = getNextYellow(temp);
        break;
      case 7:
        temp = getNextBlue(space);
        ret = getNextBlue(temp);
        break;
      case 8:
        temp = getNextGreen(space);
        ret = getNextGreen(temp);
        break;
      case 9:
        temp = getNextOrange(space);
        ret = getNextOrange(temp);
        break;
      case 10:
        ret = space;
        break;
      case 11:
        ret = cake;
        break;
      case 12:
        ret = candyCorn;
        break;
      case 13:
        ret = chocolate;
        break;
      case 14:
        ret = lollipop;
        break;
      case 15:
        ret = wrapped;
        break;
    }
    return ret;
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

  public void setCake(int space){
    cake=space;
  }

  public void setCandyCorn(int space){
    candyCorn=space;
  }

  public void setChocolate(int space){
    chocolate=space;
  }

  public void setLollipop(int space){
    lollipop=space;
  }

  public void setWrapped(int space){
    wrapped=space;
  }

  public void setGrandma(int space){
    grandma=space;
  }



  public int getNextRed(int curSpace){
    for(int i = 0; i < redSpaces.size(); i++){
      if(redSpaces.get(i) > curSpace) return redSpaces.get(i);
    }
    return grandma;
  }

  public int getNextYellow(int curSpace){
    for(int i = 0; i < yellowSpaces.size(); i++){
      if(yellowSpaces.get(i) > curSpace) return yellowSpaces.get(i);
    }
    return grandma;
  }

  public int getNextBlue(int curSpace){
    for(int i = 0; i < blueSpaces.size(); i++){
      if(blueSpaces.get(i) > curSpace) return blueSpaces.get(i);
    }
    return grandma;
  }

  public int getNextGreen(int curSpace){
    for(int i = 0; i < greenSpaces.size(); i++){
      if(greenSpaces.get(i) > curSpace) return greenSpaces.get(i);
    }
    return grandma;
  }

  public int getNextOrange(int curSpace){
    for(int i = 0; i < orangeSpaces.size(); i++){
      if(orangeSpaces.get(i) > curSpace) return orangeSpaces.get(i);
    }
    return grandma;
  }
}
