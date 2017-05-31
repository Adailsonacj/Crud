package CRUD;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ViewPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton inserirProf = null;
	private JButton inserirInstituicao = null;
	private JScrollPane barraRolagem=null;
	private JTable tabelaCadastros;
	String nome = null;
	String sobrenome = null;
	boolean ok = true;

	/**
	 * Launch the application.
	 */

	CamadaBanco c = new CamadaBanco();
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewPrincipal() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		inserirProf = new JButton("Inserir Prof");
		inserirProf.setBackground(Color.LIGHT_GRAY);
		inserirProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insercao();
				//new ViewInserirProf().setVisible(true);
			}
		});
		inserirProf.setBounds(12, 12, 117, 25);
		contentPane.add(inserirProf);

		inserirInstituicao = new JButton("Inserir instituição");
		inserirInstituicao.setBackground(Color.LIGHT_GRAY);
		inserirInstituicao.setBounds(12, 49, 177, 25);
		contentPane.add(inserirInstituicao);

		tabelaCadastros = new JTable();
		tabelaCadastros.setBounds(48, 112, 234, 335);
		contentPane.add(tabelaCadastros);
		barraRolagem = new JScrollPane(tabelaCadastros);
		contentPane.add(barraRolagem);

		try {
			tabelaCadastros.setModel(DbUtils.resultSetToTableModel(c.listarUsuarios()));

			JLabel lblIdprofessor = new JLabel("IdProfessor");
			lblIdprofessor.setBounds(48, 86, 70, 15);
			contentPane.add(lblIdprofessor);

			JLabel lblNome = new JLabel("Nome");
			lblNome.setBounds(130, 85, 70, 15);
			contentPane.add(lblNome);

			JLabel lblSobrenome = new JLabel("Sobrenome");
			lblSobrenome.setBackground(Color.LIGHT_GRAY);
			lblSobrenome.setBounds(212, 86, 70, 15);
			contentPane.add(lblSobrenome);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void insercao(){
		try{
		nome = JOptionPane.showInputDialog("Nome");
		if(nome.length()>1){
			sobrenome = JOptionPane.showInputDialog("Sobrenome");
			if(sobrenome.length()>1){
				JOptionPane.showMessageDialog(null, "Cadastro concluído!");
				c.inserirProfesores(nome, sobrenome);
			}
		}
		}catch(Exception error){
			JOptionPane.showMessageDialog(null, "Insira um nome válido!");
		}
		if(nome.length()<2||sobrenome.length()<2){
		JOptionPane.showMessageDialog(null, "Insira um nome válido!");
		}
}
}
