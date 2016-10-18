package com.vincent.ripsim;

import java.util.Scanner;

public class RIPSim {

    public static void main(String args[]) throws Exception {

        //initialize router objects
        Router r1 = new Router(1);
        Router r2 = new Router(2);
        Router r3 = new Router(3);
        Router r4 = new Router(4);
        Router r5 = new Router(5);
        Router r6 = new Router(6);
        Router r7 = new Router(7);

        //set listeners for each
        r1.setAdvertisementListener(r2);

        r2.setAdvertisementListener(r1);
        r2.setAdvertisementListener(r3);

        r3.setAdvertisementListener(r2);
        r3.setAdvertisementListener(r4);

        r4.setAdvertisementListener(r3);
        r4.setAdvertisementListener(r5);

        r5.setAdvertisementListener(r4);
        r5.setAdvertisementListener(r6);

        r6.setAdvertisementListener(r5);
        r6.setAdvertisementListener(r7);

        r7.setAdvertisementListener(r6);

        System.out.println("Initialized. Running...");

        Scanner scanner = new Scanner(System.in);

        int runCount = 1;

        System.out.println("INITIAL TABLES: ");
        r1.displayTable();
        r2.displayTable();
        r3.displayTable();
        r4.displayTable();
        r5.displayTable();
        r6.displayTable();
        r7.displayTable();

        System.out.println("Press Enter to begin simulation.");
        scanner.nextLine();

        while(true) {
            System.out.println("==============================================================================");
            System.out.println("RUN: " + runCount);

            r1.advertise();
            Thread.sleep(1000);
            r2.advertise();
            Thread.sleep(1000);
            r3.advertise();
            Thread.sleep(1000);
            r4.advertise();
            Thread.sleep(1000);
            r5.advertise();
            Thread.sleep(1000);
            r6.advertise();
            Thread.sleep(1000);
            r7.advertise();


            r1.displayTable();
            r2.displayTable();


            r3.displayTable();
            r4.displayTable();
            r5.displayTable();
            r6.displayTable();
            r7.displayTable();

            Thread.sleep(2000);
            runCount++;
            System.out.print("Press enter to run next round.");
            scanner.nextLine();
        }



    }

}
