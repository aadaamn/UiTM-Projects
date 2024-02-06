public class Node
{
    Event data;
    Node nextNode;
    // constructor 1
    public Node(Event data){ 
        this.data = data; 
    }
    // constructor 2
    Node(Event o, Node n) {
        data = o;
        nextNode = n;
    }
    // return data
    Event getData() {
        return data;
    }
    // return the next node
    Node getLink() {
    return nextNode;
    }
}
