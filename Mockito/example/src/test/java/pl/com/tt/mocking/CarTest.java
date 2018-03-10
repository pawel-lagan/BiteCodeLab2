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

public class CarTest implements WithAssertions, WithBDDMockito {

    @Test
    public void shouldBeReadyToStart() {
    }   

    @Test
    public void shouldNotBeReadyToStartWhenEngineErrors() {
    }

    @Test
    public void shouldNotBeReadyToNoFuelDetected() {
    }

    @Test
    public void shouldStartWhenAllComponentsOk() {
    }

    @Test
    public void shouldNotStartWhenSomethingIsWrong() {
    }

    @Test
    public void shouldPassParamToCheckBeforeStart() {
    }

    @Test
    public void checkShouldPassWhenOilTemperatureIsBelow100() {
    }

    @Test
    public void checkShouldFailWhenOilTemperatureIsAbove100() {
    }

    @Test
    public void checkShouldFailWhenOilPressureIsAbove1000() {
    }
}
