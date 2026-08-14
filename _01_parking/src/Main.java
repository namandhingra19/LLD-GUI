package _01_parking.src;

import javax.swing.SwingUtilities;
import _01_parking.src.ui.ParkingLotFrame;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ParkingLotFrame frame = new ParkingLotFrame();
            


            frame.setVisible(true);
        });
    }
}