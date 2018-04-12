public class Entity0 extends Entity
{    
    private int[][] distanceTable;
    // Perform any necessary initialization in the constructor
    public Entity0()
    {
        distanceTable = new int[4][4];
        distanceTable[0][0] = 0;
        distanceTable[0][1] = 999;
        distanceTable[0][2] = 999;
        distanceTable[0][3] = 999;
        distanceTable[1][0] = 999;
        distanceTable[1][1] = 1;
        distanceTable[1][2] = 999;
        distanceTable[1][3] = 999;
        distanceTable[2][0] = 999;
        distanceTable[2][1] = 999;
        distanceTable[2][2] = 3;
        distanceTable[2][3] = 999;
        distanceTable[3][0] = 999;
        distanceTable[3][1] = 999;
        distanceTable[3][2] = 999;
        distanceTable[3][3] = 7;
    }
    
    // Handle updates when a packet is received.  Students will need to call
    // NetworkSimulator.toLayer2() with new packets based upon what they
    // send to update.  Be careful to construct the source and destination of
    // the packet correctly.  Read the warning in NetworkSimulator.java for more
    // details.
    public void update(Packet p)
    {
        //dest: 1, 2, 3
        int source = p.getSource();
        int costToSource = distanceTable[source][source];
        boolean hasTableChanged = false;
        
        // Iterate through rows of distance table (Dest)
        for(int dest; dest<distanceTable.length;dest++){
            int[] row = distanceTable[dest];

            // Only want to update dest min costs for dest other than source.
            // We already know cost to source via source.
            if(source!=dest){

                // Iterate through dest row
                for(int via; via<row.length;via++){

                    // Get min cost of dest via source
                    int pktMinCost = p.getMincost(dest);

                    // Get current cost of dest via source
                    int currMinCost = row[via];

                    // Total cost to dest via source
                    int totalCost = pktMinCost+costToSource;

                    // Only want to update via for source and if the total 
                    // cost is less than what we currently have stored.
                    if(source==via && (totalCost)<currMinCost){
                        hasTableChanged = true;
                        row[via] = totalCost;
                    }
                }
            }
        }

        if(hasTableChanged == true) {
            int src = 0;
            int dest = 0;
            int[] neighbors = {1, 2, 3};
            //Grab MinCosts
            int[] mincost = {distanceTable[0][0], distanceTable[1][1], distanceTable[2][2], distanceTable[3][3]};
            //send to neighbors
            for (int i = 0; i < 3; i++) {
                dest = neighbors[i];
                Packet outpkt = new Packet(src, dest, mincost);
                NetworkSimulator.toLayer2(outpkt);
            }
        }


    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {

    }
    
    public void printDT()
    {
        System.out.println();
        System.out.println("           via");
        System.out.println(" D0 |   1   2   3");
        System.out.println("----+------------");
        for (int i = 1; i < NetworkSimulator.NUMENTITIES; i++)
        {
            System.out.print("   " + i + "|");
            for (int j = 1; j < NetworkSimulator.NUMENTITIES; j++)
            {
                if (distanceTable[i][j] < 10)
                {    
                    System.out.print(" less than 10  ");
                }
                else if (distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else 
                {
                    System.out.print(" ");
                }
                
                System.out.print(distanceTable[i][j]);
            }
            System.out.println();
        }
    }
}
