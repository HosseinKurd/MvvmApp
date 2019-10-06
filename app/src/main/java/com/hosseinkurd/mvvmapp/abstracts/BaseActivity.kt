package com.hosseinkurd.mvvmapp.abstracts

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.hosseinkurd.mvvmapp.BuildConfig
import dagger.android.AndroidInjection

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    protected val TAG = javaClass.simpleName + "_TAG"

    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    protected val activity: Activity
        get() = this

    abstract val bindingVariable: Int

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    /*fun setProgressBar(progress: ProgressDialog): BaseActivity<T, V> {
        return this
    }*/

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    override fun onStart() {
        if (BuildConfig.DEBUG) {
            Log.e("TAG_TAG", "Where am i ? " + javaClass.simpleName)
        }
        super.onStart()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // overridePendingTransition(0, R.anim.fadeout);
    }
}