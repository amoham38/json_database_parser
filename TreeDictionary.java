
//
// Complete this class: 60 points + 20 bonus points
//
// Note:
// Do NOT copy from textbook, or any other sources
// Do NOT copy from code that we did in class (as it contains errors)
//
// Do READ CODE from textbook and in-class code and write it out as your own code
//

//
//TreeDictionary implements the (self-balance) binary search tree as a Dictionary
//
public class TreeDictionary<T extends Comparable<T>>
{
    public static void main(String [] args)
    {
        //Optional: test TreeDictionary here
    }

    /**
     *
     * @param record - record to be inserted into tree
     */

    public void insert(Record<T> record) //20 points + 10 bonus (AVL insertion)
    {
        //insert this records into the tree based on its keywords

        //1. for each keyword in this record, find the node that contains this keyword
        for(T t: record.Keywords) {
            // find keyword in tree
            Node<T> key = find(t);

            // if keyword has been found insert this record into its Dyanmic Array of records
            // otherwise create a new node in tree with this record
            if(key != null) {
                key.records.insert(record);
            } else {
                Node<T> n = new Node<T>();
                n.keyword = t;
                n.records.insert(record);
                this.addNode(n);
            }
        }

        //2. if no such node exists, create a new node and assign the keyword

        //3. insert the record into the node

        //4. repeat until all keywords in the record are processed

        //(bonus: implement AVL insertion that balances the tree)
    }

