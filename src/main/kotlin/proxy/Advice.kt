package proxy

import java.lang.reflect.Method

public interface Advice {
    fun beforeMethod( method: Method)
    fun afterMethod( method: Method)
}