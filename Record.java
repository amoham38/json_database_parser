//
// !!! Do NOT Change anything in this file
//

public class Record<T>
{
    public Record()
    {
        Keywords=new LinkedList<T>();
    }

    public T Name;
    public LinkedList<T> Keywords;

    public String toString()
    {
      return "["+Name+", "+Keywords+"]";
    }
}
