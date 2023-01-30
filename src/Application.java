import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Application extends JFrame implements ActionListener, WindowListener{
    JTextArea text_area = new JTextArea();
    File file_name_container;

    public Application() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        setSize(500, 500);
        setTitle("Untitled.txt - JusText");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JScrollPane scroll_text =  new JScrollPane(text_area);
        scroll_text.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_text.setVisible(true);

        text_area.setLineWrap(true);
        text_area.setWrapStyleWord(true);
        Font font = new Font("Verdana", Font.PLAIN, 15);
        text_area.setFont(font);

        JMenuBar menu_bar = new JMenuBar();
        JMenu file_menu = new JMenu("File");
        JMenu edit_menu = new JMenu("Edit");
        JMenu help_menu = new JMenu("Help");

        createMenuItem(file_menu, "New");
        createMenuItem(file_menu, "Open");
        createMenuItem(file_menu, "Save");
        file_menu.addSeparator();
        createMenuItem(file_menu, "Exit");

        createMenuItem(edit_menu, "Cut");
        createMenuItem(edit_menu, "Copy");
        createMenuItem(edit_menu, "Paste");

        createMenuItem(help_menu, "About JusText");

        menu_bar.add(file_menu);
        menu_bar.add(edit_menu);
        menu_bar.add(help_menu);

        setJMenuBar(menu_bar);

        setJMenuBar(menu_bar);
        container.add(scroll_text);
        setVisible(true);
    }

    public void createMenuItem(JMenu menu,String name){
        JMenuItem menu_item = new JMenuItem(name);
        menu_item.addActionListener((ActionListener) this);
        menu.add(menu_item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser file_chooser = new JFileChooser();

        if (e.getActionCommand().equals("New")) {
            this.setTitle("Untitled - JusText");
            text_area.setText("");
            file_name_container = null;
        }

        else if (e.getActionCommand().equals("Open")) {
            int ret = file_chooser.showDialog(null, "Open");

            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = file_chooser.getSelectedFile();
                    OpenFile(file.getAbsolutePath());
                    this.setTitle(file.getName() + " - JusText");
                    file_name_container = file;
                }
                catch(IOException ers) {

                }
            }
        }

        else if (e.getActionCommand().equals("Save")) {
            if (file_name_container != null) {
                file_chooser.setCurrentDirectory(file_name_container);
                file_chooser.setSelectedFile(file_name_container);
            }
            else {
                file_chooser.setSelectedFile(new File("Untitled.txt"));
            }

            int ret = file_chooser.showSaveDialog(null);
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = file_chooser.getSelectedFile();
                    SaveFile(file.getAbsolutePath());
                    this.setTitle(file.getName() + " - JusText");
                    file_name_container = file;
                }
                catch (Exception ers2) {

                }
            }
        }

        else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }

        else if (e.getActionCommand().equals("Cut")) {
            text_area.cut();
        }

        else if (e.getActionCommand().equals("Copy")){
            text_area.copy();
        }

        else if (e.getActionCommand().equals("Paste")) {
            text_area.paste();
        }

        else if (e.getActionCommand().equals("About JusText")){
            JOptionPane.showMessageDialog(this,"Created by: Manoj Singh Tomer","JusText",JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void OpenFile(String file_path) throws IOException {
        BufferedReader buffered_reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_path)));
        String str;

        text_area.setText("");
        setCursor(new Cursor(Cursor.WAIT_CURSOR));

        while ((str = buffered_reader.readLine()) != null) {
            text_area.setText(text_area.getText() + str + "\r\n");
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        buffered_reader.close();

    }

    public void SaveFile(String file_path) throws IOException{
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        DataOutputStream output_stream = new DataOutputStream(new FileOutputStream(file_path));
        output_stream.writeBytes(text_area.getText());
        output_stream.close();
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}