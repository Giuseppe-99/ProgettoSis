package supporto.src;

import java.io.IOException;

public class MainCset {


    public static void run() throws IOException {
        FuzzyClustering cmean = new FuzzyClustering();

        //get number of class from user
        //System.out.println("Please input number of cluster that you want :");
        //Scanner sc= new Scanner(System.in);
        //String read1 = sc.nextLine();
        //System.out.println("please input size of data set :");
        //String read2 = sc.nextLine();

        //generate random data
        cmean.createRandomData(Integer.parseInt("70"),2,1,100, Integer.parseInt("4"));

        //write random data
        cmean.writeDataToFile(cmean.readFile(), "data_set");

        //run clustering algorithm
        cmean.run(Integer.parseInt("4"), 100, cmean.readFile());

        //write cluster center to file
        cmean.writeDataToFile(cmean.clusterCenters, "cluster_centers");
        System.out.println("Clustering Finished!!!");
    }
}