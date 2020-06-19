package imob.Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import imob.Pojo.Aluguel;
import imob.Pojo.Imobiliaria;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewVerificarAluguel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtNumeroImo;
	private JLabel lblInfo;
	private JLabel lblMessage;
	private String cpfCliente;
	private String numImovel;
	private JFormattedTextField txtCpfCli;
	private JComboBox cbxTipoImovel;
	private JComboBox cbxStatusAluguel;
	private JButton btnPagar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVerificarAluguel frame = new ViewVerificarAluguel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/**
	 * Método que configurar a mascara no JFormattedTextField
	 * 
	 * @param field
	 *            Recebe o campo para configurar a mascara do tipo CPF
	 * @throws ParseException
	 */
	public static void maskCPF(JFormattedTextField field) throws ParseException {
		MaskFormatter cpf = new MaskFormatter();
		cpf.setMask("###.###.###-##");
		cpf.install(field);
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
	public ViewVerificarAluguel(Imobiliaria imob) {
		setTitle("Verificar Aluguel");
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1040, 485);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 80, 1014, 365);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 1){
					btnPagar.setEnabled(true);
					numImovel = table.getValueAt(table.getSelectedRow(), 4).toString();
					cpfCliente = table.getValueAt(table.getSelectedRow(), 5).toString();
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Valor Aluguel", "Data do Pagamento", "Data do Vencimento", "Tipo", "Nº", "Cliente"
			}
		));
		table.setVerifyInputWhenFocusTarget(false);
		table.setUpdateSelectionOnSort(false);
		table.setGridColor(Color.BLACK);
		table.setFillsViewportHeight(true);
		table.setEditingRow(0);
		table.setEditingColumn(0);
		table.setBackground(new Color(230, 230, 250));
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setBackground(Color.white);
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("N\u00FAmero do Im\u00F3vel");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 18, 107, 14);
		contentPane.add(label);
		
		txtNumeroImo = new JTextField();
		txtNumeroImo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!validarNumero()) {
					List<Aluguel> alugueis = new ArrayList<>(imob.buscarAluguelDevedores());
					if (verificarRowsTable(alugueis)) {
						listarAlugueis(alugueis);
					}
				} else {
					buscarAluguellNum(imob);
				}
			}
		});
		txtNumeroImo.setText("");
		txtNumeroImo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumeroImo.setColumns(10);
		txtNumeroImo.setBounds(127, 15, 106, 20);
		contentPane.add(txtNumeroImo);
		
		JLabel label_1 = new JLabel("Tipo do Im\u00F3vel");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(262, 18, 87, 14);
		contentPane.add(label_1);
		
		cbxTipoImovel = new JComboBox();
		cbxTipoImovel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					List<Aluguel> alugueis = new ArrayList<>(imob.buscarAluguelDevedores());
					listarAlugueisTipo(alugueis, cbxTipoImovel.getSelectedItem().toString());
				}
			}
		});
		cbxTipoImovel.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Casa", "Apartamento", "Sitio"}));
		cbxTipoImovel.setBackground(Color.WHITE);
		cbxTipoImovel.setBounds(359, 16, 107, 20);
		contentPane.add(cbxTipoImovel);
		
		JLabel label_2 = new JLabel("CPF do Cliente");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(512, 17, 81, 14);
		contentPane.add(label_2);
		
		txtCpfCli = new JFormattedTextField();
		txtCpfCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!validarCpf()) {
					List<Aluguel> alu = new ArrayList<>(imob.buscarAluguelDevedores());
					if (verificarRowsTable(alu)) {
						listarAlugueis(alu);
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					buscarAluguelCpfCliente(imob);
				}
			}
		});
		txtCpfCli.setBounds(603, 15, 164, 20);
		contentPane.add(txtCpfCli);
		
		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewVerificarAluguel.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(196, 46, 20, 23);
		contentPane.add(lblInfo);
		
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(128, 0, 0));
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessage.setBounds(226, 50, 513, 14);
		contentPane.add(lblMessage);
			
		cbxStatusAluguel = new JComboBox();
		cbxStatusAluguel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					
					List<Aluguel> alugueisAtr = new ArrayList<>(imob.buscarAluguelDevedores());					
					List<Aluguel> alugueisQui = new ArrayList<>(imob.buscarAluguelQuitados());
					String tAlu = cbxStatusAluguel.getSelectedItem().toString();
					if(tAlu=="Quitados") {
						listarAlugueis(alugueisQui);
					}else{
						listarAlugueis(alugueisAtr);
					}
					
				}
			}
		});
		cbxStatusAluguel.setModel(new DefaultComboBoxModel(new String[] {"Atrasados", "Quitados"}));
		cbxStatusAluguel.setBackground(Color.WHITE);
		cbxStatusAluguel.setBounds(889, 12, 135, 20);
		contentPane.add(cbxStatusAluguel);
		
		JLabel lblStatusAluguel = new JLabel("Status Aluguel");
		lblStatusAluguel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblStatusAluguel.setBounds(800, 15, 87, 14);
		contentPane.add(lblStatusAluguel);
		
		btnPagar = new JButton("Efetuar Pagamento");
		btnPagar.setIcon(new ImageIcon(ViewVerificarAluguel.class.getResource("/imob/Imgs/icon_dollar-symbol.png")));
		btnPagar.setEnabled(false);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewEfetuarPagamento v = new ViewEfetuarPagamento(imob, cpfCliente, numImovel);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		btnPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPagar.setForeground(Color.BLACK);
		btnPagar.setBackground(Color.WHITE);
		btnPagar.setBounds(10, 46, 176, 23);
		contentPane.add(btnPagar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Aluguel> alugueis = new ArrayList<>(imob.buscarAluguelDevedores());
				listarAlugueis(alugueis);
				cbxStatusAluguel.setSelectedIndex(0);
				cbxTipoImovel.setSelectedIndex(0);
			}
		});
		btnAtualizar.setIcon(new ImageIcon(ViewVerificarAluguel.class.getResource("/imob/Imgs/icon_refresh-1.png")));
		btnAtualizar.setForeground(Color.BLACK);
		btnAtualizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAtualizar.setBackground(new Color(230, 230, 250));
		btnAtualizar.setBounds(889, 43, 135, 26);
		contentPane.add(btnAtualizar);
		
		defineRenderers();
		try {
			maskCPF(txtCpfCli);	
		} catch (ParseException e) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e.getMessage());
		}
		imob.verificarMulta();
		List<Aluguel> alugueis = new ArrayList<>(imob.buscarAluguelDevedores());
		listarAlugueis(alugueis);
	}
	
	public boolean verificarRowsTable(List<Aluguel> c) {
		if (c.size() != 0) {
			return true;
		} else {
			limparTabela(table);
			return false;
		}
	}
	
	public void limparTabela(JTable table) {
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		m.setNumRows(0);
		table.setModel(m);
		limparCampos();
	}

	public void limparCampos() {
		lblMessage.setText("");
		lblInfo.setEnabled(false);
		btnPagar.setEnabled(false);
		cpfCliente = "";
		numImovel = "";
	}
	
	public void buscarAluguelCpfCliente(Imobiliaria imob) {
		/*
		 * BUSCA O ALUGUEL PELO CPF DO CLIENTE
		 */
		try {
			List<Aluguel> alugueis = (imob.buscarAluguelDevedoresCpfCliente(txtCpfCli.getText()));
			limparCampos();
			limparTabela(table);
			DefaultTableModel modelAluguelTable = (DefaultTableModel) table.getModel();
			for (Aluguel a : alugueis) {
				modelAluguelTable.addRow(new Object[] { a.getValorAluguel() , a.getDataPagFormatada(), a.getDataVenFormatada(),  
						a.getImovel().getClass().getSimpleName(), a.getImovel().getNumero(), a.getCliente().getCpf() });
			}
			table.setModel(modelAluguelTable);
			
		} catch (Exception e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
	}
	
	public void buscarAluguellNum(Imobiliaria imob) {
		/*
		 * BUSCA O ALUGUEL PELO NÚMERO
		 */
		try {
			List<Aluguel> alugueis = (imob.buscarAluguelDevedoresNumero(txtNumeroImo.getText()));
			limparCampos();
			limparTabela(table);
			DefaultTableModel modelAluguelTable = (DefaultTableModel) table.getModel();
			for (Aluguel a : alugueis) {				
				modelAluguelTable.addRow(new Object[] { a.getValorAluguel() , a.getDataPagFormatada(), a.getDataVenFormatada(),  
						a.getImovel().getClass().getSimpleName(), a.getImovel().getNumero(), a.getCliente().getCpf() });
				if(alugueis.size()==1) {
					numImovel = a.getImovel().getNumero();
					cpfCliente = a.getCliente().getCpf();
				}
			}			
			table.setModel(modelAluguelTable);
		} catch (Exception e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
	}
	
	public void listarAlugueisTipo(List<Aluguel> alugueis, String tipo) {
		/*
		 * LISTA TODOS OS ALUGUÉIS PELO TIPO
		 */
		if(verificarRowsTable(alugueis)) {
			if(!tipo.equals("Todos")) {
				DefaultTableModel modelAluguelTable = (DefaultTableModel) table.getModel();
				limparCampos();
				limparTabela(table);				
				for (Aluguel a : alugueis) {
					if(a.getImovel().getClass().getSimpleName().equals(tipo)) {
						modelAluguelTable.addRow(new Object[] { a.getValorAluguel() , a.getDataPagFormatada(), a.getDataVenFormatada(),  
								a.getImovel().getClass().getSimpleName(), a.getImovel().getNumero(), a.getCliente().getCpf() });
					}				
				}
				table.setModel(modelAluguelTable);
			}else {
				listarAlugueis(alugueis);
			}
		}
	}
	
	public void listarAlugueis(List<Aluguel> alugueis) {
		/*
		 * LISTA TODOS OS ALUGUÉIS
		 */
		if(verificarRowsTable(alugueis)) {
			limparCampos();
			limparTabela(table);
			DefaultTableModel modelAluguelTable = (DefaultTableModel) table.getModel();
			for (Aluguel a : alugueis) {
				modelAluguelTable.addRow(new Object[] { a.getValorAluguel() , a.getDataPagFormatada(), a.getDataVenFormatada(),  
						a.getImovel().getClass().getSimpleName(), a.getImovel().getNumero(), a.getCliente().getCpf() });
			}
			table.setModel(modelAluguelTable);
		}
	}
	
	public boolean validarCpf() {
		if (txtCpfCli.getText().equals("   .   .   -  ")) return false;
		else return true;
	}
	
	public boolean validarNumero() {
		if (txtNumeroImo.getText().equals("")) return false;
		else return true;
	}
	
	public void defineRenderers() {
		DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
		rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
		rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
		rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);
		JTableHeader header = table.getTableHeader(); 
		TableColumnModel modeloDaColuna = table.getColumnModel();
		header.setPreferredSize(new Dimension(0, 25));
		modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(1).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(2).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(3).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(4).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(5).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(0).setMaxWidth(90);
		modeloDaColuna.getColumn(1).setMaxWidth(400);
		modeloDaColuna.getColumn(2).setMaxWidth(400);
		modeloDaColuna.getColumn(3).setMaxWidth(90);
		modeloDaColuna.getColumn(4).setMaxWidth(90);
		modeloDaColuna.getColumn(5).setMaxWidth(120);
	}
}
