package mvpstate.krpiotrek.mvpstate.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class TextDialog {

    @NonNull
    public static Func1<Object, Observable<String>> getDialogDeleteFunction(@NonNull final Context context) {
        return new Func1<Object, Observable<String>>() {
            @Override
            public Observable<String> call(Object onClickEvent) {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> subscriber) {
                        new AlertDialog.Builder(
                                context)
                                .setMessage("Select option to change text")
                                .setNegativeButton("negative", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        subscriber.onNext("negative");
                                        subscriber.onCompleted();
                                    }
                                })
                                .setPositiveButton("positive", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        subscriber.onNext("positive");
                                        subscriber.onCompleted();
                                    }
                                })
                                .create()
                                .show();
                    }
                });
            }
        };
    }
}
