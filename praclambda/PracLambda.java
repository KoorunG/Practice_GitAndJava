package praclambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PracLambda {

    public static String processFile() throws IOException {

        FileReader fr = new FileReader("data.txt");
        BufferedReader br = new BufferedReader(fr);
        
        try(br;fr){
            return br.readLine();                       // try-with-resource 구문
        }
    }



    public static String processFile(BufferedReaderProcessor p) throws IOException {

        FileReader fr = new FileReader("data.txt");
        BufferedReader br = new BufferedReader(fr);
        
        try(br;fr){
            return p.process(br);
        }
    }
    public static void main(String[] args) throws IOException {


        processFile();                                      // 역시 한 행을 처리하는 코드지만 인수가 없다.
        
        processFile((BufferedReader br) -> br.readLine());                      //한 행을 처리하는 코드
        processFile((BufferedReader br) -> br.readLine() + br.readLine());      //두 행을 처리하는 코드
        
        
    }
}
