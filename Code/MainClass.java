import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.*;

public class MainClass implements Runnable{
    
    // threads stuf
    String threadName;
    Thread t;
    public static int [][]fileData;
    public static Triedigitaltree obj=new Triedigitaltree();
    public static boolean check =false;
    public static long threadTimeTotal =0;
    
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
        long startTime=System.currentTimeMillis(); 
            
        System.out.println("Thread :"+threadName+" Created!");
        int  temp=0; int input1[]=new int[fileData[0].length];
        if(check){
            for(int i=0; i<fileData.length; i++){
                if(fileData[i][0]!=0){
                    temp=fileData[i][0];
                    fileData[i][0]=0;
                    try{
                        Thread.sleep(10);

                    }catch(Exception e){
                        
                    }

                    for(int j=0; j<fileData[0].length; j++){
                        input1[j]=fileData[i][j];
                    }
                    input1[0]=temp;

                    int x=0;
                    for(int l=0; l<input1.length; l++){
                        if(input1[l]!=0){
                            x+=1;
                        }
                    }
                    int finall[]=new int[x];
                    int temp2=0;
                    for(int l=0; l<x; l++){
                        if(input1[l]!=0){
                            finall[temp2]=input1[l];
                            temp2+=1;
                        }
                    }
                    System.out.print("Thread: "+ threadName + "\t");
                    for(int k=0; k<finall.length; k++){
                        System.out.print(finall[k]+" ");
                    }
                    obj.insert(finall);

                    }
            }
        }
        else{
            
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

            /*
            for(int i=0; i<yourData.length; i++){

                System.out.println("thread :"+threadName);
                System.out.println("->");
                for(int j=0; j<yourData[i].length; j++){
                    System.out.print(yourData[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println("thread "+threadName+":"+"here end");
            */
            for(int i=0; i<yourData.length; i++){
                int input[]=new int [yourData[i].length], counter=0;
                for(int j=0; j<yourData[i].length; j++){
                    if(yourData[i][j]!=0){
                        input[counter]=yourData[i][j];
                        counter+=1;
                    }
                }
                int x=0;
                for(int l=0; l<input.length; l++){
                    if(input[l]!=0){
                        x+=1;
                    }
                }
                int finall[]=new int[x];
                int temp1=0;
                for(int l=0; l<x; l++){
                    if(input[l]!=0){
                        finall[temp1]=input[l];
                        temp1+=1;
                    }
                }
                System.out.println("Thread: "+threadName);
                obj.insert(finall);
            }

            
        }
        System.out.println("Thread: "+threadName+" ended!");
        long endTime=System.currentTimeMillis(); 
        long totalTime=endTime-startTime;
        threadTimeTotal+=totalTime;
            
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
       
        File file = new File("D:\\chess.txt"); // file name
        int arr[][],newdata[][], numberOfThreads=0, threadName=0;
        ProcessData process= new ProcessData();int percent=0;
        //Scanner scan= new Scanner(System.in);
        //System.out.println("Input Percentage to create new data");
        percent=80;// percentage inout
        int max_num=process.maxnumber(file);// file max number
        int line_count=process.counting(file);// line in file
        int column=process.columns(file);
        arr = new int[line_count+1][max_num+1];// arr 
        arr=process.fillarray(arr,file,column,max_num);// input from file 
        arr=process.smallerarray(arr,max_num);// all zero cols remove
        arr=process.newdata(arr, percent);// percentage cols
        //newdata=process.sortingalgo(newdata);
        process.outputnewdata(arr);//file output
        newdata=process.forsortingarray((arr[0].length)-1); // read zero no value
        //process.debug(newdata);
        arr=null;
        newdata=process.sortingalgo(newdata);// bubble sort
        //process.outputsortnewdata(newdata);
        //process.debug(newdata);
        
        int row=newdata.length;
        int col=newdata[0].length;
        new MainClass(row, col);
        
        for(int i=0; i<newdata.length; i++){
            for(int j=0; j<newdata[0].length; j++){
                fileData[i][j]=newdata[i][j];  //shareing
            }
        }
        
        
        numberOfThreads=process.differentRows(newdata);  
        
        /*
        for(int i=0; i<newdata.length; i++){
            for(int j=0; j<newdata[0].length; j++){
                System.out.print(newdata[i][j]+"\t");
                
     
            }
            System.out.println();
        }
        */
        System.out.println(numberOfThreads);
        if(numberOfThreads==1){
            numberOfThreads=newdata.length/4;
            check=true;
        }
        for(int i=0; i<numberOfThreads; i++){
          threadName+=1;
          String s=String.valueOf(threadName);
          new MainClass(s);
         }
                
//long startTime=System.currentTimeMillis(); 
        int daata1[]={3, 5, 7, 9, 25, 29, 34, 36, 40, 42, 44, 48, 52, 56, 58, 60, 62, 64, 66};
        ///int daata2[]={1, 8, 14};
        
        try {
            Thread.sleep(10000);
            
            System.out.println("Thread time: " + threadTimeTotal);
            System.out.println(obj.search(daata1));
            //System.out.println(obj.search(daata2));

        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
            System.out.println("Main thread exiting.");
    }
    
}
