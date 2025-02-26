QR Code Reader
This is a QR Code Reader program developed using OpenCV for webcam integration and ZXing for QR code decoding. The program allows users to scan QR codes either through their webcam or from image files.

Work in progress. The functionality is mostly complete, but there are still some optimizations and additional features to be added.

🚀 Features:
Scan QR codes using your webcam: Point your camera at a QR code, and the program will detect and decode it.
Scan QR codes from an image file: Choose an image file containing a QR code, and the program will read the QR code content.

🔧 Technologies Used:
Java: Programming language used for the application.
OpenCV: Computer vision library for webcam capture and image processing.
ZXing: Library for decoding QR codes from images.

📦 Prerequisites:
Before running the application, ensure you have the following installed:

Java (JDK 8 or higher)
OpenCV: Install the Java bindings for OpenCV.
Download OpenCV from here.
Extract the files and note the path where OpenCV is installed.
Ensure that the opencv_java library is accessible from your project.
ZXing library (for QR code decoding).
🛠 Setup and Installation

Step 1: Clone the Repository
Clone this repository to your local machine.
bash
Copy
Edit
git clone https://github.com/yourusername/QRCodeReader.git

Step 2: Open the Project
Open the project in your IDE (IntelliJ IDEA or any Java IDE).

Step 3: Add OpenCV to the Project
Download OpenCV from the official OpenCV site.
Include the OpenCV JAR file (opencv-*.jar) in your project.
Set the path to the OpenCV native library. Update the code to point to the correct path for the OpenCV native library:
java
Copy
Edit
System.load("path_to_opencv/opencv_java451.dll"); // Windows
// Or on Linux: System.load("/usr/lib/libopencv_java451.so");

Step 4: Run the Application
To run the program, you can either:
Scan QR code using your webcam (option 0).
Scan QR code from an image file (option 1).
The program will display the decoded text from the QR code once it successfully scans it.

📝 Usage Instructions
Run the program.
Choose whether you want to scan from the webcam or an image file.
If you select the webcam option, ensure your camera is properly connected and working.
The program will display the decoded QR code content in the console.

⚠️ Known Issues
The webcam scanning might be affected by lighting conditions or camera focus.
The current file scanning works only with images that clearly show the QR code. Minor distortions or blurry images might not be detected.

🔄 Work in Progress
This project is still in development. New features and improvements will be added in the future. Your contributions, feedback, and bug reports are welcome!
