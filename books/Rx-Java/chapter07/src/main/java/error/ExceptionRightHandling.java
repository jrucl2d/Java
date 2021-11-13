package error;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import utils.Log;

public class ExceptionRightHandling
{
    public static void main(String[] args) {
        String[] grades = {"70", "88", "$100", "93", "83"};

//        onErrorReturn(grades);
        onErrorResumeNext();
    }

    private static void onErrorReturn(String[] grades) {
        Observable<Integer> source = Observable.fromArray(grades)
            .map(data -> Integer.parseInt(data))
            .onErrorReturn(e -> {
                if (e instanceof NumberFormatException) {
                    e.printStackTrace();
                }
                return -1;
            });

        source.subscribe(data -> {
            if (data < 0) {
                Log.e("Wrong Data found!", "NumberFormatException");
                return;
            }
            Log.i("Grade is " + data);
        });
    }

    private static void onError(String[] grades) {
        Observable<Integer> source = Observable.fromArray(grades)
            .map(data -> Integer.parseInt(data));

        source.subscribe(data -> Log.i("Grade is " + data),
            e -> {
            if (e instanceof NumberFormatException) {
                e.printStackTrace();
            }
                Log.e("Wrong Data found!", "NumberFormatException");
            });
    }

    private static void onErrorReturnItem(String[] grades) {
        // Throwable 을 사용하지 않아 더 코드의 가독성이 좋음.
        Observable<Integer> source = Observable.fromArray(grades)
            .map(data -> Integer.parseInt(data))
            .onErrorReturnItem(-1);

        source.subscribe(data -> {
            if (data < 0) {
                Log.e("Wrong Data found!", "NumberFormatException");
                return;
            }

            Log.i("Grade is " + data);
        });
    }

    private static void onErrorResumeNext() {
        String[] salesData = {"100", "200", "A300"};
        Observable<Integer> onParseError = Observable.defer(() -> {
            Log.i("send mail to administrator");
            return Observable.just(-1);
        }).subscribeOn(Schedulers.io()); // IO 스케줄러에서 실행됨

        Observable<Integer> source = Observable.fromArray(salesData)
            .map(Integer::parseInt)
            .onErrorResumeNext(onParseError);

        source.subscribe(data -> {
                if (data < 0) {
                    Log.e("Wrong Data found!", "NumberFormatException");
                    return;
                }

                Log.i("Grade is " + data);
            });
    }
}
