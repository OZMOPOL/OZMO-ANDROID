package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ozu.ozmo.ozmopol.Models.OzmoService;
import com.ozu.ozmo.ozmopol.Models.Post;
import com.ozu.ozmo.ozmopol.Models.Result;
import com.ozu.ozmo.ozmopol.Models.Room;

import java.util.UUID;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreatePostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreatePostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatePostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePostFragment newInstance(String param1, String param2) {
        CreatePostFragment fragment = new CreatePostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CreatePostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Button btn=(Button)view.findViewById(R.id.fragment_create_post_submit_post);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
                OzmoService service = restAdapter.create(OzmoService.class);


                Post toPost=new Post();
                String postTitle=((EditText)getView().findViewById(R.id.fragment_create_post_title)).getText().toString();
                String postContent=((EditText)getView().findViewById(R.id.fragment_create_post_content)).getText().toString();
                toPost.postTitle=postTitle;
                toPost.postContent=postContent;
                toPost.fkPostUserId=((MyApplication) getActivity().getApplication()).user;
                RandomString randomString=new RandomString(30);
                toPost.pkPostId = randomString.nextString();
                toPost.fkPostRoomId=((MyApplication) getActivity().getApplication()).selectedRoom;
                toPost.postEDate="2015-03-28T20:04:05+02:00";
                toPost.postCDate="2015-03-28T20:04:05+02:00";

                service.createPost(toPost,new Callback<Result>() {
                    @Override
                    public void success(Result result, Response response) {
                        if (result.title.equalsIgnoreCase("OK")){
                            Toast.makeText(getActivity().getApplicationContext(), "Successfully Posted !", Toast.LENGTH_SHORT).show();
                            Fragment newFragment = new FragmentRoomContent();
                            FragmentTransaction transaction =getActivity().getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container,newFragment);
                            transaction.addToBackStack("FragmentRooms");
                            // Commit the transaction
                            transaction.commit();

                        }else {
                            Toast.makeText(getActivity().getApplicationContext(),result.details, Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getApplicationContext(), "An error occured during process !", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


    }
}
