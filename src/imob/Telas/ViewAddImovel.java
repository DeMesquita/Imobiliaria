package imob.Telas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.text.MaskFormatter;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import imob.Pojo.Apartamento;
import imob.Pojo.Casa;
import imob.Pojo.Imobiliaria;
import imob.Pojo.Proprietario;
import imob.Pojo.Sitio;

import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

public class ViewAddImovel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JComboBox cbxCpfPro;
	private JTextField txtNomeProp;
	private JTextField txtRgProp;
	private JTextField txtTelefoneProp;
	private JLabel lblInfo;
	private JLabel lblMessage;
	private JComboBox cbxEstado;
	private JComboBox cbxPais;
	private JCheckBox ckbCozinha;
	private JCheckBox ckbAreaServ;
	private JCheckBox ckbAreaLazer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox ckbCasa;
	private JCheckBox ckbApartamento;
	private JCheckBox ckbSitio;
	private JTextArea txtDescricao;
	private JTextField txtCidade;
	private JTextField txtLoteamento;
	private JTextField txtCondominio;
	private JTextField txtAndar;
	private JTextField txtCasa;
	private JTextField txtCasaCas;
	private JFormattedTextField txtCep;
	private JTextField txtQuarto;
	private JTextField txtBanheiro;
	private JTextField txtSalas;
	private JTextField txtVagasGaragem;
	private JTextField txtTaxa;
	private JTextField txtArea;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAddImovel frame = new ViewAddImovel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/**
     * Método que configurar a mascara no JFormattedTextField
     * @param field Recebe o campo para configurar a mascara do tipo CEP
     * @throws ParseException
     */
	public static void maskCep(JFormattedTextField field) throws ParseException{
        MaskFormatter cep = new MaskFormatter();
        cep.setMask("##.###-###");
        cep.install(field);
	}
	
	public static Dimension redimensionarFrameTotal (){
	    return (
	        new Dimension (
	            (int) Toolkit.getDefaultToolkit ().getScreenSize ().getWidth (),
	            (int) Toolkit.getDefaultToolkit ().getScreenSize ().getHeight ()));
	}
	
	public static Point centralizarFrame (int width, int height){
	    return (
	        new Point (
	            ((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2) - (width/2),
	            ((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2) - (height/2)));
	}

	/**
	 * Create the frame.
	 */
	public ViewAddImovel(Imobiliaria imob) {
		setResizable(false);
		setTitle("Cadastrar Im\u00F3vel");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 626, 558);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Salvar");
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					addImovel(imob);
				}
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addImovel(imob);
			}
		});
		button.setIcon(new ImageIcon(ViewAddImovel.class.getResource("/imob/Imgs/icon_checked.png")));
		button.setBounds(499, 499, 111, 23);
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(new Color(230, 230, 250));
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Dados do Im\u00F3vel");
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Im\u00F3vel", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), null));
		panel.setBounds(10, 11, 600, 159);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRua = new JLabel("Rua");
		lblRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRua.setBounds(10, 27, 23, 14);
		panel.add(lblRua);
		
		txtRua = new JTextField();
		txtRua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRua.setColumns(10);
		txtRua.setBounds(54, 24, 421, 20);
		panel.add(txtRua);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBairro.setBounds(10, 59, 35, 14);
		panel.add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBairro.setColumns(10);
		txtBairro.setBounds(54, 56, 326, 20);
		panel.add(txtBairro);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCep.setBounds(419, 59, 23, 14);
		panel.add(lblCep);
		
		txtCep = new JFormattedTextField();
		txtCep.setBounds(452, 55, 138, 20);
		panel.add(txtCep);
		try {
			maskCep(txtCep);
		} catch (ParseException e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
		
		JLabel lblNumero = new JLabel("N\u00BA");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(497, 26, 23, 14);
		panel.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumero.setColumns(10);
		txtNumero.setBounds(530, 23, 60, 20);
		panel.add(txtNumero);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(10, 87, 45, 14);
		panel.add(lblEstado);
		
		JLabel lblArea = new JLabel("\u00C1rea");
		lblArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArea.setBounds(497, 118, 25, 14);
		panel.add(lblArea);
		
		cbxEstado = new JComboBox();
		cbxEstado.setBackground(Color.WHITE);
		cbxEstado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cbxEstado.setBounds(54, 84, 45, 20);
		panel.add(cbxEstado);
		
		JLabel lblPais = new JLabel("Pa\u00EDs");
		lblPais.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPais.setBounds(133, 86, 23, 14);
		panel.add(lblPais);
		
		cbxPais = new JComboBox();
		cbxPais.setModel(new DefaultComboBoxModel(new String[] {"Afeganist\u00E3o", "\u00C1frica do Sul", "Akrotiri", "Alb\u00E2nia", "Alemanha", "Andorra", "Angola", "Anguila", "Ant\u00E1rctida", "Ant\u00EDgua e Barbuda", "Antilhas Neerlandesas", "Ar\u00E1bia Saudita", "Arctic Ocean", "Arg\u00E9lia", "Argentina", "Arm\u00E9nia", "Aruba", "Ashmore and Cartier Islands", "Atlantic Ocean", "Austr\u00E1lia", "\u00C1ustria", "Azerbaij\u00E3o", "Baamas", "Bangladeche", "Barbados", "Bar\u00E9m", "B\u00E9lgica", "Belize", "Benim", "Bermudas", "Bielorr\u00FAssia", "Birm\u00E2nia", "Bol\u00EDvia", "B\u00F3snia e Herzegovina", "Botsuana", "Brasil", "Brunei", "Bulg\u00E1ria", "Burquina Faso", "Bur\u00FAndi", "But\u00E3o", "Cabo Verde", "Camar\u00F5es", "Camboja", "Canad\u00E1", "Catar", "Cazaquist\u00E3o", "Chade", "Chile", "China", "Chipre", "Clipperton Island", "Col\u00F4mbia", "Comores", "Congo-Brazzaville", "Congo-Kinshasa", "Coral Sea Islands", "Coreia do Norte", "Coreia do Sul", "Costa do Marfim", "Costa Rica", "Cro\u00E1cia", "Cuba", "Dhekelia", "Dinamarca", "Dom\u00EDnica", "Egipto", "Emiratos \u00C1rabes Unidos", "Equador", "Eritreia", "Eslov\u00E1quia", "Eslov\u00E9nia", "Espanha", "Estados Unidos", "Est\u00F3nia", "Eti\u00F3pia", "Faro\u00E9", "Fiji", "Filipinas", "Finl\u00E2ndia", "Fran\u00E7a", "Gab\u00E3o", "G\u00E2mbia", "Gana", "Gaza Strip", "Ge\u00F3rgia", "Ge\u00F3rgia do Sul e Sandwich do Sul", "Gibraltar", "Granada", "Gr\u00E9cia", "Gronel\u00E2ndia", "Guame", "Guatemala", "Guernsey", "Guiana", "Guin\u00E9", "Guin\u00E9 Equatorial", "Guin\u00E9-Bissau", "Haiti", "Honduras", "Hong Kong", "Hungria", "I\u00E9men", "Ilha Bouvet", "Ilha do Natal", "Ilha Norfolk", "Ilhas Caim\u00E3o", "Ilhas Cook", "Ilhas dos Cocos", "Ilhas Falkland", "Ilhas Heard e McDonald", "Ilhas Marshall", "Ilhas Salom\u00E3o", "Ilhas Turcas e Caicos", "Ilhas Virgens Americanas", "Ilhas Virgens Brit\u00E2nicas", "\u00CDndia", "Indian Ocean", "Indon\u00E9sia", "Ir\u00E3o", "Iraque", "Irlanda", "Isl\u00E2ndia", "Israel", "It\u00E1lia", "Jamaica", "Jan Mayen", "Jap\u00E3o", "Jersey", "Jibuti", "Jord\u00E2nia", "Kuwait", "Laos", "Lesoto", "Let\u00F3nia", "L\u00EDbano", "Lib\u00E9ria", "L\u00EDbia", "Listenstaine", "Litu\u00E2nia", "Luxemburgo", "Macau", "Maced\u00F3nia", "Madag\u00E1scar", "Mal\u00E1sia", "Mal\u00E1vi", "Maldivas", "Mali", "Malta", "Man, Isle of", "Marianas do Norte", "Marrocos", "Maur\u00EDcia", "Maurit\u00E2nia", "Mayotte", "M\u00E9xico", "Micron\u00E9sia", "Mo\u00E7ambique", "Mold\u00E1via", "M\u00F3naco", "Mong\u00F3lia", "Monserrate", "Montenegro", "Mundo", "Nam\u00EDbia", "Nauru", "Navassa Island", "Nepal", "Nicar\u00E1gua", "N\u00EDger", "Nig\u00E9ria", "Niue", "Noruega", "Nova Caled\u00F3nia", "Nova Zel\u00E2ndia", "Om\u00E3", "Pacific Ocean", "Pa\u00EDses Baixos", "Palau", "Panam\u00E1", "Papua-Nova Guin\u00E9", "Paquist\u00E3o", "Paracel Islands", "Paraguai", "Peru", "Pitcairn", "Polin\u00E9sia Francesa", "Pol\u00F3nia", "Porto Rico", "Portugal", "Qu\u00E9nia", "Quirguizist\u00E3o", "Quirib\u00E1ti", "Reino Unido", "Rep\u00FAblica Centro-Africana", "Rep\u00FAblica Checa", "Rep\u00FAblica Dominicana", "Rom\u00E9nia", "Ruanda", "R\u00FAssia", "Salvador", "Samoa", "Samoa Americana", "Santa Helena", "Santa L\u00FAcia", "S\u00E3o Crist\u00F3v\u00E3o e Neves", "S\u00E3o Marinho", "S\u00E3o Pedro e Miquelon", "S\u00E3o Tom\u00E9 e Pr\u00EDncipe", "S\u00E3o Vicente e Granadinas", "Sara Ocidental", "Seicheles", "Senegal", "Serra Leoa", "S\u00E9rvia", "Singapura", "S\u00EDria", "Som\u00E1lia", "Southern Ocean", "Spratly Islands", "Sri Lanca", "Suazil\u00E2ndia", "Sud\u00E3o", "Su\u00E9cia", "Su\u00ED\u00E7a", "Suriname", "Svalbard e Jan Mayen", "Tail\u00E2ndia", "Taiwan", "Tajiquist\u00E3o", "Tanz\u00E2nia", "Territ\u00F3rio Brit\u00E2nico do Oceano \u00CDndico", "Territ\u00F3rios Austrais Franceses", "Timor Leste", "Togo", "Tokelau", "Tonga", "Trindade e Tobago", "Tun\u00EDsia", "Turquemenist\u00E3o", "Turquia", "Tuvalu", "Ucr\u00E2nia", "Uganda", "Uni\u00E3o Europeia", "Uruguai", "Usbequist\u00E3o", "Vanuatu", "Vaticano", "Venezuela", "Vietname", "Wake Island", "Wallis e Futuna", "West Bank", "Z\u00E2mbia", "Zimbabu\u00E9"}));
		cbxPais.setBackground(Color.WHITE);
		cbxPais.setBounds(166, 84, 196, 20);
		panel.add(cbxPais);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescricao.setBounds(10, 115, 51, 14);
		panel.add(lblDescricao);
		
		JLabel lblTaxa = new JLabel("Taxa");
		lblTaxa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTaxa.setBounds(384, 118, 35, 14);
		panel.add(lblTaxa);
		
		txtDescricao = new JTextArea();
		txtDescricao.setRows(2);
		txtDescricao.setWrapStyleWord(true);
		txtDescricao.setLineWrap(true);
		txtDescricao.setColumns(8);
		txtDescricao.setBounds(64, 115, 298, 33);
		panel.add(txtDescricao);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(391, 87, 51, 14);
		panel.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCidade.setColumns(10);
		txtCidade.setBounds(452, 84, 138, 20);
		panel.add(txtCidade);
		
		txtTaxa = new JTextField();
		txtTaxa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTaxa.setColumns(10);
		txtTaxa.setBounds(419, 116, 60, 20);
		panel.add(txtTaxa);
		
		txtArea = new JTextField();
		txtArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtArea.setColumns(10);
		txtArea.setBounds(530, 116, 60, 20);
		panel.add(txtArea);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblRua, txtRua, lblBairro, txtBairro, lblCep, lblNumero, txtNumero}));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setToolTipText("Dados do Im\u00F3vel");
		panel_1.setBorder(new TitledBorder(null, "Dados do Propriet\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 181, 600, 99);
		contentPane.add(panel_1);
		
		JLabel lblCpfProp = new JLabel("CPF");
		lblCpfProp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpfProp.setBounds(10, 43, 35, 14);
		panel_1.add(lblCpfProp);
		
		JLabel lblNomeProp = new JLabel("Nome");
		lblNomeProp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNomeProp.setBounds(10, 71, 46, 14);
		panel_1.add(lblNomeProp);
		
		txtNomeProp = new JTextField();
		txtNomeProp.setEditable(false);
		txtNomeProp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNomeProp.setColumns(10);
		txtNomeProp.setBounds(55, 68, 309, 20);
		panel_1.add(txtNomeProp);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRg.setBounds(387, 71, 28, 14);
		panel_1.add(lblRg);
		
		txtRgProp = new JTextField();
		txtRgProp.setEditable(false);
		txtRgProp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRgProp.setColumns(10);
		txtRgProp.setBounds(425, 68, 165, 20);
		panel_1.add(txtRgProp);
		
		JLabel label_2 = new JLabel("Telefone");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(397, 43, 54, 14);
		panel_1.add(label_2);
		
		txtTelefoneProp = new JTextField();
		txtTelefoneProp.setEditable(false);
		txtTelefoneProp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTelefoneProp.setColumns(10);
		txtTelefoneProp.setBounds(452, 40, 138, 20);
		panel_1.add(txtTelefoneProp);
		
		cbxCpfPro = new JComboBox();
		cbxCpfPro.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				/*
				 * BUSCA PROPRIETÁRIO PELO CPF
				 */	
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					String cpf = (String) cbxCpfPro.getSelectedItem();
					try {
						Proprietario p = imob.buscarProp(cpf);
						txtNomeProp.setText(p.getNome());
						txtRgProp.setText(p.getRg());
						txtTelefoneProp.setText(p.getTelefone());
					} catch (Exception e) {
						lblInfo.setEnabled(true);
						lblMessage.setForeground(new Color(128, 0, 0));
						lblMessage.setText(e.getMessage());
					}
		            
		        }
			}
		});
		
		cbxCpfPro.setBackground(Color.WHITE);
		cbxCpfPro.setBounds(55, 41, 245, 20);
		panel_1.add(cbxCpfPro);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("Dados do Im\u00F3vel");
		panel_2.setBorder(new TitledBorder(null, "Comodos do Im\u00F3vel", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 291, 600, 197);
		contentPane.add(panel_2);
		
		JLabel lblQuarto = new JLabel("Quartos");
		lblQuarto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuarto.setBounds(10, 28, 44, 14);
		panel_2.add(lblQuarto);
		
		JLabel lblBanheiro = new JLabel("Banheiros");
		lblBanheiro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBanheiro.setBounds(10, 58, 58, 14);
		panel_2.add(lblBanheiro);
		
		JLabel lblSala = new JLabel("Salas");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSala.setBounds(226, 58, 26, 14);
		panel_2.add(lblSala);
		
		ckbCozinha = new JCheckBox("Cozinha");
		ckbCozinha.setBackground(Color.WHITE);
		ckbCozinha.setHorizontalAlignment(SwingConstants.LEFT);
		ckbCozinha.setBounds(192, 25, 80, 23);
		panel_2.add(ckbCozinha);
		
		ckbAreaServ = new JCheckBox("Area de Servi\u00E7o");
		ckbAreaServ.setBackground(Color.WHITE);
		ckbAreaServ.setHorizontalAlignment(SwingConstants.LEFT);
		ckbAreaServ.setBounds(306, 25, 118, 23);
		panel_2.add(ckbAreaServ);
		
		ckbAreaLazer = new JCheckBox("Area de Lazer");
		ckbAreaLazer.setBackground(Color.WHITE);
		ckbAreaLazer.setHorizontalAlignment(SwingConstants.LEFT);
		ckbAreaLazer.setBounds(454, 25, 112, 23);
		panel_2.add(ckbAreaLazer);
		
		JLabel lblVagasGaragem = new JLabel("Vagas na Garagem");
		lblVagasGaragem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblVagasGaragem.setBounds(388, 55, 106, 14);
		panel_2.add(lblVagasGaragem);
		
		ckbCasa = new JCheckBox("Casa");
		ckbCasa.setSelected(true);
		buttonGroup.add(ckbCasa);
		ckbCasa.setHorizontalAlignment(SwingConstants.LEFT);
		ckbCasa.setBackground(Color.WHITE);
		ckbCasa.setBounds(10, 79, 58, 23);
		panel_2.add(ckbCasa);
		
		ckbApartamento = new JCheckBox("Apartamento");
		buttonGroup.add(ckbApartamento);
		ckbApartamento.setHorizontalAlignment(SwingConstants.LEFT);
		ckbApartamento.setBackground(Color.WHITE);
		ckbApartamento.setBounds(236, 79, 106, 23);
		panel_2.add(ckbApartamento);
		
		ckbSitio = new JCheckBox("S\u00EDtio");
		buttonGroup.add(ckbSitio);
		ckbSitio.setHorizontalAlignment(SwingConstants.LEFT);
		ckbSitio.setBackground(Color.WHITE);
		ckbSitio.setBounds(485, 79, 54, 23);
		panel_2.add(ckbSitio);
		
		JLabel lblLoteamento = new JLabel("Loteamento");
		lblLoteamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLoteamento.setBounds(10, 109, 80, 14);
		panel_2.add(lblLoteamento);
		
		txtLoteamento = new JTextField();
		txtLoteamento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					addImovel(imob);
				}
			}
		});
		txtLoteamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtLoteamento.setColumns(10);
		txtLoteamento.setBounds(10, 126, 180, 20);
		panel_2.add(txtLoteamento);
		
		JLabel lnlCondominio = new JLabel("Condom\u00EDnio");
		lnlCondominio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lnlCondominio.setBounds(237, 110, 80, 14);
		panel_2.add(lnlCondominio);
		
		txtCondominio = new JTextField();
		txtCondominio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					addImovel(imob);
				}
			}
		});
		txtCondominio.setEditable(false);
		txtCondominio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCondominio.setColumns(10);
		txtCondominio.setBounds(237, 127, 187, 20);
		panel_2.add(txtCondominio);
		
		JLabel lblAndar = new JLabel("Andar");
		lblAndar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAndar.setBounds(237, 147, 37, 14);
		panel_2.add(lblAndar);
		
		txtAndar = new JTextField();
		txtAndar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					addImovel(imob);
				}
			}
		});
		txtAndar.setEditable(false);
		txtAndar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAndar.setColumns(10);
		txtAndar.setBounds(237, 165, 62, 20);
		panel_2.add(txtAndar);
		
		JLabel lblCasa = new JLabel("Casas");
		lblCasa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCasa.setBounds(485, 109, 37, 14);
		panel_2.add(lblCasa);
		
		txtCasa = new JTextField();
		txtCasa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCasa.setEditable(false);
		txtCasa.setColumns(10);
		txtCasa.setBounds(485, 127, 62, 20);
		panel_2.add(txtCasa);
		
		JLabel lblCasaCaseiro = new JLabel("Casa Caseiro");
		lblCasaCaseiro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCasaCaseiro.setBounds(484, 148, 82, 14);
		panel_2.add(lblCasaCaseiro);
		
		txtCasaCas = new JTextField();
		txtCasaCas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					addImovel(imob);
				}
			}
		});
		txtCasaCas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCasaCas.setEditable(false);
		txtCasaCas.setColumns(10);
		txtCasaCas.setBounds(484, 166, 62, 20);
		panel_2.add(txtCasaCas);
		
		txtQuarto = new JTextField();
		txtQuarto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuarto.setColumns(10);
		txtQuarto.setBounds(74, 25, 74, 20);
		panel_2.add(txtQuarto);
		
		txtBanheiro = new JTextField();
		txtBanheiro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBanheiro.setColumns(10);
		txtBanheiro.setBounds(74, 56, 74, 20);
		panel_2.add(txtBanheiro);
		
		txtSalas = new JTextField();
		txtSalas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSalas.setColumns(10);
		txtSalas.setBounds(262, 56, 74, 20);
		panel_2.add(txtSalas);
		
		txtVagasGaragem = new JTextField();
		txtVagasGaragem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtVagasGaragem.setColumns(10);
		txtVagasGaragem.setBounds(504, 53, 74, 20);
		panel_2.add(txtVagasGaragem);
		
		listarProprietarios(cbxCpfPro, imob);
		
		JButton btnAtualizarPro = new JButton("");
		btnAtualizarPro.setOpaque(false);
		btnAtualizarPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarProprietarios(cbxCpfPro, imob);
			}
		});
		btnAtualizarPro.setIcon(new ImageIcon(ViewAddImovel.class.getResource("/imob/Imgs/icon_refresh-1.png")));
		btnAtualizarPro.setToolTipText("Cadastrar Novo Propriet\u00E1rio");
		btnAtualizarPro.setForeground(Color.WHITE);
		btnAtualizarPro.setBackground(Color.WHITE);
		btnAtualizarPro.setBounds(339, 40, 25, 23);
		panel_1.add(btnAtualizarPro);
		
		JButton btnCadastrarProp = new JButton("");
		btnCadastrarProp.setBounds(565, 11, 25, 23);
		panel_1.add(btnCadastrarProp);
		btnCadastrarProp.setOpaque(false);
		btnCadastrarProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewAddProprietario v = new ViewAddProprietario(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		btnCadastrarProp.setToolTipText("Cadastrar Novo Propriet\u00E1rio");
		btnCadastrarProp.setIcon(new ImageIcon(ViewAddImovel.class.getResource("/imob/Imgs/icon_add (2).png")));
		btnCadastrarProp.setForeground(Color.WHITE);
		btnCadastrarProp.setBackground(Color.WHITE);
		
		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewAddImovel.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(128, 0, 0));
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(10, 499, 25, 20);
		contentPane.add(lblInfo);
		
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(128, 0, 0));
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessage.setBounds(35, 499, 464, 20);
		contentPane.add(lblMessage);
		
		ckbCasa.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(ckbCasa.isSelected()) {
					txtLoteamento.setEditable(true);
				}else {
					txtLoteamento.setEditable(false);
				}
			}
		});
		
		ckbApartamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(ckbApartamento.isSelected()) {
					txtCondominio.setEditable(true);
					txtAndar.setEditable(true);
				}else {
					txtCondominio.setEditable(false);
					txtAndar.setEditable(false);
				}
			}
		});
		
		ckbSitio.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(ckbSitio.isSelected()) {
					txtCasa.setEditable(true);
					txtCasaCas.setEditable(true);
				}else {
					txtCasa.setEditable(false);
					txtCasaCas.setEditable(false);
				}
			}
		});
		
		preencherCampos();
	}
	
	public void listarProprietarios(JComboBox c, Imobiliaria imob) {
		/*
		 * LISTA TODOS OS PROPRIETÁRIOS
		 */		
		List<Proprietario> proprietarios = new ArrayList<>(imob.getProprietarios().values());	
		String[] values = new String[proprietarios.size()];
		for (Proprietario p : proprietarios) {
            values[p.getCod()] = p.getCpf();
        }		
		c.setModel(new DefaultComboBoxModel(values));
		txtNomeProp.setText("");
		txtRgProp.setText("");
		txtTelefoneProp.setText("");
	}
	
	public boolean validarCampos() {
		if((txtNomeProp.getText().equals("")) || (txtBairro.getText().equals("")) || (txtCep.getText().equals("")) || (txtCidade.getText().equals("")) 
				|| (txtNumero.getText().equals("")) || (txtRua.getText().equals("")) || (txtTaxa.getText().equals(""))
				|| (cbxEstado.getSelectedItem().toString().equals("")) || (cbxPais.getSelectedItem().toString().equals(""))) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText("Preencha todos os campos com *!");
			return false;
		}else{
			return true;
		}		
	}
	
	public void preencherCampos() {
		txtArea.setText("0");
		txtBanheiro.setText("0");
		txtQuarto.setText("0");
		txtSalas.setText("0");
		txtTaxa.setText("0");
		txtVagasGaragem.setText("0");
	}
	
	public void limparCampos() {
		lblInfo.setEnabled(false);
		txtAndar.setText("");
		txtArea.setText("0");
		txtBairro.setText("");
		txtBanheiro.setText("0");
		txtCasa.setText("0");
		txtCasaCas.setText("0");
		txtCep.setText("");
		txtCidade.setText("");
		txtCondominio.setText("");
		txtDescricao.setText("");
		txtLoteamento.setText("");
		txtNumero.setText("");
		txtQuarto.setText("0");
		txtRua.setText("");
		txtSalas.setText("0");
		txtTaxa.setText("0");
		txtVagasGaragem.setText("0");
		cbxCpfPro.setSelectedIndex(0);
		txtNomeProp.setText("");
		txtRgProp.setText("");		
		txtTelefoneProp.setText("");		
		cbxEstado.setSelectedIndex(0);
		cbxPais.setSelectedIndex(0);
		ckbApartamento.setSelected(false);
		ckbAreaLazer.setSelected(false);
		ckbAreaServ.setSelected(false);
		ckbCasa.setSelected(true);
		ckbCozinha.setSelected(false);
		ckbSitio.setSelected(false);
	}
	
	public void addImovel(Imobiliaria imob) {
		if(validarCampos()) {
			try {
				Proprietario p = imob.buscarProp(cbxCpfPro.getSelectedItem().toString());
				if(ckbCasa.isSelected()) {
					imob.addImovel(new Casa(ckbCozinha.isSelected(), Integer.parseInt(txtQuarto.getText()), ckbAreaServ.isSelected(), ckbAreaLazer.isSelected(), 
							Integer.parseInt(txtVagasGaragem.getText()), Integer.parseInt(txtBanheiro.getText()), Integer.parseInt(txtSalas.getText()),
							Integer.parseInt(txtArea.getText()), txtDescricao.getText(), txtRua.getText(), txtNumero.getText(), txtBairro.getText(), txtCidade.getText(), 
							cbxEstado.getSelectedItem().toString(), txtCep.getText(), cbxPais.getSelectedItem().toString(), false, Double.parseDouble(txtTaxa.getText()),
							p, txtLoteamento.getText()));
				}else if(ckbApartamento.isSelected()) {
					imob.addImovel(new Apartamento(ckbCozinha.isSelected(), Integer.parseInt((String) txtQuarto.getText()), ckbAreaServ.isSelected(), ckbAreaLazer.isSelected(), 
							Integer.parseInt((String) txtVagasGaragem.getText()), Integer.parseInt((String)txtBanheiro.getText()), Integer.parseInt((String) txtSalas.getText()),
							Integer.parseInt(txtArea.getText()), txtDescricao.getText(), txtRua.getText(), txtNumero.getText(), txtBairro.getText(), txtCidade.getText(), 
							cbxEstado.getSelectedItem().toString(), txtCep.getText(), cbxPais.getSelectedItem().toString(), false, Double.parseDouble(txtTaxa.getText()),
							p, txtAndar.getText(),txtCondominio.getText()));
				}else if(ckbSitio.isSelected()) {
					imob.addImovel(new Sitio(ckbCozinha.isSelected(), Integer.parseInt((String) txtQuarto.getText()), ckbAreaServ.isSelected(), ckbAreaLazer.isSelected(), 
							Integer.parseInt((String) txtVagasGaragem.getText()), Integer.parseInt((String)txtBanheiro.getText()), Integer.parseInt((String) txtSalas.getText()),
							Integer.parseInt(txtArea.getText()), txtDescricao.getText(), txtRua.getText(), txtNumero.getText(), txtBairro.getText(), txtCidade.getText(), 
							cbxEstado.getSelectedItem().toString(), txtCep.getText(), cbxPais.getSelectedItem().toString(), false, Double.parseDouble(txtTaxa.getText()),
							p, Integer.parseInt(txtCasa.getText()),Integer.parseInt(txtCasaCas.getText())));
				}
				lblMessage.setForeground(Color.black);
				lblMessage.setText("Imóvel "+ txtNumero.getText() +" foi cadastrado no nome de "+ p.getNome() + " com sucesso!");
				limparCampos();
				txtRua.requestFocus();				
			} catch (Exception e) {
				lblInfo.setEnabled(true);
				lblMessage.setForeground(new Color(128, 0, 0));
				lblMessage.setText(e.getMessage());
			}		
		}
	}
}
