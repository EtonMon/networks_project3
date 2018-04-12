public class Entity3 extends Entity
{    
    private int[][] distanceTable;

    // Perform any necessary initialization in the constructor
    public Entity3()
    {
        distanceTable = new int[4][4];
        distanceTable[0][0] = 7;
        distanceTable[0][1] = 999;
        distanceTable[0][2] = 999;
        distanceTable[0][3] = 999;
        distanceTable[1][0] = 999;
        distanceTable[1][1] = 999;
        distanceTable[1][2] = 999;
        distanceTable[1][3] = 999;
        distanceTable[2][0] = 999;
        distanceTable[2][1] = 999;
        distanceTable[2][2] = 2;
        distanceTable[2][3] = 999;
        distanceTable[3][0] = 999;
        distanceTable[3][1] = 999;
        distanceTable[3][2] = 999;
        distanceTable[3][3] = 0;
    }
    
    // Handle updates when a packet is received.  Students will need to call
    // NetworkSimulator.toLayer2() with new packets based upon what they
    // send to update.  Be careful to construct the source and destination of
    // the packet correctly.  Read the warning in NetworkSimulator.java for more
    // details.
    public void update(Packet p)
    {
        //dest 0, 2
    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    }
    
    public void printDT()
    {
        System.out.println("         via");
        System.out.println(" D3 |   0   2");
        System.out.println("----+--------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            if (i == 3)
            {
                continue;
            }
            
            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j += 2)
            {
               
                if (distanceTable[i][j] < 10)
                {    
                    System.out.print("   ");
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
