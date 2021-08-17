package com.example.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;
import java.util.Objects;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        final TextInputEditText city = fragmentView.findViewById(R.id.editCity);

        Button button = fragmentView.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            MainNavigator mainNavigator = (MainNavigator) getActivity();
            assert mainNavigator != null;
            mainNavigator.startResultFragment(Objects.requireNonNull(city.getText()).toString());
        });

        initRecyclerView(fragmentView);

        return fragmentView;
    }

    private void initRecyclerView(View fragmentView) {
        RecyclerView recyclerView = fragmentView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DataSourceBuilder builder = new DataSourceBuilder(getResources());
        final List<City> dataSource = builder.build();
        final CityAdapter adapter = new CityAdapter(dataSource);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener((view, position) -> {
            City item = dataSource.get(position);
            MainNavigator mainNavigator = (MainNavigator) getActivity();
            assert mainNavigator != null;
            mainNavigator.startResultFragmentFromList(item.getName(), item.getLat(), item.getLon());
        });
    }
}


