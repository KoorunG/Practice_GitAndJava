package optional;

import java.util.Optional;

public class OfNullable {
    
    public static Optional<Integer> stringToInt(String s){
        
        try{
            return Optional.of(Integer.parseInt(s));        // Optional.of => 빈값일 경우 NullPointException 발생
                                                            // 하지만 그 전에 Integer.parseInt(s)에 의해 NumberFormatException 발생하기 때문에
        } catch (NumberFormatException e){
            return Optional.empty();                        // 이를 예외로 잡으면 Optional.empty()를 반환한다
        }                                                   // 따라서 Optional에 관련된 클래스 OptionalUtility를 만들어 둔 뒤 필요에 따라
                                                            // 이런 메소드 ( ex - stringToInt )를 모아두고 쓰면 try-catch를 쓸 필요가 없다는 것
    }

    
    public static Optional<Integer> stringToInt2(String s){
        return Optional.ofNullable(Integer.parseInt(s));
    }
}

class Test{
    public static void main(String[] args) {

        String s = "김쿠렁";                     // NumberFormatException 반환을 유도...
        OfNullable.stringToInt2(s);             // 빈값 반환 (Optional.empty())
        OfNullable.stringToInt(s);              // 여전히 NumberFormatException 반환 (즉 쓸모없다!)


    }
}