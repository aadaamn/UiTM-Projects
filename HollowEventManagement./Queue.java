public class Queue
{
    LinkedList list;
    public Queue()
    {
        list = new LinkedList(); 
    }
    // i) Add data at end of the list
    public void enqueue(Event element)
    {
        list.insertAtBack(element); 
    }
    
    // ii) Removes data at front
    public Event dequeue()
    {
        return list.removeAtFront(); 
    }

    // iii) Determine size
    public int size()
    {
        return list.size(); 
    }
    
    // iv) check wheter empty or not
    public boolean isEmpty(){
        return list.isEmpty(); 
    }
    
    // check front of the list
    public Event getFront()
    {
        return list.getFirst();
    }

}
