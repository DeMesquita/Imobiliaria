package imob.Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import imob.Pojo.Aluguel;
import imob.Pojo.Cliente;
import imob.Pojo.Imobiliaria;
import imob.Pojo.Imovel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ViewListAlugueis extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeroImo;
	private JTable table;
	private JButton btnExcluir;
	private JLabel lblInfo;
	private JLabel lblMessage;
	private String cpfCliente;
	private String numImovel;
	private JLabel lblTipoDoImvel;
	private JFormattedTextField txtCpfCli;
	private JComboBox cbxTipoImovel;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewListAlugueis frame = new ViewListAlugueis();
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
	 * Create the frame.
	 */
	public ViewListAlugueis(Imobiliaria imob) {
		setTitle("Listar Alugu\u00E9is");
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1033, 426);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNmeroDoImvel = new JLabel("N\u00FAmero do Im\u00F3vel");
		lblNmeroDoImvel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNmeroDoImvel.setBounds(10, 14, 107, 14);
		contentPane.add(lblNmeroDoImvel);
		
		txtNumeroImo = new JTextField();
		txtNumeroImo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!validarNumero()) {
					List<Aluguel> alugueis = new ArrayList<>(imob.getAlugueis().values());
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
		txtNumeroImo.setBounds(127, 11, 106, 20);
		contentPane.add(txtNumeroImo);
		
		JButton btnAtualizarAlugueis = new JButton("Listar Todos");
		btnAtualizarAlugueis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Aluguel> alugueis = new ArrayList<>(imob.getAlugueis().values());
				listarAlugueis(alugueis);
				txtCpfCli.setValue("");
				txtNumeroImo.setText("");
				cbxTipoImovel.setSelectedIndex(0);
			}
		});
		btnAtualizarAlugueis.setIcon(new ImageIcon(ViewListAlugueis.class.getResource("/imob/Imgs/icon_refresh-1.png")));
		btnAtualizarAlugueis.setForeground(Color.BLACK);
		btnAtualizarAlugueis.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAtualizarAlugueis.setBackground(new Color(230, 230, 250));
		btnAtualizarAlugueis.setBounds(882, 9, 135, 26);
		contentPane.add(btnAtualizarAlugueis);
		
		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					imob.rvAluguel(cpfCliente, numImovel);
					List<Aluguel> alu = new ArrayList<>(imob.getAlugueis().values());
					if (verificarRowsTable(alu)) {
						listarAlugueis(alu);
					}
					txtNumeroImo.setText("");
					txtCpfCli.setText("");
				} catch (Exception e1) {
					lblMessage.setForeground(new Color(128, 0, 0));
					lblMessage.setText(e1.getMessage());
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(ViewListAlugueis.class.getResource("/imob/Imgs/icon_trash.png")));
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setEnabled(false);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(10, 37, 25, 23);
		contentPane.add(btnExcluir);
		
		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewListAlugueis.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(157, 37, 20, 23);
		contentPane.add(lblInfo);
		
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(128, 0, 0));
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessage.setBounds(183, 41, 511, 14);
		contentPane.add(lblMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 62, 1007, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 1){
					btnExcluir.setEnabled(true);
					numImovel = table.getValueAt(table.getSelectedRow(), 0).toString();
					cpfCliente = table.getValueAt(table.getSelectedRow(), 4).toString();
				}
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Número", "Imóvel", "Endereço do Imóvel","Cliente", "CPF do Cliente", "Valor"
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
		
		JLabel lblCpfDoCliente = new JLabel("CPF do Cliente");
		lblCpfDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpfDoCliente.setBounds(495, 13, 81, 14);
		contentPane.add(lblCpfDoCliente);
		
		txtCpfCli = new JFormattedTextField();
		txtCpfCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!validarCpf()) {
					List<Aluguel> alu = new ArrayList<>(imob.getAlugueis().values());
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
		txtCpfCli.setBounds(586, 11, 164, 20);
		contentPane.add(txtCpfCli);
		
		lblTipoDoImvel = new JLabel("Tipo do Im\u00F3vel");
		lblTipoDoImvel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipoDoImvel.setBounds(262, 14, 87, 14);
		contentPane.add(lblTipoDoImvel);
		
		cbxTipoImovel = new JComboBox();
		cbxTipoImovel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange() == ItemEvent.SELECTED) {
					List<Aluguel> alugueis = new ArrayList<>(imob.getAlugueis().values());
					listarAlugueisTipo(alugueis, cbxTipoImovel.getSelectedItem().toString());
				}
			}
		});
		cbxTipoImovel.setBackground(Color.WHITE);
		cbxTipoImovel.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Casa", "Apartamento", "Sitio"}));
		cbxTipoImovel.setBounds(359, 12, 107, 20);
		contentPane.add(cbxTipoImovel);
		
		defineRenderers();
		List<Aluguel> alugueis = new ArrayList<>(imob.getAlugueis().values());
		listarAlugueis(alugueis);
		try {
			maskCPF(txtCpfCli);
		} catch (ParseException e) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e.getMessage());
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
		btnExcluir.setEnabled(false);
		lblInfo.setEnabled(false);
		cpfCliente = "";
		numImovel = "";
	}
	
	public boolean validarCpf() {
		if (txtCpfCli.getText().equals("   .   .   -  ")) return false;
		else return true;
	}
	
	public boolean validarNumero() {
		if (txtNumeroImo.getText().equals("")) return false;
		else return true;
	}
	
	public boolean verificarRowsTable(List<Aluguel> c) {
		if (c.size() != 0) {
			return true;
		} else {
			limparTabela(table);
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(51, 102, 153));
			lblMessage.setText("Não há alugueis cadastrados no sistema!");
			return false;
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
				modelAluguelTable.addRow(new Object[] { a.getImovel().getNumero(), a.getImovel().getClass().getSimpleName(), 
						a.getImovel().getRua()+", "+a.getImovel().getBairro()+", "+a.getImovel().getCidade()+"-"+a.getImovel().getEstado()+". "+a.getImovel().getCep(),
						a.getCliente().getNome(), a.getCliente().getCpf(), a.getValorAluguel() });
			}
			table.setModel(modelAluguelTable);
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
						modelAluguelTable.addRow(new Object[] { a.getImovel().getNumero(), a.getImovel().getClass().getSimpleName(), 
								a.getImovel().getRua()+", "+a.getImovel().getBairro()+", "+a.getImovel().getCidade()+"-"+a.getImovel().getEstado()+". "+a.getImovel().getCep(),
								a.getCliente().getNome(), a.getCliente().getCpf(), a.getValorAluguel() });
					}				
				}
				table.setModel(modelAluguelTable);
			}else {
				listarAlugueis(alugueis);
			}
		}
	}
	
	public void buscarAluguellNum(Imobiliaria imob) {
		/*
		 * BUSCA O ALUGUEL PELO NÚMERO
		 */
		try {
			List<Aluguel> alugueis = (imob.buscarAluguelNumero(txtNumeroImo.getText()));
			limparCampos();
			limparTabela(table);
			DefaultTableModel modelAluguelTable = (DefaultTableModel) table.getModel();
			for (Aluguel a : alugueis) {				
				modelAluguelTable.addRow(new Object[] { a.getImovel().getNumero(), a.getImovel().getClass().getSimpleName(), 
						a.getImovel().getRua()+", "+a.getImovel().getBairro()+", "+a.getImovel().getCidade()+"-"+a.getImovel().getEstado()+". "+a.getImovel().getCep(),
						a.getCliente().getNome(), a.getCliente().getCpf(), a.getValorAluguel() });
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
	
	public void buscarAluguelCpfCliente(Imobiliaria imob) {
		/*
		 * BUSCA O ALUGUEL PELO CPF DO CLIENTE
		 */
		try {
			List<Aluguel> alugueis = (imob.buscarAluguelCpfCliente(txtCpfCli.getText()));
			limparCampos();
			limparTabela(table);
			DefaultTableModel modelAluguelTable = (DefaultTableModel) table.getModel();
			for (Aluguel a : alugueis) {
				modelAluguelTable.addRow(new Object[] { a.getImovel().getNumero(), a.getImovel().getClass().getSimpleName(), 
						a.getImovel().getRua()+", "+a.getImovel().getBairro()+", "+a.getImovel().getCidade()+"-"+a.getImovel().getEstado()+". "+a.getImovel().getCep(),
						a.getCliente().getNome(), a.getCliente().getCpf(), a.getValorAluguel() });
			}
			table.setModel(modelAluguelTable);
			
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
		TableColumnModel modeloDaColuna = table.getColumnModel();
		header.setPreferredSize(new Dimension(0, 25));
		modeloDaColuna.getColumn(0).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(1).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(2).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(3).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(4).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(5).setCellRenderer(rendererCentro);
		modeloDaColuna.getColumn(0).setMaxWidth(90);
		modeloDaColuna.getColumn(1).setMaxWidth(100);
		modeloDaColuna.getColumn(2).setMaxWidth(400);
		modeloDaColuna.getColumn(3).setMaxWidth(300);
		modeloDaColuna.getColumn(4).setMaxWidth(120);
		modeloDaColuna.getColumn(5).setMaxWidth(60);
	}
}
