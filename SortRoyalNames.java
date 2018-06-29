import java.util.*;
  
public class SortRoyalNames
{
  public static void main(String[] args)
  {
    //int num = romanToInt("IX");
    //System.out.println(num);
    String[] names = {"Albert II","Polo IV","Alexander V","Elizabeth XXV",
        "Albert XL","Polo XLVI","William IX","Edward XXXIX",
        "Elizabeth XIX","Albert III"};

    String[] sortedNames = sortNames(names);
    System.out.println(Arrays.toString(sortedNames));
    
  }
  
  private static String[] sortNames(String[] names)
  {
    String[] sorted = new String[names.length];
    TreeMap<String, TreeMap<Integer, Integer>> map = new TreeMap<>(); //Key: name, Value: Map:(num, index)
    
    for (int i = 0; i < names.length; i++)
    {
      String name = names[i].split(" ")[0];
      String romanNum = names[i].split(" ")[1];
      int num = romanToInt(romanNum);
      
      if (map.containsKey(name))
      {
        map.get(name).put(num,i);
      }
      else
      {
        TreeMap<Integer, Integer> newName = new TreeMap<>();
        newName.put(num, i);
        map.put(name, newName);
      }
    }
    
    int count = 0;
    
    for (String entry : map.keySet())
    {
      for (Integer nums : map.get(entry).keySet())
      {
        sorted[count] = names[map.get(entry).get(nums)];
        count++;
      }
    }
    
    return sorted;
  }
  
  private static int romanToInt(String num)
  {
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1); map.put('V', 5); map.put('X', 10); map.put('L', 50); map.put('C', 100);                                         
    int result = 0;
    int curr = 0;
    int prev = 0;
    
    for (int i = num.length() - 1; i >= 0; i--)
    {
      curr = map.get(num.charAt(i));
      if (curr >= prev)
        result += curr;
      else
        result -= curr;
      prev = curr;
    }
    
    return result;
  }
}
