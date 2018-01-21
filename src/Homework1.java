package homework1;
import java.util.*;
import java.util.Stack;
import java.util.Scanner;
import javafx.scene.Node;
import javax.swing.JTree;

public class Homework1{
        public static Stack<Character> stack = new Stack<Character>();
	public static void main(String[] args) {
		// Begin of arguments input sample
		if (args.length > 0) {
			String input = args[0];
			if (input.equalsIgnoreCase("251-*32*+")) {
				System.out.println("(2*(5-1))+(3*2)=14");
			}
		}
		// End of arguments input sample
		
		// TODO: Implement your project here
	}
       public static class Node {
            char value;
            Node left,right;
    
            Node (char item){
                value = item;
                left = right = null;
        
    }
}
       public static void inflix(Node n){
           if(n.value=='+'||n.value=='-'||n.value=='*'||n.value=='/'){
               n.right =new Node(stack.pop());
               inflix(n.right);
               n.left =new Node(stack.pop());
               inflix(n.left);
               
           }
	
       }
        public boolean isOperator(char op){
                switch(op){
                        case '+':
                                return true;
                        case '-':
                                return true;
                        case '/':
                                return true;
                        case '*':
                                return true;
                        case '^':
                                return true;
                        default:
                                return false;
                }
        }
        public static void inorder(Node n) {
            
                    if (n != null) {
                            inorder(n.left);
                            System.out.print(n.value + " ");
                            inorder(n.right);
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
                    
            }
            return 0;
        
        }
    }
