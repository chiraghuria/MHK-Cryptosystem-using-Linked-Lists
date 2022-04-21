package edu.cmu.andrew.churia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class MerkleHellmanKnapsackCryptosystem {

    private SinglyLinkedList w = null;
    private SinglyLinkedList b = null;
    private BigInteger q,r;

    public MerkleHellmanKnapsackCryptosystem(){
        w = new SinglyLinkedList();
        b = new SinglyLinkedList();
    }

    public SinglyLinkedList[] keyGeneration(int n){

        // superincreasing sequence
        // w = (7^1, 7^2, ... 7^640)
        for(int i=1;i<=n; i++){
            BigInteger base = new BigInteger("7");
            BigInteger nodeVal = base.pow(i);
            w.addAtEndNode(nodeVal);
        }

        // q > sum(w[i])
        int randInt = ThreadLocalRandom.current().nextInt(200000, 400000);
        BigInteger randBigInt = new BigInteger(String.valueOf(randInt));
        //System.out.println("RandBigInt: " + randBigInt);
        q = w.listSum().add(randBigInt);
        //System.out.println("q: " + q);

        // r where r and q are co-prime
        // [Citation Reading]: https://www.quora.com/Why-are-n-and-n+1-always-coprimes-Is-this-magic
        r = q.subtract(BigInteger.ONE);
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

        SinglyLinkedList[] keys = new SinglyLinkedList[2];
        // private key
        keys[0] = w;
        keys[1] = b;

        return keys;
    }

    public char[] userInputStringToBinary(String userInput) {
        //[Citation] https://stackoverflow.com/questions/4416954/how-to-convert-a-string-to-a-stream-of-bits-in-java/4417069
        // First line (String bits = new BigInteger...) copied to convert String to Bits
        String bits = new BigInteger(userInput.getBytes()).toString(2);
        if(bits.length() < userInput.length()*8){
            int delta = userInput.length()*8 - bits.length();
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<delta;i++){
                sb.append(0);
            }
            bits = sb.append(bits).toString();
        }
        char[] binary = bits.toCharArray();
        return binary;
    }

    public BigInteger encryption(char[] binary){

        BigInteger cipher = BigInteger.ZERO;
        int m = binary.length;
        for(int i=0; i<m; i++){
            BigInteger mi = new BigInteger(String.valueOf(binary[i]));
            BigInteger bi = new BigInteger(b.getObjectAt(i).toString());
            // sum(mi*bi) from i = 1 to m
            BigInteger multiply = mi.multiply(bi);
            cipher = cipher.add(multiply);
        }

        return cipher;
    }

    public char[] decryption(BigInteger cipher){
        BigInteger rPrime = r.modInverse(q);
        BigInteger cipherRPrime = cipher.multiply(rPrime);
        BigInteger cipherPrime = cipherRPrime.mod(q);

        ArrayList<Integer> x = new ArrayList<Integer>();

        while(cipherPrime.compareTo(BigInteger.ZERO) > 0){
            BigInteger maxNode = BigInteger.ZERO;
            int maxIndex = -1;
            for(int i=0; i<w.countNodes(); i++){
                BigInteger currNode = (BigInteger) w.getObjectAt(i);
                if(currNode.compareTo(cipherPrime) <= 0){
                    maxNode = currNode;
                    maxIndex = i;
                }
            }
            cipherPrime = cipherPrime.subtract(maxNode);
            x.add(maxIndex);
        }

        // generating decrypted binary
        char[] decryptBinary = new char[w.countNodes()];
        Arrays.fill(decryptBinary,'0');

        for(int index : x){
            decryptBinary[index] = '1';
        }

        return decryptBinary;

    }

    public String decryptedBinaryToUserInputString(char[] decryptBinary){
        //[Citation - Readings] https://www.tutorialspoint.com/java/math/biginteger_tostring_radix.htm
        //[Citation - Readings] https://www.geeksforgeeks.org/biginteger-tostring-method-in-java/
        //[Citation - Readings] https://stackoverflow.com/questions/1536054/how-to-convert-byte-array-to-string-and-vice-versa

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decryptBinary.length; i++) {
            sb.append(decryptBinary[i]);
        }
        BigInteger temp = new BigInteger(sb.toString(),2);
        String decryptedString = new String(temp.toByteArray());

        return decryptedString;

    }


}
