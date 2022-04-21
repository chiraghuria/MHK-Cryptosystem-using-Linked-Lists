/**
 * @author: Chirag Huria
 * Course Name: Data Structures and Algorithms (95-771)
 * Project: Project 1
 */

package edu.cmu.andrew.churia;
import edu.colorado.nodes.ObjectNode;
import java.math.BigInteger;

public class SinglyLinkedList {

    private ObjectNode head;
    private ObjectNode tail;
    private ObjectNode cursor;

    public SinglyLinkedList(){
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    public void reset(){
        cursor = head;
    }

    public Object next(){
        Object data = cursor.getData();
        cursor = cursor.getLink();
        return data;

    }

    public boolean hasNext(){
        if(cursor!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public void addAtFrontNode(Object c){

        ObjectNode newNode = new ObjectNode(c, head);

        //check for first element of the list
        if(head==null){
            head = newNode;
            tail = newNode;
        }
        else{
            head = newNode;
        }
    }

    public void addAtEndNode(Object c){

        //check for empty list
        if(head==null){
            ObjectNode newNode = new ObjectNode(c, null);
            head = newNode;
            tail = head;
        }
        else{
            ObjectNode newNode = new ObjectNode(c, null);
            tail.setLink(newNode);
            tail = newNode;
        }
    }

    public int countNodes(){
        cursor = head;
        int count = 0;
        while(cursor!=null){
            count++;
            cursor = cursor.getLink();
        }
        return count;
    }

    public Object getObjectAt(int i){

        cursor = head;
        Object data = null;
        // check if the input is a valid position
        if (i < 0 || i > ObjectNode.listLength(head)){
            return null;
        }

        for(int j=0; j<ObjectNode.listLength(head) && cursor!=null; j++){
            if(j==i){
                data = cursor.getData();
            }
            cursor = cursor.getLink();
        }
        return data;
    }

    public Object getLast(){
        return tail.getData();
    }

    public String toString(){
        cursor = head;

        // if list is empty
        if(cursor == null){
            return null;
        }

        StringBuilder resString = new StringBuilder();

        while(cursor!=null){
            resString.append(cursor.getData().toString());
            cursor = cursor.getLink();
            if(cursor!=null){
                resString.append("-->");
            }
            else{
                resString.append("--|");
            }
        }

        return resString.toString();
    }

    public BigInteger listSum(){
        BigInteger sum = new BigInteger("0");
        cursor = head;
        while(cursor!=null){
            sum = sum.add((BigInteger) cursor.getData());
            cursor = cursor.getLink();
        }

        return sum;
    }


}
