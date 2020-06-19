package imob.Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import imob.Pojo.Aluguel;
import imob.Pojo.Imobiliaria;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewEfetuarPagamento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDataVencimento;
	private JTextField txtValorAluguel;
	private JTextField txtValorMulta;
	private String cpfCliente;
	private String numImovel;
	private JLabel lblInfo;
	private JTextArea lblMessage;
	private JTextField txtNomeCli;
	private JTextField txtNumImo;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEfetuarPagamento frame = new ViewEfetuarPagamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ViewEfetuarPagamento(Imobiliaria imob, String cpfCli, String numImov ) {
		setTitle("Realizar Pagamento do Aluguel");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 327, 248);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento");
		lblDataDeVencimento.setBounds(10, 11, 114, 14);
		contentPane.add(lblDataDeVencimento);
		
		JLabel lblValorDoAluguel = new JLabel("Valor do Aluguel");
		lblValorDoAluguel.setBounds(10, 36, 114, 14);
		contentPane.add(lblValorDoAluguel);
		
		JLabel lblValorDaMulta = new JLabel("Valor da Multa");
		lblValorDaMulta.setBounds(10, 61, 114, 14);
		contentPane.add(lblValorDaMulta);
		
		txtDataVencimento = new JTextField();
		txtDataVencimento.setEditable(false);
		txtDataVencimento.setBounds(134, 11, 177, 20);
		contentPane.add(txtDataVencimento);
		txtDataVencimento.setColumns(10);
		
		txtValorAluguel = new JTextField();
		txtValorAluguel.setEditable(false);
		txtValorAluguel.setColumns(10);
		txtValorAluguel.setBounds(134, 36, 177, 20);
		contentPane.add(txtValorAluguel);
		
		txtValorMulta = new JTextField();
		txtValorMulta.setEditable(false);
		txtValorMulta.setColumns(10);
		txtValorMulta.setBounds(134, 61, 177, 20);
		contentPane.add(txtValorMulta);
		
		JButton btnConfirmarPagamento = new JButton("Confirmar Pagamento");
		btnConfirmarPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					imob.realizarPag(cpfCliente, numImovel);
					lblMessage.setText("Pagamento efetuado com sucesso!");
					lblMessage.setForeground(Color.black);
					dispose();
				} catch (Exception e) {
					lblInfo.setEnabled(true);
					lblMessage.setForeground(new Color(128, 0, 0));
					lblMessage.setText(e.getMessage());
				}
			}
		});
		btnConfirmarPagamento.setIcon(new ImageIcon(ViewEfetuarPagamento.class.getResource("/imob/Imgs/icon_checked.png")));
		btnConfirmarPagamento.setForeground(Color.BLACK);
		btnConfirmarPagamento.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConfirmarPagamento.setBackground(new Color(230, 230, 250));
		btnConfirmarPagamento.setBounds(63, 179, 195, 29);
		contentPane.add(btnConfirmarPagamento);
		
		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewEfetuarPagamento.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(10, 154, 20, 23);
		contentPane.add(lblInfo);
		
		lblMessage = new JTextArea();
		lblMessage.setEditable(false);
		lblMessage.setWrapStyleWord(true);
		lblMessage.setLineWrap(true);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessage.setBounds(40, 136, 271, 41);
		contentPane.add(lblMessage);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 86, 114, 14);
		contentPane.add(lblCliente);
		
		txtNomeCli = new JTextField();
		txtNomeCli.setText((String) null);
		txtNomeCli.setEditable(false);
		txtNomeCli.setColumns(10);
		txtNomeCli.setBounds(134, 86, 177, 20);
		contentPane.add(txtNomeCli);
		
		JLabel lblNumImo = new JLabel("N\u00FAmero do Im\u00F3vel");
		lblNumImo.setBounds(10, 111, 114, 14);
		contentPane.add(lblNumImo);
		
		txtNumImo = new JTextField();
		txtNumImo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						imob.realizarPag(cpfCliente, numImovel);
						lblMessage.setText("Pagamento efetuado com sucesso!");
						lblMessage.setForeground(Color.black);
						dispose();
					} catch (Exception e) {
						lblInfo.setEnabled(true);
						lblMessage.setForeground(new Color(128, 0, 0));
						lblMessage.setText(e.getMessage());
					}
				}
			}
		});
		txtNumImo.setText((String) null);
		txtNumImo.setEditable(false);
		txtNumImo.setColumns(10);
		txtNumImo.setBounds(134, 111, 177, 20);
		contentPane.add(txtNumImo);
		
		cpfCliente = cpfCli;
		numImovel = numImov;
		listarInfoAluguel(imob);
	}
	
	public void listarInfoAluguel(Imobiliaria imob) {
		Aluguel a = imob.buscarAluguel(numImovel, cpfCliente);
		txtDataVencimento.setText(a.getDataVenFormatada());
		txtValorAluguel.setText(a.getValorAluguel().toString());
		txtValorMulta.setText(a.getAluguelMulta().toString());
		txtNomeCli.setText(a.getCliente().getNome());
		txtNumImo.setText(a.getImovel().getNumero());
	}
}
