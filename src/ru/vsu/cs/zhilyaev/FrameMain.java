package ru.vsu.cs.zhilyaev;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class FrameMain extends  JFrame {

    private JButton buttonSample1;
    private JButton buttonRandom1;
    private JButton buttonRandom2;
    private JTable tableArr;
    private JButton buttonSelectionSort;
    private JButton buttonLoadInputFromFile;
    private JButton buttonSaveOutputIntoFile;
    private JPanel panelMain;
    private JTable tableFromTo;
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JPanel panelPerformance;

    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;


    public FrameMain() {
        this.setTitle("Сортировки");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        JTableUtils.initJTableForArray(tableArr, 60, false, true, false, true);
        tableArr.setRowHeight(30);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        ru.vsu.cs.util.SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        ru.vsu.cs.util.JTableUtils.writeArrayToJTable(tableArr, new int[]{
                0, 1, 2, 3, 4
        });

        this.pack();

        buttonSample1.addActionListener(actionEvent -> {
            int[] arr = {3, 8, 2, 5, 6, 1, 9, 7, 0, 4};
            JTableUtils.writeArrayToJTable(tableArr, arr);
        });
        buttonRandom1.addActionListener(actionEvent -> {
            int[] arr = ArrayUtils.createRandomIntArray(10, 100);
            JTableUtils.writeArrayToJTable(tableArr, arr);
        });
        buttonRandom2.addActionListener(actionEvent -> {
            int[] arr = ArrayUtils.createRandomIntArray(500, 10000);
            JTableUtils.writeArrayToJTable(tableArr, arr);
        });

        buttonLoadInputFromFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] arr = ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath());
                    ru.vsu.cs.util.JTableUtils.writeArrayToJTable(tableArr, arr);
                }
            } catch (Exception e) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonSaveOutputIntoFile.addActionListener(actionEvent -> {
            try {
                if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                    int[] arr = JTableUtils.readIntArrayFromJTable(tableArr);
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, arr);
                }
            } catch (Exception e) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(e);
            }
        });

        buttonSelectionSort.addActionListener(actionEvent -> {
            try {
                Integer[] arr = ArrayUtils.toObject(JTableUtils.readIntArrayFromJTable(tableArr));
                assert arr != null;

                String fromText = textFieldFrom.getText();
                int from = Integer.parseInt(fromText);
                String toText = textFieldTo.getText();
                int to = Integer.parseInt(toText);

                SelectionSort.sort(arr, from, to);

                int[] primitiveArr = ArrayUtils.toPrimitive(arr);
                JTableUtils.writeArrayToJTable(tableArr, primitiveArr);
            } catch (Exception e) {
                ru.vsu.cs.util.SwingUtils.showErrorMessageBox(e);
            }
        });

    }
}
