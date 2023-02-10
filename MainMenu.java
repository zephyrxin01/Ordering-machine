
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Date;

import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {
	JLabel picLabel, title;
	JButton button;
	private Panel panel_1;
	private Panel panel_2;
	static JFrame frame;
	protected static ArrayList <Single>singleList;
	protected static ArrayList <Sets>setsList;
    protected static int hour,minute;

	public static void main(String args[]) throws IOException {
		//   產生時間
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("HH");
        hour=Integer.parseInt(ft.format(date));
        ft = new SimpleDateFormat ("mm");
        minute=Integer.parseInt(ft.format(date));
        
		/**/
		File csv = new File("D:/Sophomore/java/JAVA_final/gui/Single_meal.csv"); // CSV file
		File csv2 = new File("D:/Sophomore/java/JAVA_final/gui/Set_meal.csv");

        BufferedReader br = new BufferedReader(new FileReader(csv));
    	BufferedReader br2 = new BufferedReader(new FileReader(csv2));
        int column=5;
    	int column2=6;
        String line = "";
    	String line2 = "";
        String tempstring;
    	String tempstring2;

        String[] tempArray= new String[column];
    	String[] tempArray2= new String[column2];
        ArrayList <String> myList = new ArrayList<String>();
    	ArrayList <String> myList2 = new ArrayList<String>();
        singleList = new ArrayList<Single>();
    	setsList = new ArrayList<Sets>();
        
	   while ((line = br.readLine()) != null) {

            tempstring=line;
            tempArray = tempstring.split(",");
            for(int i=0;i< tempArray.length;i++)
            {          
              myList.add(tempArray[i]);
            }
        }
	   while ((line2 = br2.readLine()) != null) {

            tempstring2=line2;
            tempArray2 = tempstring2.split(",");
            for(int i=0;i< tempArray2.length;i++)
            {          
              myList2.add(tempArray2[i]);
            }
        }

       int row=myList.size()/column;
	   int row2=myList2.size()/column2;
       int count=0;
	   int count2=0;
       int id=0,price=0;
	   String setid="";
	   int price2=0;
       String name="",type="";
	   String name2="";
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

        singleList.get(0).setImage("/fries-small.jpg");
        singleList.get(1).setImage("/McChicken.jpg");
        singleList.get(2).setImage("/coca-cola.jpg");
        singleList.get(3).setImage("/sprite.jpg");
        singleList.get(4).setImage("/fries-mid.jpg");
        singleList.get(5).setImage("/hash-browns.jpg");
        singleList.get(6).setImage("/mcmuffin.jpg");
        singleList.get(7).setImage("/fries-big.jpg");
        singleList.get(8).setImage("/Big-Mac.jpg");
        singleList.get(9).setImage("/corn-soup-small.jpg");
        /*
    	test
    	
        System.out.println(singleList.size());
	    for(int i=0;i<singleList.size();i++)
	    {
	        System.out.println(singleList.get(i));
	    }
	    */
       Single food1=singleList.get(0),food2=singleList.get(0),food3=singleList.get(0);

	   for(int i=0;i<row2;i++)
        {
            for(int j=0;j<column2;j++)
            {
                if(j==0){
                    setid=(String)myList2.get(count2);
                }
                else if(j==1){
                    name=(String) myList2.get(count2);                        
                }
                else if(j==5){
                    price=Integer.parseInt((String)myList2.get(count2));                        
                }
                else if(j==2){
                    food1 = singleList.get(Integer.parseInt((String)myList2.get(count2)));                        
                }
		      else if(j==3){
                    food2 = singleList.get(Integer.parseInt((String)myList2.get(count2)));                        
                }
                else if(j==4){
                     food3 = singleList.get(Integer.parseInt((String)myList2.get(count2)));                       
                }
                count2++;
            }
            setsList.add(new Sets(setid,name,price,food1,food2,food3));
        }

        setsList.get(0).setImage("/setA.jpg");
        setsList.get(1).setImage("/setB.jpg");
        setsList.get(2).setImage("/setC.jpg");

        /*
    	test
    	
        System.out.println(setsList.size());
	    for(int i=0;i<setsList.size();i++)
	    {
	        System.out.println(setsList.get(i));
	    }
	    */  

        //show the main menu gui
		MainMenu main = new MainMenu();
		main.createAndShowGUI();
		frame = new JFrame();
		frame.setTitle("速食點餐系統 ");
		frame.getContentPane().add(main);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void createAndShowGUI() throws IOException {
		JPanel panel = new JPanel(new BorderLayout());
		Image image = ImageIO.read(this.getClass().getResource("/1.jpg"));
		Image imageScaled = image.getScaledInstance(350, 300, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imageScaled);
		picLabel = new JLabel(imageIcon);
		Box right = Box.createVerticalBox();
		panel_1 = new Panel();
		title = new JLabel("速食點餐機 ");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(0.0f);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.ITALIC + Font.BOLD, 18));
		title.setForeground(Color.BLUE);

		// Button, with filler
		button = new JButton(" 開始訂購食物");
		panel_1.add(button);
		button.setAlignmentX(Component.LEFT_ALIGNMENT);

		panel.add(picLabel, BorderLayout.CENTER);
		panel.add(right, BorderLayout.SOUTH);
		right.add(title);
		right.add(panel_1);
		add(panel);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodMenu food;
				try {
					food = new FoodMenu();
					food.createAndShowGUI_FOOD();
					food.setVisible(true);
					setVisible(false);
					frame.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	
}
