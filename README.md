## YAZILIM MİMARİSİ ÖDEVİ
### Decorator Tasarım Deseni
Decorator tasarım deseni, structural tasarım desenlerinden biridir. Bir nesneye dinamik olarak yeni özellikler eklemek için kullanılır. Kalıtım kullanmadan da bir nesnenin görevlerini artırabileceğimizi gösterir.
Decorator tasarım desenini Decorator sınıfları ve Component sınıfları şeklinde iki kısma ayırabiliriz. Component sınıfları içerisinde Decorator süper sınıfı bulunur. Yani şu şekilde bir yapı oluşturmak gereklidir:

![Image of Class](https://github.com/safakerer/yazilimMimarisiOdev/blob/master/Decarotor%C5%9Eablon.png)

Decorator sınıfı Component sınıfından türemiştir. Decorator sınıfı içerisinde Component türünden instance değişken bulunur.Decorator sınıfı abstract veya interface olabilir. Somut sınıf kullanmamak gereklidir.Dinamik olarak özelliklerin ekleneceği nesne, ConcreteComponent sınıfından türetilir.ConcreteDecorator nesnesi, ConcreteComponent nesnesine özelliklerin eklenmesi işlemini yapar.Decorator tasarım desenini kullanarak basit bir uygulama yapacağız.

 Component interface. Tum decorator siniflari bu interface'i implement etmek zorundadir
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
