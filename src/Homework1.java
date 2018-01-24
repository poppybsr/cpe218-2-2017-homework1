package homework1;
import static homework1.TreeDemo.createImageIcon;
import java.util.*;
import java.util.Stack;
import java.util.Scanner;
//import javafx.scene.Node;
import javax.swing.JTree;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
 
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultTreeCellRenderer;
//import javax.imageio.
 
public class Homework1{
    public static Node tree;
    public static Stack<Character> stack = new Stack<Character>();
    public static int i;
    public static void main(String[] args) {
		//String example = "251-*32*+";
                System.out.println("INPUT : ");
                Scanner pop = new Scanner(System.in);
                String py = pop.nextLine();
                String example = py;
                
                while( i<example.length())
                 {
                     stack.push(example.charAt(i));
                     i++;
                 }
                
                 tree = new Node(stack.pop());
                 infix(tree);
                 inorder(tree);
                 System.out.println(" = "+calculator(tree));
                 javax.swing.SwingUtilities.invokeLater(new Runnable() {
                     public void run(){
                         TreeDemo.createAndShowGUI();
                     }
                 });
		// End of arguments input sample
		
		// TODO: Implement your project here
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
class Node {
            Character value;
            Node left,right;
    
            Node (char item){
                value = item;}
        public String toString(){
            return value.toString();
        }
    }
            
class TreeDemo extends JPanel
    implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
 
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
     
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
 
    public TreeDemo() {
        super(new GridLayout(1,0));
 
        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode(Homework1.tree);
        createNodes(top,Homework1.tree);
 
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
 
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
 
        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }
 
        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);
 
        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);
 
        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);
 
        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); 
        splitPane.setPreferredSize(new Dimension(500, 300));
 
        //Add the split pane to this panel.
        ImageIcon NodeIcon = new ImageIcon(TreeDemo.class.getResource("middle.gif"));
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setOpenIcon(NodeIcon);
        renderer.setClosedIcon(NodeIcon);
        tree.setCellRenderer(renderer);
        add(splitPane);
    }
 
    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode TreeN = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();
 
        if (TreeN == null) return;
 
       Object nodeIn = TreeN.getUserObject();
       Node y = (Node)nodeIn;
       Homework1.inorder(y);
       htmlPane.setText(y.value.toString());
                  
    }
 
    protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = TreeDemo.class.getResource("middle.gif");
    if (imgURL != null) {
        return new ImageIcon(imgURL);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}
    
    private class BookInfo {
        public String bookName;
        public URL bookURL;
 
        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                                   + filename);
            }
        }
 
        public String toString() {
            return bookName;
        }
    }
 
    private void initHelp() {
        String s = "TreeDemoHelp.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }
 
        displayURL(helpURL);
    }
 
    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else { //null url
        htmlPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null URL.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }
 
    private void createNodes(DefaultMutableTreeNode top,Node tree) {
        if(tree.left!=null){
            DefaultMutableTreeNode left = new DefaultMutableTreeNode(tree.left);
            top.add(left);
                 createNodes(left,tree.left);
             }
             if(tree.right != null){
                 DefaultMutableTreeNode right = new DefaultMutableTreeNode(tree.right);
                 top.add(right);
                 createNodes(right,tree.right);
        }
        /*DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
 
        category = new DefaultMutableTreeNode("Books for Java Programmers");
        top.add(category);
 
        //original Tutorial
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Tutorial: A Short Course on the Basics",
            "tutorial.html"));
        category.add(book);
 
        //Tutorial Continued
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Tutorial Continued: The Rest of the JDK",
            "tutorialcont.html"));
        category.add(book);
 
        //JFC Swing Tutorial
        book = new DefaultMutableTreeNode(new BookInfo
            ("The JFC Swing Tutorial: A Guide to Constructing GUIs",
            "swingtutorial.html"));
        category.add(book);
 
        //Bloch
        book = new DefaultMutableTreeNode(new BookInfo
            ("Effective Java Programming Language Guide",
         "bloch.html"));
        category.add(book);
 
        //Arnold/Gosling
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Programming Language", "arnold.html"));
        category.add(book);
 
        //Chan
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Developers Almanac",
             "chan.html"));
        category.add(book);
 
        category = new DefaultMutableTreeNode("Books for Java Implementers");
        top.add(category);
 
        //VM
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Virtual Machine Specification",
             "vm.html"));
        category.add(book);
 
        //Language Spec
        book = new DefaultMutableTreeNode(new BookInfo
            ("The Java Language Specification",
             "jls.html"));
        category.add(book);*/
    }
         
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }
 
        //Create and set up the window.
        JFrame frame = new JFrame("TreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new TreeDemo());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
    



