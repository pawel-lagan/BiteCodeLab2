package pl.com.tt.mock;

public class Stubbing<T> {

	private MockInvocationHandler<?> handler;
	private T value;

	public Stubbing(MockInvocationHandler<?> handler) {
	    this.handler = handler;
	}

	public void thenReturn(T thenVal) {
	    value = thenVal;
	    this.handler.put(this);
	}
	
	T getValue() {
	    return value;
	}

}
