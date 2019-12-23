## YAZILIM MİMARİSİ ÖDEVİ
### Decorator Tasarım Deseni
Decorator tasarım deseni, structural tasarım desenlerinden biridir. Bir nesneye dinamik olarak yeni özellikler eklemek için kullanılır. Kalıtım kullanmadan da bir nesnenin görevlerini artırabileceğimizi gösterir.
Decorator tasarım desenini Decorator sınıfları ve Component sınıfları şeklinde iki kısma ayırabiliriz. Component sınıfları içerisinde Decorator süper sınıfı bulunur. Yani şu şekilde bir yapı oluşturmak gereklidir:

![Image of Class](https://github.com/safakerer/yazilimMimarisiOdev/blob/master/Decarotor%C5%9Eablon.png)
Decorator sınıfı Component sınıfından türemiştir. Decorator sınıfı içerisinde Component türünden instance değişken bulunur.Decorator sınıfı abstract veya interface olabilir. Somut sınıf kullanmamak gereklidir.Dinamik olarak özelliklerin ekleneceği nesne, ConcreteComponent sınıfından türetilir.ConcreteDecorator nesnesi, ConcreteComponent nesnesine özelliklerin eklenmesi işlemini yapar.
