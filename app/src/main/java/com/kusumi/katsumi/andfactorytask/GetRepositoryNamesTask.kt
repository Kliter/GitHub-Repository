import android.os.AsyncTask
import com.kusumi.katsumi.andfactorytask.Repository
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

open class GetRepositoryNamesTask: AsyncTask<String, Void, MutableList<Repository>?>() {

    private var client = OkHttpClient()

    override fun doInBackground(vararg params: String?): MutableList<Repository>? {
        try {
            val repositoryNameList: MutableList<Repository> = mutableListOf()
            val res = run("https://api.github.com/users/${params[0]}/repos")
            val resJson = JSONArray(res)
            for (i in 0 until resJson.length()) {
                repositoryNameList.add(Repository(resJson.getJSONObject(i).getString("name")))
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