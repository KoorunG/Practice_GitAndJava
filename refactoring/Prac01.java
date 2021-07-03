package refactoring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Prac01 {
    
    public String processFile() throws IOException {
        FileReader fr = new FileReader("data.txt");
        BufferedReader br = new BufferedReader(fr);
        
        try(br;fr){
            return br.readLine();
        }
    }                                                       // 1. "data.txt" 를 읽은 뒤 한 행씩 처리하는 메소드 processFile()

    public String processFileDouble(BufferedReaderProcessor p) throws IOException {
        FileReader fr = new FileReader("data.txt");
        BufferedReader br = new BufferedReader(fr);
        
        try(br;fr){
            return p.process(br);
        }                                                   // 2. BufferedReader를 인자로 받는 BufferedReaderProcessor 인터페이스를 만든 뒤 구현 (process())
    }           
}

class Test {
    public static void main(String[] args) throws IOException {
        
        Prac01 pr = new Prac01();
        System.out.println(pr.processFile());
        
        String oneLine = pr.processFileDouble((BufferedReader br) -> br.readLine());
        String twoLine = pr.processFileDouble((BufferedReader br) -> br.readLine() + br.readLine());

        System.out.println(oneLine);
        System.out.println(twoLine);
       
    }
}
