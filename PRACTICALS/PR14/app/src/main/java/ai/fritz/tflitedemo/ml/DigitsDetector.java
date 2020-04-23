package ai.fritz.tflitedemo.ml;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class DigitsDetector {
    private final String TAG = this.getClass().getSimpleName();

    // The tensorflow lite file
    private Interpreter tflite;

    // Input byte buffer
    private ByteBuffer inputBuffer = null;

    // Output array [batch_size, 10]
    private float[][] mnistOutput = null;

    // Name of the file in the assets folder
    private static final String MODEL_PATH = "mnist.tflite";

    // Specify the output size
    private static final int NUMBER_LENGTH = 10;

    // Specify the input size
    private static final int DIM_BATCH_SIZE = 1;
    private static final int DIM_IMG_SIZE_X = 28;
    private static final int DIM_IMG_SIZE_Y = 28;
    private static final int DIM_PIXEL_SIZE = 1;

    // Number of bytes to hold a float (32 bits / float) / (8 bits / byte) = 4 bytes / float
    private static final int BYTE_SIZE_OF_FLOAT = 4;


    public DigitsDetector(Activity activity) {
        try {
            tflite = new Interpreter(loadModelFile(activity));
            inputBuffer =
                    ByteBuffer.allocateDirect(
                            BYTE_SIZE_OF_FLOAT * DIM_BATCH_SIZE * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE);
            inputBuffer.order(ByteOrder.nativeOrder());
            mnistOutput = new float[DIM_BATCH_SIZE][NUMBER_LENGTH];
            Log.d(TAG, "Created a Tensorflow Lite MNIST Classifier.");
        } catch (IOException e) {
            Log.e(TAG, "IOException loading the tflite file");
        }
    }

    /**
     * Run the TFLite model
     */
    protected void runInference() {
        tflite.run(inputBuffer, mnistOutput);
    }

    /**
     * Classifies the number with the mnist model.
     *
     * @param bitmap
     * @return the identified number
     */
    public int classify(Bitmap bitmap) {
        if (tflite == null) {
            Log.e(TAG, "Image classifier has not been initialized; Skipped.");
        }
        preprocess(bitmap);
        runInference();
        return postprocess();
    }

    /**
     * Go through the output and find the number that was identified.
     *
     * @return the number that was identified (returns -1 if one wasn't found)
     */
    private int postprocess() {
        for (int i = 0; i < mnistOutput[0].length; i++) {
            float value = mnistOutput[0][i];
            Log.d(TAG, "Output for " + Integer.toString(i) + ": " + Float.toString(value));
            if (value == 1f) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Load the model file from the assets folder
     */
    private MappedByteBuffer loadModelFile(Activity activity) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(MODEL_PATH);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    /**
     * Converts it into the Byte Buffer to feed into the model
     *
     * @param bitmap
     */
    private void preprocess(Bitmap bitmap) {
        if (bitmap == null || inputBuffer == null) {
            return;
        }

        // Reset the image data
        inputBuffer.rewind();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        long startTime = SystemClock.uptimeMillis();

        // The bitmap shape should be 28 x 28
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < pixels.length; ++i) {
            // Set 0 for white and 255 for black pixels
            int pixel = pixels[i];
            // The color of the input is black so the blue channel will be 0xFF.
            int channel = pixel & 0xff;
            inputBuffer.putFloat(0xff - channel);
        }
        long endTime = SystemClock.uptimeMillis();
        Log.d(TAG, "Time cost to put values into ByteBuffer: " + Long.toString(endTime - startTime));
    }
}
