package com.inflearn.lightinstagram.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inflearn.lightinstagram.R;
import com.inflearn.lightinstagram.data.source.FeedLocalSource;
import com.inflearn.lightinstagram.ui.base.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FeedFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private FeedAdapter adapter;
    private FeedLocalSource feedLocalSource = new FeedLocalSource();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        findView(view);
        return view;
    }

    @Override
    protected void initializeToolbar(Toolbar toolbar) {
        super.initializeToolbar(toolbar);
        toolbar.setTitle(R.string.feed_title);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRecyclerView();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadItems();
    }

    private void findView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
    }

    private void setRecyclerView() {
        adapter = new FeedAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void loadItems() {
        adapter.refresh(feedLocalSource.getAll());
    }
}