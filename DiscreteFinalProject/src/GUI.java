import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.JFrame;


public class GUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private int total=0;
	
	private int index = -1;
	private ArrayList<Node> nodes = new ArrayList<>();
	
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JPanel panel4 = new JPanel();
	
	private JLabel label1= new JLabel("V = ");
	private JLabel label2= new JLabel("W = ");
	
	private JTextField text1 = new JTextField(10);
	private JTextField text2 = new JTextField(10);
	
	private JButton button1 = new JButton("添加");
	private JButton button2 = new JButton("清空");
	private JButton button3 = new JButton("计算");
	
	private JTextArea area = new JTextArea(20,40);
	private JScrollPane scroll = new JScrollPane(area);
	
	private JFrame tot = new JFrame();
	private JTextField totField = new JTextField(15);
	private JButton totButton = new JButton("Enter");
	
	public GUI() {
		init();
	}
	
	private void init() {
		this.setTitle("Demo");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.setLayout(new FlowLayout());
		this.getContentPane().add(panel1);
		this.getContentPane().add(panel2);
		this.getContentPane().add(panel3);
		this.getContentPane().add(panel4);
		
		panel1.setLayout(new FlowLayout());
		panel1.add(label1);
		panel1.add(text1);
		
		panel2.setLayout(new FlowLayout());
		panel2.add(label2);
		panel2.add(text2);
		
		panel3.setLayout(new FlowLayout());
		panel3.add(button1);
		panel3.add(button2);
		panel3.add(button3);
		
		panel4.add(scroll);
		scroll.setVisible(true);
		area.setEditable(false);
		
		this.pack();
		
		initTotal();
		
		button1.addActionListener(new add());
		button2.addActionListener(new clear());
		totButton.addActionListener(new enter());
		button3.addActionListener(new calculate());
	}
	
	private void initTotal() {
		tot.setTitle("Total");
		tot.setLayout(new FlowLayout());
		tot.getContentPane().add(totField);
		tot.getContentPane().add(totButton);
		tot.setVisible(true);
		tot.pack();
	}
	
	private Node[] covert(ArrayList<Node> list) {
	    int length = list.size();
	    Node[] temp = new Node[length];
		for(int i = 0 ; i < length ; i++) {
	    	temp[i] = nodes.get(i);
	    }
		return temp;
	}
	
	
  private class add implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  index++;
		  int v = Integer.parseInt(text1.getText());
		  int w = Integer.parseInt(text2.getText());
		  area.append("index = "+index+" , v = "+v+" , w = "+w+" \n");
		  Node node = new Node(v,w);
		  nodes.add(node);
		  text1.setText("");
		  text2.setText("");
	  }
  }
  
  private class clear implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  index=-1;
		  area.setText(null);
		  nodes.clear();
		  tot.setVisible(true);
		  
	  }
  }
  
  private class calculate implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
          Node[] bags = covert(nodes);
          core problem = new core(bags,total);
          problem.solve();
          area.append("------该背包问题的解------\n");
          area.append("最优值："+problem.getBestValue()+"\n");
          area.append("最优解（选取的背包）：\n");
          area.append(""+problem.getBestSolution()+"");
	  }
  }
  
  private class enter implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  total = Integer.parseInt(totField.getText());
		  area.setText("total = "+total+"\n");
		  totField.setText(null);
		  tot.setVisible(false);
	  }
  }
  
}
