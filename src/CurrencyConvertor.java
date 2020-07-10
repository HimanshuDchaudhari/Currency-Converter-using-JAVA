import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import org.json.JSONObject;

public class CurrencyConvertor {

	private JFrame frmCurrencyConvertor;
	private JTextField textField;
	private JTextField textField_1;
	
	String []CountryName = {"United Arab Emeriates dirham","Argentine peso","Australian dollar","Bulgarian lev",
		"Brazilian real ","Bahamian doller","Canadian doller","Swiss franc",
		"Chilean peso ","Chinese yuan","Colombian peso","Czech koruna",
		"Denmark","Dominican peso","Egyptian pound","Germany euro",
		"Fijian doller","British pound","Guatemalan quetzal","Hong Kong dollar",
		"Croatian kuna","Hungarian forint","Indonesian rupiah","Israeli new shekel",
		"Indian rupees","Icelandic krona","Japanese yen","South Korean won"
		,"Kazakhstani tenge","Mexican peso","Malaysian ringgit","Norwegian krone",
		"New Zealand Dollar","Panamanian balboa","Peruvian sol","Philippine peso",
		"Pakistani rupees","Polish zloty","Paraguayan guarani","Romanian leu",
		"Russian ruble","Saudi riyal","Swedish Krona","Singapore dollar",
		"Thai baht","Turkish lira","New Taiwan dollar","Ukraine hryvnia",
		"United States dollar","Uruguayan peso","Vietnamese domg","South African rand"};
	
	String []CountryCode= {"AED","ARS","AUD","BGN","BRL","BSD",
		"CAD","CHF","CLP","CNY","COP","CZK",
		"DKK","DOP","EGP","EUR","FJD","GBP",
		"GTQ","HKD","HRK","HUF","IDR","ILS",
		"INR","ISK","JPY","KRW","KZT","MXN",
		"MYR","NOK","NZD","PAB","PEN","PHP",
		"PKR","PLN","PYG","RON","RUB","SAR",
		"SEK","SGD","THB","TRY","TWD","UAH",
		"USD","UYU","VND","ZAR"};



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrencyConvertor window = new CurrencyConvertor();
					window.frmCurrencyConvertor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//connection

	public static double call_me(String from,String to) throws Exception {
	     String url = "https://api.exchangerate-api.com/v4/latest/"+from;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     
	     in.close();
	    JSONObject myresponse = new JSONObject(response.toString());
	    JSONObject myresponse_obj = new JSONObject(myresponse.getJSONObject("rates").toString());
	    	double d = myresponse_obj.getDouble(""+to);
	    	return d;
 	   }
	/**
	 * Create the application.
	 */
	public CurrencyConvertor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCurrencyConvertor = new JFrame();
		frmCurrencyConvertor.setForeground(Color.WHITE);
		frmCurrencyConvertor.setTitle("Currency Convertor");
		frmCurrencyConvertor.setBounds(100, 100, 723, 488);
		frmCurrencyConvertor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{678, 0};
		gridBagLayout.rowHeights = new int[]{40, 265, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frmCurrencyConvertor.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblCurrencyConvertor = new JLabel("Currency Converter");
		lblCurrencyConvertor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrencyConvertor.setFont(new Font("Arial Black", Font.BOLD, 28));
		GridBagConstraints gbc_lblCurrencyConvertor = new GridBagConstraints();
		gbc_lblCurrencyConvertor.anchor = GridBagConstraints.NORTH;
		gbc_lblCurrencyConvertor.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCurrencyConvertor.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurrencyConvertor.gridx = 0;
		gbc_lblCurrencyConvertor.gridy = 0;
		frmCurrencyConvertor.getContentPane().add(lblCurrencyConvertor, gbc_lblCurrencyConvertor);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frmCurrencyConvertor.getContentPane().add(panel, gbc_panel);
		
		JLabel lblNewLabel = new JLabel("Select Currency");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
        JComboBox FromConvert = new JComboBox(CountryName);
		
		JComboBox ToConvert = new JComboBox(CountryName);
		
		textField = new JTextField();
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						int convert = Integer.parseInt(textField.getText());
				   int selectIndex = FromConvert.getSelectedIndex();
				   int selectIndex1 = ToConvert.getSelectedIndex();
				   String code1 = CountryCode[selectIndex];
				   String code2 = CountryCode[selectIndex1];
				   
				   try {
					           double val = CurrencyConvertor.call_me(code1,code2);
					           double f = val*convert;
					           textField_1.setText(""+f);
				        } catch (Exception e) {
				         e.printStackTrace();
				       }
					}
					catch(NumberFormatException err)
					{
						textField_1.setText("Please enter valid amount");	
						
					}				  
			}
		});
		
		
		textField.setColumns(9);
		
		JLabel lblCurrencyToConvert = new JLabel("Currency To Convert In");
		lblCurrencyToConvert.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrencyToConvert.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int convert = Integer.parseInt(textField.getText());
			   int selectIndex = FromConvert.getSelectedIndex();
			   int selectIndex1 = ToConvert.getSelectedIndex();
			   String code1 = CountryCode[selectIndex];
			   String code2 = CountryCode[selectIndex1];
			   
			   try {
				           double val = CurrencyConvertor.call_me(code1,code2);
				           double f = val*convert;
				           textField_1.setText(""+f);
			        } catch (Exception eg) {
			         eg.printStackTrace();
			       }
				}
				
				catch(NumberFormatException err)
				{
					textField_1.setText("Please enter valid amount");	
					
				}
				

			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				ToConvert.setSelectedIndex(0);
				FromConvert.setSelectedIndex(0);
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmCurrencyConvertor, "Press OK to exit");
				System.exit(0);
			}
		});
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Enter the Amount");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnConvert, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(141)
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addComponent(lblNewLabel_1)
									.addComponent(FromConvert, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(173)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCurrencyToConvert, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
								.addComponent(ToConvert, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblResult))
							.addGap(70)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblCurrencyToConvert))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(FromConvert, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(ToConvert, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblResult))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConvert, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
