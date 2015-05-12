package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ozu.ozmo.ozmopol.Models.Result;
import com.ozu.ozmo.ozmopol.Models.User;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DialogLogin.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DialogLogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogLogin extends DialogFragment {
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
     * @return A new instance of fragment DialogLogin.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogLogin newInstance(String param1, String param2) {
        DialogLogin fragment = new DialogLogin();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DialogLogin() {
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
        return inflater.inflate(R.layout.fragment_dialog_login, container, false);
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


        TextView tv_confirmation=(TextView)getView().findViewById(R.id.tv_confirmation);
        tv_confirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=((EditText)getView().findViewById(R.id.tv_username)).getText().toString();
                String password=((EditText)getView().findViewById(R.id.tv_password)).getText().toString();
                if (userName.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
                    Toast.makeText(getActivity().getApplicationContext(),"Please enter user name and password to get your confirmation code.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }



                final User user=new User();

                user.userName=userName;
                user.userPass=password;


                ((MyApplication) getActivity().getApplication()).getOzmoService().sendActCode(user,new Callback<Result>() {
                    @Override
                    public void success(Result result, Response response) {

                        if (result.title.equalsIgnoreCase("OK")) {

                            ((MyApplication)getActivity().getApplication()).user = user;

                            Fragment newFragment = new FragmentConfirmation();
                            FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.addToBackStack("DialogLogin");
                            // Commit the transaction
                            transaction.commit();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), result.message, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });

        Button btnLogin=(Button)getView().findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName=((EditText)getView().findViewById(R.id.tv_username)).getText().toString();
                String password=((EditText)getView().findViewById(R.id.tv_password)).getText().toString();

                final User user=new User();
                user.userName=userName;
                user.userPass=password;

                ((MyApplication) getActivity().getApplication()).getOzmoService().checkLogin(user,new Callback<Result>() {
                    @Override
                    public void success(Result result, Response response) {
                        if (result.title.equalsIgnoreCase("OK")) {

                            User newUser= result.user;
                            ((MyApplication) getActivity().getApplication()).user = newUser;
                            SharedPreferences prefs = getActivity().getSharedPreferences(
                                    "com.ozu.ozmo.ozmopol", Context.MODE_PRIVATE);


                            prefs.edit().putBoolean("loggedIn", true).apply();
                            prefs.edit().putString("userName", newUser.userName).apply();
                            Toast.makeText(getActivity().getApplicationContext(), "You are logged in successfully !",
                                    Toast.LENGTH_SHORT).show();

                            ((ActionBarActivity) getActivity()).getSupportActionBar().show();

                            Fragment newFragment = new FragmentFrontPage();
                            FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container, newFragment);
                            transaction.addToBackStack("DialogLogin");
                            // Commit the transaction
                            transaction.commit();





//
//                            ((MyApplication) getActivity().getApplication()).getOzmoService().uProfile(user,new Callback<Result>() {
//                                @Override
//                                public void success(Result result, Response response) {
//                                    if (result.title.equalsIgnoreCase("OK")) {
//                                        User user=(User)result.body;
//                                        ((MyApplication) getActivity().getApplication()).user = user;
//                                        SharedPreferences prefs = getActivity().getSharedPreferences(
//                                                "com.ozu.ozmo.ozmopol", Context.MODE_PRIVATE);
//
//                                        prefs.edit().putBoolean("loggedIn", true).apply();
//                                        prefs.edit().putString("userName", user.userName).apply();
//                                        Toast.makeText(getActivity().getApplicationContext(), "You are logged in successfully !", Toast.LENGTH_SHORT).show();
//
//                                        ((ActionBarActivity) getActivity()).getSupportActionBar().show();
//
//                                        Fragment newFragment = new FragmentFrontPage();
//                                        FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
//                                        transaction.replace(R.id.fragment_container, newFragment);
//                                        transaction.addToBackStack("DialogLogin");
//                                        // Commit the transaction
//                                        transaction.commit();
//                                    }else{
//                                        Toast.makeText(getActivity().getApplicationContext(), result.message, Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void failure(RetrofitError error) {
//                                    Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });


                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), result.message, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        TextView tvSignUp=(TextView)getView().findViewById(R.id.tv_not_user);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new DialogSignup();
                FragmentTransaction transaction =getActivity().getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack("DialogLogin");
                // Commit the transaction
                transaction.commit();
            }
        });



    }
}
