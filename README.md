## YAZILIM MİMARİSİ ÖDEVİ
### Decorator Tasarım Deseni
Decorator tasarım deseni, structural tasarım desenlerinden biridir. Bir nesneye dinamik olarak yeni özellikler eklemek için kullanılır. Kalıtım kullanmadan da bir nesnenin görevlerini artırabileceğimizi gösterir.
Decorator tasarım desenini Decorator sınıfları ve Component sınıfları şeklinde iki kısma ayırabiliriz. Component sınıfları içerisinde Decorator süper sınıfı bulunur. Yani şu şekilde bir yapı oluşturmak gereklidir:

![Image of Class](https://github.com/safakerer/yazilimMimarisiOdev/blob/master/Decarotor%C5%9Eablon.png)

Decorator sınıfı Component sınıfından türemiştir. Decorator sınıfı içerisinde Component türünden instance değişken bulunur.Decorator sınıfı abstract veya interface olabilir. Somut sınıf kullanmamak gereklidir.Dinamik olarak özelliklerin ekleneceği nesne, ConcreteComponent sınıfından türetilir.ConcreteDecorator nesnesi, ConcreteComponent nesnesine özelliklerin eklenmesi işlemini yapar.Decorator tasarım desenini kullanarak basit bir uygulama yapacağız.

![Image of Class](https://github.com/safakerer/yazilimMimarisiOdev/blob/master/decoratorUML1.png)

 Component interface. Tum decorator sınıfları bu interface'i implement etmek zorundadır.
```java
public interface Calculator {
    public double calculate();
}
```

Decorator işleminin yapılacağı sınıf oluşturulur.
```java
public class ConcreteCalculator implements Calculator {
    private double value=0;
    public ConcreteCalculator(double value){
        this.value=value;
    }
    @Override
    public double calculate() {
        return value;
    }
```

Decorator sınıfının oluşturuyoruz. Decarotor sınıfını oluştururken abstract tanımlamak zorundayız.
```java
public abstract class CalculateDecorator implements Calculator {
    protected Calculator calculator; //eklemek zorunlu
  
    protected CalculateDecorator(Calculator calculator) {
        this.calculator = calculator;
    }
  
    @Override
    public double calculate() {
        return calculator.calculate(); //Calculator interfacedeki calculate metodunu cagirmasi zorunlu
    }
}
```

## Chain of Responsibility Tasarım Deseni
Bu tasarım deseni Davranışsal Tasarım Desenlerine (behavioral design patterns) aittir. Bu desen değişen şeylere karşı geliştirilebilir (esnek) bir altyapı sağlayan tasarım desenleri bu kategori altında yer alıyor. Chain of responsibility tasarım deseni; bir isteğin duruma göre farklı şekillerde işlem yapılması gereken durumlarda kullanılır. Bu tasarım deseninde isteğe cevap verebilecek sınıflar aynı arayüzü kullanır ve isteğin durumuna göre ya cevap verir ya da isteği zincirdeki sonraki nesneye gönderir. 

![Image of Class](https://github.com/safakerer/yazilimMimarisiOdev/blob/master/chain_pattern_uml_diagram.jpg)


Soyut bir logger sınıfı oluşturalım.
```java
public abstract class AbstractLogger {
   public static int INFO = 1;
   public static int DEBUG = 2;
   public static int ERROR = 3;

   protected int level;

   //next element in chain or responsibility
   protected AbstractLogger nextLogger;

   public void setNextLogger(AbstractLogger nextLogger){
      this.nextLogger = nextLogger;
   }

   public void logMessage(int level, String message){
      if(this.level <= level){
         write(message);
      }
      if(nextLogger !=null){
         nextLogger.logMessage(level, message);
      }
   }

   abstract protected void write(String message);
	
}
```
Soyut Logger sınıfımızı genişletelim. `ConsoleLogger.java`, `ErrorLogger.java` ve `FileLogger.java` sınıfımızı extend edelim.
```java
public class ConsoleLogger extends AbstractLogger {

   public ConsoleLogger(int level){
      this.level = level;
   }

   @Override
   protected void write(String message) {		
      System.out.println("Standard Console::Logger: " + message);
   }
}
```
Farklı türlerde Loggerlar oluşturalım.Onlara hata düzeyleri atayalım ve her Logger'a bir sonraki logger'ı ayarlayalım. Her Logger bir sonraki Logger zincirinin bir bölümünü temsil eder.
```java
public class ChainPatternDemo {
	
   private static AbstractLogger getChainOfLoggers(){

      AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
      AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
      AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

      errorLogger.setNextLogger(fileLogger);
      fileLogger.setNextLogger(consoleLogger);

      return errorLogger;	
   }

   public static void main(String[] args) {
      AbstractLogger loggerChain = getChainOfLoggers();

      loggerChain.logMessage(AbstractLogger.INFO, 
         "This is an information.");

      loggerChain.logMessage(AbstractLogger.DEBUG, 
         "This is an debug level information.");

      loggerChain.logMessage(AbstractLogger.ERROR, 
         "This is an error information.");
   }
}
```
