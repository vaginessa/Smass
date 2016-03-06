package id.co.technomotion.smass.controller.action;

public interface ParsingResponse<T> {
    void onSuccess(T result);

	void onFailure(String failedResult);
}