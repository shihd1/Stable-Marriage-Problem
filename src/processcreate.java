
/**
 * Write a description of class processcreate here.
 *
 * @author (Darren Shih)
 * @version (10/12/2018)
 */
import java.io.*;
import java.util.*;
public class processcreate
{

    final static int NUMOFQUESTIONS = 12;
    /**
     * main executable for class processcreate
     */

    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        File file = new File("/Users/mac/Desktop/SMPTestCase.csv");
        Scanner userInput = new Scanner(file);
        String title = userInput.nextLine();
        System.out.println(title);

        //create a ArrayList that encompasses each person
        ArrayList<Person> nameList = new ArrayList<>();
        while(userInput.hasNext()){
            nameList.add(readIn(userInput));
        }

        //separate male and female list
        ArrayList<Person> maleList = new ArrayList<>();
        ArrayList<Person> femaleList = new ArrayList<>();
        for (Person person : nameList) {
            if(person.gender.equalsIgnoreCase("Male")){
                maleList.add(person);
            }else{
                femaleList.add(person);
            }
        }

        //For each person in the maleList, score all the females
        for (Person person : maleList) {
            person.scoring(femaleList);
        }
        //For each person in the femaleList, score all the males
        for (Person person : femaleList) {
            person.scoring(maleList);
        }
        //Print out each of the person
        for (Person person : nameList) {
            System.out.println(person);
        }
        //Create a new file
        FileWriter fw = new FileWriter("/Users/mac/Desktop/coupleData.out");
        PrintWriter pw = new PrintWriter(fw);
        //Print number of couples
        pw.println(maleList.size());
        pw.println();
        //Output all the male rankings
        for (Person person : maleList) {
            person.output(pw);
        }
        pw.println();
        //Output all the female rankings
        for (Person person : femaleList) {
            person.output(pw);
        }
        pw.println();
        //Print out the names of males
        for (int i = 0; i < maleList.size(); i++) {
            if(i==maleList.size()-1){
                pw.print(maleList.get(i).name);
            }else{
                pw.print(maleList.get(i).name+",");
            }
        }
        pw.println();
        //Print out the names of females
        for (int i = 0; i < femaleList.size(); i++) {
            if(i==femaleList.size()-1){
                pw.print(femaleList.get(i).name);
            }else{
                pw.print(femaleList.get(i).name+",");
            }
        }
        pw.close();
        fw.close();
    }
    public static Person readIn(Scanner response) throws FileNotFoundException{
        //record, person, date, and time are not needed
        String record = response.nextLine();
        Scanner person = new Scanner(record);
        String date = person.next();
        person.useDelimiter(",");
        String time = person.next();
        //Create an array to store the list for the scores/ranks of the person
        ArrayList<String> numList = new ArrayList<>();
        for (int i = 0; i < NUMOFQUESTIONS+2; i++){
            numList.add(person.next());
        }
        //create a new object called "person"
        Person p = new Person(numList, NUMOFQUESTIONS) ;
        System.out.println(p);
        return p;
    }
}
