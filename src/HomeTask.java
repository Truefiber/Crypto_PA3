/**
 * Created by Gennadiy on 26.01.2015.
 */
public class HomeTask {

    public static void main(String[] args) {

        String testFile = "C:\\JavaProject\\CourseraHW\\6 - 2 - Generic birthday attack (16 min).mp4";
        String taskFile = "C:\\JavaProject\\CourseraHW\\fileForTest1.mp4";
        String testHash = "03c08f4ee0b576fe319338139c045c89c3e8e9409633bea29442e21425006ea8";


        System.out.println(HashAlg.getHashOfFile(testFile).equals(testHash));
    }
}
