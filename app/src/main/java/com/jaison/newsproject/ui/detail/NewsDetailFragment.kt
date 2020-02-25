package com.jaison.newsproject.ui.detail


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jaison.newsproject.R
import kotlinx.android.synthetic.main.fragment_news_detail.*


class NewsDetailFragment : Fragment() {

    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        return inflater.inflate(R.layout.fragment_news_detail, container, false)


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView(url: String) {

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        //setting web chrome client for proper handling of youtube urls
        webView.webChromeClient = object : WebChromeClient() {}

        //setting webview client to prevent webview opening url externally
        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                progressBar?.visibility = View.GONE
                super.onPageFinished(view, url)

            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                progressBar?.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)


            }
        }
        webView!!.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getString("url")?.let { setWebView(it) }
    }
}
