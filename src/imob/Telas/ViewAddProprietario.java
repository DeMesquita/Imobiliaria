package imob.Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import imob.Pojo.Imobiliaria;
import imob.Pojo.Proprietario;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

public class ViewAddProprietario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblMessage;
	private JLabel lblInfo;
	private JFormattedTextField txtRg;
	private JFormattedTextField txtCpf;
	private JFormattedTextField txtTelefone;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAddProprietario frame = new ViewAddProprietario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/**
     * Método que configurar a mascara no JFormattedTextField
     * @param field Recebe o campo para configurar a mascara do tipo CPF
     * @throws ParseException
     */
    public static void maskCPF(JFormattedTextField field) throws ParseException{
            MaskFormatter cpf = new MaskFormatter();
            cpf.setMask("###.###.###-##");
            cpf.install(field);
    }
    
    /**
     * Método que configurar a mascara no JFormattedTextField
     * @param field Recebe o campo para configurar a mascara do tipo RG
     * @throws ParseException
     */
    public static void maskRG(JFormattedTextField field) throws ParseException{
            MaskFormatter rg = new MaskFormatter();
            rg.setMask("#############");
            rg.install(field);
    }
    
    /**
     * Método que configurar a mascara no JFormattedTextField
     * @param field Recebe o campo para configurar a mascara do tipo telefone
     * @throws ParseException
     */
    public static void maskFone(JFormattedTextField field) throws ParseException{
        MaskFormatter fone = new MaskFormatter();
        fone.setMask("(##)#####-####");
        fone.install(field);
        field.setText("");
    }

	/**
	 * Create the frame.
	 */
	public ViewAddProprietario(Imobiliaria imob) {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Cadastrar Propriet\u00E1rio");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 420, 228);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(10, 14, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setColumns(10);
		txtNome.setBounds(66, 11, 333, 20);
		contentPane.add(txtNome);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRg.setBounds(10, 45, 46, 14);
		contentPane.add(lblRg);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpf.setBounds(10, 73, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefone.setBounds(10, 104, 69, 14);
		contentPane.add(lblTelefone);
		
		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewAddProprietario.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(128, 0, 0));
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(10, 126, 25, 20);
		contentPane.add(lblInfo);
		
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(128, 0, 0));
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMessage.setBounds(34, 129, 370, 14);
		contentPane.add(lblMessage);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSalvar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
					addProprietario(imob);
				}
			}
		});
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProprietario(imob);
			}
		});
		btnSalvar.setIcon(new ImageIcon(ViewAddProprietario.class.getResource("/imob/Imgs/icon_checked.png")));
		btnSalvar.setForeground(new Color(0, 0, 0));
		btnSalvar.setBackground(new Color(230, 230, 250));
		btnSalvar.setBounds(292, 155, 107, 29);
		contentPane.add(btnSalvar);
		
		txtRg = new JFormattedTextField();
		txtRg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRg.setColumns(13);
		txtRg.setBounds(66, 42, 333, 20);
		contentPane.add(txtRg);
		try {
			maskRG(txtRg);
		} catch (ParseException e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
		
		txtCpf = new JFormattedTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCpf.setBounds(66, 71, 333, 20);
		contentPane.add(txtCpf);
		try {
			maskCPF(txtCpf);
		} catch (ParseException e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
		
		txtTelefone = new JFormattedTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					addProprietario(imob);
				}
			}
		});
		txtTelefone.setText("");
		txtTelefone.setBounds(66, 102, 333, 20);
		contentPane.add(txtTelefone);
		try {
			maskFone(txtTelefone);
		} catch (ParseException e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
	}
	
	public void limparCampos() {
		lblInfo.setEnabled(false);
		txtNome.setText("");
		txtRg.setText("");
		txtCpf.setText("");
		txtTelefone.setText("");
	}
	
	public boolean validarCampos() {
		if((txtNome.getText().equals("")) || (txtCpf.getText().equals("")) || (txtRg.getText().equals("")) || (txtTelefone.getText().equals(""))) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText("Preencha todos os campos!");
			return false;
		}else {
			return true;
		}		
	}
	
	public void addProprietario(Imobiliaria imob) {
		if(validarCampos()) {
			try {
				Proprietario p = new Proprietario(txtNome.getText(), txtRg.getText(), txtCpf.getText(), txtTelefone.getText());
				imob.addProp(p);
				lblMessage.setForeground(Color.black);
				lblMessage.setText("Proprietário "+ txtNome.getText() +" cadastrado com sucesso!");
				limparCampos();
			} catch (Exception e1) {
				lblInfo.setEnabled(true);
				lblMessage.setForeground(new Color(128, 0, 0));
				lblMessage.setText(e1.getMessage());
			}
			txtNome.requestFocus();
		}
	}
}
