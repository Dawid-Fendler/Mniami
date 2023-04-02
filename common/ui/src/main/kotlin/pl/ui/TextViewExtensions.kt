package pl.ui

import android.widget.TextView
import androidx.core.text.HtmlCompat

fun TextView.parseHtml(text: String) {
    this.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
}
