package com.ozu.ozmo.ozmopol;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.ozu.ozmo.ozmopol.Models.Post;
import com.ozu.ozmo.ozmopol.Models.Result;
import com.ozu.ozmo.ozmopol.Models.Room;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPostContent.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPostContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPostContent extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    StaggeredGridView gridView;
    SwipeRefreshLayout swipeLayout;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPostContent.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPostContent newInstance(String param1, String param2) {
        FragmentPostContent fragment = new FragmentPostContent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentPostContent() {
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final String postId=((MyApplication) getActivity().getApplication()).selectedPostId;

        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.fragment_post_content_swipe_container);
        addSwipeRefreshFunction();



        Button btn_create_comment=(Button)getView().findViewById(R.id.btn_create_comment);
        btn_create_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment_str=((EditText)getView().findViewById(R.id.comment_title)).getText().toString();
                Post p=new Post();
                Post parent=new Post();
                parent.pkPostId=postId;
                p.fkPostPrntId=parent;
                RandomString rnd=new RandomString(30);

                p.fkPostUserId= ((MyApplication) getActivity().getApplication()).user;

                p.fkPostRoomId= new Room();
                p.fkPostRoomId.pkRoomId=((MyApplication) getActivity().getApplication()).selectedPost.fkPostRoomId.pkRoomId;
                p.pkPostId=rnd.nextString();
                p.postContent=comment_str;
                p.postEDate="2015-03-28T20:04:05+02:00";
                p.postCDate="2015-03-28T20:04:05+02:00";

                ((MyApplication) getActivity().getApplication()).getOzmoService().createPost(p,new Callback<Result>() {
                    @Override
                    public void success(Result result, Response response) {
                        if (result.title.equalsIgnoreCase("OK")){

                            Fragment newFragment = new FragmentPostContent();
                            FragmentTransaction transaction =getActivity().getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragment_container,newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        Toast.makeText(getActivity().getApplicationContext(), result.message, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });


        final List<Post> myPostCards=new ArrayList<Post>();


        Result req=new Result();
        req.users=new ArrayList<>();
        req.posts= new ArrayList<>();

        req.users.add(((MyApplication) getActivity().getApplication()).user);
        Post p=new Post();
        p.pkPostId=postId;
        req.posts.add(p);


        ((MyApplication) getActivity().getApplication()).getOzmoService().getPostDetails(req,new Callback<Result>() {
            @Override
            public void success(Result result, Response response) {
                if (result.title.equalsIgnoreCase("OK")){

                    for (Post pp : result.posts){
                        myPostCards.add(pp);
                    }

                    PostsAdapter pAdapter=new PostsAdapter(getActivity(),myPostCards, FragmentPostContent.this.getFragmentManager());
                    gridView = (StaggeredGridView)getView().findViewById(R.id.fragment_post_content_grid_view);
                    gridView.setAdapter(pAdapter);

                    ProgressWheel progressWheel=(ProgressWheel)getActivity().findViewById(R.id.progress_wheel);
                    progressWheel.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), result.message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_content, container, false);




    }

    public void addSwipeRefreshFunction(){
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        swipeLayout.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
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
    public boolean isTablet(){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        if (width > 720 && height > 1184){
            return true;
        } else {
            return false;
        }
    }

    public void updateColumnCountForFrontPage(){
        if(isTablet()){
            Log.d("Tablet spotted", "sizes won't change");
        } else {
            gridView.setColumnCountLandscape(1);
            gridView.setColumnCountPortrait(1);
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

}
