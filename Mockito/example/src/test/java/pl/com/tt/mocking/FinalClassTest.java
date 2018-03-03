package pl.com.tt.mocking;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

public class FinalClassTest implements WithAssertions {

    public static final class FinalClass {
        final String evilMethod() {
            return "I am evil!";
        }
    }

    @Test
    public void shouldOvercomeFinalMethod() {
        // given
        FinalClass original = new FinalClass();
        FinalClass finalClassMock = mock(FinalClass.class);
        given(finalClassMock.evilMethod()).willReturn("God blessing!");
        // when
        String result = finalClassMock.evilMethod();
        // then
        assertThat(result).isNotEqualTo(original.evilMethod());
    }

}