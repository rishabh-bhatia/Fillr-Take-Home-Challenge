package com.rishabh.fillrtask.presentation.ui;

import android.os.Handler;
import android.os.Looper;

/*
* Implement a more primitive version of AsyncTask for Android
environment. It should call 'doInBackground' on some
* background thread and the return value of that call should be passed
to 'onPostExecute'
* on the main thread.
*
* PLEASE FEEL FREE TO GOOGLE ANYTHING WITHIN REASON THAT YOU NEED.
*/
// Java
abstract class AsyncTFillrTaskAbstract<T>{
    public final void execute() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                T doInBackground = doInBackground();
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onPostExecute(doInBackground);
                    }
                });
            }
        });
        thread.start();
    }
    abstract T doInBackground();
    public void onPostExecute(T result) {
        //Implement a function
    }
}

class AsyncFillrTask extends AsyncTFillrTaskAbstract {

    @Override
    Object doInBackground() {
        return null;
    }

    @Override
    public void onPostExecute(Object result) {
        super.onPostExecute(result);
    }
}



