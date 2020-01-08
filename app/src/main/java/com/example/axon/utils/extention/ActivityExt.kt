package com.example.axon.utils.extention

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.net.Uri
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.example.axon.R

fun <A : Activity> A.hideKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    val windowToken = currentFocus?.windowToken
    if (windowToken != null) {
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun <A : FragmentActivity> A.replaceFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    moveOnRight: Boolean
) = this.replaceFragment(container, fragment, addToBackStack, true, moveOnRight)


fun <A : FragmentActivity> A.replaceFragment(
    container: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    needAnimate: Boolean,
    moveOnRight: Boolean
) = this.supportFragmentManager.replaceFragment(container, fragment, addToBackStack, needAnimate, moveOnRight)

fun <A : Activity> A.showToast(text: Any) = Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()

fun <A : Activity> A.showSnack(text: String) =
    Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()

fun <A : Activity> A.showSnack(res: Int) =
    Snackbar.make(this.findViewById(android.R.id.content), getString(res), Snackbar.LENGTH_LONG).show()

private fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    needAnimate: Boolean,
    moveOnRight: Boolean
) {
    var fragmentTransaction = this.beginTransaction()
    val fragmentTag = fragment.javaClass.simpleName
    val isFragmentExist : Boolean = this.findFragmentByTag(fragmentTag) == null
    if (addToBackStack && isFragmentExist) fragmentTransaction = fragmentTransaction.addToBackStack(fragmentTag)
    if (needAnimate) {
        val enterAnimation = if (moveOnRight) R.animator.slide_in_left else R.animator.pop_out_right
        val exitAnimation = if (moveOnRight) R.animator.slide_out_right else R.animator.pop_in_left
        val popEnterAnimation = if (moveOnRight) R.animator.pop_out_right else R.animator.slide_in_left
        val popExitAnimation = if (moveOnRight) R.animator.pop_in_left else R.animator.slide_out_right
        fragmentTransaction.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
    }
    fragmentTransaction.replace(containerViewId, fragment, fragmentTag).commit()
}