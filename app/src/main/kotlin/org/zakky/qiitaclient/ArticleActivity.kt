package org.zakky.qiitaclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import io.realm.Realm
import org.zakky.qiitaclient.model.Article
import org.zakky.qiitaclient.view.ArticleView
import javax.inject.Inject

class ArticleActivity : AppCompatActivity() {
    companion object {
        private const val ARTICLE_ID_EXTRA: String = "articleId"

        fun intent(context: Context, articleId: String): Intent =
                Intent(context, ArticleActivity::class.java)
                        .putExtra(ARTICLE_ID_EXTRA, articleId)
    }

    @Inject
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)

        setContentView(R.layout.activity_article)
        val articleView = findViewById(R.id.article_view) as ArticleView
        val webView = findViewById(R.id.web_view) as WebView

        val articleId = intent.getStringExtra(ARTICLE_ID_EXTRA)
        val article = realm.where(Article::class.java).equalTo(Article::id, articleId).findFirst()

        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
