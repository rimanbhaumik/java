package oraError;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.jar.JarFile;

interface Console {
    JTextArea errorDisplay = new JTextArea(2,10);
    JTextArea causeArea = new JTextArea(2,10);
    JTextArea actionArea = new JTextArea(2,10);
}

public class OraError implements Console{
    String error;
    static File f9, file;
    public OraError(String error) {
        this.error = error;
    }
    
    public static void main(String[] args) {
        try {
            JarFile j = new JarFile("oracleError.jar");
            InputStream is = j.getInputStream(j.getEntry("icon.PNG"));
            FileOutputStream fos = new FileOutputStream("icon.PNG");
            int read;
            while((read = is.read()) != -1)
                fos.write(read);
            fos.close();
            f9 = new File("icon.PNG");
            String url = String.valueOf(String.valueOf(f9.getCanonicalPath()));
            
            InputStream is1 = j.getInputStream(j.getEntry("data.dat"));
            FileOutputStream fos1 = new FileOutputStream("data.dat");
            int read1;
            while((read1 = is1.read()) != -1)
                fos1.write(read1);
            fos1.close();            
            file = new File("data.dat");
            OraErrorFrame frame = new OraErrorFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocation(65,10);
            frame.setSize(900,700);         
            frame.setIconImage(new ImageIcon(url).getImage());
            if(f9.delete()) {
                f9.deleteOnExit();
            } else{
                boolean con = f9.delete();
                f9.deleteOnExit();
            }
        } catch(Exception e) {
            errorDisplay.setText("Error encounter: "+e.getMessage());
        }
    }
}

class OraErrorFrame extends JFrame {
    public OraErrorFrame() {
        setTitle("Oracle Error Viewer");
        OraErrorPanel panel = new OraErrorPanel();
        getContentPane().add(panel);
        pack();
    }
}

class OraErrorPanel extends JPanel implements Console{
    private JPanel panel, panelEnd, panelEnd1, panelEnd2, panelEnd3;
    private JComboBox combo;
    private JLabel label1, label2, label3, label4, label5;
    private JButton okbutton;
    private JTextField errorCode;
    
