package com.example.sample.sampleappwithrxandfastnetwork;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import model.APIUser;
import model.User;

import static io.reactivex.Observable.fromIterable;

public class MainActivity extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // initialize Fast networking library
        AndroidNetworking.initialize(getApplicationContext());
        disposable.add(getSampleObservable().flatMap(new Function<List<APIUser>, ObservableSource<APIUser>> () {
            @Override
            public ObservableSource<APIUser> apply(List<APIUser> apiUsers) throws Exception {
                return Observable.fromIterable(apiUsers);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<APIUser>() {

            @Override
            public void onNext(APIUser user) {
                System.out.println("user is: "+ user.toString());

            }

            @Override
            public void onComplete() {

                System.out.println("Complete called");

            }

            @Override
            public void onError(Throwable e) {

            }
        }));

    }
public Observable<List<APIUser>> getSampleObservable(){
     return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllFootballFans").build().getObjectListObservable(APIUser.class);
       //return Observable.just(1,2,3,4);
}

    @Override
    protected void onStop() {
        super.onStop();
        disposable.clear();
    }
}
