import java.util.Iterator;
import java.util.Objects;

//
// Complete this class: 15 points
//
// Note:
// Do NOT copy from textbook, or any other sources
// Do NOT copy from code that we did in class (as it contains errors)
//
// Do READ CODE from textbook and in-class code and write it out as your own code
//
public class DynamicArray<T> implements Iterable<T>
{
    private T array[];
    private int size; // number of elements in array
    private int totalSize; // total size of the array
    private int pos; // points to next empty loc

    public static void main(String [] args)
    {
        //Optional: test DynamicArray here
        DynamicArray<Double> A = new DynamicArray<Double>();
        //for(int i=0;i<20;i++) A.insert(Math.random()*100);

        A.insert(1.0);
        A.insert(2.9);
        A.insert(3.2);
        A.insert(34.1);
        A.insert(3.1);

        for(Double v : A)
        {
            System.out.println(v);
        }
        System.out.println("\n");

        A.delete(1);
        for(Double v : A)
        {
            System.out.println(v);
        }

    }

    // Note: You can add any private data and methods here

    @SuppressWarnings("unchecked")
    /**
     * constructor
     */
    DynamicArray() //constructor
    {
        array = (T []) new Object[1];
        size = 0;
        pos = 0;
        totalSize = 1;

    }

    @SuppressWarnings("unchecked")
    /**
     * data - data to be inserted
     */
    public void insert(T data)
    {
        // if array is full double its size.
        if(pos== array.length) {
            T[] temp = (T []) new Object[array.length * 2];
            for(int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            totalSize = totalSize * 2;
            array = temp;
        }
        array[pos] = data;
        pos++;
        size++;

    }

    /**
     *
     * @param index - index to be fetched
     * @return - data within array location
     */

    public T get(int index) //get value by index
    {
      return array[index];
    }

    /**
     *
     * @return - size of the array
     */


    public int size() //size of the dynamic array
    {
      return size;
    }

    /**
     * deletes last value in array
     */

    void delete() //delete the last value in the array
  	{
  	    array[pos-1] = null;
  	    size--;
  	}

  	// shift array to the left at loc

    /**
     *
     * @param loc - location to be deleted
     */
  	void delete(int loc) //delete element at index "loc"
    {
        // location to be removed happens to be last element
        if(loc == array.length-1) {
            delete();
            return;
        }
  	    for(int i = loc; i < array.length-1; i++) {
  	        array[i] = array[i+1];
        }
  	    size--;
  	}

    boolean is_empty(){ return size==0;} //replace true

    public Iterator<T> iterator(){
      return new Iterator<T>() {
          int curr = 0;
          @Override
          public boolean hasNext() {
              return curr < size;
          }

          @Override
          public T next() {
              if(hasNext()) {
                  T data = array[curr];
                  curr++;
                  return data;
              }
              return null;
          }
      };
    }

    //Note: You will need to implement an iterator class using java.util.Iterator
    //      interface

    //TO DO: implement iterator here
}
