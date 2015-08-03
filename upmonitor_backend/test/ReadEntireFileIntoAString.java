
import java.io.File;
import java.io.FileNotFoundException;
import com.vng.jcore.common.Config;
import java.util.Scanner;

public class ReadEntireFileIntoAString {

    public static void main(String[] args) throws FileNotFoundException {
        String configFile = Config.CONFIG_HOME + "/" + System.getProperty("appenv") + "." + Config.CONFIG_FILE ;
        String entireFileText = new Scanner(new File(configFile))
                .useDelimiter("\\A").next();

        System.out.println(entireFileText);
    }
}
