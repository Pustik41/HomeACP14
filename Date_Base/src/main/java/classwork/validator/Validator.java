package classwork.validator;

/**
 * Created by Котято on 02.11.2016.
 */
public interface Validator<T> {

    boolean isValid(T in);
}
