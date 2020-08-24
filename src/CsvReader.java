import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CsvReader {

    public static final String SEPARATOR = ",";
    public static final String QUOTE = "\"";
    private String density[] = new String[20];
    private String city[] = new String[20];
    private int i = 0;
    public void CsvReader () throws IOException {

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader("../MOCK_DATA.csv"));
            String line = br.readLine();

            while (null != line) {
                String[] fields = line.split(SEPARATOR);
                //System.out.println(Arrays.toString(fields));

                fields = removeTrailingQuotes(fields);
                //System.out.println(Arrays.toString(fields));
                density[i] = Arrays.toString(fields);
                city[i] = Arrays.toString(fields)
                //System.out.println(density[i]);
                i++;
                line = br.readLine();


            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (null != br) {
                br.close();
            }
        }

        for (int j = 0; j < 20; j++) {
            System.out.println(density[j]);
        }
    }

    private static String[] removeTrailingQuotes(String[] fields) {

        String result[] = new String[fields.length];

        for (int i=0;i<result.length;i++){
            result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
        }
        return result;
    }

}
