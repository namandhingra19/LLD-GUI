package parking.src;

import javax.swing.SwingUtilities;
import parking.src.ui.ParkingLotFrame;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            ParkingLotFrame frame = new ParkingLotFrame();
            


            frame.setVisible(true);
        });
    }
}