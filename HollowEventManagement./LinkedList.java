public class LinkedList
{
    Node first, last, current;
    String name;
    // Constructors
    public LinkedList(String s)
    {
        name = s;
        first = last = current = null;
    }
    public LinkedList()
    {
        this("linked list");
    }

    // i) Inserting at front
    public synchronized void insertAtFront (Event insertItem)
    {
        if(isEmpty())
            first = last = new Node(insertItem);
        else
            first = new Node(insertItem, first);
    }
 
    // Inserting at back
    public synchronized void insertAtBack(Event insertItem)
    {
        if (isEmpty())
            first = last = new Node(insertItem);
        else
            last = last.nextNode = new Node(insertItem);
    }

    // ii) Removing Node
    // Remove from front
    public synchronized Event removeAtFront() throws EmptyListException
    {
        Event removeItem = null;
        if (isEmpty())
            throw new EmptyListException(name);

        removeItem = first.data; //retrieve the data

        //insert the first and last references
        if (first.equals(last))
            first = last = null;
        else
            first = first.nextNode;
        return removeItem;
    }

    // Remove based on eventID
    public synchronized Event removeEvent(int eventID) throws EmptyListException {
        if (first == null) {
            throw new EmptyListException(name);
        }
    
        Node current = first;
        Node previous = null;
    
        while (current != null) {
            if (current.data.getEventID() == eventID) {
                if (previous == null) {
                    first = current.nextNode;
                } 
                else {
                    previous.nextNode = current.nextNode;
                }
                return current.data; 
            }
            previous = current;
            current = current.nextNode;
        }
    
        return null; // Event not found
    }
    


    // iii) GetHead() aka getFirst() - first node (data)
    public synchronized Event getFirst()
    {
        if(isEmpty())
            return null;
        else
        {
            current = first;
            return current.data;
        }
    }
    // getNext() - nextNode data
    public Event getNext()
    {
        if(current != last)
        {
            current = current.nextNode;
            return current.data;
        }
        else
            return null;
    }

    // iv) returns the size of the list
    public synchronized int size()
    {
        Node current = first; 
        int size = 0;
        while(current != null)
        {
            size += 1;
            current = current.nextNode;
        }
        return size;
    }

    // v) returns true if the list is empty
    public synchronized boolean isEmpty()
    {
        return first == null;
    }

    // vi) Display All Data
    public synchronized void displayAll() {
        Node current = first;
        while (current != null) {
            System.out.println();
            System.out.println((current.data).toString()); 
            current = current.nextNode;
        }
    }


    // Function to organize the data according dates, from upcoming to the most farthest
    public synchronized void sortEventsByDate(LinkedList temp) {
        // copy original linked list, taknak tukar original file
        LinkedList copy = new LinkedList();
        Node current = first;
        while (current != null) {
            copy.insertAtBack(current.data);
            current = current.nextNode;
        } 
        // 1) if list is empty/ or only one element = means already sorted
        if (copy.isEmpty() || copy.first.nextNode == null) 
            return;                    

        // bubble sort! 
        current = copy.first;
        while (current != null) {
            // to compare next element
            Node index = current.nextNode;
            while (index != null) {
                if (copy.compareDates(current.data.getDate(), index.data.getDate()) > 0) {
                    Event a = current.data;
                    current.data = index.data;
                    index.data = a;
                }
                // update element
                index = index.nextNode;
            }
            // update element before index
            current = current.nextNode;
        }
        // when already sorted
        copy.displayAll();
    }   
    // comparing dates (to use in sorting thingy)
    private int compareDates(String dateone, String datetwo) {
        String[] date1 = dateone.split("/");
        String[] date2 = datetwo.split("/");

        int day1 = Integer.parseInt(date1[0]);
        int month1 = Integer.parseInt(date1[1]);
        int year1 = Integer.parseInt(date1[2]);

        int day2 = Integer.parseInt(date2[0]);
        int month2 = Integer.parseInt(date2[1]);
        int year2 = Integer.parseInt(date2[2]);

        if (year1 != year2) 
            return year1 - year2;
        else if (month1 != month2) 
            return month1 - month2;
        else 
            return day1 - day2;
    }

}