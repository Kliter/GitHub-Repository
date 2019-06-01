package com.kusumi.katsumi.andfactorytask


import GetRepositoryNamesTask
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.snippet_toolbar.*

class MainActivity : AppCompatActivity(), ItemClickListener {

    private var repositoryList: MutableList<Repository>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app_bar.title = getString(R.string.appbar_title)

        repositoryList = GetRepositoryNamesTask().execute(getString(R.string.username)).get()
        if (repositoryList != null) {
            val repositoryListAdapter = RepositoryListAdapter(this, repositoryList!!)
            repository_recycler_view.adapter = repositoryListAdapter
        }
        repository_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onItemClick(view: View, position: Int) {
        if (isConnected(this)) {
            webView.visibility = View.VISIBLE
            webView.webViewClient = WebViewClient()
            webView.loadUrl("https://github.com/${getString(R.string.username)}/${repositoryList?.get(position)?.repositoryName}/blob/master/README.md")
        }
        else {
            Snackbar.make(mainContentsContainer, "Network is not connected.", Snackbar.LENGTH_SHORT).show()
        }
    }
}