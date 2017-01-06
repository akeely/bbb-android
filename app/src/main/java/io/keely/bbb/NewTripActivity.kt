package io.keely.bbb

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_new_trip.*
import java.util.*

class NewTripActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_trip)

        val names = resources.getStringArray(R.array.boys_members)
        val adapter = ArrayAdapter(this, R.layout.select_name_row, names)
        boysSelectionListView.adapter = adapter
        boysSelectionListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            toggleListSelection(view, i)
        }
    }

    fun toggleListSelection(view: View, position: Int) {

        val isSelected = view.isSelected
        view.isSelected = !isSelected
    }

    fun startNewTrip(view: View) {

        val intent = Intent(this, CurrentTripActivity::class.java)
        val nameList = boysSelectionListView.checkedItemIds
                .map {id -> id.toInt()}
                .map {id -> findViewById(id) as TextView }
                .map {textView -> textView.text }
                as ArrayList

        intent.putCharSequenceArrayListExtra("trip.names", nameList)

        startActivity(intent)
    }
}
