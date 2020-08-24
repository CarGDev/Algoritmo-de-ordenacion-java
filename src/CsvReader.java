import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CsvReader {

    public static final String SEPARATOR = ";";
    public static final String QUOTE = "\"";
    private int i = 0;
    String tempDensity[] = new String[20];
    int density[] = new String[20];
    String city[] = new String[20];

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
                for (int j = 0; j < fields.length; j++) {
                    if (j==0) {
                        city[i] = fields[j];
                    } else {
                        tempDensity[i] = fields[j];
                    }
                }
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
            System.out.println(city[j]);
            System.out.println(tempDensity[j]);
            density[j] = Integer.parseInt(tempDensity[j]);
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
