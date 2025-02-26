import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

class QRCodeReader {
    static {
        System.load("D:\\Downloads2\\opencv\\build\\java\\x64\\opencv_java451.dll");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(":");
        System.out.println("0 - WEBCAM");
        System.out.println("1 - FILE");
        System.out.print("Choose your Preference ");
        int choice = scanner.nextInt();

        if (choice == 0) {
            scanQRCodeFromCamera();
        } else if (choice == 1) {
            scanQRCodeFromFile();
        } else {
            System.out.println("INVALID CHOICE!");
        }
    }

    private static void scanQRCodeFromCamera() {
        VideoCapture camera = new VideoCapture(0, org.opencv.videoio.Videoio.CAP_ANY);

        if (!camera.isOpened()) {
            System.out.println("WEBCAM COULDN'T OPEN!");
            return;
        }

        Mat frame = new Mat();
        System.out.println("SHOW THE QR CODE TO THE CAMERA... (Press 'q' to exit)");

        JFrame window = new JFrame("QR Code Scanner");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel();
        window.add(label);
        window.setSize(640, 480);
        window.setVisible(true);

        while (true) {
            camera.read(frame);
            if (!frame.empty()) {
                BufferedImage image = matToBufferedImage(frame);
                label.setIcon(new ImageIcon(image));
                window.repaint();
                                                             //System.out.println("Frame captured successfully!");

                String qrText = decodeQRCode(frame);
                if (qrText != null) {
                    System.out.println("QR CODE CONTENT: " + qrText);
                    break;
                }
            }


            try {
                if (System.in.available() > 0 && System.in.read() == 'q') {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        camera.release();
        window.dispose();
    }

    private static void scanQRCodeFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                        new BufferedImageLuminanceSource(bufferedImage)));
                Result result = new MultiFormatReader().decode(binaryBitmap);

                System.out.println("QR CODE CONTENT: " + result.getText());
            } catch (Exception e) {
                System.out.println("Cannot Read The QR: " + e.getMessage());
            }
        } else {
            System.out.println("File NOT Found.");
        }
    }

    private static String decodeQRCode(Mat frame) {
        try {
            Mat gray = new Mat();
            Imgproc.cvtColor(frame, gray, Imgproc.COLOR_BGR2GRAY);



            byte[] byteArray = new byte[(int) (gray.total() * gray.channels())];
            gray.get(0, 0, byteArray);

            InputStream in = new ByteArrayInputStream(byteArray);
            BufferedImage bufferedImage = ImageIO.read(in);

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(bufferedImage)));
            Result result = new MultiFormatReader().decode(binaryBitmap);

            return result.getText();
        } catch (Exception e) {
            return null;
        }
    }

    private static BufferedImage matToBufferedImage(Mat mat) {
        int type = (mat.channels() > 1) ? BufferedImage.TYPE_3BYTE_BGR : BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        byte[] data = new byte[(int) (mat.total() * mat.channels())];
        mat.get(0, 0, data);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);
        return image;
    }
}
