package project;

public class Player {
  private int playerNumber;
  private String playerName;
  private int lastCard;
  private int currentSpace;
  private String playerToken;
  private Boolean grandmasHouse;

  public Player(int num, String nam, String tok)
  {
    playerName = nam;
    playerNumber = num;
    playerToken = tok;
    grandmasHouse=false;
  }

  //Getters
  public String getPlayerName()
  {
    return playerName;
  }

  public int getPlayerNumber()
  {
    return playerNumber;
  }

  public int getLastCard()
  {
    return lastCard;
  }

  public int getCurrentSpace()
  {
    return currentSpace;
  }

  public String getToken()
  {
    return playerToken;
  }

  public Boolean getGrandmasHouse()
  {
    return grandmasHouse;
  }

  //Setters
  public void setPlayerName(String nam)
  {
    playerName = nam;
  }

  public void setPlayerNumber(int num)
  {
    playerNumber = num;
  }

  public void setLastCard(int card)
  {
    lastCard = card;
  }

  public void setCurrentSpace(int space)
  {
    currentSpace = space;
  }

  public void setPlayerToken(String tok)
  {
    playerToken = tok;
  }

  public void setGrandmasHouse(Boolean g)
  {
    grandmasHouse = g;
  }

  public String toString()
  {
    String str;
    str = ("Player " + playerNumber + ": " + playerName + " "+ playerToken);
    return str;
  }
}
