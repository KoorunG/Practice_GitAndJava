package optional;

import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OptionalMain {
  public static final String unknown = "Unknown";   // final 처리를 해줘서 오타방지...

  public String getCarInsuranceNameNullSafeV1(PersonV1 person) {

    
    if (person != null) {
      CarV1 car = person.getCar();
      if (car != null) {
        Insurance insurance = car.getInsurance();
        if (insurance != null) {
          return insurance.getName();
        }
      }
    }
    return unknown;   // 깊은의심 -> 코드의 구조가 엉망이 되어 별로 좋은 방법은 아님
  }

  public String getCarInsuranceNameNullSafeV2(PersonV1 person) {
    if (person == null) {
      return unknown;
    }
    CarV1 car = person.getCar();
    if (car == null) {
      return unknown;
    }
    Insurance insurance = car.getInsurance();
    if (insurance == null) {
      return unknown;
    }
    return insurance.getName();   // if의 출구가 너무 많아짐 -> 역시 깔끔하지 않으며 유지보수도 어려움
  }

  // 컴파일되지 않음:
  // (1)에서 Optional<Person>에 map(Person::getCar) 호출을 시도함. flatMap()을 이용하면 문제가 해결됨.
  // 그리고 (2)에서 Optional<Car>에 map(Car::getInsurance) 호출을 시도함. flatMap()을 이용하면 문제가 해결됨.
  // Insurance::getName은 평범한 문자열을 반환하므로 추가 "flatMap"은 필요가 없음.


  public static String getCarInsuranceName(Optional<Person> person) {           //Optional : '마치 스트림처럼' map, flatMap등의 메소드를 지원한다!
    return person
        .flatMap(Person::getCar)      // optPerson -> optCar             ( 여기서 getCar은 Person을 인자로 받아 Optional(Car)를 반환하는 메소드이다 ) => 따라서 flatMap
        .flatMap(Car::getInsurance)   // optCar -> optInsurance          ( 여기서 getInsurance는 Car를 인자로 받아 Optional(Insurance)를 반환하는 메소드이다 ) => 따라서 flatMap
        .map(Insurance::getName)      // optInsurance -> optString       ( 여기서 getName은 Insurance를 인자로 받고 String을 반환하는 메소드이다 ) => 일반 map 가능
        .orElse(unknown);             // 비어있으면 "Unknown"을 반환.
  }

  public Set<String> getCarInsuranceNames(List<Person> persons) {       // 일반 스트림
    return persons.stream()
        .map(Person::getCar)        
        .map(optCar -> optCar.flatMap(Car::getInsurance))
        .map(optInsurance -> optInsurance.map(Insurance::getName))
        .flatMap(Optional::stream)
        .collect(toSet());
  }

  public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
    if (person.isPresent() && car.isPresent()) {
      return Optional.of(findCheapestInsurance(person.get(), car.get()));
    } else {
      return Optional.empty();
    }
  }

  public Insurance findCheapestInsurance(Person person, Car car) {
    // 다른 보험사에서 제공한 질의 서비스
    // 모든 데이터 비교
    Insurance cheapestCompany = new Insurance();
    return cheapestCompany;
  }

  public Optional<Insurance> nullSafeFindCheapestInsuranceQuiz(Optional<Person> person, Optional<Car> car) {  // 두 Optional을 인자로 받아서
    return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));  // map으로 c -> (otpP. optC)
                                                                            // flatMap으로 (otpP. optC) -> optInsurance
  }

  public String getCarInsuranceName(Optional<Person> person, int minAge) {
    return person.filter(p -> p.getAge() >= minAge)
     .flatMap(Person::getCar)
     .flatMap(Car::getInsurance)
     .map(Insurance::getName)
     .orElse("Unknown");
  }

}
