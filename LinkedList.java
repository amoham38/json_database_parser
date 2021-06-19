import java.util.Iterator;
import java.util.NoSuchElementException;

//
// Complete this class: 20 points
//
// Note:
// Do NOT copy from textbook, or any other sources
// Do NOT copy from code that we did in class (as it contains errors)
//
// Do READ CODE from textbook and in-class code and write it out as your own code
//
public class LinkedList<T> implements Iterable<T>
{
    public static void main(String [] args)
    {
        //Optional: test LinkedList here
        Double A[]={0.1,0.34,0.7,23.1,-0.75};
        LinkedList<Double> M = new LinkedList<Double>(A);
        System.out.println("Linked List: "+M);

        M.testDelete();
        System.out.println("Linked List after 0.1 delete " +M);


    }

    private void testDelete() {

        this.delete(head.next);
    }

    /**
     *
     * @param A - array to create linked list from
     */
    public LinkedList(T [] A) //create a linked list from an array
    {
        if(A.length == 0) return;

        LinkedList<T> linked = new LinkedList<T>();
        int i = 0;
        while(i < A.length) {
            linked.insert(A[i]);
            i++;
        }
        this.head = linked.head;
        this.tail = linked.tail;
    }

    /**
     *
     * @param data - data to be inserted
     *
     */

  	public void insert(T data) //insert data into the linked list
  	{
  	    Node<T> newNode = new Node<T>(data);
  	    Node<T> currNode = head;

  	    // check if list is empty
        if(head.next.data == null && tail.prev.data == null) {
  	        head.next = newNode; newNode.prev = head; newNode.next = tail;
        } else {
  	        while(currNode.next != tail) currNode = currNode.next;
  	        currNode.next = newNode;
  	        newNode.prev = currNode;

  	        newNode.next = tail;
  	        tail.prev = newNode;
        }


  	}

    /**
     *
     * @param n - node to be removed
     */
    private void delete(Node<T> n) //remove node n from the linked list
    {
        Node<T> currNode = head;
        while(currNode.next != null) {
            if (currNode.data == n.data) {
                currNode.prev.next = currNode.next;
                return;
            }
            currNode = currNode.next;
        }

    }

    /**
     *
     * @return true if empty, false otherwise
     */

    public boolean is_empty() //check if the string is empty
    {
        Node<T> currNode = head;
        while(currNode.next != null) {
            currNode = currNode.next;
            if(currNode.next == tail) return false;
        }
        return true;
    }

    /**
     *
     * @return Iterator object
     */
    public Iterator<T> iterator(){

        return new Iterator<T>() {

            Node<T> currNode = head.next;

            @Override
            public boolean hasNext() {
                return currNode.next != null;
            }

            @Override
            public T next() {
                if(hasNext()) {
                    T data = currNode.data;
                    currNode = currNode.next;
                    return data;
                } else throw new NoSuchElementException();

            }
        };

    }

    //Note: You will need to implement an iterator class using java.util.Iterator
    //      interface

    //TO DO: implement iterator here

    // ----------------------------------------------------------------------
    //
    // !!! READ but Do NOT Change anything after this line
    //
    // ----------------------------------------------------------------------

    private class Node<T>
    {
      Node(){}
      Node(T data){ this.data=data; }
      public T data;
      public Node<T> next;
      public Node<T> prev; //for doubly linked list
    }

    Node<T> head; //pointing to the location BEFORER the first element
    Node<T> tail; //for doubly linked list
                  //pointing to the location AFTER the last element

    public LinkedList() //constructor
    {
      head=new Node<T>();
      tail=new Node<T>();
      head.next=tail;
      tail.prev=head;
    }

    public T last()
    {
      //nothing to return
      if(head.next==tail) return null;
      return tail.prev.data;
    }

    public String toString()
    {
      String S="(";
      for(T t : this) S=S+t+", ";
      if(is_empty()==false) S=S.substring(0,S.length()-2);
      S=S+")";
      return S;
    }
}
