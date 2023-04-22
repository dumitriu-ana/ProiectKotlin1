package com.example.licenta2.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.example.licenta2.R
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(context: Context, view: View, message: String, isError: Boolean) {
    val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    val snackbarView = snackbar.view
    if (isError) {
        snackbarView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.red))
        snackbar.setTextColor(ContextCompat.getColor(view.context, R.color.white))
    } else {
        snackbarView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.green))
        snackbar.setTextColor(ContextCompat.getColor(view.context, R.color.white))
    }
    snackbar.show()
}