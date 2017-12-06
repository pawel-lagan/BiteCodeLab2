package pl.com.tt.mocking;

import java.util.Set;

public interface Engine {
    
    public static enum EngineError {
        LAMBDA_PROBE_ERROR, TOO_MUCH_FUEL, TOO_LOW_OXYGENE, UNKNOWN_ERROR, SAFE_MODE, INJECTOR_ERROR
    }
      
    public boolean isPowerOn();
    public Long getOilTemperature();
    public Long getOilPressure();
    public boolean isRunning();
    public Set<EngineError> getErrors();
    
    public void start();
    public void stop();
}
