/**
 * Write a description of class stableMarriageProblem here.
 * 
 * @author (Darren Shih) 
 * @version (10/4/2018)
 */
import java.io.*;
import java.util.*;
public class stableMarriageProblem
{
    static ArrayList<Integer> totalPoints = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> pairInfo = new ArrayList<>();
    static int[][] rank;
    static int numCouples;
    /**
     * main executable for class stableMarriageProblem
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        File file = new File("/Users/mac/Desktop/coupleData.out");
        Scanner userInput = new Scanner(file);
        numCouples = userInput.nextInt();
        int[][] guyPreferences = new int[numCouples][numCouples];
        for(int maleI = 0; maleI < numCouples; maleI++){
            for(int femaleI = 0; femaleI < numCouples; femaleI++){
                guyPreferences[maleI][femaleI] = userInput.nextInt();
                System.out.println(guyPreferences[maleI][femaleI]+" : "+maleI+", "+femaleI);
            }
        }
        System.out.println("-----");
        int[][] girlPreferences = new int[numCouples][numCouples];
        for(int femaleI = 0; femaleI < numCouples; femaleI++){
            for(int maleI = 0; maleI < numCouples; maleI++){
                girlPreferences[femaleI][maleI] = userInput.nextInt();
                System.out.println(girlPreferences[femaleI][maleI]+" : "+femaleI+", "+maleI);
            }
        }
        userInput.nextLine();
        userInput.nextLine();
        String maleName = userInput.nextLine();
        String[] maleNameArray = maleName.split(",");
        String femaleName = userInput.nextLine();
        String[] femaleNameArray = femaleName.split(",");
        
        System.out.println("--------------------");
        //rank order --> guy
        rank = new int[numCouples][numCouples];
        for(int maleI = 0; maleI < numCouples; maleI++){
            for(int femaleI = 0; femaleI < numCouples; femaleI++){
                rank[maleI][femaleI] = guyPreferences[maleI][femaleI] + girlPreferences[femaleI][maleI];
                System.out.println(rank[maleI][femaleI]+" : "+maleI+", "+femaleI);
            }
        }
        for (int i = 0; i < numCouples; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(i);
            expand(temp, rank[0][i]);
        }
        int min = Integer.MAX_VALUE;
        int minI = -1;
        for (int i = 0; i < totalPoints.size(); i++) {
            System.out.print(totalPoints.get(i)+" "+pairInfo.get(i));
            if(totalPoints.get(i)<min){
                min = totalPoints.get(i);
                minI = i;
                System.out.println("V");
            }else{
                System.out.println("");
            }
        }
        System.out.println(pairInfo.get(minI));
        for (int i = 0; i < numCouples; i++) {
            int boyIdx = i;
            int girlIdx = pairInfo.get(minI).get(i);
            System.out.println(maleNameArray[boyIdx]+" is paired with "+femaleNameArray[girlIdx]);
        }
        FileWriter fw = new FileWriter("/Users/mac/Desktop/datingData.out"); 
        PrintWriter pw = new PrintWriter(fw);
        pw.println("Boy, Girl");
        for (int i = 0; i < numCouples; i++) {
            int boyIdx = i;
            int girlIdx = pairInfo.get(minI).get(i);
            pw.println(maleNameArray[boyIdx]+", "+femaleNameArray[girlIdx]);
        }
        pw.close();
        fw.close();
    }
    static void expand(ArrayList<Integer> girlNum, int points){
        int maleIndex = girlNum.size();
        System.out.println("Expand: "+maleIndex+" "+girlNum+" "+points);
        if(girlNum.size()<numCouples){
            for (int femaleI = 0; femaleI < numCouples; femaleI++) {
                if(girlNum.contains(femaleI)){
                    continue;
                }
                ArrayList<Integer> newGirlNum = new ArrayList<>();
                newGirlNum.addAll(girlNum);
                newGirlNum.add(femaleI);
                expand(newGirlNum, points+rank[maleIndex][femaleI]);
            }
        }else{
           totalPoints.add(points);
           pairInfo.add(girlNum);
        }
    }
}