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
import javax.swing.SwingConstants;

public class LOGIN {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;

	private Utente utente;
	private database_accidenti db;
	@SuppressWarnings("unused")
	private database_accidenti dbaccidentata;
	private int counter = 3;

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
					String sql = "SELECT * FROM utente WHERE n_carta='" + ncarta + "' AND PIN='" + pin + "'";
					resultSet = statement.executeQuery(sql);

					// controllo se la tessera è rubata
					db = new database_accidenti(Integer.parseInt(ncarta));
					if (db.cartaaccidentata(Integer.parseInt(ncarta))) {
						JOptionPane.showMessageDialog(null, "TESSERA RITIRATA! " + db.getTipo_accidente());
						textField.setText("");
						passwordField.setText("");
						System.exit(0);
					}
					if (resultSet.next() && !db.cartaaccidentata(Integer.parseInt(ncarta))) {
						// creo utente
						utente = new Utente(Integer.parseInt(ncarta));
						// refresh textbox
						textField.setText("");
						passwordField.setText("");
						// Apri la schermata principale dell'ATM e chiudi la finestra di login
						frame.hide();
						createATM(utente);
						resultSet.close();
						connection.close();
						statement.close();
					} else {
						counter--;
						if (counter == 0) {
							dbaccidentata = new database_accidenti(ncarta, "PIN errato"); /// non aggiunge la carta al
																							/// db
							JOptionPane.showMessageDialog(null, "TESSERA BLOCCATA! HAI SBAGLIATO PIN 3 VOLTE!!");
							System.exit(0);
						}
						JOptionPane.showMessageDialog(null, "Username o password errati" + counter);
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

//	// chiude connessione al DB
//	private void closeConnection() {
//		try {
//			connection.close();
//			statement.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	private void createATM(Utente utente) {
		// crea la finestra dell'ATM
		JFrame atmFrame = new JFrame("ATM");
		atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atmFrame.setSize(400, 400);

		// saluto e saldo dell'utente
		JPanel cognomeSaldo = new JPanel();
		JLabel label = new JLabel("UTENTE: " + utente.getCognome(), SwingConstants.CENTER);
		cognomeSaldo.add(label);

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

		// azione exit
		logoutButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				frame.show();
				atmFrame.dispose();
			}
		});

		// azione verifica saldo
		balanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "IL TUO SALDO CONTABILE: " + utente.getCc().getBilancio());
			}
		});

		// azione preleva contante
		withdrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int qtaprelievo = Integer.parseInt(JOptionPane.showInputDialog("Quanto vuoi prelevare?"));

				// se carta di credito = no limite prelievo!
				if (!utente.getCc().getImporto_minimo()) {
					utente.getCc().PrelevaContante(qtaprelievo, utente.getN_conto());
				} else if (utente.getCc().getBilancio() > 0 && (utente.getCc().getBilancio() - qtaprelievo) > 0) {
					utente.getCc().PrelevaContante(qtaprelievo, utente.getN_conto());
				} else {
					JOptionPane.showMessageDialog(null,
							"IL TUO SALDO CONTABILE: " + utente.getCc().getBilancio() + " NON HAI ABBASTANZA SOLDI!");
				}
			}
		});

		// azione deposita contante
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int qtadeposito = Integer.parseInt(JOptionPane.showInputDialog("Quanto vuoi depositare?"));
				utente.getCc().depositaContante(qtadeposito, utente.getN_conto());
			}
		});

		// aggiunge i pannelli alla finestra
		// atmFrame.add(loginPanel, BorderLayout.NORTH);
		atmFrame.add(optionsPanel, BorderLayout.SOUTH);
		atmFrame.add(cognomeSaldo, BorderLayout.NORTH);

		// mostra la finestra
		atmFrame.setVisible(true);
	}
}
