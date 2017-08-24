public class DeckOfCards
{
  private Card[] deck;
  private int size;
  private int num;
  
  public DeckOfCards(int n)
  {
    num = n;
    int count = 0;
    deck = new Card[52*num];
    
    String[] suits = {"Hearts", "Spades", "Clubs", "Diamonds"};
    String[] ranks = {"2","3","4","5","6","7","8","9","10","Jack","Queen",
      "King","Ace"};
    int[] points = {2, 3, 4, 5, 6, 7, 8, 9, 10,
    10, 10, 10, 11};
    for( int x = 0; x < num; x++)
    {
    for(int s = 0; s < suits.length; s++)
    {
      for(int r = 0; r < ranks.length; r++)
      {
        deck[count] = new Card(ranks[r], suits[s], points[r]);
        count++;
      }
    }
    }
    size = deck.length;
    shuffle();
  }
  
  public void shuffle()
  {
    Card[] random = new Card[52*num];
    
    for (int a = 0; a < random.length; a++)
    {
      random[a] = new Card("a","a",100);
    }
    
    for(int c = 0; c < deck.length; c++)
    {
    Card current = deck[c];
    int index = 0;
    do {
      index = (int)(Math.random()*random.length);
    } while (!(random[index].getSuit().equals("a")));
    random[index] = current;
    }
    size = deck.length;
    deck = random;
  }
  
  public int getSize()
  {
    return size;
  }
  
  public boolean isEmpty()
  {
    return size <= 0;
  }
  
  public Card deal()
  {
    if (!isEmpty())
    {
      Card current = deck[size - 1];
      size--;
      return current;
    }
    else
    {
      return null;
    }
  }
  
  
  
  public String toString()
  {
    String sum = "";
    for(int c = 0;  c < deck.length; c++)
    {
      sum += deck[c]+"\n";
    }
    return sum;
  }
  
}