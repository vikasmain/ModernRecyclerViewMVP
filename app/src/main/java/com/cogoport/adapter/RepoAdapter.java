package com.cogoport.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cogoport.R;
import com.cogoport.model.MainCategoryData;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.GithubRepoViewHolder> {
    private Context context;
    String name,id;
    private List<MainCategoryData> gitHubRepos = new ArrayList<>();
    private List<MainCategoryData> categoryData = new ArrayList<>();

    public RepoAdapter(List<MainCategoryData> gitHubRepos) {
        this.gitHubRepos = gitHubRepos;
    }


    @Override
    public GithubRepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, null);
        GithubRepoViewHolder rcv = new GithubRepoViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(GithubRepoViewHolder holder, int position) {

        holder.textRepoName.setText(gitHubRepos.get(position).getName());
        holder.textRepoDescription.setText(gitHubRepos.get(position).getDescription());
        holder.textLanguage.setText("Languages: "+gitHubRepos.get(position).getLanguage());
        holder.texturl.setText(Html.fromHtml(gitHubRepos.get(position).getHtmlUrl()));
        holder.stars.setText(""+gitHubRepos.get(position).getStargazersCount());

    }

    @Override
    public int getItemCount() {
        return gitHubRepos.size();
    }
    public static class GithubRepoViewHolder  extends RecyclerView.ViewHolder{
         TextView textRepoName;
         TextView textRepoDescription;
         TextView textLanguage;
         TextView texturl;
         TextView stars;

        public GithubRepoViewHolder(View view) {
            super(view);
             List<MainCategoryData> gitHubRepos = new ArrayList<>();
            textRepoName = (TextView) view.findViewById(R.id.item_name);
            textRepoDescription = (TextView) view.findViewById(R.id.textVie3);
            textLanguage = (TextView) view.findViewById(R.id.textView4);
            texturl =(TextView)view.findViewById(R.id.textView5);
            texturl.setClickable(true);
            texturl.setMovementMethod(LinkMovementMethod.getInstance());

            stars = (TextView) view.findViewById(R.id.stars);

        }

//        public  void setGitHubRepo(MainCategoryData gitHubRepo) {
//            textRepoName.setText(gitHubRepo.name);
//            textRepoDescription.setText(gitHubRepo.description);
//            textLanguage.setText("Language: " + gitHubRepo.language);
//            textStars.setText("Stars: " + gitHubRepo.stargazersCount);
//        }
    }

}
