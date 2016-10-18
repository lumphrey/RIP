package com.vincent.ripsim;

import java.util.HashMap;


public interface AdvertisementListener {

    public void onReceivedAdvertisement(int sender, HashMap<String, TableEntry> routingTable);

}

