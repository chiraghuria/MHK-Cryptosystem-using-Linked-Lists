/**
 * @author: Chirag Huria
 * Course Name: Data Structures and Algorithms (95-771)
 * Project: Project 1
 */

package edu.cmu.andrew.churia;

import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;


public class MerkleHellmanKnapsackCryptosystemDriver {
    public static void main(String args[]){

        MerkleHellmanKnapsackCryptosystem mhkc = new MerkleHellmanKnapsackCryptosystem();
        Scanner keyboard = new Scanner(System.in);
        String userInput;
        int inputLengthBytes;
        boolean validInput = false;
        do{
            System.out.println("Enter a string and I will encrypt it as single large integer:");
            userInput = keyboard.nextLine();
            inputLengthBytes = userInput.toCharArray().length;
            if(inputLengthBytes <= 80){
                validInput = true;
            }
            else{
                System.out.println("The entered string is too long, please try again.\n");
            }

        } while(validInput==false);

        System.out.println("User Input Bytes: " + inputLengthBytes);
        int n = 8*inputLengthBytes;

        //int n = 640;
        //userInput = "Welcome to Data Structures and Algorithms";

        SinglyLinkedList[] keys = mhkc.keyGeneration(n);
        // private key
        SinglyLinkedList w = keys[0];
        // public key
        SinglyLinkedList b = keys[1];

        System.out.println("User Input: " + userInput);
        char[] binary = mhkc.userInputStringToBinary(userInput);
        System.out.println("Binary for User Input \"" + userInput + "\": \n"+ String.valueOf(binary));

        // encryption
        BigInteger cipher = mhkc.encryption(binary);
        System.out.println("Cipher: \n" + cipher);

        // decryption
        char[] decryptBinary = mhkc.decryption(cipher);
        System.out.println("Decrypted Binary: \n" + String.valueOf(decryptBinary));

        String decryptString = mhkc.decryptedBinaryToUserInputString(decryptBinary);
        System.out.println("Decrypted String: \n" + decryptString);






















        //-----------------KEY GENERATION -----------------//
        /*for(int i=1;i<=n; i++){
            BigInteger base = new BigInteger("7");
            BigInteger nodeVal = base.pow(i);
            w.addAtEndNode(nodeVal);
        }

        // q > sum(w[i])
        int randInt = ThreadLocalRandom.current().nextInt(200000, 400000);
        BigInteger randBigInt = new BigInteger(String.valueOf(randInt));
        //System.out.println("RandBigInt: " + randBigInt);
        BigInteger q = w.listSum().add(randBigInt);
        //System.out.println("q: " + q);

        // r where r and q are co-prime
        // [Citation Reading]: https://www.quora.com/Why-are-n-and-n+1-always-coprimes-Is-this-magic
        BigInteger r = q.subtract(BigInteger.ONE);
        BigInteger resGcd = r.gcd(q);
        //System.out.println("resGcd: " + resGcd);

        // calculating B where b[i] = r.w[i] mod q
        // b = rw mod q
        BigInteger modResult;

        for(int i=1;i<=n;i++){
            BigInteger wNode = new BigInteger(String.valueOf(w.getObjectAt(i-1)));
            //System.out.println(wNode);
            BigInteger rw = r.multiply(wNode);
            modResult = rw.mod(q);
            b.addAtEndNode(modResult);
        }
        System.out.println(b.getObjectAt(639));
        // public key = b
        // private key = w
*/


        // ------------------SCRAP------------------------------------------------------------------
        // test for constructor, addAtFrontNode and addAtEndNode
        /*SinglyLinkedList s = new SinglyLinkedList();
        s.addAtFrontNode(10);
        s.addAtFrontNode(20);
        s.addAtFrontNode(30);
        s.addAtFrontNode(40);
        s.addAtEndNode(60);

        // test for toString, countNodes, getObjectAt, and getLast
        System.out.println("All Nodes: " + s.toString());
        System.out.println("Count of Nodes: " + s.countNodes());
        System.out.println("Get Object At index 2: " + s.getObjectAt(2));
        System.out.println("Get Last: " + s.getLast());

        // test per question
        s.reset();
        while(s.hasNext()){
            System.out.println(s.next());
        }*/




    }
}
