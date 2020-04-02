package com.builder.assignment.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.builder.assignment.R;
import com.builder.assignment.base.BaseActivity;
import com.builder.assignment.data.model.Hit;
import com.builder.assignment.util.NetworkUtils;
import com.builder.assignment.util.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Inject
    ViewModelFactory viewModelFactory;
    private ListViewModel viewModel;

    private Context mContext;
    private RecyclerView rvMain;
    private TextView tvToolbarTitle;

    private MainRecyclerAdapter adapter;
    private int page = 1;
    private List<Hit> hits = new ArrayList<>();

    private ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
        mContext = this;
        rvMain = findViewById(R.id.rvMain);
        tvToolbarTitle = findViewById(R.id.tvToolbarTitle);
        pbProgress = findViewById(R.id.pbProgress);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new MainRecyclerAdapter(mContext);
        rvMain.setAdapter(adapter);

        rvMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == hits.size() - 1) {
                    //bottom of list!
                    loadMore();
                }

            }
        });


        if (NetworkUtils.isNetworkConnected(mContext)) {
            viewModel.getList(page);
        } else {
            NetworkUtils.showNoInternetToast(mContext);
        }

        viewModel.getListResponse().observe(this, result -> {
            hits.addAll(result);
            if (adapter != null) {
                adapter.updateData(hits);
            } else {
                adapter = new MainRecyclerAdapter(mContext);
                adapter.updateData(hits);

            }

            tvToolbarTitle.setText("Posts: " + hits.size());

        });
        viewModel.getError().observe(this, error -> {

        });
        viewModel.getLoading().observe(this, loading -> {
            if (loading){
                pbProgress.setVisibility(View.VISIBLE);
            }else {
                pbProgress.setVisibility(View.GONE);

            }
        });


    }

    private void loadMore() {
        if (NetworkUtils.isNetworkConnected(mContext)) {
            page++;
            viewModel.getList(page);
        } else {
            NetworkUtils.showNoInternetToast(mContext);

        }

    }
}
