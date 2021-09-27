package com.raywenderlich.listmaker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.raywenderlich.listmaker.databinding.MainActivityBinding
import com.raywenderlich.listmaker.ui.main.MainFragment
import com.raywenderlich.listmaker.ui.main.MainViewModel
import com.raywenderlich.listmaker.ui.main.MainViewModelFactory
import com.raywenderlich.listmaker.ui.main.ui.detail.ListDetailActivity

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentInteractionListener  {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this,

            MainViewModelFactory(
                PreferenceManager.getDefaultSharedPreferences(this))
        )
            .get(MainViewModel::class.java)


        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            val mainFragment = MainFragment.newInstance(this)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detail_container, mainFragment).commitNow()
        }

        binding.fabButton.setOnClickListener {
            showCreateListDialog()
        }
    }

    override fun listItemTapped(list: TaskList) {
        showListDetail(list)
    }

    private fun showCreateListDialog() {
        // retrieve strings from Strings.xml
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)
        // construct dialog and input field for user
        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT
        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)
        // implement onClickListener and creates new list in MainViewModel
        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->
            dialog.dismiss()

            val taskList = TaskList(listTitleEditText.text.toString())
            viewModel.saveList(taskList)
            showListDetail(taskList)
        }
        // creates dialog
        builder.create().show()
    }

    private fun showListDetail(list: TaskList) {
        val listDetailIntent = Intent(this,
            ListDetailActivity::class.java)
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)
        startActivityForResult(listDetailIntent,LIST_DETAIL_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data:
                                  Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 1
        if (requestCode == LIST_DETAIL_REQUEST_CODE && resultCode ==
            Activity.RESULT_OK) {
            // 2
            data?.let{
                // 3

                viewModel.updateList(data.getParcelableExtra(INTENT_LIST_KEY)!!)
                viewModel.refreshLists()
            }
        }
    }

    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }
            }