    /**
     *
     * @param n - node to be added into tree
     */
    private void addNode(Node<T> n) {
        // check if tree is empty, if so set n as root
        if(this.root == null) {
            this.root = n;
            return;
        }

        Node<T> parent = null;
        Node<T> current = root;

        while(current != null) {
            parent = current;
            if(current.keyword.compareTo(n.keyword) <= 0 ) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if(parent.keyword.compareTo(n.keyword) <= 0 ){
            parent.right = n;
            n.parent = parent;
        } else {
            parent.left = n;
            n.parent = parent;
        }
    }

    /**
     *
     * @return LinkedList In order traversal of tree
     */

    private LinkedList<Node<T>> InOrderTraversal() //10 points
    {
        //TODO : store in-order traversal of tree nodes in a linked list
        LinkedList<Node<T>> t = new LinkedList<>();
        InOrderHelper(root, t);
        return t;
    }

    /**
     *
     * @param r - Node to start In Order Traversal from
     * @param t - LinkedList to store in order trav.
     *
     */

    private void InOrderHelper(Node<T> r, LinkedList<Node<T>> t) {

        if(r == null) return;

        InOrderHelper(r.left, t);
        t.insert(r);
        InOrderHelper(r.right, t);
    }

    /**
     *
     * @param r - node to start in order traversal from
     * @param t - Dynamic array to store in order traversal
     */

    private void InOrderHelper(Node<T> r, DynamicArray<Node<T>> t) {
        if(r == null) return;

        InOrderHelper(r.left, t);
        t.insert(r);
        InOrderHelper(r.right, t);

    }

    /**
     *
     * @param keyword - keyword to be removed
     */

    private void remove(T keyword) //10 points + 10 bonux (AVL remove)
    {
        //
        // TODO: use a keyword to remove a node that contains this word
        //
        Node<T> tbr = find(keyword);
        removeHelper(tbr, root);


        //(bonus: implement AVL remove that balances the tree)
    }

    /**
     *
     * @param tbr - node to be removed
     * @param sp - starting point
     */
    private void removeHelper(Node<T> tbr, Node<T> sp) {

        if(tbr == null) return;

        // node does not have any children
        // node has a single child on either side
        // node has two children

        if(tbr.left != null && tbr.right != null) {
            // find in order successor of node to be removed
            DynamicArray<Node<T>> trav = new DynamicArray<>();
            InOrderHelper(tbr.right, trav);

            // copy keyword and records from i.o.s to
            // node to be removed
            Node<T> ios = trav.get(0);
            tbr.keyword = ios.keyword;
            tbr.records = ios.records;

            // remove i.o.s.
            removeHelper(ios, tbr.right);

        } else if(tbr.left == null && tbr.right == null) {
            // figure out which side of parent node, node to be removed is located
            // set parents link to null, set tbr's parent link to null

            if(tbr.parent.left == tbr) {
                tbr.parent.left = null;
            } else if(tbr.parent.right == tbr) {
                tbr.parent.right = null;
            }
            tbr.parent = null;
        } else if(tbr.left != null) {
            tbr.left.parent = tbr.parent;
            if(tbr.parent.left == tbr) tbr.parent.left = tbr.left;
            else if(tbr.parent.right == tbr) tbr.parent.right = tbr.left;
            tbr.parent = null;
        } else if(tbr.right != null) {
            if(tbr == root) {
                root = tbr.right;
                return;
            }
            tbr.right.parent = tbr.parent;
            if(tbr.parent.right == tbr) tbr.parent.right = tbr.right;
            else if(tbr.parent.left == tbr) tbr.parent.left = null;
            tbr.parent = null;

        }

    }

    /**
     *
     * @param keyword - keyword to be found
     * @return
     *
     */

    private Node<T> find(T keyword) //10 points
    {
        //
        // TODO: find a node that contains this keyword
        //
        return findHelper(keyword, root);

    }

    /**
     *
     * @param keyword - keyword to be found
     * @param r - Starting point node
     * @return - Node with desired keyword
     */

    private Node<T> findHelper(T keyword, Node<T> r) {

        if(r == null) {
            return null;
        }
        if(r.keyword.compareTo(keyword) == 0) {
            return r;
        }
        Node<T> left = findHelper(keyword, r.left);
        Node<T> right = findHelper(keyword, r.right);

        if(left != null) {
            return left;
        }
        return right;

    }

    /**
     *
     * @param keywords - linked list of keywords to be found
     * @return - dynamic array of records containing desired keywords
     */
    public DynamicArray<Record<T>> find( LinkedList<T> keywords  ) //10 points
    {
      //
      //TODO: find an array of records that contain the given keywords
      //

      //hint: use find_then_build

        if(keywords.is_empty()) return null;

        TreeDictionary<T> newT = new TreeDictionary<>();

        DynamicArray<Record<T>> recs = new DynamicArray<>();
        DynamicArray<Node<T>> newTNodes = new DynamicArray<>();

        int count = 0;
        for(T t: keywords) {
            count++;
        }

        if(count == 1) {
            for(T t: keywords) {
                Node<T> key = find(t);
                if(key == null) return null;
                return recs=key.records;
            }
        }


        for( T t: keywords) {
            if(newT == null) return null;
            if(newT.root == null) newT = find_then_build(t);
            else newT = newT.find_then_build(t);
        }

        if(newT == null) return null;


        InOrderHelper(newT.root, newTNodes);

        for(Node<T> t: newTNodes) {
            if(recs.size() != 0) {
                for(Record<T> a: t.records) {
                    boolean flg = false;
                    for(Record<T> b: recs) {
                        if (a.Name.equals(b.Name)) {
                            flg = true;
                            break;
                        }
                    }
                    if(!flg) recs.insert(a);
                }
            } else recs = t.records;
        }

        // return node with largest number of records in tree
        //recs = max_recs.records;

        if(newT.root == null) {
            return null;
        } else {
            return recs;
        }

    }

    // ----------------------------------------------------------------------
    //
    // !!! READ but Do NOT Change anything after this line
    //
    // ----------------------------------------------------------------------

    private class Node<T>
    {
        Node(){ records=new DynamicArray<Record<T>>();}
        Node(T k){ keyword=k; records=new DynamicArray<Record<T>>();}

        T keyword; //nodes are ordered by Keywords
        Node<T> parent;
        Node<T> left, right; //children
        DynamicArray<Record<T>> records;
        public String toString(){ return "["+keyword+" ("+records.size()+")]"; }
    }

    private Node<T> root; //root of the tree, can be null

    //build this tree by inserting the records
    public void build( DynamicArray<Record<T>> records )
    {
        for(Record<T> r : records)
        {
          insert(r);
        }

    }

    //find a node that contains the given keyword and then
    //build a tree using the records stored in the found node
    //finally return the tree
    private TreeDictionary<T> find_then_build(T keyword)
    {
        //
        //use keyword to find the node
        Node<T> node = find(keyword);
        if(node==null) return null;

        //
        //build the tree from this node's record
        TreeDictionary<T> newT=new TreeDictionary<T>();
        newT.build(node.records);

        //
        //remove the keyword from the Tree
        newT.remove(keyword);

        //done
        return newT;
    }

    public String toString()
    {
      //list all the keyworkds and number of records for each keyword
      //visit all nodes in In-Order traversal.
      LinkedList<Node<T>> nodes = InOrderTraversal();
      String S="Tree Dictionary: {";
      for(Node<T> node : nodes) S+=node.toString()+", ";
      if(!nodes.is_empty()) S=S.substring(0,S.length()-2);
      S+="}";
      return S;
    }
}
