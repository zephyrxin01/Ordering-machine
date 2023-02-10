
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;  
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class OrderMenu extends FoodMenu{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderMenu window = new OrderMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public OrderMenu() throws IOException {
		createAndShowGUI_ORDER();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	void createAndShowGUI_ORDER() throws IOException {
		frame = new JFrame("�q�ʪ̸�T");
		frame.setBounds(100, 100, 420, 300); // x,y,width,height
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel lblFillInDetails = new JLabel("�ж�g�H�U���");
		lblFillInDetails.setBounds(120, 11, 131, 26);
		lblFillInDetails.setFont(new Font("Serif", Font.PLAIN, 16));
		lblFillInDetails.setForeground(Color.BLUE);
		frame.getContentPane().add(lblFillInDetails);

		JLabel lblName = new JLabel("�q�ʤH");
		lblName.setBounds(10, 46, 46, 14);
		frame.getContentPane().add(lblName);

		textField = new JTextField();
		textField.setBounds(82, 43, 176, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblAddress = new JLabel("�p����T");
		lblAddress.setBounds(10, 92, 80, 14);
		frame.getContentPane().add(lblAddress);

		textField_1 = new JTextField();
		textField_1.setBounds(82, 89, 176, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JLabel lblAddress_1 = new JLabel("�a�}");
		lblAddress_1.setBounds(10, 137, 62, 14);
		frame.getContentPane().add(lblAddress_1);

		Border border = BorderFactory.createLineBorder(Color.BLACK);
		textArea = new JTextArea();
		textArea.setBounds(82, 132, 236, 85);
		textArea.setMargin(new Insets(10,10,10,10)); // top,left,bottom,right
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		frame.getContentPane().add(textArea);

		JButton btnCancel = new JButton("�����q�� ");
		btnCancel.setBounds(130, 228, 89, 23);
		frame.getContentPane().add(btnCancel);

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodMenu food = new FoodMenu();
				try {
					food.createAndShowGUI();
					food.setVisible(true);
					setVisible(false);
				} 
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				frame.dispose();
			}
		});

		JButton btnConfirm = new JButton("�T�{�e�X");
		btnConfirm.setBounds(229, 228, 89, 23);
		frame.getContentPane().add(btnConfirm);

		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("") || textField_1.getText().equals("")
						|| textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�ж�g������");
				} 
				else {
					String Filename = JOptionPane.showInputDialog("�z���\�I�N�|�ɳt�e�F, ���±z���ϥ�.\n�п�Jtxt�ɦW�H�x�s�q�\���� ");
					String Filecontents = String.format("%2s\t%10s\t%s\t%s\t%5s\n","�~��","�\�I�W��","���","�ƶq","���B");
					for(int i=0;i<singleList.size();i++)
				    {
				        if((singleList.get(i).getNum())!=0)
				        {
				        	Filecontents+=(singleList.get(i)+"\n");
				        }
				    }

				    for(int i=0;i<setsList.size();i++)
				    {
				        
				        if((setsList.get(i).getNum())!=0)
				        {
				        	Filecontents+=(setsList.get(i)+"\n");
				        }
				    }
				    String sGetdiscount ="";
				    if(getdiscount==true){	sGetdiscount="(��ojava�u�f�w�馩10��)";}
				    Filecontents+=String.format("\n%s%d%s\n","�`���B:  ",total,sGetdiscount);
				    Filecontents+=("\n"+"�q�ʤH:"+textField.getText()+"\t�p����T:"+textField_1.getText()+"\t�a�}"+textArea.getText());
				    Filename = Filename + ".txt";
				    BufferedWriter bw = null;
				    try {
					  File file = new File(Filename);
					  file.createNewFile();
					  FileWriter fw = new FileWriter(file);
			          bw = new BufferedWriter(fw);
			          bw.write(Filecontents);
					} 
					catch (Exception e2) {
					  e2.printStackTrace();
					} 
					finally {
					  if (bw != null) {
					  	try {
					    bw.close();
					  } 
					  catch (IOException e3) {
					    e3.printStackTrace();
					  }
					}//end generating ordering details
					}
					MainMenu main = new MainMenu();
					try {
						main.main(null);
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
					main.setVisible(true);
					// setVisible(false);
					frame.dispose();
				}
			}
		});
		Image image = ImageIO.read(this.getClass().getResource("/order.png"));
		Image imageScaled = image.getScaledInstance(100, 65, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imageScaled);
		JLabel lblNewLabel = new JLabel(imageIcon);
		lblNewLabel.setBounds(268, 19, 126, 90);
		frame.getContentPane().add(lblNewLabel);

	}

	public void setVisible(boolean b) {
	}
}
