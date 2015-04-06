package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ozu.ozmo.ozmopol.Models.OzmoService;
import com.ozu.ozmo.ozmopol.Models.Result;
import com.ozu.ozmo.ozmopol.Models.User;

import java.util.UUID;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DialogSignup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DialogSignup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogSignup extends Fragment {
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
     * @return A new instance of fragment DialogSignup.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogSignup newInstance(String param1, String param2) {
        DialogSignup fragment = new DialogSignup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DialogSignup() {
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
        return inflater.inflate(R.layout.fragment_dialog_signup, container, false);
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

        final Button btnSignup=(Button)getView().findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignup.setEnabled(false);
                String userName=((EditText)getView().findViewById(R.id.username_signup)).getText().toString();
                String userEmail=((EditText)getView().findViewById(R.id.email_signup)).getText().toString();
                String userPassword=((EditText)getView().findViewById(R.id.password_signup)).getText().toString();
                String userPasswordConfirm=((EditText)getView().findViewById(R.id.password_signup_confirm)).getText().toString();

                if (!userPassword.equalsIgnoreCase(userPasswordConfirm)){
                    Toast.makeText(getActivity().getApplicationContext(), "Password does not match the confirm password !", Toast.LENGTH_SHORT).show();
                    btnSignup.setEnabled(true);
                    return;
                }

                User user=new User();

                RandomString randomString=new RandomString(30);
                user.pkUserId= randomString.nextString();
                user.userName=userName;
                user.userPass=userPassword;
                user.userEmail=userEmail;
                user.userStatus="1";

                RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://10.100.92.22:8080").build();
                OzmoService service = restAdapter.create(OzmoService.class);
                service.signUp(user,new Callback<Result>() {
                    @Override
                    public void success(Result result, Response response) {
                        if (result.title.equalsIgnoreCase("OK")){
                            Toast.makeText(getActivity().getApplicationContext(), "You have successgully signed up !", Toast.LENGTH_SHORT).show();

                            Fragment newFragment = new DialogLogin();
                            FragmentTransaction transaction =getActivity().getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container,newFragment);
                            transaction.addToBackStack("DialogSignup");
                            // Commit the transaction
                            transaction.commit();


                        }else{
                            Toast.makeText(getActivity().getApplicationContext(),result.details, Toast.LENGTH_LONG).show();
                            btnSignup.setEnabled(true);
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });




            }
        });
    }
}