package io.keely.bbb

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory
import io.keely.bbb.client.android.BBBClient
import io.keely.bbb.client.android.model.Trips
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            createNewTrip(view)
        }

        getTrips("fakeGroup")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun createNewTrip(view: View) {
        startActivity(Intent(this, NewTripActivity::class.java))
    }

    fun getTrips(group: String) {

        GetTripsTask().execute(group)
    }

    fun getContext(): Context {
        return this
    }

    inner class GetTripsTask : AsyncTask<String, Void, Trips>() {

        val factory = ApiClientFactory()
        val client: BBBClient = factory.build<BBBClient>(BBBClient::class.java)

        override fun doInBackground(vararg params: String?): Trips {
            return client.groupTripGet(params.first())
        }

        override fun onPostExecute(result: Trips?) {

            val rows = result?.map { t -> "${t.date}: ${t.totalWon}" }
            val adapter = ArrayAdapter(getContext(), R.layout.existing_trip_row, rows)
            existingTripsListView.adapter = adapter
            getTripsProgress.visibility = R.id.none
        }
    }
}
