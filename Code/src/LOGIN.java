import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LOGIN {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGIN window = new LOGIN();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LOGIN() {
		initialize();
		createConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("NCarta");
		lblNewLabel.setBounds(72, 72, 73, 15);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PIN");
		lblNewLabel_1.setBounds(72, 118, 73, 15);
		frame.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(169, 70, 114, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(169, 116, 114, 19);
		frame.getContentPane().add(passwordField);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(169, 164, 117, 25);
		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					String ncarta = textField.getText();
					String pin = new String(passwordField.getPassword());
					String sql = "SELECT * FROM utente WHERE n_carta='" + ncarta + "' AND PIN='" + pin
							+ "'";
					resultSet = statement.executeQuery(sql);
					if (resultSet.next()) {
						//refresh textbox
						textField.setText("");
						passwordField.setText("");
						// Apri la schermata principale dell'ATM e chiudi la finestra di login
						frame.hide();
						createATM();
					} else {
						JOptionPane.showMessageDialog(null, "Username o password errati");
						textField.setText("");
						passwordField.setText("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// crea connessione al DB
	private void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createATM() {
		// crea la finestra dell'ATM
		JFrame atmFrame = new JFrame("ATM");
		atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atmFrame.setSize(400, 400);
		// crea il pannello delle opzioni
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(4, 2));
		JButton balanceButton = new JButton("Verifica Saldo");
		JButton withdrawButton = new JButton("Preleva Contante");
		JButton depositButton = new JButton("Deposita Contante");
		JButton transfertButton = new JButton("Trasferisci Saldo");
		JButton viewTransButton = new JButton("Visualizza transazioni recenti");
		JButton changeButton = new JButton("Cambia password");
		JButton closeAccButton = new JButton("Chiudi conto");
		JButton logoutButton = new JButton("Esci");
		optionsPanel.add(balanceButton);
		optionsPanel.add(withdrawButton);
		optionsPanel.add(depositButton);
		optionsPanel.add(transfertButton);
		optionsPanel.add(viewTransButton);
		optionsPanel.add(changeButton);
		optionsPanel.add(closeAccButton);
		optionsPanel.add(logoutButton);

		// azioni
		logoutButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				frame.show();
				atmFrame.dispose();
			}
		});

		// aggiunge i pannelli alla finestra
		// atmFrame.add(loginPanel, BorderLayout.NORTH);
		atmFrame.add(optionsPanel, BorderLayout.SOUTH);

		// mostra la finestra
		atmFrame.setVisible(true);
	}
}
