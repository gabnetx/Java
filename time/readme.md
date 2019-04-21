# API, since Java 8
https://docs.oracle.com/javase/8/docs/api/java/time/package-frame.html

## Examples

```java
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
public class Tester
{
   public static void main(String argumentos[]){
       
       Tester t = new Tester();
       
        LocalDate mydate = LocalDate.now();
        System.out.println("La fecha es: "+ mydate);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String text = mydate.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        
        System.out.println("La fecha es:"+ text);
        
       DayOfWeek lunes = DayOfWeek.MONDAY; 
       System.out.printf("8 days more: %s%n",lunes.plus(8)); 
       System.out.printf("2 days less: %s%n",lunes.minus(2));
        
   } 
}
```
