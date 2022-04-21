/**
 * @author: Chirag Huria
 * Course Name: Data Structures and Algorithms (95-771)
 * Project: Project 1
 */

package edu.colorado.nodes;

public class ObjectNodeDriver {
    public static void main(String args[]){

        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        char alphaArray[] = alphabets.toCharArray();

        ObjectNode alphabetList = new ObjectNode(alphaArray[0],null);

        for(int i=25; i>0; i--){
            alphabetList.addNodeAfter(alphaArray[i]);
        }

        // display strings
        System.out.println(alphabetList.toString());
        System.out.println(alphabetList.displayEveryThird());

        // display length of list (non-recursive and recursive)
        System.out.println("Number of nodes = " + ObjectNode.listLength(alphabetList));
        System.out.println("Number of nodes = " + ObjectNode.listLength_rec(alphabetList));

        // list copy (non-recursive)
        ObjectNode k = ObjectNode.listCopy(alphabetList);
        System.out.println(k.toString());

        // display length of list (non-recursive and recursive)
        System.out.println("Number of nodes in k = " + ObjectNode.listLength(k));
        System.out.println("Number of nodes in k = " + ObjectNode.listLength_rec(k));


        // list copy (recursive)
        ObjectNode k2 = ObjectNode.listCopy(alphabetList);
        System.out.println(k2.toString());

        // display length of list (non-recursive and recursive)
        System.out.println("Number of nodes in k2 = " + ObjectNode.listLength(k2));
        System.out.println("Number of nodes in k2 = " + ObjectNode.listLength_rec(k2));


    }
}
