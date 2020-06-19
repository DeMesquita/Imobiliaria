package imob.Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import imob.DAO.ClienteDAO;
import imob.Pojo.Cliente;
import imob.Pojo.Imobiliaria;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewListCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField txtNome;
	private JLabel lblMessage;
	private JButton btnListarTodos;
	private JButton btnExcluir;
	private JLabel lblInfo;
	private JFormattedTextField txtCpf;
	private String nomeCli;
	private String cpfCli;
	ClienteDAO clienteDao = new ClienteDAO();

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { ViewListCli frame = new ViewListCli();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

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

	/**
	 * Create the frame.
	 */
	public ViewListCliente(Imobiliaria imob) {
		setResizable(false);
		setMaximumSize(new Dimension(630, 314));
		setBackground(Color.WHITE);
		setType(Type.UTILITY);
		setTitle("Listar Clientes");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 314);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Nome");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(10, 11, 32, 14);
		contentPane.add(label);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!validarNome()) {
					List<Cliente> cli = new ArrayList<>(clienteDao.listarClientes());
					if (verificarRowsTable(cli)) {
						listarClientes(cli);
					}
				} else {
					buscarClienteNome(imob);
				}
			}
		});
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNome.setColumns(10);
		txtNome.setBounds(52, 8, 200, 20);
		contentPane.add(txtNome);

		JLabel label_1 = new JLabel("CPF");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(280, 11, 20, 14);
		contentPane.add(label_1);

		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setViewportBorder(null);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(10, 57, 604, 217);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1){
					btnExcluir.setEnabled(true);
					nomeCli = table.getValueAt(table.getSelectedRow(), 1).toString();
					cpfCli = table.getValueAt(table.getSelectedRow(), 3).toString();					
				}
			}
		});
		
		table.setModel(
				new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"COD", "Nome", "RG", "CPF", "Telefone"
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

		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(51, 102, 153));
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMessage.setBounds(130, 36, 368, 14);
		contentPane.add(lblMessage);

		btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListarTodos.setIcon(new ImageIcon(ViewListCliente.class.getResource("/imob/Imgs/icon_refresh-1.png")));
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * LISTA TODOS OS CLIENTES
				 */
				List<Cliente> cli = new ArrayList<>(clienteDao.listarClientes());
				if (verificarRowsTable(cli)) {
					listarClientes(cli);
					txtNome.setText("");
					txtCpf.setText("");
				}
			}
		});
		btnListarTodos.setForeground(new Color(0, 0, 0));
		btnListarTodos.setBackground(new Color(230, 230, 250));
		btnListarTodos.setBounds(479, 8, 135, 26);
		contentPane.add(btnListarTodos);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					clienteDao.excluirCliente(txtCpf.getText());					
					List<Cliente> cli = new ArrayList<>(clienteDao.listarClientes());
					if (verificarRowsTable(cli)) {
						listarClientes(cli);
					}
					txtCpf.setText("");
					txtNome.setText("");
				} catch (Exception e1) {
					lblMessage.setForeground(new Color(128, 0, 0));
					lblMessage.setText(e1.getMessage());
				}
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(ViewListCliente.class.getResource("/imob/Imgs/icon_trash.png")));
		btnExcluir.setForeground(Color.WHITE);
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setBounds(10, 32, 25, 23);
		contentPane.add(btnExcluir);

		lblInfo = new JLabel("");
		lblInfo.setIcon(new ImageIcon(ViewListCliente.class.getResource("/imob/Imgs/icon_information.png")));
		lblInfo.setEnabled(false);
		lblInfo.setBounds(100, 32, 20, 23);
		contentPane.add(lblInfo);

		txtCpf = new JFormattedTextField();
		txtCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!validarCpf()) {
					List<Cliente> cli = new ArrayList<>(clienteDao.listarClientes());
					if (verificarRowsTable(cli)) {
						listarClientes(cli);
					}
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					buscarClienteCpf(imob);
				}
			}
		});
		txtCpf.setBounds(310, 9, 155, 20);
		contentPane.add(txtCpf);
		try {
			maskCPF(txtCpf);
		} catch (ParseException e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}

		/*
		 * PREENCHE OS DADOS NA TABELA DE CLIENTES
		 */
		defineRenderers();
		List<Cliente> clientes = new ArrayList<>(clienteDao.listarClientes());
		if (verificarRowsTable(clientes)) {
			listarClientes(clientes);
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
		nomeCli = "";
		cpfCli = "";
	}

	public boolean validarNome() {
		if (txtNome.getText().equals("")) return false;
		else return true;
	}
	
	public boolean validarCpf() {
		if (txtCpf.getText().equals("   .   .   -  ")) return false;
		else return true;
	}
	/* *
	  	lblInfo.setEnabled(true);
		lblMessage.setForeground(new Color(128, 0, 0));
		lblMessage.setText("Preencha todos os campos!");
	 * */

	public boolean verificarRowsTable(List<Cliente> c) {
		if (c.size() != 0) {
			return true;
		} else {
			limparTabela(table);
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(51, 102, 153));
			lblMessage.setText("Não há clientes cadastrados no sistema!");
			return false;
		}
	}

	public void listarClientes(List<Cliente> clientes) {
		/*
		 * LISTA TODOS OS CLIENTES
		 */
		limparCampos();
		limparTabela(table);	
		DefaultTableModel modelCliTable = (DefaultTableModel) table.getModel();
		for (Cliente c : clientes) {
			modelCliTable.addRow(new Object[] { c.getCod(), c.getNome(), c.getRg(), c.getCpf(), c.getTelefone() });
		}
		table.setModel(modelCliTable);
	}

	public void buscarClienteNome(Imobiliaria imob) {
		/*
		 * BUSCA O CLIENTE PELO CPF
		 */
		try {
			List<Cliente> clientes = new ArrayList<>(clienteDao.getClienteById(txtNome.getText().toUpperCase()));
			listarClientes(clientes);				
		} catch (Exception e1) {
			lblInfo.setEnabled(true);
			lblMessage.setForeground(new Color(128, 0, 0));
			lblMessage.setText(e1.getMessage());
		}
	}
	
	public void buscarClienteCpf(Imobiliaria imob) {
		/*
		 * BUSCA O CLIENTE PELO NOME
		 */
		try {
			Cliente c = (clienteDao.getClienteByCpf(txtCpf.getText()));
			limparCampos();
			limparTabela(table);
			DefaultTableModel modelCliTable = (DefaultTableModel) table.getModel();
			modelCliTable.addRow(new Object[] { c.getCod(), c.getNome(), c.getRg(), c.getCpf(), c.getTelefone() });
			table.setModel(modelCliTable);
			btnExcluir.setEnabled(true);
			nomeCli = c.getNome();
			cpfCli = c.getCpf();
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
		modeloDaColuna.getColumn(0).setMaxWidth(50);
		modeloDaColuna.getColumn(1).setMaxWidth(205);
		modeloDaColuna.getColumn(2).setMaxWidth(116);
		modeloDaColuna.getColumn(3).setMaxWidth(116);
		modeloDaColuna.getColumn(4).setMaxWidth(116);
	}
}
