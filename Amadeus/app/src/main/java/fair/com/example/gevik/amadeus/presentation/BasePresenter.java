package fair.com.example.gevik.amadeus.presentation;

/**
 * Created by gvalijani on 11/3/17.
 */

public interface BasePresenter<T> {

    void setView(T view);

    void dropView();
}
