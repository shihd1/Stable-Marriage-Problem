/**
 * Write a description of class Person here.
 * 
 * @author (Darren Shih) 
 * @version (10/22/2018)
 */
import java.io.*;
import java.util.*;
public class Person
{
    //Initialize these 5 variables
    String name;
    String gender;
    ArrayList<String> myAttribute;
    ArrayList<String> preferedAttribute;
    int[] candidateScore ;
    //int[] rank;
    
    /**
     * Constructor for objects of class Person
     */
    
    public Person(ArrayList<String> input, int numOfQuestions){
        //declaring these variables
        this.name=input.get(0);
        this.gender=input.get(1);
        this.myAttribute= new ArrayList<>();
        this.myAttribute.addAll(input.subList(2, 2+(numOfQuestions/2)));
        this.preferedAttribute= new ArrayList<>();
        this.preferedAttribute.addAll(input.subList(2+(numOfQuestions/2), numOfQuestions+2));
    }
    
    //When called to print the object, it does this function
    public String toString(){
        String result = "name:"+this.name+"\n"+
                "gender:"+this.gender+"\n"+
                "myAttribute"+myAttribute+"\n"+
                "preferedAttribute:"+preferedAttribute+"\n"+
                "candidateScore:"+Arrays.toString(candidateScore)+"\n"/*+
                "rank:"+Arrays.toString(rank)+"\n"*/;
        return result ;
    }
    
    public void scoring( ArrayList<Person> candidates ){
        //create a new array called candidate score
        candidateScore = new int[candidates.size()] ;
        //scores the person with all the possible candidates
        for (int i = 0; i < candidateScore.length; i++) {
            candidateScore[i] = this.pairScore(candidates.get(i));
        }
        //ranking();
    }
    
    public int pairScore(Person p2){
        //adds all the deviation of the questions (which is squared)
        int sum = 0;
        for (int i = 0; i < this.myAttribute.size(); i++) {
            int deviation = Integer.parseInt(p2.myAttribute.get(i)) - Integer.parseInt(this.preferedAttribute.get(i));
            sum+=Math.pow(deviation, 2);
        }
        return sum ;
    }
    
//    private void ranking(){
//        rank = new int[candidateScore.length];
//        for (int i = 0; i < rank.length; i++) {
//            rank[i]=-1;            
//        }
//        int rankNum = 1;
//        while(true){
//            int min = Integer.MAX_VALUE;
//            int minIdx = -1;
//            for (int i = 0; i < candidateScore.length; i++) {
//                if( rank[i]!=-1){
//                    continue;
//                }
//                if( candidateScore[i] < min){
//                    min = candidateScore[i];
//                    minIdx = i;
//                }
//            }
//            rank[minIdx] = rankNum ;
//            if( rankNum==rank.length){
//                break; 
//            }
//            rankNum++;
//        }
//    }
    
    public void output(PrintWriter pw){
        //for the person, print the candidateScore (Array)
        for (int i : candidateScore) {
            pw.print(i+" ");
        }
        pw.println();
    }
}
