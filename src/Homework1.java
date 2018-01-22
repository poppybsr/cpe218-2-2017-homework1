package homework1;
import java.util.*;
import java.util.Stack;
import java.util.Scanner;
import javafx.scene.Node;
import javax.swing.JTree;

public class Homework1{
    public static Node tree;
    public static Stack<Character> stack = new Stack<Character>();
    public static int i;
    public static void main(String[] args) {
		String example = "251-*32*+";
                
                while( i<example.length())
                 {
                     stack.push(example.charAt(i));
                     i++;
                 }
                 tree = new Node(stack.pop());
                 infix(tree);
                 inorder(tree);
                 System.out.println(" = "+calculator(tree));
		// End of arguments input sample
		
		// TODO: Implement your project here
	}
    public static class Node {
            Character value;
            Node left,right;
    
            Node (char item){
                value = item;
        
    }
}

    public static void infix(Node n){
           if(n.value=='+'||n.value=='-'||n.value=='*'||n.value=='/'){
               n.right =new Node(stack.pop());
               infix(n.right);
               n.left =new Node(stack.pop());
               infix(n.left);
               
           }
	
       }
    public static void inorder(Node n) {
            if(n!=null)
        {   if(n.left!=null) System.out.print("(");
            inorder(n.left);
            System.out.print(n.value);
            inorder(n.right);  
            if(n.right!=null) System.out.print(")");
        } 
        }
        
        public static int calculator(Node n){

            switch(n.value){
                case'+':
                    return calculator(n.left)+calculator(n.right);
                case'-':
                    return calculator(n.left)-calculator(n.right);
                case'*':
                    return calculator(n.left)*calculator(n.right);
                case'/':
                    return calculator(n.left)/calculator(n.right);
                default:
                    return Integer.parseInt(n.value.toString());
                    
            }
            
            }
    }


    



