import java.io.FileInputStream; //import to describe file which is to take data
import java.io.FileNotFoundException; //import to give error if don't find file
import java.io.FileOutputStream; //import to describe file which is to write data
import java.io.IOException; //import  to describe error
import java.io.PrintWriter; //import to write something to file
import java.util.Scanner; //import to take data from file

//class to read data from different two csv file and merge them into a new file
public class Test{
    private static String fileName1="data1.csv"; // this string is one of the csv file name
    private static String fileName2="data2.csv"; //this string is other csv file name
    private static String createFile="mergedData.csv"; //this string is new csv file name

    public static void main(String[] args) {
        readData();
    }//end of the main

    //porpuse of method is to read data which is ID from file
    public static void readData() {
        try {
            FileInputStream ios1 = new FileInputStream(fileName1);//to connect file which is take data
            Scanner scan=new Scanner(ios1); //to read data from connecting file
            int i=0; //to find which line because if at first line, there isn't importan data
            while (scan.hasNextLine()) {
                if(i==0){//to find first line
                    String data=scan.nextLine();//to scan data in first line
                    writeString();
                }else{        
                    String[] data2=scan.nextLine().split(","); // in file to convert string array from string values seperated by commas
                    findSameID(data2);                                                      
                }//end of the if-else  
                i++;//increase line number       
            }//end of the while loop
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }//end of the try-catch  
    }//end of the method

    //porpuse of method is to find same id in file from taking data
    public static void findSameID(String[] data){
        try {
            FileInputStream ios = new FileInputStream(fileName2);//to connect file which is take data
            Scanner scan=new Scanner(ios);//to read data from connecting file
            boolean x=false;//to work while loop
            int i=0; //to find which line because if at first line, there isn't important data
            while (x==false) { //to try to find same id 
                if(i==0){
                    String data1=scan.nextLine();//to scan data in first line
                }else{
                    String[] data2=scan.nextLine().split(",");// in file to convert string array from string values seperated by commas
                    if(Integer.parseInt(data[0])==Integer.parseInt(data2[0])){//to control equal between taking id and reading id
                        x=true;//to stop while loop
                        WriteData(data, data2);
                    }//end of the if
                }//end of the if-else              
                i++; //increase line number          
            }//end of the while loop
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }//end of the try-catch        
    }//end of the method

    //porpuse of method is to write first line
    public static void writeString() {
        try {
            FileOutputStream fos=new FileOutputStream(createFile);//to connect file and to create file if don't create
            PrintWriter pw=new PrintWriter(fos); // to write data to file
            pw.println("ID,Name,LastName,Q,Midterm,Final,Department");//to describe to what will write
            pw.flush();//to save data
            fos.close();//to close FileOutputStream
            pw.close();//to close PrintWriter
        } catch (FileNotFoundException e) {//to describe error if file don't find
            System.err.println(e.getMessage());
        } catch(IOException e){//to describe error
            System.err.println(e.getMessage());
        }//end of the try-catch  
    }//end of the method

    //porpuse of method is to write data without first line
    public static void WriteData(String[] data1, String[] data2) {
        double sumQuiz=Double.parseDouble(data2[1])+Double.parseDouble(data2[2])+Double.parseDouble(data2[3]);//to calculate sum of three quiz
        double meanQuiz=sumQuiz/3; //to calculate mean of three quiz
        try {
            FileOutputStream fos=new FileOutputStream(createFile,true);//to connect file and to create file if don't create 
            PrintWriter pw=new PrintWriter(fos); // to write data to file
            pw.println(data1[0]+","+data1[1]+","+data1[2]+","+meanQuiz+","+data2[4]+","+data2[5]+","+data1[3]);//to describe to what will write
            pw.flush(); //to save data
            fos.close(); //to close FileOutputStream
            pw.close(); //to close PrintWriter
        } catch (FileNotFoundException e) { //to describe error if file don't find
            System.err.println(e.getMessage());
        } catch(IOException e){ //to describe error
            System.err.println(e.getMessage());
        }//end of the try-catch     
    }//end of the method
}//end of the class