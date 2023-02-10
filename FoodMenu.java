
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class FoodMenu extends MainMenu{
	static private JFrame frame;
	static private JButton backButton, orderButton,discountButton;
	static private JTextField textField;
	static private GridBagConstraints gbc;
	private JTable table;
	DefaultTableModel dtm;
	/*singleList.add(new Single(id,name,price,type,breakfast));*/
	//show gui
	private int[] price;
	private int[] priceDrinks;
	private int[] priceSnacks;
	private int[] priceSets;
	private int totalPrice = 0;
	
	private boolean[] breakfastType;
	private String[] nameFood;
	private String[] nameDrinks;
	private String[] nameSnacks;
	private String[] nameSets;
	
	private JSpinner[] numSpinner;
	static private JLabel[] foodLabel;
	static private JLabel[] foodImage;
	private String[] file;

	private JSpinner[] numSpinnerDrinks;
	static private JLabel[] drinkLabel;
	static private JLabel[] drinkImage;
	private String[] fileDrinks;

	private JSpinner[] numSpinnerSnacks;
	static private JLabel[] snacksLabel;
	static private JLabel[] snacksImage;
	private String[] fileSnacks;

	private JSpinner[] numSpinnerSets;
	static private JLabel[] setsLabel;
	static private JLabel[] setsImage;
	private String[] fileSets;

	//use num of singleList to create ->cant do it cause i want to seperate to differednt tabbedpane
	private static int ELEMENTS =3;
	private static final int DRINK_ELEMENTS = 3;
	private static final int SNACKS_ELEMENTS = 4;
	private static final int SETS_ELEMENTS = 3;

	//calculate total variable
	protected static int total = 0;
	protected static int discount = 0;
	private int[] foodTotal=new int[ELEMENTS];
	private int[] drinksTotal=new int[DRINK_ELEMENTS];
	private int[] snacksTotal=new int[SNACKS_ELEMENTS];
	private int[] setsTotal=new int[SETS_ELEMENTS];
	protected boolean getdiscount = false;

	protected int totalForFoods;
	protected int totalForDrinks;
	protected int totalForSnacks;
	protected int totalForSets; 

	//save to the shopping details
	protected static ArrayList <Single> shoppingList = new ArrayList<Single>();
	protected static ArrayList <Sets> shoppingList2 = new ArrayList<Sets>();
	void createAndShowGUI_FOOD() throws IOException {
		frame = new JFrame("餐點選單 ");
		frame.setBounds(100, 100, 750, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel lblFoodOrdered = new JLabel("購物車");
		lblFoodOrdered.setBounds(560, 10, 80, 15);

		frame.getContentPane().add(lblFoodOrdered);//add to frame content to display label

		table = new JTable();
		backButton = new JButton();
		orderButton = new JButton();
		discountButton = new JButton();
		dtm = new DefaultTableModel();//a table of zero columns and zero rows
		final String header[] = new String[] { "餐點名稱", "數量", "總價", "Spinner" };
		//String header[] = new String[] { "Item", "Qty", "Price" };//errorrrrrrrrrrrrrrrrrrrrrrr??
		dtm.setColumnIdentifiers(header);
		dtm.addRow(header);
		table = new JTable();
		table.setModel(dtm);
		table.setBounds(475, 30, 1, 1); // int x, int y, int width, int height
		table.setSize(245, 300); // width,height
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		/**/
		table.getColumnModel().getColumn(3).setMinWidth(0); // hide spinner
															// column
		table.getColumnModel().getColumn(3).setMaxWidth(0); // hide spinner
															// column
		table.setShowGrid(false); // remove cell boarder
		frame.getContentPane().add(table);
		JLabel lblTotal = new JLabel("總金額  : ");
		lblTotal.setBounds(520, 340, 70, 15);
		frame.getContentPane().add(lblTotal);
		textField = new JTextField();
		textField.setBounds(585, 340, 85, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		orderButton = new JButton("確認訂購");
		orderButton.setBounds(500, 385, 90, 20);
		frame.getContentPane().add(orderButton);
		backButton = new JButton("回上一頁");
		backButton.setBounds(610, 385, 90, 20);
		frame.getContentPane().add(backButton);
		discountButton = new JButton("優惠碼");
		discountButton.setBounds(555, 415, 90, 20);
		frame.getContentPane().add(discountButton);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		addIt(tabbedPane, "主餐");
		addIt1(tabbedPane, "副餐");
		addIt2(tabbedPane, "飲料");
		addIt3(tabbedPane, "套餐");
		tabbedPane.setBounds(18, 11, 450, 450);
		frame.getContentPane().add(tabbedPane);
		frame.setVisible(true);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainMenu menu = new MainMenu();
					menu.main(header);
					// menu.createAndShowGUI();
					menu.setVisible(true);
					// setVisible(false);
					frame.dispose();
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "您尚未選擇餐點");
				} else {
					try {
						//put final order and print it in text
						OrderMenu order = new OrderMenu();
						order.main(header);
						order.setVisible(true);
						setVisible(false);
						frame.dispose();
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

		discountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("請在送出前輸入優惠碼以獲得折扣\n(單筆訂單最多獲得一次折扣)");
				if(s.equals ("java"))
				{
					dtm.addRow(new Object[] { "java優惠", 1, -10, 1 });
					discount = -10;
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					System.out.println(total);
					textField.setText(total + "");
					getdiscount = true;
				}

			}
		});

	}

	void addIt(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		gbc = new GridBagConstraints(); // getting constraints for the specified
										// component
		gbc.insets = new Insets(10, 0, 0, 0);
		
		foodImage = new JLabel[ELEMENTS];
		foodLabel = new JLabel[ELEMENTS];
		numSpinner = new JSpinner[ELEMENTS];
		file = new String[ELEMENTS];
		price = new int[ELEMENTS];
		nameFood = new String[ELEMENTS];
		breakfastType = new boolean [ELEMENTS];

		int j=0;
		for(int i=0;i<10;i++)
		{
			if(singleList.get(i).getType().equals(singleList.get(1).getType()))
			{
				
				foodLabel[j]=new JLabel(singleList.get(i).getName());
				nameFood[j]=singleList.get(i).getName();
				price[j] =	singleList.get(i).getPrice();
				file[j] =	singleList.get(i).getImage();
				breakfastType[j] = singleList.get(i).getBF();
				j++;					
			}
		}
		
		for (int i = 0; i < ELEMENTS; i++) {
			
			System.out.print(file[i]);	
			try {
			
			Image image = ImageIO.read(this.getClass().getResource(file[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
//			Image image = ImageIO.read(file[i]);
//			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			
			numSpinner[i] = new JSpinner(spnummodel);
			numSpinner[i].addChangeListener(listener);
			foodImage[i] = new JLabel(imageIcon);
			
			}
			catch(Exception e) {
				System.out.print(e);
			}
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		for (int i = 0; i < ELEMENTS; i++) {
			if (i % 3 == 0) {
				//modify axis y
				gbc.gridy += 3;
				gbc.gridx = 0;
			}
			if(hour<10&&minute<30&&breakfastType[i]==true)
			{
				panel.add(foodImage[i], gbc);
				gbc.gridy++; // gridy---> add one row,for foodLabel
				panel.add(foodLabel[i], gbc);
				gbc.gridy--; // remove the row
				gbc.gridx++; // move to next column
				panel.add(numSpinner[i], gbc);
				gbc.gridx++; // move to next column
				tabbedPane.addTab(text, panel);
			}
			else if(breakfastType[i]==false)
			{
				panel.add(foodImage[i], gbc);
				gbc.gridy++; // gridy---> add one row,for foodLabel
				panel.add(foodLabel[i], gbc);
				gbc.gridy--; // remove the row
				gbc.gridx++; // move to next column
				panel.add(numSpinner[i], gbc);
				gbc.gridx++; // move to next column
				tabbedPane.addTab(text, panel);
			}

			
		}
	}


	void addIt1(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		drinkImage = new JLabel[DRINK_ELEMENTS];
		drinkLabel = new JLabel[DRINK_ELEMENTS];
		numSpinnerDrinks = new JSpinner[DRINK_ELEMENTS];
		priceDrinks = new int[DRINK_ELEMENTS];
		fileDrinks = new String[DRINK_ELEMENTS];
		nameDrinks = new String[DRINK_ELEMENTS];
		int j=0;
		for(int i=0;i<10;i++)
		{
			if(singleList.get(i).getType().equals(singleList.get(2).getType()))
			{
				
				drinkLabel[j] = new JLabel(singleList.get(i).getName());
				nameDrinks[j] = singleList.get(i).getName();
				priceDrinks[j] = singleList.get(i).getPrice();
				fileDrinks[j] =	singleList.get(i).getImage();
				j++;					
			}
		}

		for (int i = 0; i < DRINK_ELEMENTS; i++) {
			Image image = ImageIO.read(this.getClass().getResource(fileDrinks[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinnerDrinks[i] = new JSpinner(spnummodel);
			numSpinnerDrinks[i].addChangeListener(listenerForDrinks);

			drinkImage[i] = new JLabel(imageIcon);
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		gbc.insets = new Insets(10, 5, 0, 0); // top,left,bottom,right
		for (int i = 0; i < DRINK_ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridx = 0;
				gbc.gridy += 2;
			}
			panel.add(drinkImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(drinkLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinnerDrinks[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);

		}
	}

	void addIt2(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		snacksImage = new JLabel[SNACKS_ELEMENTS];
		snacksLabel = new JLabel[SNACKS_ELEMENTS];
		numSpinnerSnacks = new JSpinner[SNACKS_ELEMENTS];
		priceSnacks = new int[SNACKS_ELEMENTS];
		fileSnacks = new String[SNACKS_ELEMENTS];
		nameSnacks = new String[SNACKS_ELEMENTS];
		int j=0;
		for(int i=0;i<10;i++)
		{
			if(singleList.get(i).getType().equals(singleList.get(0).getType()))
			{
				
				snacksLabel[j]=new JLabel(singleList.get(i).getName());
				nameSnacks[j]=singleList.get(i).getName();
				priceSnacks[j] =	singleList.get(i).getPrice();
				fileSnacks[j] =	singleList.get(i).getImage();
				j++;					
			}
		}
		
		for (int i = 0; i < SNACKS_ELEMENTS; i++) {
			Image image = ImageIO.read(this.getClass().getResource(fileSnacks[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinnerSnacks[i] = new JSpinner(spnummodel);
			numSpinnerSnacks[i].addChangeListener(listenerForSnacks);
			snacksImage[i] = new JLabel(imageIcon);
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		gbc.insets = new Insets(10, 5, 0, 0); // top,left,bottom,right
		for (int i = 0; i < SNACKS_ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridx = 0;
				gbc.gridy += 2;
			}
			panel.add(snacksImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(snacksLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinnerSnacks[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);
		}

	}

	void addIt3(JTabbedPane tabbedPane, String text) throws IOException {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		setsImage = new JLabel[SETS_ELEMENTS];
		setsLabel = new JLabel[SETS_ELEMENTS];
		numSpinnerSets = new JSpinner[SETS_ELEMENTS];
		priceSets = new int[SETS_ELEMENTS];

		fileSets = new String[SETS_ELEMENTS];
		fileSets[0] = new String("/setA.jpg");
		fileSets[1] = new String("/setB.jpg");
		fileSets[2] = new String("/setC.jpg");

		setsLabel[0] = new JLabel("普通套餐");
		setsLabel[1] = new JLabel("薯餅套餐");
		setsLabel[2] = new JLabel("吃很飽套餐");
		
		priceSets[0] = 60;
		priceSets[1] = 70;
		priceSets[2] = 70;

		for (int i = 0; i < SETS_ELEMENTS; i++) {
			Image image = ImageIO.read(this.getClass().getResource(fileSets[i]));
			Image imageScaled = image.getScaledInstance(80, 95, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(imageScaled);
			SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1); // value,minimum,maximum,stepSize
			numSpinnerSets[i] = new JSpinner(spnummodel);
			numSpinnerSets[i].addChangeListener(listenerForSets);
			setsImage[i] = new JLabel(imageIcon);
		}
		gbc.gridx = 0; // gridx 0 represent the most left
		gbc.insets = new Insets(10, 5, 0, 0); // top,left,bottom,right
		for (int i = 0; i < SETS_ELEMENTS; i++) {
			if (i % 3 == 0) {
				gbc.gridx = 0;
				gbc.gridy += 2;
			}
			panel.add(setsImage[i], gbc);
			gbc.gridy++; // gridy---> add one row,for foodLabel
			panel.add(setsLabel[i], gbc);
			gbc.gridy--; // remove the row
			gbc.gridx++; // move to next column
			panel.add(numSpinnerSets[i], gbc);
			gbc.gridx++; // move to next column
			tabbedPane.addTab(text, panel);
		}
		

	}

	ChangeListener listener = new ChangeListener() {

		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			//following click of the spinner
			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals(nameFood[0])) {
						dtm.setValueAt(quantity, row, 1); // obj, row, column
						dtm.setValueAt(price[0] * quantity, row, 2);
						foodTotal[0] = price[0] * quantity;
						singleList.get(1).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals(nameFood[1])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(price[1] * quantity, row, 2);
						foodTotal[1] = price[1] * quantity;
						singleList.get(6).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals(nameFood[2])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(price[2] * quantity, row, 2);
						foodTotal[2] = price[2] * quantity;
						singleList.get(8).setNum(quantity);
						//shoppingList.add(singleList.get(8));
					}
					if (quantity == 0) {
						dtm.removeRow(row);
						//shoppingList.remove();cant work , think another way
					}
					totalForFoods = foodTotal[0] + foodTotal[1] + foodTotal[2] ;
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");
					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
			//first click of the spinner
			for (int i = 0; i < ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				/*
				cant rewrite it like this
				if (numSpinner[i] == e.getSource()) {
				
					totalPrice = price[i];	
					if(foodLabel[i].getText()==nameFood[i])
					{
						foodTotal[i] = price[i];
					}
					totalForFoods = foodTotal[0] + foodTotal[1] + foodTotal[2];
					total = totalForFoods + totalForDrinks + totalForSnacks;
					textField.setText(total + "");
					dtm.addRow(new Object[] { foodLabel[i].getText(), quantity, totalPrice, numSpinner[i] });
					return;
					}
				}*/
				//switch-case can only use constant variables
				if (numSpinner[i] == e.getSource()) {
					totalPrice = price[i];
					
					switch (foodLabel[i].getText()) {
					case "麥香雞":
						foodTotal[0] = price[0];
						singleList.get(1).setNum(1);
						break;
					case "滿福堡":
						foodTotal[1] = price[1];
						singleList.get(6).setNum(1);
						break;
					case "大麥克":
						foodTotal[2] = price[2];
						singleList.get(8).setNum(1);
						break;
					}/**/					
					totalForFoods = foodTotal[0] + foodTotal[1] + foodTotal[2];
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");
					dtm.addRow(new Object[] { foodLabel[i].getText(), quantity, totalPrice, numSpinner[i] });
					//IF total Price replace by total when  quantity is 1 the above items will display the sum of all product() but total num is correct
					return;
				}				
			}
		}

	};


	ChangeListener listenerForSnacks = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			/*	
			if(((JSpinner) e.getSource()).getNextValue()==null)
			{
				JOptionPane.showMessageDialog(null, "Reach limit! You can only purchase a maximum of ten items on the day!");
			}
			wondering how to show message when the user clicked to the max(10)?stateChanged?
			*/
			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals(nameSnacks[0])) {
						dtm.setValueAt(quantity, row, 1); // obj, row,
						dtm.setValueAt(priceSnacks[0] * quantity, row, 2);
						snacksTotal[0] = priceSnacks[0] * quantity; // column
						singleList.get(0).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals(nameSnacks[1])) {
						dtm.setValueAt(quantity, row, 1); // obj, row, // column
						dtm.setValueAt(priceSnacks[1] * quantity, row, 2);
						snacksTotal[1] = priceSnacks[1] * quantity;
						singleList.get(4).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals(nameSnacks[2])) {
						dtm.setValueAt(quantity, row, 1); // obj, row, // column
						dtm.setValueAt(priceSnacks[2] * quantity, row, 2);
						snacksTotal[2] = priceSnacks[2] * quantity;
						singleList.get(5).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals(nameSnacks[3])) {
						dtm.setValueAt(quantity, row, 1); // obj, row, // column
						dtm.setValueAt(priceSnacks[3] * quantity, row, 2);
						snacksTotal[3] = priceSnacks[3] * quantity;
						singleList.get(7).setNum(quantity);
					}


					if (quantity == 0) {
						dtm.removeRow(row);
					}
					totalForSnacks = snacksTotal[0] + snacksTotal[1]+snacksTotal[2] + snacksTotal[3];
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");
					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < SNACKS_ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				if (numSpinnerSnacks[i] == e.getSource()) {
					totalPrice = priceSnacks[i];
					switch (snacksLabel[i].getText()) {
					case "薯條(小)":
						snacksTotal[0] = priceSnacks[0];
						singleList.get(0).setNum(1);
						break;
					case "薯條(中)":
						snacksTotal[1] = priceSnacks[1];
						singleList.get(4).setNum(1);
						break;
					case "薯餅":
						snacksTotal[2] = priceSnacks[2];
						singleList.get(5).setNum(1);
						break;
					case "薯條(大)":
						snacksTotal[3] = priceSnacks[3];
						singleList.get(7).setNum(1);
						break;
					}
					totalForSnacks = snacksTotal[0] + snacksTotal[1]+snacksTotal[2] + snacksTotal[3];
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");
					dtm.addRow(new Object[] { snacksLabel[i].getText(), quantity, totalPrice, numSpinnerSnacks[i] });
					return;
				}

			}
		}

	};

	ChangeListener listenerForDrinks = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals(nameDrinks[0])) {
						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(priceDrinks[0] * quantity, row, 2);
						drinksTotal[0] = priceDrinks[0] * quantity;
						singleList.get(2).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals(nameDrinks[1])) {
						dtm.setValueAt(quantity, row, 1); // obj, row,
															// column
						dtm.setValueAt(priceDrinks[1] * quantity, row, 2);
						drinksTotal[1] = priceDrinks[1] * quantity;
						singleList.get(3).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals(nameDrinks[2])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(priceDrinks[2] * quantity, row, 2);
						drinksTotal[2] = priceDrinks[2] * quantity;
						singleList.get(9).setNum(quantity);
					}
					if (quantity == 0) {
						dtm.removeRow(row);
					}
					totalForDrinks = drinksTotal[0] + drinksTotal[1] + drinksTotal[2];
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");

					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < DRINK_ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				if (numSpinnerDrinks[i] == e.getSource()) {
					totalPrice = priceDrinks[i];
					switch (drinkLabel[i].getText()) {
					case "可樂(小)":
						drinksTotal[0] = priceDrinks[0];
						singleList.get(2).setNum(1);
						break;
					case "雪碧(小)":
						drinksTotal[1] = priceDrinks[1];
						singleList.get(3).setNum(1);
						break;
					case "玉米濃湯":
						drinksTotal[2] = priceDrinks[2];
						singleList.get(9).setNum(1);
						break;
					}
					totalForDrinks = drinksTotal[0] + drinksTotal[1] + drinksTotal[2];
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");
					dtm.addRow(new Object[] { drinkLabel[i].getText(), quantity, totalPrice, numSpinnerDrinks[i] });
					return;
				}

			}

		}
	};


	ChangeListener listenerForSets = new ChangeListener() {
		public void stateChanged(ChangeEvent e) {

			final int quantity = (int) ((JSpinner) e.getSource()).getValue();
			final int rows = table.getRowCount();
			for (int row = 0; row < rows; row++) {
				if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals("普通套餐")) {
						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(priceSets[0] * quantity, row, 2);
						setsTotal[0] = priceSets[0] * quantity;
						setsList.get(0).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals("薯餅套餐")) {
						dtm.setValueAt(quantity, row, 1); // obj, row,
															// column
						dtm.setValueAt(priceSets[1] * quantity, row, 2);
						setsTotal[1] = priceSets[1] * quantity;
						setsList.get(1).setNum(quantity);
					} 
					else if (dtm.getValueAt(row, 0).equals("吃很飽套餐")) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(priceSets[2] * quantity, row, 2);
						setsTotal[2] = priceSets[2] * quantity;
						setsList.get(2).setNum(quantity);
					}
					if (quantity == 0) {
						dtm.removeRow(row);
					}
					totalForSets = setsTotal[0] + setsTotal[1] + setsTotal[2];
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");

					return;
				}
			}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < SETS_ELEMENTS; i++) {
				// looking for the "clicked" JSpinner
				if (numSpinnerSets[i] == e.getSource()) {
					totalPrice = priceSets[i];
					switch (setsLabel[i].getText()) {
					case "普通套餐":
						setsTotal[0] = priceSets[0];
						setsList.get(0).setNum(1);
						break;
					case "薯餅套餐":
						setsTotal[1] = priceSets[1];
						setsList.get(1).setNum(1);
						break;
					case "吃很飽套餐":
						setsTotal[2] = priceSets[2];
						setsList.get(2).setNum(1);
						break;
					}
					totalForSets = setsTotal[0] + setsTotal[1] + setsTotal[2];
					total = totalForFoods + totalForDrinks + totalForSnacks + totalForSets + discount;
					textField.setText(total + "");
					dtm.addRow(new Object[] { setsLabel[i].getText(), quantity, totalPrice, numSpinnerSets[i] });
					return;
				}

			}

		}
	};
	/*
	public void setVisible(boolean b) throws IOException {
	}
	*/
	public void setVisible(boolean b) {
	}
}
