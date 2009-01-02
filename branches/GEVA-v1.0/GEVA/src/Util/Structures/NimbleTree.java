/*
 * NimbleTree.java
 *
 * Created on 16 October 2006, 16:45
 *
 * Tree structure
 */

package Util.Structures;

import java.util.Stack;

/**
 * Lightweight tree n-arity structure
 * @author EHemberg
 */
public class NimbleTree<E> {

    private TreeNode<E> root,currentNode;
    private Stack<TreeNode<E>> freeNodes;// nodes that have been taken off the tree and recycled 
    private int nodeCount; //Tracks number of nodes in the tree
    private int depth; // maximum depth of tree
    private int currentLevel; // 
    private int maxStackSize;

    /** Creates a new instance of NimbleTree */
    public NimbleTree() {
        this.root = newNode();
        this.currentNode = this.root;
        this.nodeCount = 1;
        this.depth = 0;
        this.currentLevel = 0;
        //Important to make a new stack
        this.freeNodes = new Stack<TreeNode<E>>();
        this.maxStackSize = 10;
    }

    /** Copy constructor
     *  @param n copied tree
     */
    public NimbleTree(NimbleTree<E> n) {
        this.root = n.root;
        this.currentNode = n.currentNode;
        this.nodeCount = n.nodeCount;
        this.freeNodes = new Stack<TreeNode<E>>();
        this.depth = n.depth;
        this.currentLevel = n.currentLevel;
    }

    protected TreeNode<E> newNode()
    {   return new TreeNode<E>();
    }
    
    /**
     * Set max stack size
     * @param i max stack size
     */
    public void setMaxStackSize(int i) {
        this.maxStackSize = i;
    }

    /**
     * Get max stack size
     * @return max stack size
     */
    public int getMaxStackSize() {
        return this.maxStackSize;
    }

    /**
     * Create nodes and push to the stack
     */
    public void populateStack() {
        TreeNode<E> tn;
        for(int i = 0; i < maxStackSize; i++) {
            tn = newNode();
            this.freeNodes.push(tn);
        }
    }

    /**
     * Get root of tree
     * @return tree root
     */
    public TreeNode<E> getRoot() {
        return this.root;
    }

    /**
     * Set tree root
     * @param tn root of tree
     */
    public void setRoot(TreeNode<E> tn){
        this.root = tn;
    }

    /**
     * Get current node
     * @return current node
     */
    public TreeNode<E> getCurrentNode(){
        return this.currentNode;
    }

    /**
     * Set current node
     * @param tn node to be current
     */
    public void setCurrentNode(TreeNode<E> tn){
        this.currentNode = tn;
    }

    /**
     * Get node count
     * @return number of nodes in tree
     */
    public int getNodeCount(){
        return this.nodeCount;
    }

    /**
     * Set node count
     * @param i number to set
     */
    public void setNodeCount(int i){
        this.nodeCount = i;
    }

    /**
     * Set depth of tree
     * @param i depth
     */
    public void setDepth(int i) {
        this.depth = i;
    }

    /**
     * Get maximum depth of tree
     * @return tree max depth
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * Get current level
     * @return current level
     */
    public int getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * Set current level
     * @param i level to set
     */
    public void setCurrentLevel(int i) {
        this.currentLevel = i;
    }

    /**
     * Add a child to the current node. Take a node from the free nodes.
     * INFINITE LOOP POSSIBILITY??!!
     * @param data data contained in the child
     */
    public void addChild( E data) {
        if(!this.freeNodes.empty()) {
            TreeNode<E> n = this.freeNodes.pop();
            n.setData(data);
            n.setParent(this.currentNode);
            n.clear();
            this.setDepth(this.depth + 1);
            this.currentNode.add(n);
            this.nodeCount++;
        } else {
            populateStack();
            addChild(data);//Infinite loop possibility
        }
    }

    @Override
    public String toString() {
        String s = "";
        s += root.toString();
        return s;
    }
}
