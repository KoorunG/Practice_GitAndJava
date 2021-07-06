package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Execute_Arround {

    /* 목표 : 두 줄을 읽어들이는 BufferedReader 만들기! -> 
    BufferedReaderProcessor 인터페이스를 만든 뒤, BufferedReader을 인자로 받는 메소드를 구현하도록 만들고 구현 */
    public static void main(String[] args) throws IOException {

        String oneLine = process((BufferedReader br) -> br.readLine());
        String twoLine = process((BufferedReader br) -> br.readLine() + "\n" + br.readLine());

        System.out.println(oneLine);
        System.out.println(twoLine);
    }

    public static String process(BufferedReaderProcessor p) throws IOException {
        
        FileReader fr = new FileReader("data.txt");
        BufferedReader br = new BufferedReader(fr);

        try(fr;br){
            return p.processFile(br);
        }
    }


}
