package virtualdba;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.jar.JarFile;
import java.sql.*;

public class VirtualDBA {
    public static void main(String[] args) {
        DBAFrame frame = new DBAFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
class DBAFrame extends JFrame {
    public DBAFrame() {
        setTitle("Virtual DBA");
        DBAPanel panel = new DBAPanel();
        getContentPane().add(panel);
        pack();
    }
}
interface Console {
    JTextArea ta = new JTextArea("Welcome to Virtual DBA ver-1.0 Beta\n\n\nPlease connect to the database.\n");
    JTextField commandArea = new JTextField();
}
class DBAPanel extends JPanel implements Console{
    private JButton but0;
    private JPanel panel;
    private JScrollPane scrollPane;
    private JTextField address, username, port, SID;
    private JPasswordField password;
    private JLabel serverlabel, userlabel, passwordlabel, toplabel, portlabel, sidlabel;
    public Connection conec;
    public ConnectFrame cf;
    
    public DBAPanel() {
        setLayout(new BorderLayout());
        JMenuBar menubar = new JMenuBar();
        JMenu jme = new JMenu("File");
        JMenu jmdba = new JMenu("Action");
        JMenu jmex = new JMenu("Execute");
        JMenu jmh = new JMenu("Help");
        
        JMenu monitor1 = new JMenu("Monitoring 1");
        JMenu monitor2 = new JMenu("Monitoring 2");
        JMenu monitor3 = new JMenu("Monitoring 3");
        JMenu misc = new JMenu("Miscellaneous");
        
        JMenuItem menuItem = new JMenuItem("Exit");
        JMenuItem menuItem0 = new JMenuItem("Execute SQL");
        JMenuItem menuItem1 = new JMenuItem("How to Use?");
        JMenuItem menuItem2 = new JMenuItem("About");
        JMenuItem menuItem3 = new JMenuItem("Connect");
        
        JMenuItem menuItem4 = new JMenuItem("Access");
        JMenuItem menuItem5 = new JMenuItem("Active Sessions");
        JMenuItem menuItem6 = new JMenuItem("Cache Hit Ratio");
        JMenuItem menuItem7 = new JMenuItem("Call Stack");
        JMenuItem menuItem8 = new JMenuItem("Code Dep");
        JMenuItem menuItem9 = new JMenuItem("Code Dep On");
        JMenuItem menuItem10 = new JMenuItem("Column Defaults");
        JMenuItem menuItem11 = new JMenuItem("Db Cache Advice");
        JMenuItem menuItem12 = new JMenuItem("Db Info");
        JMenuItem menuItem13 = new JMenuItem("Dispatchers");
        JMenuItem menuItem14 = new JMenuItem("Errors");
        JMenuItem menuItem15 = new JMenuItem("Error Stack");
        JMenuItem menuItem16 = new JMenuItem("Explain");
        JMenuItem menuItem17 = new JMenuItem("File Io");
        JMenuItem menuItem18 = new JMenuItem("Fks");
        JMenuItem menuItem19 = new JMenuItem("Free Space");
        JMenuItem menuItem20 = new JMenuItem("Health");
        JMenuItem menuItem21 = new JMenuItem("High Water Mark");
        JMenuItem menuItem22 = new JMenuItem("Hot Blocks");
        JMenuItem menuItem23 = new JMenuItem("Identify Trace File");
        JMenuItem menuItem24 = new JMenuItem("Index Extents");
        JMenuItem menuItem25 = new JMenuItem("Index Monitoring Status");
        JMenuItem menuItem26 = new JMenuItem("Index Partitions");
        JMenuItem menuItem27 = new JMenuItem("Index Usage");
        JMenuItem menuItem28 = new JMenuItem("Invalid Objects");
        JMenuItem menuItem29 = new JMenuItem("Jobs");
        JMenuItem menuItem30 = new JMenuItem("Jobs Running");
        JMenuItem menuItem31 = new JMenuItem("Latches");
        JMenuItem menuItem32 = new JMenuItem("Latch Hit Ratios");
        JMenuItem menuItem33 = new JMenuItem("Latch Holders");
        JMenuItem menuItem34 = new JMenuItem("Library Cache");
        JMenuItem menuItem35 = new JMenuItem("License");
        JMenuItem menuItem36 = new JMenuItem("Locked Objects");
        JMenuItem menuItem37 = new JMenuItem("Longops");
        JMenuItem menuItem38 = new JMenuItem("Lru Latch Ratio");
        JMenuItem menuItem39 = new JMenuItem("Max Extents");
        JMenuItem menuItem40 = new JMenuItem("Monitor");
        JMenuItem menuItem41 = new JMenuItem("Monitoring Status");
        JMenuItem menuItem42 = new JMenuItem("Monitor Memory");
        JMenuItem menuItem43 = new JMenuItem("Non Indexed Fks");
        JMenuItem menuItem44 = new JMenuItem("Object Status");
        JMenuItem menuItem45 = new JMenuItem("Obj Lock");
        JMenuItem menuItem46 = new JMenuItem("Open Cursors");
        JMenuItem menuItem47 = new JMenuItem("Open Cursors By Sid");
        JMenuItem menuItem48 = new JMenuItem("Open Cursors Full By Sid");
        JMenuItem menuItem49 = new JMenuItem("Parameters");
        JMenuItem menuItem50 = new JMenuItem("Parameter Diffs");
        JMenuItem menuItem51 = new JMenuItem("Pga Target Advice");
        JMenuItem menuItem52 = new JMenuItem("Pipes");
        JMenuItem menuItem53 = new JMenuItem("Profiler Runs");
        JMenuItem menuItem54 = new JMenuItem("Profiler Run Details");
        JMenuItem menuItem55 = new JMenuItem("Rbs Extents");
        JMenuItem menuItem56 = new JMenuItem("Rbs Stats");
        JMenuItem menuItem57 = new JMenuItem("Recovery Status");
        JMenuItem menuItem58 = new JMenuItem("Roles");
        JMenuItem menuItem59 = new JMenuItem("Search Source");
        JMenuItem menuItem60 = new JMenuItem("Sessions");
        JMenuItem menuItem61 = new JMenuItem("Session Events");
        JMenuItem menuItem62 = new JMenuItem("Session Events By Sid");
        JMenuItem menuItem63 = new JMenuItem("Session Events By Spid");
        JMenuItem menuItem64 = new JMenuItem("Session Io");
        JMenuItem menuItem65 = new JMenuItem("Session Rollback");
        JMenuItem menuItem66 = new JMenuItem("Session Stats");
        JMenuItem menuItem67 = new JMenuItem("Session Waits");
        JMenuItem menuItem68 = new JMenuItem("Show Space");
        JMenuItem menuItem69 = new JMenuItem("Source");
        JMenuItem menuItem70 = new JMenuItem("Spfile Parameters");
        JMenuItem menuItem71 = new JMenuItem("Sql Area");
        JMenuItem menuItem72 = new JMenuItem("Sql Text");
        JMenuItem menuItem73 = new JMenuItem("Sql Text By Sid");
        JMenuItem menuItem74 = new JMenuItem("System Events");
        JMenuItem menuItem75 = new JMenuItem("System Parameters");
        JMenuItem menuItem76 = new JMenuItem("System Stats");
        JMenuItem menuItem77 = new JMenuItem("Table Dep");
        JMenuItem menuItem78 = new JMenuItem("Table Extents");
        JMenuItem menuItem79 = new JMenuItem("Table Indexes");
        JMenuItem menuItem80 = new JMenuItem("Table Partitions");
        JMenuItem menuItem81 = new JMenuItem("Table Stats");
        JMenuItem menuItem82 = new JMenuItem("Temp Io");
        JMenuItem menuItem83 = new JMenuItem("Temp Segments");
        JMenuItem menuItem84 = new JMenuItem("Top Latches");
        JMenuItem menuItem85 = new JMenuItem("Top Sessions");
        JMenuItem menuItem86 = new JMenuItem("Top");
        JMenuItem menuItem87 = new JMenuItem("Trace Runs");
        JMenuItem menuItem88 = new JMenuItem("Trace Run Details");
        JMenuItem menuItem89 = new JMenuItem("Ts Extent Map");
        JMenuItem menuItem90 = new JMenuItem("Ts Full");
        JMenuItem menuItem91 = new JMenuItem("Tuning");
        JMenuItem menuItem92 = new JMenuItem("Unusable Indexes");
        JMenuItem menuItem93 = new JMenuItem("Unused Space");
        JMenuItem menuItem94 = new JMenuItem("User Hit Ratio");
        
        JMenuItem menuItem95 = new JMenuItem("Analyze All");
        JMenuItem menuItem96 = new JMenuItem("Column Comments");
        JMenuItem menuItem97 = new JMenuItem("Comments");
        JMenuItem menuItem98 = new JMenuItem("Compare Schemas");
        JMenuItem menuItem99 = new JMenuItem("Compile All");
        JMenuItem menuItem100 = new JMenuItem("Compile All Bodies");
        JMenuItem menuItem101 = new JMenuItem("Compile All Funcs");
        JMenuItem menuItem102 = new JMenuItem("Compile All Procs");
        JMenuItem menuItem103 = new JMenuItem("Compile All Specs");
        JMenuItem menuItem104 = new JMenuItem("Compile All Trigs");
        JMenuItem menuItem105 = new JMenuItem("Compile All Views");
        JMenuItem menuItem106 = new JMenuItem("Conversion Api");
        JMenuItem menuItem107 = new JMenuItem("Dict Comments");
        JMenuItem menuItem108 = new JMenuItem("Drop All");
        JMenuItem menuItem109 = new JMenuItem("Gen Health");
        JMenuItem menuItem110 = new JMenuItem("Get Pivot");
        JMenuItem menuItem111 = new JMenuItem("Login");
        JMenuItem menuItem112 = new JMenuItem("Proc Defs");
        JMenuItem menuItem113 = new JMenuItem("Rebuild Index");
        JMenuItem menuItem114 = new JMenuItem("Soap Api");
        JMenuItem menuItem115 = new JMenuItem("String Agg");
        JMenuItem menuItem116 = new JMenuItem("String Api");
        JMenuItem menuItem117 = new JMenuItem("Switch Schema");
        JMenuItem menuItem118 = new JMenuItem("Table Comments");
        JMenuItem menuItem119 = new JMenuItem("Table Defs");
        JMenuItem menuItem120 = new JMenuItem("Table Differences");
        
        jme.add(menuItem3);
        jme.addSeparator();
        jme.add(menuItem);
        jmex.add(menuItem0);
        jmdba.add(monitor1);
        jmdba.add(monitor2);
        jmdba.add(monitor3);
        jmdba.addSeparator();
        jmdba.add(misc);
        jmh.add(menuItem1);
        jmh.addSeparator();
        jmh.add(menuItem2);
        
        monitor1.add(menuItem4);
        monitor1.add(menuItem5);
        monitor1.add(menuItem6);
        monitor1.add(menuItem7);
        monitor1.add(menuItem8);
        monitor1.add(menuItem9);
        monitor1.add(menuItem10);
        monitor1.add(menuItem11);
        monitor1.add(menuItem12);
        monitor1.add(menuItem13);
        monitor1.add(menuItem14);
        monitor1.add(menuItem15);
        monitor1.add(menuItem16);
        monitor1.add(menuItem17);
        monitor1.add(menuItem18);
        monitor1.add(menuItem19);
        monitor1.add(menuItem20);
        monitor1.add(menuItem21);
        monitor1.add(menuItem22);
        monitor1.add(menuItem23);
        monitor1.add(menuItem24);
        monitor1.add(menuItem25);
        monitor1.add(menuItem26);
        monitor1.add(menuItem27);
        monitor1.add(menuItem28);
        monitor1.add(menuItem29);
        monitor1.add(menuItem30);
        monitor1.add(menuItem31);
        monitor1.add(menuItem32);
        monitor1.add(menuItem33);
        monitor2.add(menuItem34);
        monitor2.add(menuItem35);
        monitor2.add(menuItem36);
        monitor2.add(menuItem37);
        monitor2.add(menuItem38);
        monitor2.add(menuItem39);
        monitor2.add(menuItem40);
        monitor2.add(menuItem41);
        monitor2.add(menuItem42);
        monitor2.add(menuItem43);
        monitor2.add(menuItem44);
        monitor2.add(menuItem45);
        monitor2.add(menuItem46);
        monitor2.add(menuItem47);
        monitor2.add(menuItem48);
        monitor2.add(menuItem49);
        monitor2.add(menuItem50);
        monitor2.add(menuItem51);
        monitor2.add(menuItem52);
        monitor2.add(menuItem53);
        monitor2.add(menuItem54);
        monitor2.add(menuItem55);
        monitor2.add(menuItem56);
        monitor2.add(menuItem57);
        monitor2.add(menuItem58);
        monitor2.add(menuItem59);
        monitor2.add(menuItem60);
        monitor2.add(menuItem61);
        monitor2.add(menuItem62);
        monitor2.add(menuItem63);
        monitor3.add(menuItem64);
        monitor3.add(menuItem65);
        monitor3.add(menuItem66);
        monitor3.add(menuItem67);
        monitor3.add(menuItem68);
        monitor3.add(menuItem69);
        monitor3.add(menuItem70);
        monitor3.add(menuItem71);
        monitor3.add(menuItem72);
        monitor3.add(menuItem73);
        monitor3.add(menuItem74);
        monitor3.add(menuItem75);
        monitor3.add(menuItem76);
        monitor3.add(menuItem77);
        monitor3.add(menuItem78);
        monitor3.add(menuItem79);
        monitor3.add(menuItem80);
        monitor3.add(menuItem81);
        monitor3.add(menuItem82);
        monitor3.add(menuItem83);
        monitor3.add(menuItem84);
        monitor3.add(menuItem85);
        monitor3.add(menuItem86);
        monitor3.add(menuItem87);
        monitor3.add(menuItem88);
        monitor3.add(menuItem89);
        monitor3.add(menuItem90);
        monitor3.add(menuItem91);
        monitor3.add(menuItem92);
        monitor3.add(menuItem93);
        monitor3.add(menuItem94);
        
        misc.add(menuItem95);
        misc.add(menuItem96);
        misc.add(menuItem97);
        misc.add(menuItem98);
        misc.add(menuItem99);
        misc.add(menuItem100);
        misc.add(menuItem101);
        misc.add(menuItem102);
        misc.add(menuItem103);
        misc.add(menuItem104);
        misc.add(menuItem105);
        misc.add(menuItem106);
        misc.add(menuItem107);
        misc.add(menuItem108);
        misc.add(menuItem109);
        misc.add(menuItem110);
        misc.add(menuItem111);
        misc.add(menuItem112);
        misc.add(menuItem113);
        misc.add(menuItem114);
        misc.add(menuItem115);
        misc.add(menuItem116);
        misc.add(menuItem117);
        misc.add(menuItem118);
        misc.add(menuItem119);
        misc.add(menuItem120);
        
        menubar.add(jme);
        menubar.add(jmdba);
        menubar.add(jmex);
        menubar.add(jmh);
        
        add(menubar,BorderLayout.NORTH);
        
        ta.setRows(41);
        ta.setColumns(92);
        ta.setEditable(false);
        scrollPane = new JScrollPane(ta);
        add(scrollPane,BorderLayout.CENTER);
        
        add(commandArea,BorderLayout.SOUTH);
        
        ActionListener execute = new ExecuteAction();
        ActionListener sql = new SQLAction();
        ActionListener connect = new ConnectAction();
        ActionListener close = new CloseAction();
        ActionListener how = new HowAction();
        ActionListener about = new AboutAction();
        
        menuItem.addActionListener(close);
        menuItem0.addActionListener(sql);
        menuItem1.addActionListener(how);
        menuItem2.addActionListener(about);
        menuItem3.addActionListener(connect);
        
        menuItem4.addActionListener(execute);
        menuItem5.addActionListener(execute);
        menuItem6.addActionListener(execute);
        menuItem7.addActionListener(execute);
        menuItem8.addActionListener(execute);
        menuItem9.addActionListener(execute);
        menuItem10.addActionListener(execute);
        menuItem11.addActionListener(execute);
        menuItem12.addActionListener(execute);
        menuItem13.addActionListener(execute);
        menuItem14.addActionListener(execute);
        menuItem15.addActionListener(execute);
        menuItem16.addActionListener(execute);
        menuItem17.addActionListener(execute);
        menuItem18.addActionListener(execute);
        menuItem19.addActionListener(execute);
        menuItem20.addActionListener(execute);
        menuItem21.addActionListener(execute);
        menuItem22.addActionListener(execute);
        menuItem23.addActionListener(execute);
        menuItem24.addActionListener(execute);
        menuItem25.addActionListener(execute);
        menuItem26.addActionListener(execute);
        menuItem27.addActionListener(execute);
        menuItem28.addActionListener(execute);
        menuItem29.addActionListener(execute);
        menuItem30.addActionListener(execute);
        menuItem31.addActionListener(execute);
        menuItem32.addActionListener(execute);
        menuItem33.addActionListener(execute);
        menuItem34.addActionListener(execute);
        menuItem35.addActionListener(execute);
        menuItem36.addActionListener(execute);
        menuItem37.addActionListener(execute);
        menuItem38.addActionListener(execute);
        menuItem39.addActionListener(execute);
        menuItem40.addActionListener(execute);
        menuItem41.addActionListener(execute);
        menuItem42.addActionListener(execute);
        menuItem43.addActionListener(execute);
        menuItem44.addActionListener(execute);
        menuItem45.addActionListener(execute);
        menuItem46.addActionListener(execute);
        menuItem47.addActionListener(execute);
        menuItem48.addActionListener(execute);
        menuItem49.addActionListener(execute);
        menuItem50.addActionListener(execute);
        menuItem51.addActionListener(execute);
        menuItem52.addActionListener(execute);
        menuItem53.addActionListener(execute);
        menuItem54.addActionListener(execute);
        menuItem55.addActionListener(execute);
        menuItem56.addActionListener(execute);
        menuItem57.addActionListener(execute);
        menuItem58.addActionListener(execute);
        menuItem59.addActionListener(execute);
        menuItem60.addActionListener(execute);
        menuItem61.addActionListener(execute);
        menuItem62.addActionListener(execute);
        menuItem63.addActionListener(execute);
        menuItem64.addActionListener(execute);
        menuItem65.addActionListener(execute);
        menuItem66.addActionListener(execute);
        menuItem67.addActionListener(execute);
        menuItem68.addActionListener(execute);
        menuItem69.addActionListener(execute);
        menuItem70.addActionListener(execute);
        menuItem71.addActionListener(execute);
        menuItem72.addActionListener(execute);
        menuItem73.addActionListener(execute);
        menuItem74.addActionListener(execute);
        menuItem75.addActionListener(execute);
        menuItem76.addActionListener(execute);
        menuItem77.addActionListener(execute);
        menuItem78.addActionListener(execute);
        menuItem79.addActionListener(execute);
        menuItem80.addActionListener(execute);
        menuItem81.addActionListener(execute);
        menuItem82.addActionListener(execute);
        menuItem83.addActionListener(execute);
        menuItem84.addActionListener(execute);
        menuItem85.addActionListener(execute);
        menuItem86.addActionListener(execute);
        menuItem87.addActionListener(execute);
        menuItem88.addActionListener(execute);
        menuItem89.addActionListener(execute);
        menuItem90.addActionListener(execute);
        menuItem91.addActionListener(execute);
        menuItem92.addActionListener(execute);
        menuItem93.addActionListener(execute);
        menuItem94.addActionListener(execute);
        menuItem95.addActionListener(execute);
        menuItem96.addActionListener(execute);
        menuItem97.addActionListener(execute);
        menuItem98.addActionListener(execute);
        menuItem99.addActionListener(execute);
        menuItem100.addActionListener(execute);
        menuItem101.addActionListener(execute);
        menuItem102.addActionListener(execute);
        menuItem103.addActionListener(execute);
        menuItem104.addActionListener(execute);
        menuItem105.addActionListener(execute);
        menuItem106.addActionListener(execute);
        menuItem107.addActionListener(execute);
        menuItem108.addActionListener(execute);
        menuItem109.addActionListener(execute);
        menuItem110.addActionListener(execute);
        menuItem111.addActionListener(execute);
        menuItem112.addActionListener(execute);
        menuItem113.addActionListener(execute);
        menuItem114.addActionListener(execute);
        menuItem115.addActionListener(execute);
        menuItem116.addActionListener(execute);
        menuItem117.addActionListener(execute);
        menuItem118.addActionListener(execute);
        menuItem119.addActionListener(execute);
        menuItem120.addActionListener(execute);
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
    
    private class SQLAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            ResultSet rset;
            ResultSetMetaData rsmetad;
            try {
                String command = commandArea.getText();
                if(command == null)
                    ta.append("\n\nNo Statement entered.\n");
                else {
                    if(command.charAt(command.length()-1) == ';'){
                        String newcommand = "";
                        for(int i = 0; i < (command.length()-1); i++)
                            newcommand = newcommand + command.charAt(i);
                        command = newcommand;
                    }
                    Statement st = conec.createStatement();
                    rset = st.executeQuery(command);
                    rsmetad = rset.getMetaData();
                    ta.append("\n\n");
                    for(int  i = 1; i <= rsmetad.getColumnCount(); i++) {
                        ta.append(rsmetad.getColumnName(i)+"\t");
                    }
                    ta.append("\n");
                    while(rset.next()) {
                        for(int k = 1; k <= rsmetad.getColumnCount(); k++) {
                            ta.append(rset.getString(k)+"\t");
                        }
                        ta.append("\n");
                    }
                }
            } catch(NullPointerException e){
                ta.append("\nNo sql quenry entered or not connected to database.\n");
            } catch(SQLException se) {
                ta.append( "\nSQL Error : " + se.getMessage());
            } catch(Exception e) {
                ta.append("\nError : " + e.getMessage());
            }
        }
    }
    
    private class MyPopup implements Runnable {
        public void run(){
            try {
                JarFile j = new JarFile("virtualdba.jar");
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
                Popup popup = factory.getPopup(label, label, 350, 250);
                popup.show();
                Thread.sleep(5000);
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
                JarFile j = new JarFile("virtualdba.jar");
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
    
    private class ExecuteAction implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            ResultSet rs;
            ResultSetMetaData rsmd;
            String command = event.getActionCommand();
            String sqlfile = command +".sql";
            sqlfile = sqlfile.toLowerCase();
            if(sqlfile.contains(" "))
                sqlfile = sqlfile.replace(" ","_");
            try {
                JarFile j = new JarFile("virtualdba.jar");
                InputStream is = j.getInputStream(j.getEntry(sqlfile));
                if(is == null)
                    ta.append("\nFeature not available in Beta Version\n");
                else {
                    FileOutputStream fos = new FileOutputStream(sqlfile);
                    int read;
                    while((read = is.read()) != -1)
                        fos.write(read);
                    fos.close();
                    File f1 = new File(sqlfile);
                    BufferedReader br = new BufferedReader(new FileReader(f1));
                    String sqlcommand = br.readLine();
                    ta.append("\n\nThe following command is executed..\n\n"+sqlcommand);
                    Statement s = conec.createStatement();
                    rs = s.executeQuery(sqlcommand);
                    rsmd = rs.getMetaData();
                    ta.append("\n\n");
                    for(int  i = 1; i <= rsmd.getColumnCount(); i++) {
                        ta.append(rsmd.getColumnName(i)+"\t");
                    }
                    ta.append("\n");
                    while(rs.next()) {
                        for(int k = 1; k <= rsmd.getColumnCount(); k++) {
                            ta.append(rs.getString(k)+"\t");
                        }
                        ta.append("\n");
                    }
                    br.close();
                    if (f1.delete())
                        ta.append("\nReady for next Operation\n");
                }
            } catch(SQLException se) {
                ta.append( "\nSQL Error : " + se.getMessage());
            }  catch(Exception e) {
                ta.append("\nError : " + e.getMessage());
            }
        }
    }
    
    private class ConnectAction implements ActionListener, Console {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            try{
                cf = new ConnectFrame();
                cf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cf.setResizable(false);
                cf.setVisible(true);
            } catch(Exception e){
                ta.append("\nError encounter: "+e.getMessage()+"\n");
            }
        }
    }
    
    class ConnectFrame extends JFrame {
        public ConnectFrame() {
            setTitle("Provide the details.");
            ConnectPanel panel1 = new ConnectPanel();
            getContentPane().add(panel1);
            pack();
        }
    }
    
    class ConnectPanel extends JPanel implements ActionListener, Console{
        public ConnectPanel() {
            super();
            setBorder(BorderFactory.createTitledBorder(""));
            GridBagLayout gbpanel1 = new GridBagLayout();
            GridBagConstraints gbcpanel1 = new GridBagConstraints();
            setLayout(gbpanel1);
            
            toplabel = new JLabel("Please provide the connection details");
            gbcpanel1.gridx = 4;
            gbcpanel1.gridy = 0;
            gbcpanel1.gridwidth = 13;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 1;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(toplabel, gbcpanel1);
            add(toplabel);
            
            serverlabel = new JLabel("Server IP :");
            gbcpanel1.gridx = 2;
            gbcpanel1.gridy = 2;
            gbcpanel1.gridwidth = 5;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 1;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(serverlabel, gbcpanel1);
            add(serverlabel);
            
            address = new JTextField();
            gbcpanel1.gridx = 7;
            gbcpanel1.gridy = 2;
            gbcpanel1.gridwidth = 10;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 0;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(address, gbcpanel1);
            add(address);
            
            portlabel = new JLabel("Port:");
            gbcpanel1.gridx = 2;
            gbcpanel1.gridy = 3;
            gbcpanel1.gridwidth = 5;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 1;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(portlabel, gbcpanel1);
            add(portlabel);
            
            port = new JTextField();
            gbcpanel1.gridx = 7;
            gbcpanel1.gridy = 3;
            gbcpanel1.gridwidth = 10;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 0;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(port, gbcpanel1);
            add(port);
            
            sidlabel = new JLabel("SID:");
            gbcpanel1.gridx = 2;
            gbcpanel1.gridy = 4;
            gbcpanel1.gridwidth = 5;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 1;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(sidlabel, gbcpanel1);
            add(sidlabel);
            
            SID = new JTextField();
            gbcpanel1.gridx = 7;
            gbcpanel1.gridy = 4;
            gbcpanel1.gridwidth = 10;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 0;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(SID, gbcpanel1);
            add(SID);
            
            userlabel = new JLabel("Username:");
            gbcpanel1.gridx = 2;
            gbcpanel1.gridy = 5;
            gbcpanel1.gridwidth = 5;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 1;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(userlabel, gbcpanel1);
            add(userlabel);
            
            username = new JTextField();
            gbcpanel1.gridx = 7;
            gbcpanel1.gridy = 5;
            gbcpanel1.gridwidth = 10;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 0;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(username, gbcpanel1);
            add(username);
            
            passwordlabel = new JLabel("Password");
            gbcpanel1.gridx = 2;
            gbcpanel1.gridy = 6;
            gbcpanel1.gridwidth = 5;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 1;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(passwordlabel, gbcpanel1);
            add(passwordlabel);
            
            password = new JPasswordField();
            gbcpanel1.gridx = 7;
            gbcpanel1.gridy = 6;
            gbcpanel1.gridwidth = 10;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 0;
            gbcpanel1.anchor = GridBagConstraints.NORTH;
            gbpanel1.setConstraints(password, gbcpanel1);
            add(password);
            
            but0 = new JButton("Ok");
            but0.addActionListener(this);
            gbcpanel1.gridx = 7;
            gbcpanel1.gridy = 8;
            gbcpanel1.gridwidth = 10;
            gbcpanel1.gridheight = 1;
            gbcpanel1.fill = GridBagConstraints.BOTH;
            gbcpanel1.weightx = 1;
            gbcpanel1.weighty = 0;
            gbcpanel1.anchor = GridBagConstraints.CENTER;
            gbpanel1.setConstraints(but0, gbcpanel1);
            add(but0);
        }
        
        public void actionPerformed(ActionEvent e) {
            String serveraddress = address.getText();
            String portadd = port.getText();
            String SIDname = SID.getText();
            String user = username.getText();
            char[] passc = password.getPassword();
            String pass = "";
            for(int i = 0; i < passc.length; i++){
                pass = pass + passc[i];
            }
            if((serveraddress.equals("")) || (portadd.equals("")) || (SIDname.equals("")) || (user.equals("")) || (pass.equals(""))) {
                ta.append("\n\nPlease provide the login information of the database you want to connect.\n");
            } else {
                ta.append("\nConnected to database "+SIDname+" having IP "+serveraddress+" with user name "+ user+"....\n");
                DatabaseConnect dc = new DatabaseConnect(serveraddress, portadd, SIDname, user, pass);
                //DatabaseConnect dc = new DatabaseConnect("147.149.100.136", "1521", "gib1", "gib_admin", "g1badm1n");
                dc.connect();
            }
            cf.setVisible(false);
        }
    }
    
    class DatabaseConnect implements Console{
        String serveraddress, portadd, SIDname, user, pass;
        
        DatabaseConnect(String serveraddress, String portadd, String SIDname , String user, String pass) {
            this.serveraddress = serveraddress;
            this.portadd = portadd;
            this.SIDname = SIDname;
            this.user = user;
            this.pass = pass;
        }
        
        public void connect(){
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conec = DriverManager.getConnection("jdbc:oracle:thin:@"+serveraddress+":"+portadd+":"+SIDname, user, pass);
            } catch(SQLException se) {
                ta.append( "SQL Error : " + se.getMessage());
            } catch(ClassNotFoundException ce) {
                ta.append( "Class Error : " + ce.getMessage());
            } catch(Exception e) {
                ta.append("Error : " + e.getMessage());
            }
        }
    }
}
