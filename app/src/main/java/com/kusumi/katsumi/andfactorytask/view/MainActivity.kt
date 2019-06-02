package com.kusumi.katsumi.andfactorytask.view


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.webkit.WebViewClient
import com.kusumi.katsumi.andfactorytask.R
import com.kusumi.katsumi.andfactorytask.contract.RepositoryListContract
import com.kusumi.katsumi.andfactorytask.model.GitHubService
import com.kusumi.katsumi.andfactorytask.presenter.RepositoryListPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar.*

class MainActivity : AppCompatActivity(), RepositoryListAdapter.OnRepositoryItemClickListener, RepositoryListContract.View {

    private var repositoryListPresenter: RepositoryListContract.UserActions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()

        // Instantiate Presenter and show repository list.
        repositoryListPresenter = RepositoryListPresenter(this)
        (repositoryListPresenter as RepositoryListContract.UserActions).getRepositoryList(getString(R.string.username))
    }

    private fun setupView() {
        app_bar.title = getString(R.string.appbar_title)
    }

    override fun onRepositoryItemClick(repository: GitHubService.Repository) {
        repositoryListPresenter?.selectRepositoryItem(this, repository)
    }


    override fun showRepositories(repositoryList: MutableList<GitHubService.Repository>?) {
        if (repositoryList != null) {
            repository_recycler_view.adapter = RepositoryListAdapter(this, repositoryList)
            (repository_recycler_view.adapter as RepositoryListAdapter).setItemsAndRefresh(repositoryList)
            repository_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun showReadme(repositoryName: String) {
        webView.visibility = View.VISIBLE
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://github.com/${getString(R.string.username)}/$repositoryName/blob/master/README.md")
    }

    override fun showError() {
        Snackbar.make(mainContentsContainer, "Network is not connected.", Snackbar.LENGTH_SHORT).show()
    }
}