package filecomparator;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.jar.JarFile;
import javax.imageio.ImageIO;
interface Console {
    JTextArea ta = new JTextArea("Welcome to File Comparator ver-1.0\n\n\nPlease provide the path of the 2 files to compare\n");
}

public class FileComparatorVer1 implements Console {
    static File f9;
    public static void main(String[] args) {
        ComparatorFrame frame = new ComparatorFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        try {
            JarFile j = new JarFile("filecomparator.jar");
            InputStream is = j.getInputStream(j.getEntry("icon.PNG"));
            FileOutputStream fos = new FileOutputStream("icon.PNG");
            int read;
            while((read = is.read()) != -1)
                fos.write(read);
            fos.close();
            f9 = new File("icon.PNG");
            String url = String.valueOf(String.valueOf(f9.getCanonicalPath()));
            frame.setIconImage(new ImageIcon(url).getImage());
            if(f9.delete()) {
                f9.deleteOnExit();                
            }
            else{
                boolean con = f9.delete();
                f9.deleteOnExit();
            }
        } catch(Exception e) {
            ta.append("Error encounter: "+e.getMessage());
        }
    }
}

class ComparatorFrame extends JFrame {
    public ComparatorFrame() {
        setTitle("File Comparator");
        ComparatorPanel panel = new ComparatorPanel();
        getContentPane().add(panel);
        pack();
    }
}

class ComparatorPanel extends JPanel implements Console{
    private JButton submit;
    private JPanel panel;
    private String result;
    private JScrollPane scrollPane;
    private File f1, f2;
    JFileChooser chooser = new JFileChooser();
    
    public ComparatorPanel() {
        result = "";
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
        
        ta.setRows(20);
        ta.setColumns(40);
        ta.setEditable(false);
        ta.setForeground(Color.white);
        ta.setBackground(Color.black);
        scrollPane = new JScrollPane(ta);
        
        add(scrollPane,BorderLayout.CENTER);
        
        ActionListener submit = new SubmitAction();
        ActionListener reset = new ResetAction();
        ActionListener selectfile = new SelectAction();
        ActionListener close = new CloseAction();
        ActionListener how = new HowAction();
        ActionListener about = new AboutAction();
        
        menuItem.addActionListener(close);
        menuItem1.addActionListener(how);
        menuItem2.addActionListener(about);
        
        panel = new JPanel();
        
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        panel.setLayout(gridbag);
        
        addButton("Select 1st File", gridbag, c, selectfile);
        c.gridwidth = GridBagConstraints.REMAINDER;
        addButton("Select 2nd File", gridbag, c, selectfile);
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weighty = 1.0;
        addButton("Submit", gridbag, c, submit);
        addButton("Reset", gridbag, c, reset);
        
        add(panel, BorderLayout.SOUTH);
    }
    private void addButton(String label, GridBagLayout gridbag, GridBagConstraints c, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        gridbag.setConstraints(button, c);
        panel.add(button);
    }
    
    private class CloseAction implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            ta.append("\n\nClosing the application....");
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
                JarFile j = new JarFile("filecomparator.jar");
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
                Popup popup = factory.getPopup(label, label, 75, 120);
                popup.show();
                Thread.sleep(4500);
                popup.hide();
                if (f1.exists()){
                    f1.deleteOnExit();
                }
            } catch (Exception ex){
                ta.append("Error encounter: "+ex.getMessage());
            }
        }
    }
    
    private class HowAction extends Component implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            try {
                ta.append("\n Opening the help document\n");
                JarFile j = new JarFile("filecomparator.jar");
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
                ta.append("\nError encounter: "+e.getMessage()+"\n");
            }
        }
    }
    
    private class SelectAction extends Component implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            String select = event.getActionCommand();
            SelectAction parent = new SelectAction();
            int returnVal = chooser.showOpenDialog(parent);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                if(select.equals("Select 1st File")){
                    f1 = chooser.getSelectedFile();
                    ta.append("\nFirst  File Name :"+chooser.getSelectedFile().getName()+"\n");
                    chooser.setCurrentDirectory(f1);
                }
                if(select.equals("Select 2nd File")) {
                    f2 = chooser.getSelectedFile();
                    ta.append("\nSecond File Name :"+chooser.getSelectedFile().getName()+".\n");
                    chooser.setCurrentDirectory(f2);
                }
            }
        }
    }
    
    private class ResetAction implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            f1 = f2 = null;
            ta.append("\n\n\nPlease provide the path of the 2 files to compare\n\n\n");
        }
    }
    
    private class SubmitAction implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            FileComparator fc = new FileComparator();
            try{
                boolean bresult = fc.Compare(f1,f2);
                if(bresult){
                    result = "Both files match";
                    ta.append(result+"\n");
                } else {
                    result = "The files are different";
                    ta.append(result+"\n");
                }
            } catch(Exception e){
                ta.append("\nError encounter: "+e.getMessage()+"\n");
            }
        }
    }
}

class FileComparator implements Console{
    
    public boolean Compare(File f1, File f2) throws IOException, NullPointerException {
        
        FileInputStream fis1 = new FileInputStream(f1);
        FileInputStream fis2 = new FileInputStream(f2);
        int read1, read2;
        
        while((read1 = fis1.read()) != -1) {
            while((read2 = fis2.read()) != -1) {
                if(read1 != read2){
                    return false;
                }
                break;
            }
            continue;
        }
        fis1.close();
        fis2.close();
        return true;
    }
}