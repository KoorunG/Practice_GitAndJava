package chapter3;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderProcessor {

    String processFile(BufferedReader br) throws IOException;

}
