public class HiLowCount implements Counter
{
  private final static String[] rank = {"2", "3", "4", "5", "6", "7", 
    "8", "9","10","Jack","Queen","King","Ace"};
  private final static int[] count = {1, 1, 1, 1, 1, 0, 0, 0, -1, -1, -1, -1, -1};
  
  private int getIndex(String r)
  {
    for(int c = 0; c < rank.length; c++)
    {
      if (r.equals(rank[c]))
        return c;
    }
    return -1;
  }
  
  public int getCount(String r)
  {
    return count[getIndex(r)];
  }
}