package com.kusumi.katsumi.andfactorytask.contract

import android.content.Context
import com.kusumi.katsumi.andfactorytask.model.GitHubService

interface RepositoryListContract {

    // View
    interface View {
        fun showRepositories(repositoryList: MutableList<GitHubService.Repository>?)
        fun showReadme(repositoryName: String)
        fun showError()
    }

    // Presenter
    interface UserActions {
        fun getRepositoryList(userName: String)
        fun selectRepositoryItem(context: Context, repository: GitHubService.Repository )
    }

    // Model
    interface FetchDataModel {
        fun getRepositoryList(userName: String): MutableList<GitHubService.Repository>?
    }
}