package homework1;
import java.util.*;
import java.util.Stack;
import java.util.Scanner;
import javafx.scene.Node;
import javax.swing.JTree;

public class Homework1{
    public static Node tree;
    public static Stack<Character> stack = new Stack<Character>();
    public static void main(String[] args) {
		// Begin of arguments input sample
		String example = "251-*32*+";
                 for(int i = 0; i < example.length(); i++)
                 {
                     stack.push(example.charAt(i));
                     //System.out.print(EX);
                 }
                 tree = new Node(stack.pop());
                 infix(tree);
                 inorder(tree);
                 System.out.print("="+calculator(tree));
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
    public static boolean isOperator(Node op){
                switch(op.value){
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
            if(n!=null)
        {   //System.out.print("(");
            inorder(n.left);
            System.out.print(n.value);
            inorder(n.right);  
            //System.out.print(")");
        } 
        }
        
        public static int calculator(Node n){
       /* if (n.left == null && n.right == null)
            return 0;
        else
        {
            int result = 0;
            int left = calculator(n.left);
            int right = calculator(n.right);
 
            switch (n.value)
            {
            case '+' : result = left + right; break;
            case '-' : result = left - right; break;
            case '*' : result = left * right; break;
            case '/' : result = left / right; break;
            default : Integer.parseInt(n.value.toString());
            }
            return result;
        }*/
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


    



