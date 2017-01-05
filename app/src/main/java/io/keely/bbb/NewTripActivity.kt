package io.keely.bbb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_new_trip.*

class NewTripActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_trip)

        val names = resources.getStringArray(R.array.boys_members)
        val adapter = ArrayAdapter(this, R.layout.namerow, names)
        boysSelectionListView.adapter = adapter
    }
}
