package com.kusumi.katsumi.andfactorytask.model

import GetRepositoryNamesTask
import com.kusumi.katsumi.andfactorytask.contract.RepositoryListContract

interface GitHubService {

    /**
     * The Model of repository list.
     */
    class Repositories: RepositoryListContract.FetchDataModel {
        override fun getRepositoryList(userName: String): MutableList<Repository>? {
            return GetRepositoryNamesTask().execute(userName).get()
        }
    }

    /**
     * The Model of single repository.
     */
    class Repository(val repositoryName: String? = null)
}