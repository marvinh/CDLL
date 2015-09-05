/**Marvin Harootoonyan Project 1 Comp. 282 Class#19974 Thursdays*/
public class CDLL<T>{
    private Node head;
    private int numberOfEntries;
    public CDLL(){
        head=null;
        numberOfEntries=0;
    }
    public boolean insert(int index,T item){
        Node newNode = new Node(item);
        Node walker = head;
        if(index>-1&&index<=numberOfEntries){
            if(head==null){
                head=newNode;
                head.setNext(head);
                head.setPrev(head);
                numberOfEntries++;
                return true;
            }else if(index == 0){
                newNode.setPrev(head.getPrev());
                head.getPrev().setNext(newNode);
                newNode.setNext(head);
                head.setPrev(newNode);
                head=newNode;
            }else{
                for(int i = 0; i < index;i++){
                    walker=walker.getNext();
                }
                walker.getPrev().setNext(newNode);
                newNode.setPrev(walker.getPrev());
                newNode.setNext(walker);
                walker.setPrev(newNode);
            }
            numberOfEntries++;
            return true;
        }
        return false;
    }
    public boolean recursiveInsert(int index,T item)
    {
        if(index>-1&&index<=numberOfEntries){
            head = insert(index,head,item);
            numberOfEntries++;
            return true;
        }else{
            return false;
        }
    }
    private Node insert(int index,Node currentNode,T item){
        Node newNode = new Node(item);
        if(index==0){
            currentNode = new Node(item);
            currentNode.setNext(currentNode);
            currentNode.setPrev(currentNode);
        }else if(index>0){
            Node after = insert(index-1,currentNode.getNext(),item);
            currentNode.setNext(after);
            currentNode.setPrev(after.getPrev());
            after.setPrev(currentNode);
            
        }
        return currentNode;
    }
    public T remove(int index){
        Node walker = head;
        T item = null;
        if(index>-1&&index<=numberOfEntries&&head!=null){
            if(index==0){
                item = head.getData();
                head=head.getNext();
            }else{
                for(int i = 0; i < index;i++){
                    walker=walker.getNext();
                }
                item  = walker.getData();
                walker.getPrev().setNext(walker.getNext());
            }
            numberOfEntries--;
            return item;
        }
        return null;
    }
    /**get iteratively*/
    public T get(int index)
    {
        int i = 0;
        Node walker = head;
        if(index<=numberOfEntries&&index>-1&&head!=null)
        {
            while(i<index)
            {
                walker=walker.getNext();
                i++;
            }
            return walker.getData();
        }
        return null;
    }
    /**get recursively*/
    public T recursiveGet(int index)
    {
        if(index<=numberOfEntries&&index>-1&&head!=null)
        {
            return get(index,head).getData();
        }
        return null;
    }
    private Node get(int index,Node currentNode)
    {
        if(index>0)
        {
            currentNode = get(index-1,currentNode.getNext());
        }
        return currentNode;
    }
    /**recursive remove*/
    public T recursiveRemove(int index)
    {
        T item =null;
        if(index<=numberOfEntries&&index>-1&&head!=null){
            item = get(index);
            head=remove(index,head);
            numberOfEntries--;
            return item;
        }
        return null;
    }
    private Node remove(int index, Node currentNode){
        if(index==0){
            currentNode=currentNode.getNext();
        }else if(index>0){
            remove(index-1,currentNode.getNext());
        }
        return currentNode;
    }
    public void print(){
        int i = numberOfEntries;
        Node walker = head;
        while(i>0){
            System.out.print("["+walker.getData()+"]<=>");
            i--;
            walker=walker.getNext();
        }
        System.out.print("<=>");
    }
    public static class TIMER{
        private long start;
        private long stop;
        public TIMER(){
            start=stop=0;
        }
        public void Start(){
            start=System.nanoTime();
        }
        public void Stop(){
            stop=System.nanoTime();
            System.out.println(" "+(stop-start)*1e-6+" ms ");
        }
    }
    public static void main(String [] args){
        System.out.println("Project 1 Circular Doubly Linked List.");
        CDLL<Integer> list = new CDLL<Integer>();
        TIMER t = new TIMER();
        try{
            System.out.println("Iteratively:");
            System.out.println();
            System.out.print("Insert 1000: ");
            t.Start();
            for(int i = 0; i < 1000; i++){
                list.insert(i, i);
            }
            t.Stop();
            t.Start();
            System.out.print("Get "+list.get(999) + "th index:");
            t.Stop();
            t.Start();
            System.out.print("Remove "+list.remove(999)+ "th index:");
            t.Stop();
            System.out.println();
            System.out.print("Insert 10,000: ");
            t.Start();
            for(int i = 0; i < 10000; i++){
                list.insert(i, i);
            }
            t.Stop();
            t.Start();
            System.out.print("Get "+list.get(9999)+ "th index:");
            t.Stop();
            t.Start();
            System.out.print("Remove "+list.remove(9999)+ "th index:");
            t.Stop();
            
            System.out.println("Insert 100,000: ");
            t.Start();
            for(int i = 0; i < 100000; i++){
                list.insert(i, i);
            }
            t.Stop();
            t.Start();
            System.out.println("Get "+list.get(99999)+ "th index:");
            t.Stop();
            t.Start();
            System.out.print("Remove "+list.remove(99999)+ "th index:");
            t.Stop();
            System.out.println();
            System.out.println("Recursively:");
            System.out.println();
            System.out.print("Insert 1000: ");
            t.Start();
            for(int i = 0; i < 1000; i++){
                list.recursiveInsert(i, i);
            }
            t.Stop();
            t.Start();
            System.out.print("Get"+list.recursiveGet(999)+ "th index:");
            t.Stop();
            t.Start();
            System.out.print("Remove"+list.recursiveRemove(999)+ "th index:");
            t.Stop();
            System.out.println();
            System.out.print("Insert 10,000: ");
            t.Start();
            for(int i = 0; i < 10000; i++){
                list.recursiveInsert(i, i);
            }
            t.Stop();
            t.Start();
            System.out.print("Get "+list.recursiveGet(9999)+ "th index:");
            t.Stop();
            t.Start();
            System.out.print("Remove "+list.recursiveRemove(9999)+ "th index:");
            t.Stop();
            System.out.println();
            System.out.print("Insert 100,000: ");
            t.Start();
            for(int i = 0; i < 100000; i++){
                list.recursiveInsert(i, i);
            }
            t.Stop();
            t.Start();
            System.out.print("Get "+list.recursiveGet(99999)+ "th index:");
            t.Stop();
            t.Start();
            System.out.print("Remove "+list.recursiveRemove(99999)+ "th index:");
            t.Stop();
        }catch(StackOverflowError e){
            System.out.println("caught "+e);
        }
    }
    private class Node{
        T data;
        Node next;
        Node prev;
        private Node(T dataPortion){
            setData(dataPortion);
            setNext(null);
            setPrev(null);
        }
        private void setData(T dataPortion){
            data=dataPortion;
        }
        private T getData(){
            return data;
        }
        private void setNext(Node nextNode){
            next=nextNode;
        }
        private Node getNext(){
            return next;
        }
        private void setPrev(Node prevNode){
            prev=prevNode;
        }
        private Node getPrev(){
            return prev;
        }
    }
}
