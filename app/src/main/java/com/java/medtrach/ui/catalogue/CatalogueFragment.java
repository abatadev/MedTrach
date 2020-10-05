package com.java.medtrach.ui.catalogue;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.java.medtrach.MapsActivity;
import com.java.medtrach.R;
import com.java.medtrach.common.Common;
import com.java.medtrach.model.DrugModel;

public class CatalogueFragment extends Fragment {

    private Button addPharmacyButton, addDrugButton;
    private EditText searchBarEditText;
    private ImageView microphoneButton;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private RecyclerView recyclerView;

    FirebaseRecyclerAdapter<DrugModel, CatalogueViewHolder> adapter;
    FirebaseRecyclerOptions<DrugModel> options;
    private DatabaseReference drugReference;

    private DrugModel drugModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_catalogue, container, false);

        addPharmacyButton = root.findViewById(R.id.add_pharmacy_button);
        addDrugButton = root.findViewById(R.id.add_drugs_button);
        searchBarEditText = root.findViewById(R.id.catalogue_search_bar);
        microphoneButton = root.findViewById(R.id.catalogue_microphone_image);

        drugModel = new DrugModel();

        recyclerView = (RecyclerView) root.findViewById(R.id.catalogue_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        drugReference = FirebaseDatabase.getInstance().getReference().child(Common.DRUG_REF);

        addPharmacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddPharmacyActivity.class);
                startActivity(intent);
            }
        });

        addDrugButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddDrugActivity.class);
                startActivity(intent);
            }
        });

        loadData("");

        searchBarEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString() != null) {
                    loadData(editable.toString());
                } else {
                    loadData("");
                }
            }
        });

        return root;
    }

    private void loadData(String data) {
        Query query = drugReference.orderByChild("drugName").startAt(data).endAt(data + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<DrugModel>()
                .setQuery(query, DrugModel.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<DrugModel, CatalogueViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CatalogueViewHolder holder, int position, @NonNull DrugModel model) {
                holder.drugName.setText(drugModel.getDrugName());

                // Open MapsActivity.class
                getContext().startActivity(new Intent(getContext(), MapsActivity.class));
            }

            @NonNull
            @Override
            public CatalogueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_catalogue, parent, false);
                return new CatalogueViewHolder(view);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

}