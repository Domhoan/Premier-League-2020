package com.example.premier_league.data.source.local

import android.os.AsyncTask
import com.example.premier_league.data.source.remote.OnDataLoadedCallback

class LocalAsyncTask<P, R>(
    private val callback: OnDataLoadedCallback<R>,
    private val handler: (P) -> R
) : AsyncTask<P, Void, R?>() {

    override fun doInBackground(vararg params: P): R? =
        try {
            handler(params[0])
        } catch (e: Exception) {
            null
        }

    override fun onPostExecute(result: R?) {
        super.onPostExecute(result)

        result?.let {
            callback.onSuccess(it)
        } ?: callback.onFailure(Exception(LOADING_ASYNC_TASK_EXCEPTION))
    }

    companion object {
        private const val LOADING_ASYNC_TASK_EXCEPTION = "LoadingAsyncTask Exception"
    }
}
