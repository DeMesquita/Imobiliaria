package imob.Principal;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.SwingConstants;

import imob.Pojo.Aluguel;
import imob.Pojo.Cliente;
import imob.Pojo.Imobiliaria;
import imob.Pojo.Imovel;
import imob.Pojo.Proprietario;
import imob.Telas.ViewAddCliente;
import imob.Telas.ViewAddImovel;
import imob.Telas.ViewAlugarImovel;
import imob.Telas.ViewListAlugueis;
import imob.Telas.ViewListCliente;
import imob.Telas.ViewListImovel;
import imob.Telas.ViewVerificarAluguel;

import java.awt.Font;

import javax.swing.JLabel;

public class ViewPrincipal extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Imobiliaria imob = new Imobiliaria();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
					frame.setVisible(true);
					//frame.setResizable(false);
					frame.isMaximumSizeSet();
					frame.setLocation(centralizarFrame(frame.getWidth(), frame.getHeight()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	public ViewPrincipal() {
		getContentPane().setBackground(new Color(255, 255, 255));
		IniciarImob(imob);
		setResizable(false);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewPrincipal.class.getResource("/imob/Imgs/icon_2_key_imob.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setForeground(Color.LIGHT_GRAY);
		setTitle("ImobSN");
		setBounds(100, 100, 1196, 700);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMaximumSize(new Dimension(0, 20));
		menuBar.setMargin(new Insets(20, 0, 20, 0));
		menuBar.setBorder(null);
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuBar);
		
		JMenu mnCliente = new JMenu("Cliente");
		mnCliente.setBorder(null);
		mnCliente.setMargin(new Insets(20, 2, 20, 2));
		mnCliente.setForeground(new Color(0, 0, 0));
		mnCliente.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnCliente.setHorizontalAlignment(SwingConstants.CENTER);
		mnCliente.setBackground(new Color(230, 230, 250));
		mnCliente.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_cliente_1.png")));
		menuBar.add(mnCliente);
		
		JMenuItem mntmCadastrarCliente = new JMenuItem("Cadastrar");
		mntmCadastrarCliente.setBorder(null);
		mntmCadastrarCliente.setOpaque(true);
		mntmCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewAddCliente v = new ViewAddCliente(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);				
			}
		});
		mntmCadastrarCliente.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_add (2).png")));
		mntmCadastrarCliente.setBackground(new Color(230, 230, 250));
		mnCliente.add(mntmCadastrarCliente);
		
		JMenuItem mntmListarCliente = new JMenuItem("Listar");
		mntmListarCliente.setBorder(null);
		mntmListarCliente.setOpaque(true);
		mntmListarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewListCliente v = new ViewListCliente(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		mntmListarCliente.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_list.png")));
		mntmListarCliente.setBackground(new Color(230, 230, 250));
		mnCliente.add(mntmListarCliente);
		
		JMenu mnImovel = new JMenu("Im\u00F3vel");
		mnImovel.setMargin(new Insets(20, 2, 20, 2));
		mnImovel.setForeground(new Color(0, 0, 0));
		mnImovel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnImovel.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_house.png")));
		mnImovel.setBackground(Color.WHITE);
		menuBar.add(mnImovel);
		
		JMenuItem mntmCadastrarImovel = new JMenuItem("Cadastrar");
		mntmCadastrarImovel.setBorder(null);
		mntmCadastrarImovel.setOpaque(true);
		mntmCadastrarImovel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAddImovel v = new ViewAddImovel(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		mntmCadastrarImovel.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_add (2).png")));
		mntmCadastrarImovel.setBackground(new Color(230, 230, 250));
		mnImovel.add(mntmCadastrarImovel);
		
		JMenuItem mntmListarImovel = new JMenuItem("Listar");
		mntmListarImovel.setBorder(null);
		mntmListarImovel.setOpaque(true);
		mntmListarImovel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewListImovel v = new ViewListImovel(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		mntmListarImovel.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_list.png")));
		mntmListarImovel.setBackground(new Color(230, 230, 250));
		mnImovel.add(mntmListarImovel);
		
		JMenu mnAdministrativo = new JMenu("Administrativo");
		mnAdministrativo.setMargin(new Insets(20, 2, 20, 2));
		mnAdministrativo.setForeground(new Color(0, 0, 0));
		mnAdministrativo.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_agent_2.png")));
		mnAdministrativo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnAdministrativo.setBackground(new Color(204, 204, 255));
		menuBar.add(mnAdministrativo);
		
		JMenuItem mntmAlugarImovel = new JMenuItem("Alugar Im\u00F3vel");
		mntmAlugarImovel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewAlugarImovel v = new ViewAlugarImovel(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		mntmAlugarImovel.setBorder(null);
		mntmAlugarImovel.setOpaque(true);
		mntmAlugarImovel.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_imob_.png")));
		mntmAlugarImovel.setBackground(new Color(230, 230, 250));
		mnAdministrativo.add(mntmAlugarImovel);
		
		JMenuItem mntmVerificarAlugueis = new JMenuItem("Verificar Alugu\u00E9is");
		mntmVerificarAlugueis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewVerificarAluguel v = new ViewVerificarAluguel(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		mntmVerificarAlugueis.setBorder(null);
		mntmVerificarAlugueis.setOpaque(true);
		mntmVerificarAlugueis.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_duplex-1.png")));
		mntmVerificarAlugueis.setBackground(new Color(230, 230, 250));
		mnAdministrativo.add(mntmVerificarAlugueis);
		
		JMenuItem mntmListarAlugueis = new JMenuItem("Listar Alugu\u00E9is");
		mntmListarAlugueis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewListAlugueis v = new ViewListAlugueis(imob);
				v.setVisible(true);
				v.setResizable(false);
				v.setLocation(centralizarFrame(v.getWidth(), v.getHeight()));
				v.setFocusableWindowState(true);
			}
		});
		mntmListarAlugueis.setBorder(null);
		mntmListarAlugueis.setOpaque(true);
		mntmListarAlugueis.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/icon_property.png")));
		mntmListarAlugueis.setBackground(new Color(230, 230, 250));
		mnAdministrativo.add(mntmListarAlugueis);
		getContentPane().setLayout(null);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 1190, 645);
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imob/Imgs/wallpaperblue 1 34526jpg.jpg")));
		getContentPane().add(lblBackground);
	}
	
	public void IniciarImob(Imobiliaria imob) {
//		/*
//		 * VARIÁVEIS DE CLIENTES E PROPRIETÁRIOS
//		 * */
//		Cliente c1 = new Cliente("Lívia Almada", "8888888888888", "888.999.999-99", "(88)99999-9999");
//		Cliente c2 = new Cliente("Maria Glades da Silva", "1111111111111", "999.888.999-99", "(88)99999-9999");
//		Cliente c3 = new Cliente("Elisa de Sousa", "222222222222", "999.999.889-99", "(88)99999-9999");
//		Cliente c4 = new Cliente("Henrique de Melo", "4545864597526", "999.459.999-99", "(88)99999-9999");
//		Cliente c5 = new Cliente("Lívia Almada", "1231544586423", "999.999.759-99", "(88)99999-9999");
//		Cliente c6 = new Cliente("Maria do Céu Farias", "9878564254789", "999.999.999-99", "(88)99999-9999");
//		Cliente c7 = new Cliente("Jefferson Gleyser", "2156435486452", "999.987.999-99", "(88)99999-9999");
//		Cliente c8 = new Cliente("Júnior Ventura", "2135484687513", "459.999.999-99", "(88)99999-9999");
//		Cliente c9 = new Cliente("Aloísio Saraiva", "9897854687973", "999.999.999-49", "(88)99999-9999");
//		Cliente c10 = new Cliente("Vanessa Carmelo", "8564235487965", "999.929.999-99", "(88)99999-9999");
//		
//		Proprietario p1 = new Proprietario("Maria do Carmo", "3544851245697", "999.999.899-99", "(88)99999-9999");
//		Proprietario p2 = new Proprietario("Eduarda Nunes", "2156435486452", "945.987.999-99", "(88)99999-9999");
//		Proprietario p3 = new Proprietario("Carlos Henrique", "2135484687513", "459.999.999-99", "(88)99999-9999");
//		Proprietario p4 = new Proprietario("Aloísio Saraiva", "9897854687973", "359.999.999-49", "(88)99999-9999");
//		Proprietario p5 = new Proprietario("Vanessa Carmelo", "8564235487965", "978.229.999-99", "(88)99999-9999");
//		
//		Imovel i1 = new Casa(true, 5, true, false, 1, 5, 2, 230.00, "Perto do Centro", "Rubens Monte", "124A", "Maraponga", 
//				"Fortaleza", "CE", "63.904-000", "Brasil", false, 34.00, p1, "Terréo");
//		Imovel i2 = new Casa(true, 2, true, true, 1, 5, 2, 800.00, "Perto do Praia", "Centro-Sul", "185A3", "Copacabana", 
//				"São Paulo", "CE", "63.989-000", "Brasil", false, 78.00, p2, "Terréo");
//		Imovel i3 = new Apartamento(true, 3, false, false, 1, 5, 2, 480.00, "Perto do Centro", "Doutor Eudásio Barroso", "845J", "Maraponga", 
//				"Quixadá", "CE", "63.904-000", "Brasil", false, 34.00, p4, "Pedra dos Ventos", "2");
//		Imovel i4 = new Casa(true, 5, true, false, 1, 5, 2, 230.00, "Perto do Centro", "Rubens Monte", "3257", "Maraponga", 
//				"Fortaleza", "CE", "63.904-000", "Brasil", false, 34.00, p3, "Terréo");
//		Imovel i5 = new Sitio(true, 15, true, true, 5, 10, 2, 2480.00, "Perto do Centro", "Rubens Monte", "4M58", "Maraponga", 
//				"Fortaleza", "CE", "63.904-000", "Brasil", false, 120.00, p5, 5, 1);
//		
//		/*
//		 * ADICIONANDO CLIENTES
//		 * */
//		try {
//			imob.addCli(c1);
//			imob.addCli(c2);
//			imob.addCli(c3);
//			imob.addCli(c4);
//			imob.addCli(c5);
//			imob.addCli(c6);
//			imob.addCli(c7);
//			imob.addCli(c8);
//			imob.addCli(c9);
//			imob.addCli(c10);
//		} catch (Exception e) {
//		}
//		
//		/*
//		 * ADICIONANDO PROPRIETÁRIOS
//		 * */
//		try {
//			imob.addProp(p1);
//			imob.addProp(p2);
//			imob.addProp(p3);
//			imob.addProp(p4);
//			imob.addProp(p5);
//		} catch (Exception e) {
//		}
//		
//		/*
//		 * ADICIONANDO IMÓVEIS
//		 * */
//		try {
//			imob.addImovel(i1);
//			imob.addImovel(i2);
//			imob.addImovel(i3);
//			imob.addImovel(i4);
//			imob.addImovel(i5);
//			
//		} catch (Exception e) {
//		}
//		
//		/*
//		 * ALUGANDO IMÓVEIS
//		 * */
//		SimpleDateFormat formato = new SimpleDateFormat( "dd-MM-yyyy" );
//		try {
//			Date data = formato.parse("23-11-2017"); 
//			imob.addAluguel(new Aluguel(new Date(), data, 450.0, 0.0, 10.0, c1, i1));
//			imob.addAluguel(new Aluguel(new Date(), data, 850.0, 0.0, 10.0, c3, i2));
//			imob.addAluguel(new Aluguel(new Date(), new Date(), 740.0, 10.0, 0.0, c4, i3));
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	}
}
