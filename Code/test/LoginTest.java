import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

	// loginTEST
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ATM";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWORD = "admin";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private atm atm;
	private Utente utente;
	@SuppressWarnings("unused")
	private Transazione t;
	private int counter = 3;
	private database_accidenti db;
	@SuppressWarnings("unused")
	private database_accidenti dbaccidentata;
	// login
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	private Utente ubeneficiario;
	ArrayList<Transazione> transactions;

	/**
	 * LANCIO L'APP.
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

	@Test
	public void testMainMethod() {
		LOGIN window = new LOGIN();
		LOGIN.main(new String[0]);
		assertTrue(window.frame.isVisible());
	}

	/**
	 * CREO L'APPLICAZIONE.
	 */
	public LoginTest() {
		initialize();
		createConnection();
	}

	@Test
	public void testLoginConstructor() {
		LOGIN login = new LOGIN();
		assertNotNull(login);
	}

	/**
	 * INIZIALIZZO LA FRAME.
	 */
	private void initialize() {
		// usiamo filiale di bergamo
		atm = new atm("bergamo");

		// creazione frame + titolo dinamico
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("BANCA D'ITALIA-" + atm.getLuogo());

		// creiamo label per login
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

		// bottone accedi
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(169, 164, 117, 25);
		frame.getContentPane().add(btnNewButton);

		// azione bottone LOGIN
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
						JOptionPane.showMessageDialog(null, "TESSERA RITIRATA PER " + db.getTipo_accidente());
						textField.setText("");
						passwordField.setText("");
					}
					if (resultSet.next() && !db.cartaaccidentata(Integer.parseInt(ncarta))) {
						// creo utente
						utente = new Utente(Integer.parseInt(ncarta));
						t = new Transazione("Login", 1, 1, utente.getN_carta(), 0);
						// refresh textbox
						textField.setText("");
						passwordField.setText("");
						// Apri la schermata principale dell'ATM e chiudi la finestra di login
						frame.hide();
						createATM(utente);
						resultSet.close();
					} else {
						counter--;
						if (counter == 0) {
							dbaccidentata = new database_accidenti(ncarta, "PIN errato");
							JOptionPane.showMessageDialog(null, "TESSERA BLOCCATA! HAI SBAGLIATO PIN 3 VOLTE!!");
						}
						JOptionPane.showMessageDialog(null,
								"Username o password errati; Ancora " + counter + " tentativi rimasti");
						passwordField.setText("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Test
	public void testInitialize() {
		initialize();

		assertNotNull(frame);
		assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
		assertTrue(frame.getContentPane().getLayout() == null);
		assertEquals("BANCA D'ITALIA-bergamo", frame.getTitle());

		assertNotNull(textField);
		assertEquals(10, textField.getColumns());
		assertNotNull(passwordField);

		assertNotNull(atm);
		assertEquals("bergamo", atm.getLuogo());

		JButton btnNewButton = (JButton) frame.getContentPane().getComponent(3);
		assertNotNull(btnNewButton);
		assertEquals("Login", btnNewButton.getText());

		JLabel lblNewLabel = (JLabel) frame.getContentPane().getComponent(0);
		assertNotNull(lblNewLabel);
		assertEquals("NCarta", lblNewLabel.getText());

		JLabel lblNewLabel_1 = (JLabel) frame.getContentPane().getComponent(1);
		assertNotNull(lblNewLabel_1);
		assertEquals("PIN", lblNewLabel_1.getText());
	}

	/**
	 * CONNESSIONE AL DB.
	 */
	public void createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM", "admin", "admin");
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	private void TestCreateConnection() {
		try {
			Class.forName(DB_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			assertTrue(connection != null);
			connection.close();
		} catch (ClassNotFoundException e) {
			fail("Failed to load database driver");
		} catch (SQLException e) {
			fail("Failed to connect to database" + e);
		}
	}

	/**
	 * CREO INTERFACCIA BANCA.
	 */
	private void createATM(Utente utente) {
		// crea la finestra dell'ATM
		JFrame atmFrame = new JFrame("ATM");
		atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		atmFrame.setSize(400, 400);

		// saluto e saldo dell'utente
		JPanel cognomeSaldo = new JPanel();
		JLabel label = new JLabel("UTENTE: " + utente.getNome() + " " + utente.getCognome(), SwingConstants.CENTER);
		cognomeSaldo.add(label);

		// crea il pannello delle opzioni
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new GridLayout(4, 2));
		JButton balanceButton = new JButton("Verifica Saldo");
		JButton withdrawButton = new JButton("Preleva Contante");
		JButton depositButton = new JButton("Deposita Contante");
		JButton transfertButton = new JButton("Trasferisci Saldo");
		JButton viewTransButton = new JButton("Visualizza storico");
		JButton changeButton = new JButton("Cambia PIN");
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

		// azione EXIT
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
				atmFrame.dispose();
			}
		});

		// azione CAMBIO PIN
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean pinCorrect = false;
				while (!pinCorrect) {
					String initialPin = JOptionPane.showInputDialog(null, "Inserisci il nuovo pin (5 cifre):");
					String confirmedPin = JOptionPane.showInputDialog(null, "Conferma il nuovo pin:");
					if (initialPin.equals(confirmedPin) && initialPin.length() == 5) {
						utente.modificaPIN(initialPin);
						t = new Transazione("Cambio PIN", 1, 1, utente.getN_carta(), 0);
						pinCorrect = true;
					} else {
						JOptionPane.showMessageDialog(null, "PIN ERRATO RIPROVA!");
					}
				}
			}
		});

		// azione CANCELLA UTENTE
		closeAccButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				utente.cancellaUTENTE();
			}
		});

		// azione VERIFICA SALDO
		balanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "IL TUO SALDO CONTABILE: " + utente.getCc().getBilancio());
			}
		});

		// azione LEGGI STORICO
		viewTransButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				transactions = new ArrayList<Transazione>();
				transactions = storico(utente.getN_carta());
				printTransactions(transactions, atmFrame);
			}
		});

		// azione PRELEVA CONTANTE
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
				t = new Transazione("Prelievo", 1, 1, utente.getN_carta(), qtaprelievo);
			}
		});

		// azione DEPOSITA CONTANTE
		depositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int qtadeposito = Integer.parseInt(JOptionPane.showInputDialog("Quanto vuoi depositare?"));
				utente.getCc().depositaContante(qtadeposito, utente.getN_conto());
				JOptionPane.showMessageDialog(null,
						"ENTRATA: " + qtadeposito + "€; NUOVO SALDO: " + utente.getCc().getBilancio() + "€");
				t = new Transazione("Deposito", 1, 1, utente.getN_carta(), qtadeposito);
			}
		});

		// azione trasferisci contante
		transfertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// qta - n_conto - cognome beneficiario
				int qtatrasferita = Integer.parseInt(JOptionPane.showInputDialog("Quanto vuoi trasferire?"));
				int beneficiario = Integer
						.parseInt(JOptionPane.showInputDialog("Inserire Numero di CONTO del beneficiario: "));
				String cognomeBeneficiario = JOptionPane.showInputDialog("Inserire il Cognome del beneficiario: ");

				// creo oggetto beneficiario
				if (utente.esisteUTENTE(beneficiario, cognomeBeneficiario)) {
					ubeneficiario = new Utente(beneficiario, cognomeBeneficiario);
					// se carta di credito = no limite prelievo!
					if (!utente.getCc().getImporto_minimo()) {
						utente.getCc().PrelevaContante(qtatrasferita, utente.getN_conto());
						ubeneficiario.getCc().depositaContante(qtatrasferita, ubeneficiario.getN_conto());
					} else if (utente.getCc().getBilancio() > 0 && (utente.getCc().getBilancio() - qtatrasferita) > 0) {
						utente.getCc().PrelevaContante(qtatrasferita, utente.getN_conto());
						ubeneficiario.getCc().depositaContante(qtatrasferita, ubeneficiario.getN_conto());
					} else {
						JOptionPane.showMessageDialog(null, "IL TUO SALDO CONTABILE: " + utente.getCc().getBilancio()
								+ " NON HAI ABBASTANZA SOLDI!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "INSERIRE UN NUMERO DI CONTO/COGNOME CORRETTO!");
				}
				t = new Transazione("Trasferimento", 1, 1, utente.getN_carta(), qtatrasferita);
			}
		});

		// aggiunge i pannelli alla finestra
		// atmFrame.add(loginPanel, BorderLayout.NORTH);
		atmFrame.add(optionsPanel, BorderLayout.SOUTH);
		atmFrame.add(cognomeSaldo, BorderLayout.NORTH);

		// mostra la finestra
		atmFrame.setVisible(true);
	}

	/**
	 * FUNZIONE STAMPA TRANSAZIONI.
	 */
	public static void printTransactions(ArrayList<Transazione> transactions, JFrame framedaaprire) {
		JFrame frame = new JFrame("Transazioni");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				framedaaprire.setVisible(true);
			}
		});
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		for (Transazione t : transactions) {
			JPanel transactionPanel = new JPanel();
			transactionPanel.setLayout(new GridLayout(2, 8, 10, 10));
			transactionPanel.setBorder(BorderFactory.createTitledBorder("Transazione"));

			JLabel n_serieLabel = new JLabel("n_serie: ");
			n_serieLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(n_serieLabel);
			JLabel n_serieValue = new JLabel("" + t.getN_serie());
			n_serieValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(n_serieValue);

			JLabel tipo_transazioneLabel = new JLabel("tipo_transazione: ");
			tipo_transazioneLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(tipo_transazioneLabel);
			JLabel tipo_transazioneValue = new JLabel(t.getTipo_transazione());
			tipo_transazioneValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(tipo_transazioneValue);

			JLabel n_ATMLabel = new JLabel("n_ATM: ");
			n_ATMLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(n_ATMLabel);
			JLabel n_ATMValue = new JLabel("" + t.getN_ATM());
			n_ATMValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(n_ATMValue);

			JLabel n_filialeLabel = new JLabel("n_filiale: ");
			n_filialeLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(n_filialeLabel);
			JLabel n_filialeValue = new JLabel("" + t.getN_filiale());
			n_filialeValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(n_filialeValue);

			JLabel n_cartaLabel = new JLabel("n_carta: ");
			n_cartaLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(n_cartaLabel);
			JLabel n_cartaValue = new JLabel("" + t.getN_carta());
			n_cartaValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(n_cartaValue);

			JLabel importoLabel = new JLabel("importo: ");
			importoLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(importoLabel);
			JLabel importoValue = new JLabel("" + t.getImporto());
			importoValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(importoValue);

			JLabel data_transazioneLabel = new JLabel("data_transazione: ");
			data_transazioneLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(data_transazioneLabel);
			JLabel data_transazioneValue = new JLabel("" + t.getData_transazione().toString());
			data_transazioneValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(data_transazioneValue);

			JLabel ora_transazioneLabel = new JLabel("ora_transazione: ");
			ora_transazioneLabel.setFont(new Font("Arial", Font.BOLD, 14));
			transactionPanel.add(ora_transazioneLabel);
			JLabel ora_transazioneValue = new JLabel("" + t.getOra_transazione().toString());
			ora_transazioneValue.setFont(new Font("Arial", Font.PLAIN, 14));
			transactionPanel.add(ora_transazioneValue);

			panel.add(transactionPanel);
		}
		frame.add(new JScrollPane(panel));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setVisible(true);
	}

	@Test
	public void testPrintTransactions() {
		ArrayList<Transazione> transactions = new ArrayList<>();
		transactions.add(new Transazione(1, "Tipo 1", 1, 1, 1, 100, new Date(System.currentTimeMillis()),
				new Time(System.currentTimeMillis())));
		transactions.add(new Transazione(2, "Tipo 2", 2, 2, 2, 200, new Date(System.currentTimeMillis()),
				new Time(System.currentTimeMillis())));
		JFrame framedaaprire = new JFrame();
		JFrame frame = new JFrame("Transazioni");
		LoginTest.printTransactions(transactions, framedaaprire);
		assertEquals(frame.getDefaultCloseOperation(), JFrame.DISPOSE_ON_CLOSE);
		assertEquals(frame.getTitle(), "Transazioni");
	}

	/**
	 * FUNZIONE CREA ARRAY DI TRANSAZIONI.
	 */
	public ArrayList<Transazione> storico(int n_carta) {
		ArrayList<Transazione> transactions = new ArrayList<Transazione>();
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM transazione where n_carta ='" + n_carta
					+ "' ORDER BY data_transazione,ora_transazione DESC");

			while (rs.next()) {
				int n_serie = rs.getInt("n_serie");
				String tipo_transazione = rs.getString("tipo_transazione");
				int n_ATM = rs.getInt("n_atm");
				int n_filiale = rs.getInt("n_filiale");
				int importo = rs.getInt("importo");
				Date data_transazione = rs.getDate("data_transazione");
				Time ora_transazione = rs.getTime("ora_transazione");

				Transazione t = new Transazione(n_serie, tipo_transazione, n_ATM, n_filiale, n_carta, importo,
						data_transazione, ora_transazione);
				transactions.add(t);
			}

			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return transactions;
	}
}
