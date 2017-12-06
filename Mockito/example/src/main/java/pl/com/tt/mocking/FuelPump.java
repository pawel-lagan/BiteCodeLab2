package pl.com.tt.mocking;


public interface FuelPump {
    public boolean isOn();
    public boolean isFuelDetected();
    public boolean isSystemError();
    public boolean isRunning();
    
    public void start();
    public void stop();
}
