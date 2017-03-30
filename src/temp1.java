import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Label;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Image;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;

public class temp1 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					temp1 frame = new temp1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public temp1() {
		setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		setBackground(Color.WHITE);
		setTitle("\u05E4\u05E8\u05D5\u05D9\u05D9\u05E7\u05D8 \u05D2\u05DE\u05E8 ");
		setDefaultLookAndFeelDecorated(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage("img\\noam.jpg"));
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\mo.gif"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(100, 100, 1100, 600);
		ImageIcon con= new ImageIcon("img\\ariel_low_.jpg");
		lblNewLabel.setIcon(con);
		//lblNewLabel.setIcon(new ImageIcon("C:\\Users\\moshe\\\u05E1\u05DE\u05E1\u05D8\u05E8 \u05D0\\\u05E4\u05E8\u05D5\u05D9\u05D9\u05E7\u05D8\\logo\\logo_low1.png"));
		contentPane.add(lblNewLabel, BorderLayout.SOUTH);
		String[] week= {"Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday"};
		//String[] table_title={"moshe","moria","maly"};
		StringBuffer sb = new StringBuffer("hh"); 
		sb.append(System.getProperty("line.separator"));
		sb.append("kk");

		//String aaa= "course" +"\r\n"+"techer";
		StringBuffer p[][]= new StringBuffer [13][6];
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[0].length; j++) {
				p[i][j]=sb;
			}
			
		}
				/*{{"a","b","c"},
					{"aa","bb","cc"},
					{"aaa","bbb","ccc"},
					{"aaaa","bbbb","cccc"}
		};*/
		table = new JTable(p,week);
		//table.setPreferredScrollableViewportSize(new Dimension(200,400));
		//table.setFillsViewportHeight(true);
		
		//table.setBounds(200, 450, 200, 100);
		
		table.setVisible(false);
		contentPane.add(table, BorderLayout.PAGE_START);
		
		btnNewButton = new JButton("view table");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setBounds(450,300, 100, 50);
		btnNewButton.setLayout(getLayout());
		btnNewButton.setVisible(true);
		contentPane.add(btnNewButton, BorderLayout.NORTH);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel.setVisible(false);
				table.setVisible(true);
				System.out.println("hi moshe");
			}
		});
			
		
				
			
	}

}
