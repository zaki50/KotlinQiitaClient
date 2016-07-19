package org.zakky.qiitaclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import org.zakky.qiitaclient.model.Article
import org.zakky.qiitaclient.model.User

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(this)
        listAdapter.articles = listOf(dummyArticle("123", "Kotlin入門", "たろう"),
                dummyArticle("234", "Java入門", "じろう"))

        val listView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.getItem(position) as Article
            ArticleActivity.intent(this, article).let { startActivity(it) }
        }
    }

    private fun dummyArticle(id: String, title: String, userName: String): Article =
            Article(id = id,
                    title = title,
                    url = "http://example.com/article/${id}",
                    user = User(id = "", name = userName, profileImageUrl = ""))
}
