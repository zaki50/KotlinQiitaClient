package org.zakky.qiitaclient

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import io.realm.Realm
import org.zakky.qiitaclient.client.ArticleClient
import org.zakky.qiitaclient.model.Article
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    @Inject
    lateinit var articleClient: ArticleClient

    @Inject
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)

        setContentView(R.layout.activity_main)

        val listView = findViewById(R.id.list_view) as ListView
        val progressBar = findViewById(R.id.progress_bar) as ProgressBar
        val queryEditText = findViewById(R.id.query_edit_text) as EditText
        val searchButton = findViewById(R.id.search) as Button

        val listAdapter = ArticleListAdapter(this)
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.getItem(position) as Article

            val articleId = article.id ?: return@setOnItemClickListener
            ArticleActivity.intent(this, articleId).let { startActivity(it) }
        }

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({ articles: List<Article> ->
                        queryEditText.text.clear()

                        realm.executeTransaction {
                            realm.delete(Article::class.java)
                            listAdapter.articles = realm.copyToRealmOrUpdate(articles)
                        }
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー: $it")
                    })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