    public OraErrorPanel() {
        errorCode = new JTextField();
        causeArea.setEditable(false);
        actionArea.setEditable(false);
        errorDisplay.setEditable(false);
        setLayout(new BorderLayout());
        
        JMenuBar menubar = new JMenuBar();
        JMenu jme = new JMenu("File");
        JMenu jmh = new JMenu("Help");
        JMenuItem menuItem = new JMenuItem("Exit");
        JMenuItem menuItem1 = new JMenuItem("How to Use?");
        JMenuItem menuItem2 = new JMenuItem("About");
        jme.add(menuItem);
        jmh.add(menuItem1);
        jmh.addSeparator();
        jmh.add(menuItem2);
        menubar.add(jme);
        menubar.add(jmh);
        
        add(menubar,BorderLayout.NORTH);
        
        panel = new JPanel();
        BorderLayout blp = new BorderLayout();
        
        panel.setLayout(blp);
        add(panel, BorderLayout.SOUTH);
        
        String []dataCombo = { "ORA - ", "EXP - ", "IMP - ", "SQL*Loader - ", "KUP - ", "DBV - ", "NID - ", "LCD - ", "QSM - ", "RMAN - ", "LRM - ", "LFI - ", "PLS - ", "AMD - ", "TNS - ", "NNC - ", "NNO - ", "NNL - ", "NPL - ", "NCR - ", "NZE - ", "MOD - ", "O2F - ", "O2I - ", "O2U - ", "PCB - ", "PCF - ", "PCC - ", "SQL - ", "AUD - ", "IMG - ", "VID - ", "DRG - ", "LPX - ", "LSX - ", "EPC - " };
        combo = new JComboBox(dataCombo);
        
        panel.add(combo, BorderLayout.WEST);
        errorCode.setColumns(5);
        panel.add(errorCode, BorderLayout.CENTER);
        okbutton = new JButton("OK");
        panel.add(okbutton, BorderLayout.EAST);
        
        panelEnd = new JPanel();
        BorderLayout blp1 = new BorderLayout();
        panelEnd.setLayout(blp1);
        add(panelEnd,BorderLayout.CENTER);
        
        label1 = new JLabel("Error Code Finder", 0);
        label2 = new JLabel("Enter 5 digit error code", 0);
        
        panelEnd1 = new JPanel();
        panelEnd1.setLayout(new GridLayout(3, 1, 1, 1));
        
        panelEnd2 = new JPanel();
        panelEnd2.setLayout(new GridLayout(3, 1, 10, 10));
        
        panelEnd3 = new JPanel();
        
        panelEnd.add(label1, BorderLayout.NORTH);
        panelEnd.add(panelEnd1, BorderLayout.WEST);
        panelEnd.add(panelEnd2, BorderLayout.CENTER);
        panelEnd.add(panelEnd3, BorderLayout.EAST);
        panelEnd.add(label2, BorderLayout.SOUTH);
        
        label5 = new JLabel("Error: ");
        panelEnd1.add(label5);
        panelEnd2.add(errorDisplay);
        
        
        label3 = new JLabel("Cause: ");
        panelEnd1.add(label3);
        panelEnd2.add(causeArea);
        
        label4 = new JLabel("Action: ");
        panelEnd1.add(label4);
        panelEnd2.add(actionArea);
        
        panel.setBackground(new Color(176, 208, 138));
        panelEnd.setBackground(new Color(176, 208, 138));
        panelEnd1.setBackground(new Color(176, 208, 138));
        panelEnd2.setBackground(new Color(176, 208, 138));
        panelEnd3.setBackground(new Color(176, 208, 138));
        
        ActionListener ok = new OkAction();
        ActionListener close = new CloseAction();
        ActionListener how = new HowAction();
        ActionListener about = new AboutAction();
        
        okbutton.addActionListener(ok);
        menuItem.addActionListener(close);
        menuItem1.addActionListener(how);
        menuItem2.addActionListener(about);
    }
    private class CloseAction implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }
    
    private class AboutAction extends Component implements ActionListener{
        public void actionPerformed(ActionEvent event){
            MyPopup mp = new MyPopup();
            Thread t = new Thread(mp);
            t.start();
        }
    }
    
    private class MyPopup implements Runnable {
        public void run(){
            try {
                JarFile j = new JarFile("oracleError.jar");
                InputStream is = j.getInputStream(j.getEntry("about.PNG"));
                FileOutputStream fos = new FileOutputStream("about.PNG");
                
                int read;
                while((read = is.read()) != -1)
                    fos.write(read);
                fos.close();
                File f1 = new File("about.PNG");
                JLabel label = new JLabel();
                label.setIcon( new ImageIcon(String.valueOf(String.valueOf(f1.getCanonicalPath()))) );
                PopupFactory factory = PopupFactory.getSharedInstance();
                Popup popup = factory.getPopup(label, label, 380, 280);
                popup.show();
                Thread.sleep(4000);
                popup.hide();
                if (f1.exists()){
                    f1.deleteOnExit();
                }
            } catch (Exception ex){
                errorDisplay.append("Error encounter: "+ex.getMessage());
            }
        }
    }
    
    private class HowAction extends Component implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            try {
                JarFile j = new JarFile("oracleError.jar");
                InputStream is = j.getInputStream(j.getEntry("release.htm"));
                FileOutputStream fos = new FileOutputStream("release.htm");
                int read;
                while((read = is.read()) != -1)
                    fos.write(read);
                fos.close();
                Runtime rt = Runtime.getRuntime();
                File f = new File("release.htm");
                rt.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe ".concat(String.valueOf(String.valueOf(f.getCanonicalPath()))));
                if (f.exists()){
                    f.deleteOnExit();
                }
            } catch(Exception e) {
                errorDisplay.append("\nError encounter: "+e.getMessage()+"\n");
            }
        }
    }
    
    private class OkAction implements ActionListener, Console {
        
        public void actionPerformed(ActionEvent event){
            String error;
            errorDisplay.setText("");
            causeArea.setText("");
            actionArea.setText("");
            if(errorCode.getText().length() == 5) {
                error = ((String)combo.getSelectedItem()).replace(" ","")+errorCode.getText();
                read(error);
            } else {
                errorDisplay.setText("Invalid Error Code Given");
            }
        }
    }
    
    public void read(String error) {
        try {
            File file = new File("data.dat");                        
            BufferedReader br = new BufferedReader(new FileReader(file));
            String display;
            while ((display = br.readLine()) != null) {
                if(display.startsWith(error, 0)){
                    errorDisplay.append(display+"\n");
                    while(!((display = br.readLine()).substring(0,6).equals("Cause:"))){
                        errorDisplay.append(display+"\n");
                    }
                    causeArea.append(display.replaceFirst("Cause:", ""));
                    while(!((display = br.readLine()).substring(0,7).equals("Action:"))){
                        causeArea.append(display+"\n");
                    }
                    actionArea.append(display.replaceFirst("Action:", ""));
                    while(!((display = br.readLine()).substring(0,4).equals(error.substring(0,4)))){
                        actionArea.append(display+"\n");
                    }
                    break;
                }
            }
            if(display == null)
                errorDisplay.setText("No error by this given code is found.");
            br.close();
        }  catch(IOException e) {
            errorDisplay.setText("\nError: " + e.getMessage());
        }
    }
}
