package ru.vsu.cs.zhilyaev;
import java.util.Locale;

public class Main {
    public static void winMain()  {
        Locale.setDefault(Locale.ROOT);
        ru.vsu.cs.util.SwingUtils.setDefaultFont("Times New Roman", 18);

        java.awt.EventQueue.invokeLater(() -> new FrameMain().setVisible(true));
    }

    public static void main(String[] args) throws Exception {
        winMain();
    }
}
