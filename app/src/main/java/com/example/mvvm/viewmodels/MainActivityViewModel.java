package com.example.mvvm.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.mvvm.adapters.RecyclerAdapter;
import com.example.mvvm.models.NicePlace;
import com.example.mvvm.repositories.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private NicePlaceRepository mRepo;

    //    mutableLive data its changeable unlike LiveData which is observable only.mutableLiveData is a subclass of LiveData.
    private MutableLiveData<List<NicePlace>> mNicePlaces;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if (mNicePlaces != null){
            return;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public void addNewValue(){
        mIsUpdating.setValue(true);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                List<NicePlace> currentPlace = mNicePlaces.getValue();
//                currentPlace.add(nicePlace);
//                mNicePlaces.postValue(currentPlace);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }
    /*    The reason for using this method with LiveData,its because Live data is Immutable and in case of a rotation of the screen , data that was observed won't change*/
    public LiveData<List<NicePlace>> getNicePlaces() {
        return mNicePlaces; }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}