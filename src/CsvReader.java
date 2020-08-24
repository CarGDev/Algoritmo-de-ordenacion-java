import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

    public static final String SEPARATOR = ";";
    public static final String QUOTE = "\"";
    private int i = 0;
    String tempDensity[] = new String[20];
    int density[] = new int[20];
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
            //System.out.println(city[j]);
            //System.out.println(tempDensity[j]);
            density[j] = Integer.parseInt(tempDensity[j]);
            //System.out.println(density[j]);
        }
        quicksort(city, density, 0, 19);

    }

    private static String[] removeTrailingQuotes(String[] fields) {

        String result[] = new String[fields.length];


        for (int i=0;i<result.length;i++){
            result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");

        }
        return result;
    }

    private static void resultArray (String[] B, int[] A, int C) {
        String result = null;
        System.out.println("---------empieza" + C + "---------");
        for (int k = 0; k < A.length; k++) {
            if (k==0) {
                result = "[ " + B[k] + ": " + Integer.toString(A[k]);
            } else if (k == A.length - 1) {
                result = result + " | " + B[k] + ": " + Integer.toString(A[k])  + " ]";
            } else {
                result = result + " | " + B[k] + ": " + Integer.toString(A[k]);
            }
        }
        System.out.println(result);
    }

    public static void quicksort(String[] city, int A[], int izq, int der) {

        String pivoteCity=city[izq];
        int pivote=A[izq]; // tomamos primer elemento como pivote
        int i=izq;         // i realiza la búsqueda de izquierda a derecha
        int j=der;         // j realiza la búsqueda de derecha a izquierda
        int aux;
        String auxCity;
        System.out.println(A);

        while(i < j){                          // mientras no se crucen las búsquedas
            while(A[i] <= pivote && i < j) i++; // busca elemento mayor que pivote
            while(A[j] > pivote) j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                resultArray(city, A, 1);
                auxCity = city[i];
                aux= A[i];                      // los intercambia
                city[i] = city[j];
                A[i]=A[j];
                city[j]=auxCity;
                A[j]=aux;
                resultArray(city, A, 2);
            }
        }

        resultArray(city, A, 3);
        city[izq]=city[j];
        city[j]=pivoteCity;
        A[izq]=A[j];      // se coloca el pivote en su lugar de forma que tendremos
        A[j]=pivote;      // los menores a su izquierda y los mayores a su derecha

        if(izq < j-1) {
            resultArray(city, A, 4);
            quicksort(city, A,izq,j-1);          // ordenamos subarray izquierdo
        }
        if(j+1 < der) {
            resultArray(city, A, 5);
            quicksort(city, A,j+1,der);          // ordenamos subarray derecho
        }
        resultArray(city, A, 6);
        //for (int h = 0; h < 20; h++) {
        //    System.out.println(city[h] + ": " + A[h]);
        //}
    }

}
