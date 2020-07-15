package com.example.finalrideapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalrideapp.model.db.entities.GetTrips
import com.example.finalrideapp.model.network.responses.GetTripsResponse
import com.example.finalrideapp.model.repositories.NewUserRepository
import com.example.finalrideapp.util.ApiException
import com.example.finalrideapp.util.Coroutines
import com.example.finalrideapp.util.NoInternetException
import com.google.gson.JsonObject
import kotlinx.coroutines.Job

class MyprofileViewModel(private val repository: NewUserRepository) : ViewModel() {

    private lateinit var job: Job

    private val _activities = MutableLiveData<GetTripsResponse>()
    val activities: LiveData<GetTripsResponse>
        get() = _activities

    fun getTrips() {
        job = Coroutines.ioTheMain(
            {repository.getTrips()},
            {
                _activities.value = it
            }
        )


        /*
        {
            try {
                val tripResponse = repository.getTrips()
                Log.d("hiiiiiiiiiiii", activities.toString())
                _activities.value = tripResponse
                Log.d("activity", activities.toString())

            } catch(e: ApiException){
                //tripResponse?.onFailure(e.message!!)
                Log.d("ApiException", e.message!!)
            }catch (e: NoInternetException){
                //tripResponse?.onFailure(e.message!!)
                Log.d("ApiException", e.message!!)
            }

        }

         */

    }
    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}
