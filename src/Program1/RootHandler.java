package Program1;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class RootHandler implements HttpHandler {

    String nameFile;
    String start = "<table border=\"1\">" + "<tbody>";
    String end = "</tbody>" + "</table>";
    String one = Files.readString(Path.of("1.txt"));
    String two = Files.readString(Path.of("2.txt"));
    String three = Files.readString(Path.of("3.txt"));
    String four = Files.readString(Path.of("4.txt"));
    String five = Files.readString(Path.of("5.txt"));

    RootHandler(String nameFile) throws IOException {
        this.nameFile=nameFile;
    }

    @Override
    public void handle(HttpExchange he) throws IOException {
        String response = he.getRequestURI().getPath();
        if (response.length() == 2) {
            Path fileName = Path.of(nameFile);
            String mid =  Files.readString(fileName);
            String midDigit = mid.replaceAll("\\D+"," ");

            String suma = countSum(midDigit);
            String fin = start+mid+end+suma;
            he.sendResponseHeaders(200, fin.length());
            OutputStream os = he.getResponseBody();
            os.write(fin.getBytes());
            os.close();

        }else if(response.contains("_") && !response.matches(".*[a-z].*")){
            boolean isCorrect = true;
            String temp = response.replaceAll("_"," ");
            String numWarehouse = temp.replaceAll("\\D+"," ");
            String[] numWarehouseArray = numWarehouse.split(" ");
            int[] results = new int[numWarehouseArray.length-1];

            for(int i=1;i<numWarehouseArray.length;i++)
              results[i-1] = Integer.parseInt(numWarehouseArray[i]);

            for(int x:results){
                if(!(x>0&&x<6)){
                   isCorrect=false;
                }

            }
            String mid="";
            if(isCorrect) {
                for (int a : results) {

                    switch (a) {
                        case 1:
                            mid += one;
                            break;
                        case 2:
                            mid +=two;
                            break;
                        case 3:
                            mid+=three;
                            break;
                        case 4:
                            mid+=four;
                            break;
                        case 5:
                            mid+=five;
                            break;
                        default:

                    }
                }
                String midDigit = mid.replaceAll("\\D+"," ");
                String suma = countSum(midDigit);
                String fin = start+mid+end+suma;
                he.sendResponseHeaders(200, fin.length());
                OutputStream os = he.getResponseBody();
                os.write(fin.getBytes());
                os.close();

            }else{
                String error = "Magazyn nie istnieje lub wpisales blednie magazyn! Wpisz np. http://localhost:8080/1";
                he.sendResponseHeaders(200, error.length());
                OutputStream os = he.getResponseBody();
                os.write(error.getBytes());
                os.close();
            }

        } else {
            String error = "Magazyn nie istnieje lub wpisales blednie magazyn! Wpisz np. http://localhost:8080/1";
            he.sendResponseHeaders(200, error.length());
            OutputStream os = he.getResponseBody();
            os.write(error.getBytes());
            os.close();
        }

    }

    public String countSum(String midDigit){
        int sum=0;
        String[] midDigitArray = midDigit.split(" ");
        int[]results = new int[midDigitArray.length];

        for(int i=1;i<midDigitArray.length;i++)
            results[i]=Integer.parseInt(midDigitArray[i]);

        for(int a : results)
            sum+=a;
        String suma = Integer.toString(sum);
        return suma;

    }

}
