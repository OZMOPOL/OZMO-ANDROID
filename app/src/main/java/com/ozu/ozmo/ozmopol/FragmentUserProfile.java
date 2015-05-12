package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ozu.ozmo.ozmopol.Models.Post;
import com.ozu.ozmo.ozmopol.Models.Result;
import com.ozu.ozmo.ozmopol.Models.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentUserProfile.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentUserProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUserProfile extends Fragment {
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
     * @return A new instance of fragment FragmentUserProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUserProfile newInstance(String param1, String param2) {
        FragmentUserProfile fragment = new FragmentUserProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentUserProfile() {
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
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
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
        final User user= ((MyApplication) getActivity().getApplication()).userProfile;


        final Button btnFollow=(Button)getView().findViewById(R.id.btn_follow_user);
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnFollow.getText().toString().equalsIgnoreCase("Follow")){
                    btnFollow.setText("Unfollow");
                    btnFollow.setBackgroundColor(Color.parseColor("#4dffaa"));
                }else{
                    btnFollow.setText("Follow");
                    btnFollow.setBackgroundColor(Color.parseColor("#e95564"));
                }

            }
        });




        TextView tvName=(TextView)getView().findViewById(R.id.txName);
        TextView tvEmail=(TextView)getView().findViewById(R.id.txEmail);
        final TextView tvPosts=(TextView)getView().findViewById(R.id.txPosts);
        final TextView tvComments=(TextView)getView().findViewById(R.id.txComments);
        tvName.setText(user.userName);
        tvEmail.setText(user.userEmail);

        ((MyApplication) getActivity().getApplication()).getOzmoService().getUserProfile(user,new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result.title.equalsIgnoreCase("OK")){
                    User newuser = result.users.get(0);
                    for (Post p  : newuser.posts){
                        tvPosts.setText(tvPosts.getText() + "\n" + p.postTitle);
                    }
                    for (Post p : newuser.comments){
                        if (p.postTitle==null) {
                            tvComments.setText(tvPosts.getText() + "\n" + p.postContent);
                        }
                    }

                }
                Toast.makeText(getActivity().getApplicationContext(), result.message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
