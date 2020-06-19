package imob.Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import imob.Pojo.Cliente;
import imob.Pojo.Imobiliaria;
import imob.Pojo.Imovel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;

public class ViewListImovel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField txtNumero;
	private JTable table;
	private JLabel lblInfo;
	private JLabel lblMessage;
	private JButton btnAtualizarTabela;
	private JButton btnExcluir;
	private JFormattedTextField txtCep;
	private String numImovel;
	private String cepImovel;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewListImovel frame = new ViewListImovel();
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
	
	/**
	 * Create the frame.
	 */
	public ViewListImovel(Imobiliaria imob) {
		setTitle("Listar Im\u00F3veis");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 424);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumero = new JLabel("N\u00FAmero");
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumero.setBounds(265, 17, 54, 14);
		contentPane.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!validarNumero()) {
					List<Imovel> imo = new ArrayList<>(imob.getImoveis().values());
					if (verificarRowsTable(imo)) {
						listarImoveis(imo);
					}
				}				
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					buscarImovelNum(imob);
				}
			}
		});
		txtNumero.setText("");
		txtNumero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNumero.setColumns(10);
		txtNumero.setBounds(329, 14, 106, 20);
		contentPane.add(txtNumero);
		
		btnAtualizarTabela = new JButton("Listar Todos");
		btnAtualizarTabela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Imovel> imovels = new ArrayList<>(imob.getImoveis().values());
				if (verificarRowsTable(imovels)) {
					listarImoveis(imovels);
				}
				txtCep.setText("00.000-000");
				txtNumero.setText("");
			}
		});
		btnAtualizarTabela.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAtualizarTabela.setIcon(new ImageIcon(ViewListImovel.class.getResource("/imob/Imgs/icon_refresh-1.png")));
		btnAtualizarTabela.setForeground(Color.BLACK);
		btnAtualizarTabela.setBackground(new Color(230, 230, 250));
		btnAtualizarTabela.setBounds(749, 9, 135, 26);
		contentPane.add(btnAtualizarTabela);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					imob.rvImovel(numImovel);
					List<Imovel> imo = new ArrayList<>(imob.getImoveis().values());
					if (verificarRowsTable(imo)) {
						listarImoveis(imo);
					}
					txtCep.setText("00.000-000");
					txtNumero.setText("");
				} catch (Exception e1) {
					lblMessage.setForeground(new Color(128, 0, 0));
					lblMessage.setText(e1.getMessage());
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(ViewListImovel.class.getResource("/imob/Imgs/icon_trash.png")));
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(10, 35, 25, 23);
		contentPane.add(btnExcluir);
		
		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewListImovel.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(157, 35, 20, 23);
		contentPane.add(lblInfo);
		
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(128, 0, 0));
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessage.setBounds(183, 39, 425, 14);
		contentPane.add(lblMessage);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 60, 874, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1){
					btnExcluir.setEnabled(true);
					numImovel = table.getValueAt(table.getSelectedRow(), 3).toString();				
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tipo", "Propriet\u00E1rio", "Endere\u00E7o", "N\u00BA", "CEP"
			}
		));
		table.setVerifyInputWhenFocusTarget(false);
		table.setUpdateSelectionOnSort(false);
		table.setGridColor(new Color(0, 0, 0));
		table.setEditingRow(0);
		table.setEditingColumn(0);
		table.setBackground(new Color(230, 230, 250));
		table.setAutoCreateRowSorter(true);
		table.getTableHeader().setBackground(Color.white);
		scrollPane.setViewportView(table);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCep.setBounds(10, 14, 35, 14);
		contentPane.add(lblCep);
		
		txtCep = new JFormattedTextField();
		txtCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (!validarCep()) {
					List<Imovel> imo = new ArrayList<>(imob.getImoveis().values());
					if (verificarRowsTable(imo)) {
						listarImoveis(imo);
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					buscarImovelCep(imob);
				}
			}
		});
		txtCep.setBounds(44, 12, 167, 20);
		contentPane.add(txtCep);
		try {
			maskCep(txtCep);
		} catch (ParseException e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(Color.red);
			lblMessage.setText(e1.getMessage());
		}
		
		/*
		 * PREENCHE OS DADOS NA TABELA DE IMÓVEIS
		 */
		defineRenderers();
		List<Imovel> imovels = new ArrayList<>(imob.getImoveis().values());
		if (verificarRowsTable(imovels)) {
			listarImoveis(imovels);
		}
		txtCep.setText("00.000-000");
	}
	
	public void limparTabela(JTable table) {
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		m.setNumRows(0);
		table.setModel(m);
		limparCampos();
	}

	public void limparCampos() {
		lblMessage.setText("");
		btnExcluir.setEnabled(false);
		lblInfo.setEnabled(false);
		numImovel = "";
		cepImovel = "";
	}

	public boolean validarNumero() {
		if (txtNumero.getText().equals("")) return false;
		else return true;
	}
	
	public boolean validarCep() {
		if (txtCep.getText().equals("  .   -   ")) return false;
		else return true;
	}
	
	public boolean verificarRowsTable(List<Imovel> i) {
		if (i.size() != 0) {
			return true;
		} else {
			limparTabela(table);
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(51, 102, 153));
			lblMessage.setText("Não há clientes cadastrados no sistema!");
			return false;
		}
	}
	
	public void listarImoveis(List<Imovel> imoveis) {
		/*
		 * LISTA TODOS OS IMÓVEIS
		 */
		limparCampos();
		limparTabela(table);
		DefaultTableModel modelImovelTable = (DefaultTableModel) table.getModel();
		for (Imovel c : imoveis) {
			modelImovelTable.addRow(new Object[] { c.getClass().getSimpleName(), c.getProprietario().getNome(), 
					"Rua"+c.getRua()+" - "+c.getBairro(), c.getNumero(), c.getCep() });
		}
		table.setModel(modelImovelTable);
	}
	
	public void buscarImovelCep(Imobiliaria imob) {
		/*
		 * BUSCA O CLIENTE PELO CEP
		 */
		try {
			List<Imovel> imoveis = (imob.buscarImovelCep(txtCep.getText()));
			listarImoveis(imoveis);			
		} catch (Exception e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
	}
	
	public void buscarImovelNum(Imobiliaria imob) {
		/*
		 * BUSCA O CLIENTE PELO NÚMERO
		 */
		try {
			Imovel c = (imob.buscarImovel(txtNumero.getText()));
			limparCampos();
			limparTabela(table);
			DefaultTableModel modelImovelTable = (DefaultTableModel) table.getModel();
			modelImovelTable.addRow(new Object[] { c.getClass().getSimpleName(), c.getProprietario().getNome(), 
					"Rua"+c.getRua()+" - "+c.getBairro(), c.getNumero(), c.getCep() });
			table.setModel(modelImovelTable);
			btnExcluir.setEnabled(true);
			numImovel = c.getNumero();
			cepImovel = c.getCep();
		} catch (Exception e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
	}
	
	public void defineRenderers() {
		DefaultTableCellRenderer rendererCentro = new DefaultTableCellRenderer();
		rendererCentro.setHorizontalAlignment(SwingConstants.CENTER);
		DefaultTableCellRenderer rendererDireita = new DefaultTableCellRenderer();
		rendererDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer rendererEsquerda = new DefaultTableCellRenderer();
		rendererEsquerda.setHorizontalAlignment(SwingConstants.LEFT);
		JTableHeader header = table.getTableHeader(); 
		header.setPreferredSize(new Dimension(0, 25)); 
		TableColumnModel modeloDaColuna = table.getColumnModel();
		modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(1).setCellRenderer(rendererEsquerda);
		modeloDaColuna.getColumn(2).setCellRenderer(rendererEsquerda);
		modeloDaColuna.getColumn(3).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(4).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(0).setMaxWidth(80);
		modeloDaColuna.getColumn(1).setPreferredWidth(80);
		modeloDaColuna.getColumn(2).setPreferredWidth(80);
		modeloDaColuna.getColumn(3).setMaxWidth(90);
		modeloDaColuna.getColumn(4).setMaxWidth(100);
	}
}
