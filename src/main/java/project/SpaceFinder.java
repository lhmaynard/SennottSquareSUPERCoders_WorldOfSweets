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

    cake= 3;
    wrapped= 15;
    chocolate= 26;
    lollipop= 37;
    candyCorn= 47;
    grandma=60;
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
        ret = candyCorn;
        break;
      case 12:
        ret = lollipop;
        break;
      case 13:
        ret = wrapped;
        break;
      case 14:
        ret = chocolate;
        break;
      case 15:
        ret = cake;
        break;
    }
    return ret;

  }

  public int findBoomerangSpace(int card, int space){

    int temp;
    int ret=-1;
    switch(card){
      case 0:
        ret = getPrevRed(space);
        break;
      case 1:
        ret = getPrevYellow(space);
        break;
      case 2:
        ret = getPrevBlue(space);
        break;
      case 3:
        ret = getPrevGreen(space);
        break;
      case 4:
        ret = getPrevOrange(space);
        break;
      case 5:
        temp = getPrevRed(space);
        ret = getPrevRed(temp);
        break;
      case 6:
        temp = getPrevYellow(space);
        ret = getPrevYellow(temp);
        break;
      case 7:
        temp = getPrevBlue(space);
        ret = getPrevBlue(temp);
        break;
      case 8:
        temp = getPrevGreen(space);
        ret = getPrevGreen(temp);
        break;
      case 9:
        temp = getPrevOrange(space);
        ret = getPrevOrange(temp);
        break;
      case 10:
        ret = space;
        break;
      case 11:
        ret = candyCorn;
        break;
      case 12:
        ret = lollipop;
        break;
      case 13:
        ret = wrapped;
        break;
      case 14:
        ret = chocolate;
        break;
      case 15:
        ret = cake;
        break;
    }
    return ret;

  }



  public int findDadCard(int space, Deck deck){

//need to do check for skip even if not candy candyCards
//check behind
//check skips
//check candy next
//check colors

    if(space > cake)
      if(deck.contains(15)) return 15;
    if(space > wrapped)
      if(deck.contains(13)) return 13;
    if(space > chocolate)
      if(deck.contains(14)) return 14;
    if(space > lollipop)
      if(deck.contains(12)) return 12;
    if(space > candyCorn)
      if(deck.contains(11)) return 11;


    if(deck.contains(10)) return 10;


    int count=0;
    int checkTarget = space + 1;
    int checkCard = 0;
    while(checkTarget != grandma){
      if(checkTarget == cake)
        if(deck.contains(15)) return 15;

      if(checkTarget == wrapped)
        if(deck.contains(13)) return 13;

      if(checkTarget == chocolate)
        if(deck.contains(14)) return 14;

      if(checkTarget == lollipop)
        if(deck.contains(12)) return 12;

      if(checkTarget == candyCorn)
        if(deck.contains(11)) return 11;

      checkCard = 0;
      if(count > 4) checkCard = 5;

      if(redSpaces.contains(checkTarget) && deck.contains(checkCard)) return checkCard;
      checkCard++;
      if(yellowSpaces.contains(checkTarget) && deck.contains(checkCard)) return checkCard;
      checkCard++;
      if(blueSpaces.contains(checkTarget) && deck.contains(checkCard)) return checkCard;
      checkCard++;
      if(greenSpaces.contains(checkTarget) && deck.contains(checkCard)) return checkCard;
      checkCard++;
      if(orangeSpaces.contains(checkTarget) && deck.contains(checkCard)) return checkCard;

      checkTarget++;
      count++;
    }
    return -1;

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


  public int getPrevRed(int curSpace){
    for(int i = redSpaces.size() -1; i >= 0; i--){
      if(redSpaces.get(i) < curSpace) return redSpaces.get(i);
    }
    return 0;
  }

  public int getPrevYellow(int curSpace){
    for(int i = redSpaces.size() -1; i >= 0; i--){
      if(yellowSpaces.get(i) < curSpace) return yellowSpaces.get(i);
    }
    return 0;
  }

  public int getPrevBlue(int curSpace){
    for(int i = redSpaces.size() -1; i >= 0; i--){
      if(blueSpaces.get(i) < curSpace) return blueSpaces.get(i);
    }
    return 0;
  }

  public int getPrevGreen(int curSpace){
    for(int i = redSpaces.size() -1; i >= 0; i--){
      if(greenSpaces.get(i) < curSpace) return greenSpaces.get(i);
    }
    return 0;
  }

  public int getPrevOrange(int curSpace){
    for(int i = redSpaces.size() -1; i >= 0; i--){
      if(orangeSpaces.get(i) < curSpace) return orangeSpaces.get(i);
    }
    return 0;
  }


}
