package pl.com.tt.mocking;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.WithAssertions;
import org.assertj.core.util.Sets;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import pl.com.tt.mocking.Engine.EngineError;

public class CarTest2 implements WithAssertions, WithBDDMockito {
    private static final Long TEST_PARAM_VALUE = 5L;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private FuelPump fuelPump;
    
    @Mock
    private Engine engine;
    
    @InjectMocks
    private Car car;
    
    @Test
    public void shouldBeReadyToStart() {
        when(fuelPump.isFuelDetected()).thenReturn(true);
        when(fuelPump.isOn()).thenReturn(true);
        when(fuelPump.isRunning()).thenReturn(false);
        when(engine.isPowerOn()).thenReturn(true);
        when(engine.isRunning()).thenReturn(false);
        
        boolean result = car.checkBeforeStart(TEST_PARAM_VALUE);
        assertThat(result).isTrue();
    }
    
    @Test
    public void shouldNotBeReadyToStartWhenEngineErrors() {
        when(fuelPump.isFuelDetected()).thenReturn(true);
        when(fuelPump.isOn()).thenReturn(true);
        when(fuelPump.isRunning()).thenReturn(false);
        when(engine.isPowerOn()).thenReturn(true);
        when(engine.isRunning()).thenReturn(false);
        when(engine.getErrors()).thenReturn(Sets.newLinkedHashSet(EngineError.INJECTOR_ERROR));
        
        boolean result = car.checkBeforeStart(TEST_PARAM_VALUE);
        assertThat(result).isFalse();
    }
    
    @Test
    public void shouldNotBeReadyToNoFuelDetected() {
        when(fuelPump.isFuelDetected()).thenReturn(false);
        when(fuelPump.isOn()).thenReturn(true);
        when(fuelPump.isRunning()).thenReturn(false);
        when(engine.isPowerOn()).thenReturn(true);
        when(engine.isRunning()).thenReturn(false);
        
        boolean result = car.checkBeforeStart(TEST_PARAM_VALUE);
        assertThat(result).isFalse();
    }
    

    @Test
    public void shouldStartWhenAllComponentsOk() {
        car = spy(car);
        doReturn(true).when(car).checkBeforeStart(TEST_PARAM_VALUE);
        
        car.start(TEST_PARAM_VALUE);
        
        verify(fuelPump, times(1)).start();
        verify(engine, times(1)).start();
    }
    
    @Test
    public void shouldNotStartWhenSomethingIsWrong() {
        car = spy(car);
        doReturn(false).when(car).checkBeforeStart(TEST_PARAM_VALUE);
        
        car.start(TEST_PARAM_VALUE);
        
        verify(fuelPump, times(0)).start();
        verify(engine, times(0)).start();
    }
    
    @Test
    public void shouldPassParamToCheckBeforeStart() {
        car = spy(car);
        
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        doReturn(true).when(car).checkBeforeStart(captor.capture());
        
        car.start(TEST_PARAM_VALUE);
        
        assertThat(captor.getValue()).isEqualTo(TEST_PARAM_VALUE);
    }
    
    @Test
    public void checkShouldPassWhenOilTemperatureIsBelow100() {
        prepareOkComponentsParams();
        boolean check = car.check();
        
        assertThat(check).isTrue();
    }

    @Test
    public void checkShouldFailWhenOilTemperatureIsAbove100() {
        prepareOkComponentsParams();
        when(engine.getOilTemperature()).thenReturn(200L);
        boolean check = car.check();
        
        assertThat(check).isFalse();
    }

    @Test
    public void checkShouldFailWhenOilPressureIsAbove1000() {
        prepareOkComponentsParams();
        when(engine.getOilPressure()).thenReturn(1200L);
        boolean check = car.check();
        
        assertThat(check).isFalse();
    }
    private void prepareOkComponentsParams() {
        when(fuelPump.isSystemError()).thenReturn(false);
        when(fuelPump.isRunning()).thenReturn(true);
        when(engine.isRunning()).thenReturn(true);
        when(engine.getErrors()).thenReturn(Sets.newHashSet());
        when(engine.getOilPressure()).thenReturn(800L);
        when(engine.getOilTemperature()).thenReturn(100L);
    }
    
}
