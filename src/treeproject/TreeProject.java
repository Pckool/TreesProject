package treeproject;
import java.io.*;
import java.util.*;
/**
 *
 * @author clesca.philippe
 */
public class TreeProject {
    public static BinarySearchTree tree = new BinarySearchTree();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        Scanner f = new Scanner(new File("C:\\Users\\clesca.philippe\\Documents\\GitHub\\TreesProject\\MORSECODE"));
        tree.add('*');
        while(f.hasNext()){
            String line = f.next();
            String code = f.next();
            Character c = line.charAt(0);
            tree.add(c, 0, code);
        }
        f.close();
        tree.printBinarySearchTree();
        System.out.println("");
        
        Scanner f2 = new Scanner(new File("C:\\Users\\clesca.philippe\\Documents\\GitHub\\TreesProject\\TESTMORSECODE"));
        while(f2.hasNext()){
            String sentence = f2.nextLine();
            System.out.println(sentence);
            System.out.println(decodeSentence(sentence));
            
        }
        f2.close();
        
    }
    public static String decodeSentence(String sentence){
        String result = "";
        // Declare  int  to  determine  the  number  of  words  (use  a  method  to  complete  task)
        int numWrds = countWords(sentence);
        //Declare  a  String  array  to  hold  the  words  to  decode,  size  should  be  the  int  you   declared  to  hold  the  number  of  words
        String[] words = new String[numWrds];
        //Use  a  method  to  separate  the  words  in  the  sentence  and  store  in  array.  
        separateWords(words, sentence);
        
        for(int x = 0; words.length > x; x++){
            int spaces = countSpaces(words[x]);
            String[] codes = new String[spaces];
            separateLetters(codes, words[x]);
            result += decodeWord(codes) + " ";
        }
        
        return result;
    }
    public static String decodeWord(String[] codes){
        String word = "";
        for(int x = 0; codes.length > x; x++){
            word += decodeLetter(codes[x]);
        }
        return word;
    }
    public static Character decodeLetter(String code){
        Character lett = ' ';
        // get  the  root  of  morsecode  tree  and  store  in  a  BinarySearchTreeNode  object
        BinarySearchTreeNode<Character> node = tree.getRoot();
        for(int x = 0; x < code.length(); x++){
            // if  the  charAt  current  spot  of  code  is  a  ‘.’  Then  go  left  (getLeft())  store  in   root  
            if(code.charAt(x) == '.'){
                node = node.getLeft();
            }
            if(code.charAt(x) == '-'){
                node = node.getRight();
            }
        }
        lett = node.getInfo();
        return lett;
    }
    
    public static void separateLetters(String[] letters, String word){
        String letCode = "";
        int count = 0;
        for(int x = 0; word.length() > x; x++){
            // If  word  current  character  is  not  space(‘  ‘)  then  add  character  to  code  
            if(word.charAt(x) != ' '){
                letCode += word.charAt(x);
            }
            // Else  store  code  in  array  (index  counting  variable),  empty  code  string  and   add  to  counting  variable 
            else{
                letters[count] = letCode;
                letCode = "";
                count++;
            }
        }
        letters[count] = letCode;
    }
    
    public static void separateWords(String[] words, String sentence){
        String word = "";
        int count = 0;
        for(int x = 0; sentence.length() > x; x++){
            if(sentence.charAt(x) != '/' ){
                word += sentence.charAt(x);
            }
            // Else  store  word  in  array  (index  counting  variable),  empty  word  string  and   add  to  counting  variable   
            else{
                words[count] = word;
                word = "";
                count++;
            }
        }
        // Store  last  word  in  array  
        words[count] = word;  
    }
    
    
    public static int countSpaces(String phrase) {
        int count = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') count++;
        }
        return count + 1; //add one more to count the last letter 
    }
    public static int countWords(String phrase) {
        int count = 0;
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == '/') count++;
        }
        return count + 1; //add one more for the last word 
    }
}

/*
OUTPUT:

H S V I F U E L R A P W J * B D X N C K Y T Z G Q M O 
.... . .-.. .-.. ---/-.-. .-.. .- ... ...
HELLO CLASS 
-.-. --- -. --. .-. .- - ..- .-.. .- - .. --- -. ...
CONGRATULATIONS 
-.-- --- ..-/.... .- ...- ./... ..- -.-. -.-. . ... ... ..-. ..- .-.. .-.. -.--/-.. . -.-. --- -.. . -../-- --- .-. ... ./-.-. --- -.. .
YOU HAVE SUCCESSFULLY DECODED MORSE CODE 

* 
.--. .... .. .-.. .. .--. .--. ./-.-. .-.. . ... -.-. .-
PHILIPPE CLESCA 
*/