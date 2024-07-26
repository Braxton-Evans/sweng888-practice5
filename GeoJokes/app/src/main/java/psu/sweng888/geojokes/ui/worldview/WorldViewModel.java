package psu.sweng888.geojokes.ui.worldview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorldViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public WorldViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the world view fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}