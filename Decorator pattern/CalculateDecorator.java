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