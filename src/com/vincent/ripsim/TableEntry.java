package com.vincent.ripsim;

public class TableEntry {

    private String dest;
    private int nextHop;
    private int hopsToDest;

    /**
     * Constructor
     * @param dest
     * @param nextHop
     * @param hopsToDest
     */
    public TableEntry(String dest, int nextHop, int hopsToDest) {
        this.dest = dest;
        this.nextHop = nextHop;
        this.hopsToDest = hopsToDest;
    }


    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setNextHop(int newNextHop) {
        nextHop = newNextHop;
    }

    public void setHopsToDest(int newHopsToDest) {
        hopsToDest = newHopsToDest;
    }

    /**
     * Returns ID of next hop router.
     * @return int routerId
     */
    public int getNextHop() {
        return nextHop;
    }

    public int getHopsToDest() {
        return hopsToDest;
    }

    public String toString() {
        return dest + " , " + nextHop + " , " + hopsToDest;
    }
}
