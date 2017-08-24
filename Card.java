public class Card
{
  private String rank;
  private String suit;
  private int points;
  
  public Card(String r, String s, int p)
  {
    rank = r;
    suit = s;
    points = p;
  }
  
  public String getSuit() { return suit; }
  
  public String getRank() { return rank; }
  
  public int getPoints() { return points; }
  
  
  public String toString()
  {
    return rank+" of "+suit;
  }
  
  
}