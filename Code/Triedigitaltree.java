public class Triedigitaltree {
//Tree stuf 
    public static int records=0;
    protected Node head=new Node(0);
    protected int size; 
    // threads stuf
    
    public class Node{
        public int data;
        public int value=0;
        Node [] next=new Node[100];
        Boolean isEndOfWord=false;
        Node(){
            data=0;
            for(int i=0; i<100; i++){
                next[i]=null;
            }
        }
        Node(int d){        
            data=d;
            for(int i=0; i<100; i++){
                next[i]=null;
            }
        }
    }
    
    Triedigitaltree(){
        size=0;
    }
        
    public void insert(int data[]){
        System.out.println("Inserting:");
        for(int i=0; i<data.length; i++){
            if(data[i]==0){
                System.out.println("0's are not allowed!"); //if data contains 0 invalid input!
                return;
            }
        }
        /*    
        for(int i=0; i<data.length; i++)
            System.out.println(data[i]+ " ");    //Display input
        */
        int num, index;
        boolean already=false;
        Node CurrentNode=head;
        for(int i=0; i<data.length; i++){     
            num=data[i];
            index=-1;
            for(int j=0;j<100; j++){                //if already in tree
                if(CurrentNode.next[j]==null){
                  
                }
                else{

                    if(CurrentNode.next[j].data==num){
                        already=true; 
                        index=j;
                        if(i == data.length-1 && CurrentNode.next[index].isEndOfWord != true){   //if final element is already there and not marked as final then mark it.
                            records++;
                            CurrentNode.next[index].isEndOfWord=true;
                        }
                        break;
                    }
                }
            }
            
            if(!already){                             //it number is not in the tree already.
                for(int j=0; j<100; j++){
                    if(CurrentNode.next[j]==null){    //find a null pointer
                        index=j;
                        break;
                    }    
                }
                if(index==-1){
                    System.out.println("All links are full!!");   //no null pointers left. There are more diff numbers then 100
                    return;
                }
                if(CurrentNode.next[index] == null){   //null space 
                    Node newnode= new Node(num);        //inserting data element
                    CurrentNode.next[index]=newnode;
                }


                if(i == data.length-1 && CurrentNode.next[index].isEndOfWord != true){  //if final element then mark as end of word.
                    records++;
                    CurrentNode.next[index].isEndOfWord=true;
                }
                
            }
            CurrentNode=CurrentNode.next[index];   //incrmenting
            CurrentNode.value+=1;
            already=false;
            
        }
    }


public boolean search(int data[]){
    System.out.println("Searching:");
        for(int i=0; i<data.length; i++)    //display data 
            System.out.println(data[i]);
        
    
        for(int i=0; i<data.length; i++){    //if data contain 0 then return flse
            if(data[i]==0)    return false;
        }
        
        Node CurrentNode=head;
        boolean found=false;
        int foundNums=0;
        int index=-1, num;
        for(int i=0; i<data.length; i++){
            num=data[i];
            for(int j=0; j<100; j++){
                if(CurrentNode.next[j]!=null){
                    if(CurrentNode.next[j].data == num){
                        System.out.println(num +":"+ CurrentNode.next[j].value); 
                        if(i==data.length-1){
                            if(CurrentNode.next[j].isEndOfWord){
                               System.out.println("FOUND!");
                           }
                           else{
                               System.out.println("NOT FOUND!");
                           }
                       }
                        
                        CurrentNode=CurrentNode.next[j];
                       
                       found=true;
                       break;

                   }  
                }
                
            }
            
        if(!found){
            System.out.println("NOT Found");
            return false;
        }
            
        found =false;
        }
        return true;
    }
       
}
