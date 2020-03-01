package ru.skillbranch.devintensive.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

// stackoverflow наше все
fun Activity.hideKeyboard() {
    val view = currentFocus ?: View(this)
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
