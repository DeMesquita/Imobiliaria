package imob.Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import imob.Pojo.Aluguel;
import imob.Pojo.Cliente;
import imob.Pojo.Imobiliaria;
import imob.Pojo.Imovel;

import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ItemListener;
import java.util.Date;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ViewAlugarImovel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeCli;
	private JTextField txtRgCli;
	private JTextField txtTelefoneCli;
	private JTextField txtRuaImo;
	private JTextField txtCepImovel;
	private JTextField txtBairroImo;
	private JTextField txtCidadeImo;
	private JTextField txtTaxaImo;
	private JTextField txtAreaImo;
	private JTextField txtQuartosImo;
	private JTextField txtBanheirosImo;
	private JTextField txtSalasImo;
	private JTextField txtVagasGaragemImo;
	private JTextField txtTipoImo;
	private JLabel lblInfo;
	private JTextField txtEstadoImo;
	private JTextField txtPaisImo;
	private JCheckBox ckbCozinhaImo;
	private JCheckBox ckbAreaServImo;
	private JCheckBox ckbAreaLazImo;
	private JComboBox cbxCpfCli;
	private JComboBox cbxNumeroImo;	
	private JSpinner txtValorAluguel;
	private JSpinner txtTaxaAluguel;
	private JTextArea lblMessage;
	private JFormattedTextField txtDataVenc;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAlugarImovel frame = new ViewAlugarImovel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
     * Método que configurar a mascara no JFormattedTextField
     * @param field Recebe o campo para configurar a mascara do tipo DATA
     * @throws ParseException
     */
	public static void maskData(JFormattedTextField field) throws ParseException{
        MaskFormatter data = new MaskFormatter();
        data.setMask("##/##/####");
        data.install(field);
	}
	
	/**
	 * Create the frame.
	 */
	public ViewAlugarImovel(Imobiliaria imob) {
		setResizable(false);
		setTitle("Alugar Im\u00F3vel");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 633, 462);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.setLayout(null);
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Cliente", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 604, 75);
		contentPane.add(panel);
		
		JLabel label_1 = new JLabel("CPF");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(10, 19, 35, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Nome");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(10, 47, 46, 14);
		panel.add(label_2);
		
		txtNomeCli = new JTextField();
		txtNomeCli.setText("");
		txtNomeCli.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNomeCli.setEditable(false);
		txtNomeCli.setColumns(10);
		txtNomeCli.setBounds(55, 44, 309, 20);
		panel.add(txtNomeCli);
		
		JLabel label_3 = new JLabel("RG");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(387, 47, 28, 14);
		panel.add(label_3);
		
		txtRgCli = new JTextField();
		txtRgCli.setText("");
		txtRgCli.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRgCli.setEditable(false);
		txtRgCli.setColumns(10);
		txtRgCli.setBounds(425, 44, 165, 20);
		panel.add(txtRgCli);
		
		JLabel label_4 = new JLabel("Telefone");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(361, 19, 54, 14);
		panel.add(label_4);
		
		txtTelefoneCli = new JTextField();
		txtTelefoneCli.setText("");
		txtTelefoneCli.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTelefoneCli.setEditable(false);
		txtTelefoneCli.setColumns(10);
		txtTelefoneCli.setBounds(425, 16, 165, 20);
		panel.add(txtTelefoneCli);
		
		cbxCpfCli = new JComboBox();
		cbxCpfCli.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				/*
				 * BUSCA CLIENTE PELO CPF
				 */	
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					String cpf = (String) cbxCpfCli.getSelectedItem();
					try {
						Cliente c = imob.buscarCliCpf(cpf);
						txtNomeCli.setText(c.getNome());
						txtRgCli.setText(c.getRg());
						txtTelefoneCli.setText(c.getTelefone());
					} catch (Exception e) {
						lblInfo.setEnabled(true);
						lblMessage.setForeground(new Color(128, 0, 0));
						lblMessage.setText(e.getMessage());
					}		            
		        }
			}
		});
		cbxCpfCli.setBackground(Color.WHITE);
		cbxCpfCli.setBounds(55, 17, 245, 20);
		panel.add(cbxCpfCli);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setToolTipText("");
		panel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Im\u00F3vel", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 12), null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 97, 604, 203);
		contentPane.add(panel_1);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCep.setBounds(407, 48, 35, 14);
		panel_1.add(lblCep);
		
		cbxNumeroImo = new JComboBox();
		cbxNumeroImo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				/*
				 * BUSCA IMÓVEL PELO NÚMERO
				 */
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String numeroImovel = cbxNumeroImo.getSelectedItem().toString();
					try {
						Imovel imovel = imob.buscarImovel(numeroImovel);
						txtAreaImo.setText(String.valueOf(imovel.getArea()));
						txtBairroImo.setText(imovel.getBairro());
						txtBanheirosImo.setText(String.valueOf(imovel.getBanheiros()));
						txtCidadeImo.setText(imovel.getCidade());
						txtCepImovel.setText(imovel.getCep());
						txtQuartosImo.setText(String.valueOf(imovel.getQuarto()));
						txtRuaImo.setText(imovel.getRua());
						txtSalasImo.setText(String.valueOf(imovel.getSala()));
						txtTaxaImo.setText(String.valueOf(imovel.getTaxa()));
						txtVagasGaragemImo.setText(String.valueOf(imovel.getVagaGaragem()));
						txtEstadoImo.setText(imovel.getEstado());
						txtPaisImo.setText(imovel.getPais());
						txtTipoImo.setText(imovel.getClass().getSimpleName());
						ckbAreaLazImo.setSelected(imovel.isAreaLaz());
						ckbAreaServImo.setSelected(imovel.isAreaServ());
						ckbCozinhaImo.setSelected(imovel.isCozinha());
					} catch (Exception e1) {
						lblInfo.setEnabled(true);
						lblMessage.setForeground(new Color(128, 0, 0));
						lblMessage.setText(e1.getMessage());
					}            
		        }
			}
		});
		cbxNumeroImo.setBackground(Color.WHITE);
		cbxNumeroImo.setBounds(55, 17, 203, 20);
		panel_1.add(cbxNumeroImo);
		
		JLabel label = new JLabel("Rua");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 48, 23, 14);
		panel_1.add(label);
		
		txtRuaImo = new JTextField();
		txtRuaImo.setEditable(false);
		txtRuaImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRuaImo.setColumns(10);
		txtRuaImo.setBounds(54, 45, 329, 20);
		panel_1.add(txtRuaImo);
		
		JLabel label_5 = new JLabel("N\u00BA");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(10, 19, 23, 14);
		panel_1.add(label_5);
		
		txtCepImovel = new JTextField();
		txtCepImovel.setEditable(false);
		txtCepImovel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCepImovel.setColumns(10);
		txtCepImovel.setBounds(452, 44, 138, 20);
		panel_1.add(txtCepImovel);
		
		JLabel label_6 = new JLabel("Bairro");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(10, 80, 35, 14);
		panel_1.add(label_6);
		
		txtBairroImo = new JTextField();
		txtBairroImo.setEditable(false);
		txtBairroImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBairroImo.setColumns(10);
		txtBairroImo.setBounds(54, 77, 308, 20);
		panel_1.add(txtBairroImo);
		
		JLabel label_7 = new JLabel("Cidade");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_7.setBounds(395, 79, 51, 14);
		panel_1.add(label_7);
		
		txtCidadeImo = new JTextField();
		txtCidadeImo.setEditable(false);
		txtCidadeImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCidadeImo.setColumns(10);
		txtCidadeImo.setBounds(452, 76, 138, 20);
		panel_1.add(txtCidadeImo);
		
		JLabel label_9 = new JLabel("Taxa");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_9.setBounds(328, 19, 35, 14);
		panel_1.add(label_9);
		
		txtTaxaImo = new JTextField();
		txtTaxaImo.setEditable(false);
		txtTaxaImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTaxaImo.setColumns(10);
		txtTaxaImo.setBounds(370, 16, 60, 20);
		panel_1.add(txtTaxaImo);
		
		JLabel label_10 = new JLabel("\u00C1rea");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_10.setBounds(477, 19, 25, 14);
		panel_1.add(label_10);
		
		txtAreaImo = new JTextField();
		txtAreaImo.setEditable(false);
		txtAreaImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAreaImo.setColumns(10);
		txtAreaImo.setBounds(511, 16, 79, 20);
		panel_1.add(txtAreaImo);
		
		JLabel label_11 = new JLabel("Estado");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_11.setBounds(10, 108, 45, 14);
		panel_1.add(label_11);
		
		JLabel label_12 = new JLabel("Pa\u00EDs");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_12.setBounds(133, 107, 23, 14);
		panel_1.add(label_12);
		
		JLabel label_8 = new JLabel("Quartos");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_8.setBounds(20, 136, 44, 14);
		panel_1.add(label_8);
		
		txtQuartosImo = new JTextField();
		txtQuartosImo.setEditable(false);
		txtQuartosImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtQuartosImo.setColumns(10);
		txtQuartosImo.setBounds(88, 133, 62, 20);
		panel_1.add(txtQuartosImo);
		
		JLabel label_13 = new JLabel("Banheiros");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_13.setBounds(20, 166, 58, 14);
		panel_1.add(label_13);
		
		txtBanheirosImo = new JTextField();
		txtBanheirosImo.setEditable(false);
		txtBanheirosImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBanheirosImo.setColumns(10);
		txtBanheirosImo.setBounds(88, 163, 62, 20);
		panel_1.add(txtBanheirosImo);
		
		ckbCozinhaImo = new JCheckBox("Cozinha");
		ckbCozinhaImo.setEnabled(false);
		ckbCozinhaImo.setHorizontalAlignment(SwingConstants.LEFT);
		ckbCozinhaImo.setBackground(Color.WHITE);
		ckbCozinhaImo.setBounds(202, 133, 80, 23);
		panel_1.add(ckbCozinhaImo);
		
		ckbAreaServImo = new JCheckBox("Area de Servi\u00E7o");
		ckbAreaServImo.setEnabled(false);
		ckbAreaServImo.setHorizontalAlignment(SwingConstants.LEFT);
		ckbAreaServImo.setBackground(Color.WHITE);
		ckbAreaServImo.setBounds(316, 133, 118, 23);
		panel_1.add(ckbAreaServImo);
		
		ckbAreaLazImo = new JCheckBox("Area de Lazer");
		ckbAreaLazImo.setEnabled(false);
		ckbAreaLazImo.setHorizontalAlignment(SwingConstants.LEFT);
		ckbAreaLazImo.setBackground(Color.WHITE);
		ckbAreaLazImo.setBounds(464, 133, 112, 23);
		panel_1.add(ckbAreaLazImo);
		
		JLabel label_14 = new JLabel("Salas");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_14.setBounds(236, 166, 26, 14);
		panel_1.add(label_14);
		
		txtSalasImo = new JTextField();
		txtSalasImo.setEditable(false);
		txtSalasImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSalasImo.setColumns(10);
		txtSalasImo.setBounds(272, 163, 62, 20);
		panel_1.add(txtSalasImo);
		
		JLabel label_15 = new JLabel("Vagas na Garagem");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_15.setBounds(398, 163, 106, 14);
		panel_1.add(label_15);
		
		txtVagasGaragemImo = new JTextField();
		txtVagasGaragemImo.setEditable(false);
		txtVagasGaragemImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtVagasGaragemImo.setColumns(10);
		txtVagasGaragemImo.setBounds(514, 160, 62, 20);
		panel_1.add(txtVagasGaragemImo);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(395, 108, 51, 14);
		panel_1.add(lblTipo);
		
		txtTipoImo = new JTextField();
		txtTipoImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTipoImo.setEditable(false);
		txtTipoImo.setColumns(10);
		txtTipoImo.setBounds(452, 105, 138, 20);
		panel_1.add(txtTipoImo);
		
		txtEstadoImo = new JTextField();
		txtEstadoImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEstadoImo.setEditable(false);
		txtEstadoImo.setColumns(10);
		txtEstadoImo.setBounds(55, 106, 62, 20);
		panel_1.add(txtEstadoImo);
		
		txtPaisImo = new JTextField();
		txtPaisImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPaisImo.setEditable(false);
		txtPaisImo.setColumns(10);
		txtPaisImo.setBounds(166, 106, 196, 20);
		panel_1.add(txtPaisImo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setToolTipText("Dados do Im\u00F3vel");
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Aluguel", TitledBorder.LEADING, TitledBorder.TOP,  new Font("Tahoma", Font.BOLD, 12), null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 311, 600, 75);
		contentPane.add(panel_2);
		
		JLabel lblDataPagamento = new JLabel("Data Vencimento");
		lblDataPagamento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDataPagamento.setBounds(10, 20, 95, 14);
		panel_2.add(lblDataPagamento);
		
		JLabel lblValorAluguel = new JLabel("Valor do Aluguel");
		lblValorAluguel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValorAluguel.setBounds(10, 45, 95, 14);
		panel_2.add(lblValorAluguel);
		
		JLabel lblTaxaDoAluguel = new JLabel("Taxa do Aluguel");
		lblTaxaDoAluguel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTaxaDoAluguel.setBounds(325, 47, 95, 14);
		panel_2.add(lblTaxaDoAluguel);
		
		txtValorAluguel = new JSpinner();
		txtValorAluguel.setForeground(Color.WHITE);
		txtValorAluguel.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		txtValorAluguel.setBounds(113, 43, 117, 20);
		panel_2.add(txtValorAluguel);
		
		txtTaxaAluguel = new JSpinner();
		txtTaxaAluguel.setForeground(Color.WHITE);
		txtTaxaAluguel.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		txtTaxaAluguel.setBounds(425, 43, 117, 20);
		panel_2.add(txtTaxaAluguel);
		
		txtDataVenc = new JFormattedTextField();
		txtDataVenc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDataVenc.setBounds(115, 18, 137, 20);
		panel_2.add(txtDataVenc);
		
		JButton btnAlugar = new JButton("Alugar");
		btnAlugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAluguel(imob);
			}
		});
		btnAlugar.setIcon(new ImageIcon(ViewAlugarImovel.class.getResource("/imob/Imgs/icon_checked.png")));
		btnAlugar.setForeground(Color.BLACK);
		btnAlugar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAlugar.setBackground(new Color(230, 230, 250));
		btnAlugar.setBounds(503, 397, 111, 25);
		contentPane.add(btnAlugar);
		
		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewAlugarImovel.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(new Color(128, 0, 0));
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(10, 397, 25, 20);
		contentPane.add(lblInfo);
		
		lblMessage = new JTextArea();
		lblMessage.setEditable(false);
		lblMessage.setWrapStyleWord(true);
		lblMessage.setLineWrap(true);
		lblMessage.setColumns(8);
		lblMessage.setRows(2);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMessage.setBounds(45, 388, 448, 45);
		contentPane.add(lblMessage);
		
		listarProprietarios(cbxCpfCli, imob);
		listarImoveis(cbxNumeroImo, imob);
		try {
			maskData(txtDataVenc);
		} catch (ParseException e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
	}
	
	public boolean validarCampos() {
		if((txtNomeCli.getText().equals("")) || txtCepImovel.getText().equals("") || txtDataVenc.getText().equals("  /  /    ")
				|| txtValorAluguel.getValue().equals(new Double(0)) || txtTaxaAluguel.getValue().equals(new Double(0))) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText("Preencha todos os campos!");
			return false;
		}else {
			return true;
		}		
	}
	
	public void limparCampos() {
		lblInfo.setEnabled(false);
		cbxCpfCli.setSelectedIndex(0);
		cbxNumeroImo.setSelectedIndex(0);
		txtNomeCli.setText("");
		txtRgCli.setText("");		
		txtTelefoneCli.setText("");
		txtAreaImo.setText("");
		txtBairroImo.setText("");
		txtBanheirosImo.setText("");
		txtCidadeImo.setText("");
		txtCepImovel.setText("");
		txtQuartosImo.setText("");
		txtRuaImo.setText("");
		txtSalasImo.setText("");
		txtTaxaImo.setText("");
		txtVagasGaragemImo.setText("");
		txtEstadoImo.setText("");
		txtPaisImo.setText("");
		txtTipoImo.setText("");
		txtDataVenc.setText("");
		txtValorAluguel.setValue(new Double(0));
		txtTaxaAluguel.setValue(new Double(0));
		ckbAreaLazImo.setSelected(false);
		ckbAreaServImo.setSelected(false);
		ckbCozinhaImo.setSelected(false);
	}
	
	public void addAluguel(Imobiliaria imob) {
		if(validarCampos()) {
			try {
				Aluguel a = null;
				Cliente c = imob.buscarCli(txtNomeCli.getText(), cbxCpfCli.getSelectedItem().toString());
				Imovel i = imob.buscarImovel(cbxNumeroImo.getSelectedItem().toString());
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date dateVen = formatter.parse(txtDataVenc.getText());
				a = new Aluguel(new Date(), dateVen, Double.parseDouble(txtValorAluguel.getValue().toString()), 0.0, Double.parseDouble(txtTaxaAluguel.getValue().toString()), c, i);
				imob.addAluguel(a);
				limparCampos();
				lblMessage.setForeground(Color.black);
				lblMessage.setText("Aluguel da casa de número "+ i.getNumero() +" efetuada no\nnome do(a) cliente "+ c.getNome() + " com sucesso!");
				listarImoveis(cbxNumeroImo, imob);
				cbxCpfCli.requestFocus();				
			} catch (Exception e) {
				lblInfo.setEnabled(true);
				lblMessage.setForeground(new Color(128, 0, 0));
				lblMessage.setText(e.getMessage());
			}			
		}
	}
	
	public void listarProprietarios(JComboBox c, Imobiliaria imob) {
		/*
		 * LISTA TODOS OS CLIENTES
		 */		
		List<Cliente> clientes = new ArrayList<>(imob.getClientes().values());	
		String[] values = new String[clientes.size()+1];
		values[0] = "Selecione um Cliente";
		int cont = 1;
		for (Cliente p : clientes) {
            values[cont] = p.getCpf();
            cont++;
        }
		c.setModel(new DefaultComboBoxModel(values));
	}
	
	public void listarImoveis(JComboBox c, Imobiliaria imob) {
		/*
		 * LISTA TODOS OS IMÓVEIS NÃO ALUGADOS
		 */		
		List<Imovel> imoveis = new ArrayList<>(imob.getImoveis().values());
		List<Imovel> imoveisNaoALu = new ArrayList<>();
		for (Imovel i : imoveis) {
			if(!i.isStatus()) {
				imoveisNaoALu.add(i);
			}            
        }
		
		String[] values = new String[imoveisNaoALu.size()+1];
		values[0] = "Selecione um Imóvel";
		int cont = 1;
		for (Imovel i : imoveisNaoALu) {
			values[cont] = i.getNumero();
			cont++;
		}
		c.setModel(new DefaultComboBoxModel(values));
	}
}
