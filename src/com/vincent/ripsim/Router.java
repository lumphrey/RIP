package com.vincent.ripsim;

import java.util.ArrayList;
import java.util.HashMap;

public class Router implements AdvertisementListener {

    private HashMap<String, TableEntry> routingTable;
    private ArrayList<Router> listeners;
    private int routerNum;

    /**
     * Constructor.
     */
    public Router(int num) {
        routerNum = num;
        routingTable = new HashMap<>();
        listeners = new ArrayList<Router>();

        //add initial entries
        switch(routerNum) {
            case 1:
                addEntry("n1", 0, 1);
                addEntry("n2", 0, 1);
                break;
            case 2:
                addEntry("n2", 0, 1);
                addEntry("n3", 0, 1);
                break;
            case 3:
                addEntry("n3", 0, 1);
                addEntry("n4", 0, 1);
                break;
            case 4:
                addEntry("n4", 0, 1);
                addEntry("n5", 0, 1);
                break;
            case 5:
                addEntry("n5", 0, 1);
                addEntry("n6", 0, 1);
                break;
            case 6:
                addEntry("n6", 0, 1);
                addEntry("n7", 0, 1);
                break;
            case 7:
                addEntry("n7", 0, 1);
                addEntry("n8", 0, 1);
                break;

        }
    }

    public void addEntry(String dest, int nextHop, int hopsToDest) {
        TableEntry entryToAdd = new TableEntry(dest, nextHop, hopsToDest);
        routingTable.put(dest, entryToAdd);
    }

    public TableEntry getEntry(String dest) {
        return routingTable.get(dest);
    }

    public boolean containsEntry(String dest) {
        return routingTable.containsKey(dest);
    }

    /**
     * sends out an advertisement to all listeners
     */
    public void advertise() {
        for (Router listener : listeners) {
            listener.onReceivedAdvertisement(routerNum, routingTable);
        }
    }

    public void setAdvertisementListener(Router router) {
        listeners.add(router);
    }

    @Override
    public void onReceivedAdvertisement(int sender, HashMap<String, TableEntry> advertisedTable) {
        System.out.println("Router " + routerNum + " received advertisement from Router " + sender);
        //compare entries and update


        for(int i = 0; i < 8; i++) {

            TableEntry entry = null;
            TableEntry advertisedEntry = null;
            String networkName = null;


            networkName = Constants.networks[i];

            if(this.routingTable.containsKey(networkName)) {    //Routing table contains entry for n(i)
                entry = this.routingTable.get(networkName);     //retrieve table entry for n(i)
                if(advertisedTable.containsKey(networkName)) {  //advertised table also contains entry for n(i)
                    advertisedEntry = advertisedTable.get(networkName);  //retrieve advertised entry for n(i)

                    if(entry.getNextHop() == sender) {        //Routing table's entry for n(i) has advertiser as next hop
                        if(advertisedEntry.getHopsToDest() != entry.getHopsToDest()) {       //update distance to n(i) according to advertiser
                            entry.setHopsToDest(advertisedEntry.getHopsToDest()+1);
                            this.routingTable.put(networkName, entry);
                        }
                    }

                    else if(entry.getNextHop() != sender) {      //Routing table's entry for n(i) is NOT the advertising router
                        if(advertisedEntry.getHopsToDest() < entry.getHopsToDest()) {      //compare hops to n(i)
                            entry.setNextHop(sender);                                      //if advertiser has shorter route, set advertiser as next hop for n(i)
                            entry.setHopsToDest(advertisedEntry.getHopsToDest()+1);
                            this.routingTable.put(networkName, entry);
                        }
                    }
                }
            }

            else if(!routingTable.containsKey(networkName)) {         //Routing table does NOT contain entry for n(i)
                if(advertisedTable.containsKey(networkName)) {        //but the advertised table does
                    advertisedEntry = advertisedTable.get(networkName);     //copy advertised entry into routing table and adjust distance
                    entry = new TableEntry(networkName, 0, 0);

                    //entry = advertisedTable.get(networkName);
                    //entry.setDest(networkName);

                    entry.setHopsToDest(advertisedEntry.getHopsToDest()+1);
                    entry.setNextHop(sender);
                    this.routingTable.put(networkName, entry);
                }
            }

        }

    }

    public void displayTable() {
        System.out.println("------------------------------------------------------");
        System.out.println("ROUTING TABLE FOR R" + routerNum);
        String temp;

        TableEntry entry;
        for(int i = 0; i < 8; i++) {
            entry = this.routingTable.get(Constants.networks[i]);
            if (entry != null) {
                temp = "Destination: " + entry.getDest() + "\t\tNext Hop: R" + entry.getNextHop() + "\tHops: " + entry.getHopsToDest();
                temp = temp.replaceAll("R0", "00");
                System.out.println(temp);
            }
        }

        System.out.println("------------------------------------------------------");
    }
}
