package com.innnovacer.summergeek2020;
import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class blogpostid {
    @Exclude
    public String blogpostid;
    public<T extends blogpostid> T withId(@NonNull final String id)
    {
        this.blogpostid = id;
        return(T)this;
    }
}