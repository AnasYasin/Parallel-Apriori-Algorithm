package CA_Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainClass implements Runnable{
    
    // threads stuf
    String threadName;
    Thread t;
    public static int [][]fileData;
    public static Triedigitaltree obj=new Triedigitaltree();
       
    MainClass(String name){
        threadName = name; 
        t = new Thread(this, name);
        System.out.println("New thread created: " + t);
        t.start();
    }
   
    MainClass(int row, int col){
        fileData=new int [row][col];
    } 
   
    
    @Override
    public void run() {
        
    
        
        int  temp=0;
        for(int i=0; i<fileData.length; i++){
            if(fileData[i][0]!=0){
                temp=fileData[i][0];
                break;
            }
        }
        int add=0;
        for(int i=0; i<fileData.length; i++){
            if(fileData[i][0]==temp){
                add+=1;
            }
        }
        int row=add, col=fileData[0].length;
        int yourData[][]=new int[row][col];
        row=0;
        
        for(int i=0; i<fileData.length; i++){
            if(fileData[i][0]==temp){
                for(int j=0; j<fileData[i].length; j++){
                    yourData[row][j]=fileData[i][j];
                    
                    fileData[i][j]=0;
                }
                row+=1;
            }
        }
        
        System.out.println("thread "+threadName+":"+"here");
        for(int i=0; i<yourData.length; i++){
            System.out.println("thread :"+threadName);
            System.out.println("->");
            for(int j=0; j<yourData[i].length; j++){
                System.out.print(yourData[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("thread "+threadName+":"+"here end");
        
        for(int i=0; i<yourData.length; i++){
            int input[]=new int [yourData[i].length], counter=0;
            for(int j=0; j<yourData[i].length; j++){
                if(yourData[i][j]!=0){
                    input[counter]=yourData[i][j];
                    counter+=1;
                }
            }
            
            for(int k=0; k<counter; k++){
                System.out.print(input[k] + " ");
            }
            int x=0;
            for(int l=0; l<input.length; l++){
                if(input[l]!=0){
                    x+=1;
                }
            }
            int finall[]=new int[x];
            
            for(int l=0; l<x; l++){
                if(input[l]!=0){
                    finall[l]=input[l];
                }
            }
            System.out.println("end");
            obj.insert(finall);
            
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        File file = new File("D:\\chess.txt"); 
        int arr[][],newdata[][], numberOfThreads=0, threadName=0;
        ProcessData process= new ProcessData();int percent=0;
        //Scanner scan= new Scanner(System.in);
        //System.out.println("Input Percentage to create new data");
        percent=80;
        int max_num=process.maxnumber(file);
        int line_count=process.counting(file);
        int column=process.columns(file);
        arr = new int[line_count+1][max_num+1];
        arr=process.fillarray(arr,file,column,max_num);
        arr=process.smallerarray(arr,max_num);
        arr=process.newdata(arr, percent);
        process.outputnewdata(arr);
        newdata=process.forsortingarray((arr[0].length)-1);
        //process.debug(newdata);
        arr=null;
        newdata=process.sortingalgo(newdata);
        //process.debug(newdata);
        
        int row=newdata.length;
        int col=newdata[0].length;
        new MainClass(row, col);
        
        for(int i=0; i<newdata.length; i++){
            for(int j=0; j<newdata[0].length; j++){
                fileData[i][j]=newdata[i][j];
     
            }
        }
        
        
        numberOfThreads=process.differentRows(newdata);
        for(int i=0; i<numberOfThreads; i++){
          threadName+=1;
          String s=String.valueOf(threadName);
          new MainClass(s);
         }
        
        int daata1[]={1,14};
        int daata2[]={14};
        
        try {
         Thread.sleep(10000);
         System.out.println(obj.search(daata1));
         System.out.println(obj.search(daata2));
         
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
            System.out.println("Main thread exiting.");
    }
    
}























