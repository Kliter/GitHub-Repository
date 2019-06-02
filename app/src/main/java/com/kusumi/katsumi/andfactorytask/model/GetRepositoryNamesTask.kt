
import android.os.AsyncTask
import com.kusumi.katsumi.andfactorytask.model.GitHubService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException


/**
 * This class is task that is used for getting repositories data.
 */
class GetRepositoryNamesTask: AsyncTask<String, Void, MutableList<GitHubService.Repository>?>() {

    private var client = OkHttpClient()

    override fun doInBackground(vararg params: String?): MutableList<GitHubService.Repository>? {
        try {
            val repositoryNameList: MutableList<GitHubService.Repository> = mutableListOf()
            val res = run("https://api.github.com/users/${params[0]}/repos")
            val resJson = JSONArray(res)
            for (i in 0 until resJson.length()) {
                repositoryNameList.add(
                    GitHubService.Repository(
                        resJson.getJSONObject(i).getString(
                            "name"
                        )
                    )
                )
            }
            return repositoryNameList
        }
        catch (error: IOException) {
            error.printStackTrace()
            return null
        }
        catch (error: JSONException) {
            error.printStackTrace()
            return null
        }
    }

    private fun run(url: String): String {
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }
}