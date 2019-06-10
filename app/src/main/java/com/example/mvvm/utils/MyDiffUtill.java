package com.example.mvvm.utils;

import android.support.v7.util.DiffUtil;

import com.example.mvvm.models.NicePlace;

import java.util.List;

public class MyDiffUtill extends DiffUtil.Callback {

    private List<NicePlace> oldList;
    private List<NicePlace> newList;

    public MyDiffUtill(List<NicePlace> oldList, List<NicePlace> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldPosition, int newPosition) {
        return oldList.get(oldPosition) == newList.get(newPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldPosition, int newPosition) {
        NicePlace oldItems =  oldList.get(oldPosition);
        NicePlace newItems =  newList.get(newPosition);
        return oldItems.getImageUrl().equals(newItems.getImageUrl()) &&
                oldItems.getTitle().equals(newItems.getTitle());
    }
}
