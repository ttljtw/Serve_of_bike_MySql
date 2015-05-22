package Server;
import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Main_GUI extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5491373336408971480L;
	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private JMenuItem jMenuItem1;
	static JTextArea jTextArea1;
	private JMenuItem deleteMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem pasteMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem cutMenuItem;
	private JMenu jMenu4;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem newFileMenuItem;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
    static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");//设置日期格式
    
    
    private File file = null;
    FileDialog openFD,saveFD;
    String filename = "无标题";
    FileReader read;
    BufferedReader in;
    BufferedWriter out;
    FileWriter writer;
    
    
    private JFileChooser filechooser = new JFileChooser() ; 

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		new Thread(new MyServer1()).start();
        new Thread(new MyServer2()).start();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main_GUI inst = new Main_GUI();
				inst.setSize(600, 400);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		});
	}
	
	public Main_GUI() {
		super();
		initGUI();
	}
	
	private void initGUI() {
	    openFD=new FileDialog(this,"打开",FileDialog.LOAD);
	    saveFD=new FileDialog(this,"保存",FileDialog.SAVE);
		try {
			{
				this.setTitle("无标题-服务器（For BikeLock）");
				{
					jTextArea1 = new JTextArea();
					jTextArea1.setEditable(false);
					jTextArea1.setLineWrap(true);        //激活自动换行功能 
					jTextArea1.setWrapStyleWord(true);            // 激活断行不断字功能
				    JPanel panelOutput = new JPanel();
					BorderLayout panelOutputLayout = new BorderLayout();
					panelOutput.setLayout(panelOutputLayout);
				    panelOutput.add(new JScrollPane(jTextArea1), BorderLayout.CENTER);
					getContentPane().add(panelOutput, BorderLayout.CENTER);
				}
			}
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("文件");
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("新建");
						newFileMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								file = null; 
								if(!("".equals(jTextArea1.getText()))){ 
								Object[] options = { " 保存(S) ", " 不保存(N) "," 取消 " }; 
								int s = JOptionPane.showOptionDialog(null, 
										"是否将改动保存到  无标题？", getTitle(),  
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
								null, options, options[0]); 
								switch(s){
								case 0: 
							        saveFD.show();
							        filename=saveFD.getDirectory()+saveFD.getFile();
							        if(filename!=null)
							        {
							        	filename = filename+".txt";
							        	try
							        	{
							        		File file=new File(filename);
							        		writer=new FileWriter(file);
							        		out=new BufferedWriter(writer);
							        		out.write(jTextArea1.getText(),0,
							        				(jTextArea1.getText()).length());
							        		out.close();
							        		writer.close();
							        	}
							        	catch(IOException e2){} 
							        	}
							        break; 
								  case 1: 
									  jTextArea1.setText("");
								  } 
								} 
							}
						});
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("保存");
						saveMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
						        saveFD.show();
						        filename=saveFD.getDirectory()+saveFD.getFile();
						        if(filename!=null)
						        {
						         try
						           {
						            File file=new File(filename);
						            writer=new FileWriter(file);
						            out=new BufferedWriter(writer);
						            out.write(jTextArea1.getText(),0,(jTextArea1.getText()).length());
						            setTitle(saveFD.getFile()+"-服务器（For BikeLock）"); 
						            out.close();
						            writer.close();
						           }
						          catch(IOException e2){} 
						        }
							}
						});
					}
					{
						saveAsMenuItem = new JMenuItem();
						jMenu3.add(saveAsMenuItem);
						saveAsMenuItem.setText("另存为 ...");
						saveAsMenuItem.addActionListener(new saveAsMenuItem_actionAdapter(this));
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("退出");
						exitMenuItem.addActionListener(new ActionListener() {
				            public void actionPerformed(ActionEvent e) {
				                System.exit(0);
				            }
				        });
					}
				}
				{
					jMenu4 = new JMenu();
					jMenuBar1.add(jMenu4);
					jMenu4.setText("编辑");
					{
						cutMenuItem = new JMenuItem();
						jMenu4.add(cutMenuItem);
						cutMenuItem.setText("剪切");
					}
					{
						copyMenuItem = new JMenuItem();
						jMenu4.add(copyMenuItem);
						copyMenuItem.setText("复制");
					}
					{
						pasteMenuItem = new JMenuItem();
						jMenu4.add(pasteMenuItem);
						pasteMenuItem.setText("粘贴");
					}
					{
						jSeparator1 = new JSeparator();
						jMenu4.add(jSeparator1);
					}
					{
						deleteMenuItem = new JMenuItem();
						jMenu4.add(deleteMenuItem);
						deleteMenuItem.setText("删除");
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(jMenu5);
					jMenu5.setText("帮助");
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("帮助");
						helpMenuItem.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								JOptionPane.showMessageDialog(null, 
										"安卓客户端 端口号30000\n车锁控制端 端口号8899", 
										"帮助 服务器（For BikeLock）", 
										JOptionPane.INFORMATION_MESSAGE); 
							}
						});
					}
					{
						jMenuItem1 = new JMenuItem();
						jMenu5.add(jMenuItem1);
						jMenuItem1.setText("关于");
						jMenuItem1.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								JOptionPane.showMessageDialog(null, 
										"版本号：1.0\nCopyright@2015 BikeLock.cn All Right Reserved.", 
										"关于 服务器（For BikeLock）", 
										JOptionPane.INFORMATION_MESSAGE); 
							}
						});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String set_jTextArea1(String jsonString) {
		return set_jTextArea1(jsonString,null);
	}

    public static String set_jTextArea1(String jsonString,String add) {//向界面输出信息
		// TODO Auto-generated method stub
    	String temp;
    	if (add == null) {
    		temp = jsonString+"\n";
		}
    	else {
    		temp = df.format(new Date())+" "+add+":"+jsonString+"\n";
		}
    	jTextArea1.append(temp);
		jTextArea1.paintImmediately(jTextArea1.getBounds());
		jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
		return temp;
	}
    
    
    public void saveAsMenuItemActionPerformed(ActionEvent evt){ 
    	filechooser.setDialogTitle("另存为..."); 
    	int returnVal = filechooser.showSaveDialog(this); 
    	if(returnVal == JFileChooser.APPROVE_OPTION) { 
    		file=filechooser.getSelectedFile(); 
    		try{ 
    			FileWriter fw=new FileWriter(file); 
    			fw.write(jTextArea1.getText()); 
    			setTitle(filechooser.getSelectedFile().getName()+"-服务器（For BikeLock）"); 
    			fw.close(); 
    		} 
    		catch(Exception e){ 
    			e.printStackTrace(); 
    		} 
    	} 
    } 
 
}

//定义另存为事件类 
class saveAsMenuItem_actionAdapter implements ActionListener{
	
	Main_GUI adaptee; 

	saveAsMenuItem_actionAdapter(Main_GUI adaptee){ 
		this.adaptee = adaptee; 
	} 
	public void actionPerformed(ActionEvent evt){ 
		adaptee.saveAsMenuItemActionPerformed(evt); 
	}
}
