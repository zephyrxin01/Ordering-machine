import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class ReadCSV {

    public static void main(String[] args) throws IOException{

        File csv = new File("D:/Sophomore/java/final/JAVA_final/gui//Single_meal.csv"); // CSV file

        BufferedReader br = new BufferedReader(new FileReader(csv));

        int column=5;
        String line = "";
        String tempstring;

        String[] tempArray= new String[column];
        ArrayList <String> myList = new ArrayList<String>();
        ArrayList <Single> singleList = new ArrayList<Single>();
        while ((line = br.readLine()) != null) {

            tempstring=line;
            tempArray = tempstring.split(",");
            for(int i=0;i< tempArray.length;i++)
            {          
              myList.add(tempArray[i]);
            }
        }
        int row=myList.size()/column;
        int count=0;
        int id=0,price=0;
        String name="",type="";
        boolean breakfast=false;
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                if(j==0){
                    id=Integer.parseInt((String)myList.get(count));
                }
                else if(j==1){
                    name=(String) myList.get(count);                        
                }
                else if(j==2){
                    price=Integer.parseInt((String)myList.get(count));                        
                }
                else if(j==3){
                    type=(String) myList.get(count);                        
                }
                else if(j==4){
                    breakfast=Boolean.parseBoolean((String)myList.get(count));                        
                }
                count++;
            }
            singleList.add(new Single(id,name,price,type,breakfast));
        }

    
    br.close();
    /*
    test
    */
    System.out.println(singleList.size());
    for(int i=0;i<singleList.size();i++)
    {
        System.out.println(singleList.get(i));
    }
     
    }
    
    
}