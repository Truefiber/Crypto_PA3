import org.apache.commons.lang.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gennadiy on 26.01.2015.
 */
public class HashAlg {

    public static String getHashOfFile(String fileName) {

        List<byte[]> listOfBlocks = chopFileOnBlocks(fileName, 1024);
        byte[] finalHash = makeHash(listOfBlocks);

        return getStringOfHash(finalHash);
    }

    private static List<byte[]> chopFileOnBlocks(String fileName, int sizeOfBlocks) {

        byte[] blockOfData = new byte[sizeOfBlocks];
        int bytesWereRead;
        List<byte[]> listOfBlocks = new ArrayList<byte[]>();

        try {

            File file = new File(fileName);
            FileInputStream inputStream = new FileInputStream(file);

            while ((bytesWereRead=inputStream.read(blockOfData)) != -1){

                listOfBlocks.add(Arrays.copyOfRange(blockOfData, 0, bytesWereRead));

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfBlocks;
    }

    private static byte[] makeHash(List<byte[]> list) {

        byte[] fileHash = new byte[256];

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            fileHash = messageDigest.digest(list.get(list.size() - 1));

            for (int i = list.size() - 2; i >= 0; i--) {

                byte[] concatBlockAndHash = ArrayUtils.addAll(list.get(i), fileHash);
                fileHash = messageDigest.digest(concatBlockAndHash);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return fileHash;

    }

    private static String getStringOfHash(byte[] hashToString) {

        StringBuilder hashBuilder = new StringBuilder();

        for (byte b : hashToString) {
            hashBuilder.append(String.format("%02x", b));
        }

        return hashBuilder.toString();

    }
}
