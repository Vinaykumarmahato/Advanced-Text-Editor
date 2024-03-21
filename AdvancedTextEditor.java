import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class AdvancedTextEditor extends JFrame {

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;
    private JMenuItem findItem;
    private JMenuItem replaceItem;
    private JMenuItem wordCountItem;

    public AdvancedTextEditor() {
        setTitle("Advanced Text Editor Created By Vinay Kumar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        cutItem = new JMenuItem("Cut");
        copyItem = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        findItem = new JMenuItem("Find");
        replaceItem = new JMenuItem("Replace");
        wordCountItem = new JMenuItem("Word Count");

        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        findItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        replaceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        wordCountItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));

        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());
        findItem.addActionListener(e -> findText());
        replaceItem.addActionListener(e -> replaceText());
        wordCountItem.addActionListener(e -> countWords());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        menuBar.add(cutItem);
        menuBar.add(copyItem);
        menuBar.add(pasteItem);
        menuBar.add(findItem);
        menuBar.add(replaceItem);
        menuBar.add(wordCountItem);

        setJMenuBar(menuBar);

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add a toolbar for easier access to common functions
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(openItem);
        toolbar.add(saveItem);
        toolbar.addSeparator();
        toolbar.add(cutItem);
        toolbar.add(copyItem);
        toolbar.add(pasteItem);
        toolbar.addSeparator();
        toolbar.add(findItem);
        toolbar.add(replaceItem);
        toolbar.addSeparator();
        toolbar.add(wordCountItem);

        add(toolbar, BorderLayout.PAGE_START);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                textArea.read(br, null);
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
                textArea.write(bw);
                bw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void findText() {
        String searchText = JOptionPane.showInputDialog("Find:");
        if (searchText != null) {
            String text = textArea.getText();
            int index = text.indexOf(searchText);
            if (index >= 0) {
                textArea.setCaretPosition(index);
                textArea.setSelectionStart(index);
                textArea.setSelectionEnd(index + searchText.length());
            }
        }
    }
    private void replaceText() {
        String searchText = JOptionPane.showInputDialog("Find:");
        if (searchText != null) {
            String replaceText = JOptionPane.showInputDialog("Replace with:");
            if (replaceText != null) {
                String text = textArea.getText();
                text = text.replace(searchText, replaceText);
                textArea.setText(text);
            }
        }
    }

    private void countWords() {
        String text = textArea.getText();
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        JOptionPane.showMessageDialog(this, "Word Count: " + wordCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Use the system look and feel for a native appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            AdvancedTextEditor textEditor = new AdvancedTextEditor();
            textEditor.setVisible(true);
        });
    }
}