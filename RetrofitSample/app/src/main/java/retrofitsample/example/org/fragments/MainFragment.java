package retrofitsample.example.org.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rafaels.universapptest.model.Model;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitsample.example.org.R;
import retrofitsample.example.org.activities.MainActivity;
import retrofitsample.example.org.adapter.MiAdaptador;
import retrofitsample.example.org.model.remote.APIService;
import retrofitsample.example.org.model.remote.ApiUtils;

public class MainFragment extends Fragment {

    public static final String TAG = "MAIN_FRAGMENT";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private MiAdaptador adaptador;

    private APIService mAPIService;
    private ProgressDialog progressDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        mAPIService = ApiUtils.getApiService();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        sendPost();

        return view;
    }

    private void sendPost() {
        mAPIService.callModel().enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Log.d("RetroFit1",  call.toString()+"");
                Log.d("RetroFit2",  response.toString()+"");
                Log.d("RetroFit3",  response.message()+"");
                Log.d("RetroFit4",  response.isSuccessful()+"");
                Log.d("RetroFit5",  response.code()+"");
                switch (response.code()) {
                    case 200:
                        Model data = response.body();
                        loadAdapter(data);
                        Log.d("RetroFit6",  data.getResults().get(0).getName().getFirst());
                        progressDialog.dismiss();
                        break;

                    default:
                        break;
                }
            }
            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("RetroFitError", "Unable to submit post to API.");
            }
        });
    } // Fin sendPost

    private void loadAdapter(Model data){
        adaptador = new MiAdaptador(getActivity(), data);
        recyclerView.setAdapter(adaptador);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

}
