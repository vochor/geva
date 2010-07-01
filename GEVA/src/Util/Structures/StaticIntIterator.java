/*
 * StaticIntIterator.java
 *
 * Created on 20 February 2007, 22:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Util.Structures;

/**
 *
 * @author Conor
 */
public class StaticIntIterator implements IntIterator {
    private final int[] data;
    private final int size;
    private int index;

    /**
     * Create instance
     * @param data array with data
     * @param size size
     */
    public StaticIntIterator(int[] data, int size) {
        this.index = 0;
        this.size = size;
        this.data = data;
    }
    
    public boolean hasNext() {
        return this.index < this.size;
    }
    
    public int next() {
        int ret = this.data[index];
        index ++;
        return ret;
        
    }


    public static void main(String args[]) {
        StaticIntList L = new StaticIntList(100);
        for(int i = 0;i<100;i++) {
            L.set(i,i);
        }
        
        IntIterator i = L.iterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
        StaticIntList L2 = new StaticIntList(L);
        i = L2.iterator();
        while(i.hasNext()) {
            System.out.println(i.next());
        }
    }
    
    
    
    
}
