package com.kusumi.katsumi.andfactorytask.presenter

import android.content.Context
import com.kusumi.katsumi.andfactorytask.contract.RepositoryListContract
import com.kusumi.katsumi.andfactorytask.isConnected
import com.kusumi.katsumi.andfactorytask.model.GitHubService

class RepositoryListPresenter(private val repositoryListView: RepositoryListContract.View) :
    RepositoryListContract.UserActions {

    private val gitHubService: GitHubService.Repositories = GitHubService.Repositories()

    override fun getRepositoryList(userName: String) {
        repositoryListView.showRepositories(gitHubService.getRepositoryList(userName))
    }

    override fun selectRepositoryItem(context: Context, repository: GitHubService.Repository) {
        if (isConnected(context)) {
            repositoryListView.showReadme(repository.repositoryName!!)
        }
        else {
            repositoryListView.showError()
        }
    }
}